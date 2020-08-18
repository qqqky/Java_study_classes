package pascalsTriangle;
/*PP 12.9 Design and implement a recursive program to determine and
print the Nth line of Pascal’s Triangle, as shown below. Each
interior value is the sum of the two values above it. Hint: use an
array to store the values on each line.*/

//prints the selected row of Pascal's triangle (starting from row 1 to 34)
//for rows >34 have to use long numbers = not implemented.

public class PascalsTriangle {

	public static final int rowNum = 8;		//<--- change this to print another row
	
	public static void main (String [] args)
	{
	
		TriangleOfPascal triangle = new TriangleOfPascal(rowNum); //<--- 8th row
		
		System.out.println(triangle);
		//triangle.printFullTriangle();
		
	}
}
//takes desired row number as argument
class TriangleOfPascal
{
	private int line;
	private int [][]triangle;
	
	public TriangleOfPascal (int line)
	{
		this.line = line;
		
		if(line>0)
		{
			triangle = new int [this.line][this.line];
			triangle [0][0]=1;
		}
		
		
		
		if(line >1)
		{
			triangle [1][0]=1;
			triangle [1][1]=1;
			fill(line-1,line-1);
		
		}
		
		
		
	}
	public void fill(int number, int start)
	{
		
		
	//fills the "static" values of every row	
		
		triangle[number][0]=1;
		triangle[number][1]=number;
		triangle[number][number]=1;
		triangle[number][number-1]=number;
		
	//repeats the previous process with every line recursively
	if(number>1)
		fill(number-1, number-1);
	
	
	//fills out the inner triangle, values of which depend on previous rows
	//(only appears starting from row 4)
	if(number>3)
		{
		
		start = number -2;
		fillInline(number, start);
			
		}
	}
	//fills inner values of the triangle when row>index(3)
	public void fillInline(int number, int start)
	{
		triangle[number][start]=triangle[number-1][start-1]+triangle[number-1][start];
		
		if(start>2)
			fillInline(number, start-1);
	}
	
	public void printFullTriangle()
	{
		for (int i=0; i<triangle.length; i++)
			{
			for (int j=0; j<triangle[line-1].length; j++)
			System.out.print(triangle[i][j]+"\t");
			System.out.println();
			}		
		
	}
	
	
	
	public String toString()
	{
	String a = "";
	
	for (int x=0; x<triangle.length;x++)
		if(triangle[line-1][x]!=0)
		a=a+triangle[line-1][x]+"\t";
	
	return a;
	}
}
