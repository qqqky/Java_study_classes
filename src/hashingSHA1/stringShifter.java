package hashingSHA1;

/** String helper. 
 * Performs left circular shift on a string of any length.
 * Performs XOR on any number of given binary strings.
 * @author qqqky
 */
public class stringShifter {

	private final boolean test = false;
	
	public stringShifter ()
	{
		
	}
	public String leftCircularShift (String s, int shiftBy)
	{
		if(test) System.out.println("Original string: "+s+"\nShifted by: "+shiftBy);
		int length = s.length();
		String result = "";
		int target = 0;
		
		if (shiftBy>=length)
			target = shiftBy%length;
		else target = shiftBy;
		
		
		StringBuilder sb = new StringBuilder();
		for(int x=target; x<length; x++)
			sb.append(s.substring(x, x+1));
		for(int x=0; x<target; x++)
			sb.append(s.substring(x, x+1));
		
		result = sb.toString();
		if(test) System.out.println("Result string:\t"+result);
		return result;
	}
	public String XORmyBinaryStrings (String ... strings)
	{
		if(strings.length<=1)
			return "not enough parameters to XOR";
		String result = "";
		
		for(int x=0; x<strings.length-1; x++)
		{
			strings[0] = XORhelper(strings[0], strings[x+1]);
		}
		result = strings[0];
		
		return result;
	}
	public String XORhelper (String a, String b)
	{
		
		if(a.isEmpty() || b.isEmpty()) return "some string is empty";
		if(a.length() != b.length()) return "error, refuse to XOR strings of unequal length";
		int length = a.length();
		String result = "";
		StringBuilder sb = new StringBuilder();
		
		//needs a test if strings only contain 0 and 1
		
		for(int x=0; x<length;x++)
		{
			String tempA = a.substring(x,x+1);
			String tempB = b.substring(x,x+1);
			
			if(tempA.equals(tempB))
				sb.append("0");
			else sb.append("1");
		}
		
		result = sb.toString();
		
		return result;
	}
	public String NOTmyBinaryString(String a)
	{
		if(a.isEmpty()) return "";
		//System.out.println("Input : "+a);
		int length = a.length();
		
		StringBuilder sb = new StringBuilder();
		
		for(int x=0; x<length; x++)
			{
			if(a.substring(x, x+1).equals("0"))
				sb.append("1");
			else sb.append("0");
			}
		//System.out.println("Output: "+sb.toString());
		return sb.toString();
	}
	public String ANDmyBinaryStrings(String... strings)
	{
		if(strings.length<=1)
			return "not enough parameters to AND";
		String result = "";
		
		for(int x=0; x<strings.length-1; x++)
		{
			strings[0] = ANDhelper(strings[0], strings[x+1]);
		}
		result = strings[0];
		
		return result;
	}
	
	public String ANDhelper(String a, String b)
	{
		
		if(a.isEmpty() || b.isEmpty()) return "some string is empty";
		if(a.length() != b.length()) return "error, refuse to AND strings of unequal length";
		int length = a.length();
		String result = "";
		StringBuilder sb = new StringBuilder();
	
		//needs a test if strings only contain 0 and 1
	
		for(int x=0; x<a.length();x++)
		{
			String tempA = a.substring(x,x+1);
			String tempB = b.substring(x,x+1);
		
			if(tempA.equals("1") && tempB.equals("1"))
				sb.append("1");
			else sb.append("0");
		}
	
		result = sb.toString();
		
		return result;
	}
	
	//"Choose function" ---> Ch(x,y,z) = ( x AND y) XOR ((NOT x) AND z)
	public String Choose(String x, String y, String z)
	{
		if(x.isEmpty() || y.isEmpty() || z.isEmpty()) return "ERROR, some string is empty";
		String result = "";
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		temp1 = ANDmyBinaryStrings(x,y);
		temp2 = NOTmyBinaryString(x);
		temp3 = ANDmyBinaryStrings(temp2, z);
		result = XORmyBinaryStrings(temp1, temp3);
		
		return result;
		
	}
	//"Parity Function" ---> Parity(x,y,z) =  x XOR y XOR z
	public String Parity(String x, String y, String z)
	{
		if(x.isEmpty() || y.isEmpty() || z.isEmpty()) return "ERROR, some string is empty";
		String result = "";
		result = XORmyBinaryStrings(x, y, z);
		return result;
	}
	//"Majority Function" ---> Maj(x,y,z) = (x AND y) XOR (x AND z) XOR (y AND z)
	public String Majority(String x, String y, String z)
	{
		if(x.isEmpty() || y.isEmpty() || z.isEmpty()) return "ERROR, some string is empty";
		String result = "";
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		temp1 = ANDmyBinaryStrings(x, y);
		temp2 = ANDmyBinaryStrings(x, z);
		temp3 = ANDmyBinaryStrings(y, z);
		result = XORmyBinaryStrings(temp1, temp2, temp3);
		return result;
	}
	public long mod32addition (long...numbers)
	{
		long result = 0;
		if(numbers.length<1) {
			System.out.println("Error. Not enough numbers to add, returning 0."); return 0L;
		}
		int length = numbers.length;
		long power32 = (long)Math.pow(2, 32);
		
		if(test) {
			System.out.println("Mod32Addition was called with the following array:");
			for(int x=0; x<numbers.length;x++)
				System.out.println(numbers[x]);
			System.out.println("--------------------------");
		}
		
		for(int x=0; x<length-1; x++)
			{
			long temp = numbers[0]+numbers[x+1];
			numbers[0]= temp & (power32-1);	// same as "temp%power32"
			result = numbers[0];
			}
		
		if(test) System.out.println("Result of this addition was: "+result+"\n---------------");
		
		return result;
	}
	
	//pre-defined function for SHA-1 (path chosen depends on iteration)
	public String functionSHA1(String x, String y, String z, int iteration)
	{
		String result = "";
		
		if(iteration<20) {
		result = Choose(x, y, z);
		}else if (iteration<40) {
		result = Parity(x, y, z);
		}else if (iteration<60) {
		result = Majority(x, y, z);	
		}else {
		result = Parity(x, y, z);
		}	
		return result;
		
	}
	public String to32bitString(long number)
	{
		if(number>=Math.pow(2, 32)) {
			System.out.print(number + "--->");return "number too big for 32-bit representation";
		}
		if(number<0) return "less than zero?????";
		
		if(test) System.out.println("Input number: "+number);
		StringBuilder sb = new StringBuilder();
		String temp = Long.toBinaryString(number);
		if(test) System.out.println("After conversion: "+temp);
		int length = temp.length();
		
		if(length == 32) return temp;
		if(length>32)
		{
			for(int x=0;x<32;x++)
				{
				sb.append(temp.substring(x+32, x+33));
				}
		}
		if(length<32)
		{
			for(int x=0; x<32-length;x++)
				sb.append("0");
			sb.append(temp);
		}
		if(test) System.out.println("----------------------------");
		return sb.toString();	
	}
	//pre-defined constants K, value depends on iteration of the loop
	public String Kt(int iteration)
	{
		String result = "";
		if(iteration <20) {
			result = "01011010100000100111100110011001"; //5a827999		t0-t19
		} else if(iteration <40) {
			result = "01101110110110011110101110100001"; //6ed9eba1		t20-t39
		} else if(iteration <60) {
			result = "10001111000110111011110011011100"; //8f1bbcdc		t40-t59
		} else {
			result = "11001010011000101100000111010110";  //ca62c1d6	t60-t79
		}
		return result;
	}
}
	

