package punctuationCounter;
import java.util.Scanner;
import java.io.*;

/**
 * 
 * Counts punctuation marks from .txt file
 * 
 */


public class PunctuationCounter {

	
	public static void main(String[]args) throws IOException
	{
		Scanner scan1 = new Scanner(new File("src/punctuationCounter/text1.txt")); // example txt
	
		//program counts only the following punctuation marks:
	
		int questionMarks=0;			//		?
		int dashes=0;					//		-
		int commas=0;					//		,
		int periods=0;					//		.
		int exclamationMarks=0;			//		!
		int semicolons=0;				//		;
		int colons=0;					//		:
		int parenthesesLeft=0;			//		(
		int parenthesesRight=0;			//		)		
		
	while(scan1.hasNextLine())
	{
		String a=scan1.nextLine();
		a=a.replaceAll("\\s","").replaceAll("\\w", "").replaceAll("\"","").replaceAll("/","");
			
		
		
		if(!a.equals(""))
		{
			System.out.println(a);
		
		Scanner scan2 = new Scanner(a);			
		String b=scan2.nextLine();		//second scanner scans line of punctuation marks
		int currentSymbol=1;
			while(currentSymbol<b.length())
			{
					
				if (b.charAt(currentSymbol)== '?')
					questionMarks++;
				if (b.charAt(currentSymbol)== '-')
					dashes++;
				if (b.charAt(currentSymbol)== ',')
					commas++;
				if (b.charAt(currentSymbol)== '.')
					periods++;
				if (b.charAt(currentSymbol)== '!')
					exclamationMarks++;
				if (b.charAt(currentSymbol)== ';')
					semicolons++;
				if (b.charAt(currentSymbol)== ':')
					colons++;
				if (b.charAt(currentSymbol)== '(')
					parenthesesLeft++;
				if (b.charAt(currentSymbol)== ')')
					parenthesesRight++;
				currentSymbol++;
			}
		scan2.close();
		}
	
		
	}
	System.out.println("Text file contains the following number of punctuation marks:");
	System.out.println(" ?: \t"+ questionMarks);
	System.out.println(" !: \t"+ exclamationMarks);
	System.out.println(" -: \t"+dashes);
	System.out.println(" ,: \t"+commas);
	System.out.println(" ;: \t"+semicolons);
	System.out.println(" .: \t"+periods);
	System.out.println(" :: \t"+colons);
	System.out.println(" (: \t"+parenthesesLeft);
	System.out.println(" ): \t"+parenthesesRight);
	
	scan1.close();
		
	}
}
