package hashingSHA1;
/**
 * By the book:
 * 1. prepare message schedule - chop message into 
 * 
 * @var initialH - constants for initial hash values h0-h4
 * @var constantsK - other constants predefined for SHA-1
 * @var test - set true to enable comments (debug)
 * @author qqqky
 *
 */
public class shaCalculator
{
	private final boolean test = false;
	private String input;
	private String[] words;
	private long[] currentHashValues = new long[5];
	private final String[] initialH = { //initial hash values
	"01100111010001010010001100000001",	//"67452301",	H0
	"11101111110011011010101110001001",	//"efcdab89",	H1
	"10011000101110101101110011111110",	//"98badcfe",	H2
	"00010000001100100101010001110110",	//"10325476",	H3
	"11000011110100101110000111110000"	//"c3d2e1f0"	H4
	};
	private final String[] constantsK = { //pre-defined constants
	"01011010100000100111100110011001", //5a827999 for 	t0-t19
	"01101110110110011110101110100001", //6ed9eba1		t20-t39
	"10001111000110111011110011011100", //8f1bbcdc		t40-t59
	"11001010011000101100000111010110"  //ca62c1d6		t60-t79
	};
	private long a ,b, c, d, e, T;
	
	public shaCalculator()
	{
	//this.input=input;
	hexStringToUnsignedBinary converter = new hexStringToUnsignedBinary();
	
	a = Long.parseLong("67452301", 16);
	b = Long.parseLong("efcdab89", 16);
	c = Long.parseLong("98badcfe", 16);
	d = Long.parseLong("10325476", 16);
	e = Long.parseLong("c3d2e1f0", 16);
	T = 0;
	
	currentHashValues[0]=a;
	currentHashValues[1]=b;
	currentHashValues[2]=c;
	currentHashValues[3]=d;
	currentHashValues[4]=e;
	}
	public String calculateSHA1 (String msg)
	{
		String answer = "";
		input = msg;
		words = prepareMessageSchedule(msg);
		cycle(words);
		
		if(test)
		{
		System.out.println("Current variable values before adding to previous hash:");
		System.out.println("A: "+this.a);
		System.out.println("B: "+this.b);
		System.out.println("C: "+this.c);
		System.out.println("D: "+this.d);
		System.out.println("E: "+this.e);
		}
		
		
		updateIntermediateHashValues();
		
		if(test)
		{
			System.out.println("Current hash values after updating (512-bit segment finished):");
			for(long h: currentHashValues)
				System.out.println(h);
		}
		
		
		hexStringToUnsignedBinary converter = new hexStringToUnsignedBinary();
		stringShifter shifter = new stringShifter();
		for(int x=0; x<5; x++)
		{
			String temp = converter.convertFromBinaryString(
					shifter.to32bitString(currentHashValues[x]));
			answer+=temp;	
		}
		
		return answer;
			
	}
	public void cycle (String[] input)
	{
		
		stringShifter shifter = new stringShifter();
		
		for(int x=0; x<80; x++)
		{
		
		String ROTLa = shifter.leftCircularShift(shifter.to32bitString(this.a), 5);
		
		String f = shifter.functionSHA1(
				shifter.to32bitString(this.b),
				shifter.to32bitString(this.c),
				shifter.to32bitString(this.d),
				x);
		
		String e = shifter.to32bitString(this.e);
		
		String Kt = shifter.Kt(x);
		
		String Wt = words[x];

		this.T = shifter.mod32addition(
				Long.parseLong(ROTLa, 2), Long.parseLong(f, 2),
				Long.parseLong(e, 2), Long.parseLong(Kt, 2), 
				Long.parseLong(Wt, 2));
		this.e=this.d;
		this.d=this.c;
		String ROTLb = shifter.leftCircularShift(shifter.to32bitString(this.b), 30);
		this.c= Long.parseLong(ROTLb, 2);
		this.b=this.a;
		this.a=this.T;
		}
		
	}
	public String[] prepareMessageSchedule (String msg)
	{
		
		String temp1 = convertASCIIToBinary(msg);
		String temp2 = addPaddingTo512(temp1);
		String[] temp3 = chopInto32BitWords(temp2);
		String[] temp4 = generate80words(temp3);
		
	//print out the generated 80 words
	if(test)
		for(int x=0; x<temp4.length;x++)
			System.out.println("Word "+x+": "+temp4[x]);
		
		return temp4;
	}
	public void updateIntermediateHashValues()
	{
			long mod32 = (long)Math.pow(2, 32);
			long temp = currentHashValues[0];
			currentHashValues[0]=(temp+a) & (mod32-1);
			temp = currentHashValues[1];
			currentHashValues[1]=(temp+b) & (mod32-1);
			temp = currentHashValues[2];
			currentHashValues[2]=(temp+c) & (mod32-1);
			temp = currentHashValues[3];
			currentHashValues[3]=(temp+d) & (mod32-1);
			temp = currentHashValues[4];
			currentHashValues[4]=(temp+e) & (mod32-1);
			
		
	}
	public String convertASCIIToBinary(String msg)
	{
		if(msg.isEmpty()) return "";
		
		String[] b = msg.split("");
		String answer = "";
		String temp = "";
		StringBuilder sb = new StringBuilder();
		
		for (String character:b)
		{
			int aa = character.codePointAt(0);
			String x = Integer.toBinaryString(aa);
			if(x.length()<8)
				{
				for(int y=0; y<8-(x.length()); y++)	
					 sb.append("0");
				}
			sb.append(x);
			
		}
		answer = sb.toString();
		
		return answer;
	}
	public String addPaddingTo512(String binaryMsg)
	{
		
		int length = binaryMsg.length();
		StringBuilder sb = new StringBuilder();
		
		if(length < 448) {
	
			sb.append(binaryMsg);
			sb.append("1");
			for(int x=0; x<447-length; x++)
				sb.append("0");
			int lengthpadding = 64-Integer.toBinaryString(length).length();
			
			for(int x=0; x<lengthpadding; x++)
				sb.append("0");
			
			sb.append(Long.toBinaryString(length));
							
		}
		else {
			System.out.println("ERROR, string longer than 448 bits of converted data. Choose other string");
		}
		if(test) System.out.println("Padded full 512-bit string: \n\t"+sb.toString());
		return sb.toString();
	}
	
