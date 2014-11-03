import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Solution {

	
	static Set<String> h;
	static HashMap<Character,Integer> boggle;
	static String input,output;
	
	static void loadFile() throws FileNotFoundException{
		File file = new File(System.getProperty("user.dir") + "/word_dictionary");			//change dictionary here
		Scanner s=new Scanner(file);
		while(s.hasNextLine()){
			String str = s.nextLine().toLowerCase();
				if(str.length()>=3)
					h.add(str);
		}
		s.close();
	}
	
	
	static int getBoggleCount(String s){		
		
		boggle= new HashMap<Character,Integer>();
		HashMap<Character,Integer> local;
		int count=0;
		
		for(int i=0;i<s.length();i++)
			if(boggle.containsKey(s.charAt(i)))
				boggle.put(s.charAt(i),boggle.get(s.charAt(i))+1);
			else
				boggle.put(s.charAt(i),1);
			
		
		for(String comb: h){
			local= new HashMap<Character,Integer>(boggle);
			for(int i=0;i<comb.length();i++){
				if(local.containsKey(comb.charAt(i)))
					if(i==(comb.length()-1) && (local.get(comb.charAt(i))>=1))
						count++;
					else if(local.get(comb.charAt(i))>=1)
						local.put(comb.charAt(i),local.get(comb.charAt(i))-1);
					else
						break;
				else
					break;
			}
				
		}
		return count;
	}
	
	static void SolveBoggles() throws FileNotFoundException, UnsupportedEncodingException{
		
		File file = new File(System.getProperty("user.dir") +"/"+input);
		PrintWriter writer = new PrintWriter(output);
		Scanner s=new Scanner(file);
		while(s.hasNextLine()){
			String a=s.nextLine();
			writer.println(a+" "+getBoggleCount(a));
		}
		s.close();
		writer.close();
	}
	
	public static void main(String args[]){

	h = new HashSet<String>();	
	
	input = args[0];	//first arg is the input.txt	
	output =args[1];	//second arg is the output.txt
	
	try {
		loadFile();
		SolveBoggles();
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	

}
	
}
