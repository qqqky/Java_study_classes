package hashingSHA1;

/**
 * Performs back and forth conversions from binary to hex.
 * 
 * (Java's parseBinaryString() and parseHexString() won't do the trick, as they
 * tend to cut off the leading zeros from output)
 *
 */

public class hexStringToUnsignedBinary {


	public hexStringToUnsignedBinary()
	{
		
	}
	//converts 0x-formatted hex string to unsigned binary string (not used in SHA)
	public String convertFromHexString(String hex0xformatted)
	{
			if (hex0xformatted.isEmpty()) return "ERROR. no input to convert";
			if(!hex0xformatted.startsWith("0x")) return "ERROR. wrong hex input";
			
			String result = "";
			StringBuilder sb = new StringBuilder();
			String temp = hex0xformatted.substring(2);
			int length = temp.length();
			
			for(int x=0; x<length; x++)
			{
				String temp2 = temp.substring(x, x+1).toLowerCase();
		
				switch(temp2)
				{
				case "0":
					sb.append("0000");
					break;
				case "1":
					sb.append("0001");
					break;
				case "2":
					sb.append("0010");
					break;
				case "3":
					sb.append("0011");
					break;
				case "4":
					sb.append("0100");
					break;
				case "5":
					sb.append("0101");
					break;
				case "6":
					sb.append("0110");
					break;
				case "7":
					sb.append("0111");
					break;
				case "8":
					sb.append("1000");
					break;
				case "9":
					sb.append("1001");
					break;
				case "a":
					sb.append("1010");
					break;
				case "b":
					sb.append("1011");
					break;
				case "c":
					sb.append("1100");
					break;
				case "d":
					sb.append("1101");
					break;
				case "e":
					sb.append("1110");
					break;
				case "f":
					sb.append("1111");
					break;
				default:
					System.out.print("somethingiswrong");
				}
			}
			result = sb.toString();
			return result;
	}
	//converts binary string to hex string
	public String convertFromBinaryString(String binaryString)
	{
		if(binaryString.isEmpty()) return "string is empty. cant convert";
		int length = binaryString.length();
		if(length%4 != 0) return "conversion when length%4 != 0 not implemented";
		
		String result = "";
		StringBuilder sb = new StringBuilder();
		
		for(int x=0; x<length/4; x++)
		{
			String temp2 = binaryString.substring(x*4, x*4+4).toLowerCase();
			
			switch(temp2)
			{
			case "0000":
				sb.append("0");
				break;
			case "0001":
				sb.append("1");
				break;
			case "0010":
				sb.append("2");
				break;
			case "0011":
				sb.append("3");
				break;
			case "0100":
				sb.append("4");
				break;
			case "0101":
				sb.append("5");
				break;
			case "0110":
				sb.append("6");
				break;
			case "0111":
				sb.append("7");
				break;
			case "1000":
				sb.append("8");
				break;
			case "1001":
				sb.append("9");
				break;
			case "1010":
				sb.append("a");
				break;
			case "1011":
				sb.append("b");
				break;
			case "1100":
				sb.append("c");
				break;
			case "1101":
				sb.append("d");
				break;
			case "1110":
				sb.append("e");
				break;
			case "1111":
				sb.append("f");
				break;
			default:
				System.out.print("somethingiswrong");
			}
		}
		result = sb.toString();
		return result;
	}
	
}