	public String addPaddingTo32 (String number)
	{
		System.out.println(number.length());
		if(number.length()<32)
		{
			StringBuilder build = new StringBuilder(number);
			for(int x=0; x< (32-number.length()); x++)
				{
				build.insert(0, "0");
				}
			return build.toString();
		}
		
		else return number;	
	}
	// "Prepare message schedule" = make 80 32-bit words
	// 1.Divide 512-bit string into 16 32-bit words (iteration t0-t15), then 
	// 2. generate the rest 65 words using 16 32-bit ones doing
	// 		left shifting by 1 (iteration t16-t79) based on given formula.
	public String[] chopInto32BitWords (String chunk)
	{
		String[] temp = new String[16];
		if(chunk.length()%16!=0) {
			System.out.println("ERROR. String is not 512 characters. Returning empty array");
			return temp;
		}
		
		for(int x=0; x<16; x++)
		{
			temp[x] = chunk.substring(x*32,x*32+32);
		}
		if(test)
		{
			System.out.println("Full 512-bit piece after chopping into 16 32-bit words:");
			for(String a:temp)
				System.out.print(a + "\n");
		}
		
		return temp;
	}
	// 2. iteration t16-t79 formula:
	//	W(t) = rotateLeftBy1((Word(t-3)) XOR (Word(t-8)) XOR (Word(t-14) XOR (Word(t-16))
	public String[] generate80words (String[] first16Words)
	{
		String[] allWords = new String[80];
		for(int x=0;x<first16Words.length;x++)
			allWords[x]=first16Words[x];
		
		stringShifter shifter = new stringShifter();
		
		for(int x=16; x<80;x++)
		{
			int word1 = x-3;
			int word2 = x-8;
			int word3 = x-14;
			int word4 = x-16;
			
			String XORed = shifter.XORmyBinaryStrings(
					allWords[word1], allWords[word2], allWords[word3], allWords[word4]);
			if(test) System.out.println("After XORing, the "+x+"th word is: "+XORed);
			allWords[x]=shifter.leftCircularShift(XORed, 1);
			if(test) System.out.println("After shifting by 1 this word is: "+allWords[x]);
		}
		
		return allWords;
	}

}
