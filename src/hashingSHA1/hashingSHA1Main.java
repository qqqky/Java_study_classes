package hashingSHA1;
/**
 * Simple SHA-1 calculator in Java.
 * Supports max 56 ASCII character string (one 512-bit chunk after padding).
 *  
 * @author qqqky
 */

public class hashingSHA1Main {

/**
 * @param query - target string for calculating SHA-1
 */
public static void main(String[] args)
{
	shaCalculator calc = new shaCalculator();
	String query = "ab";		//<--- change to calculate SHA-1 for a different String
	String answer = calc.calculateSHA1(query);
	
	System.out.println("Your query: "+query);
	System.out.println("SHA-1 hash: "+ answer);
	
}
}