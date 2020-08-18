package cityRoutes;
/*PP 12.13 Write a program that prompts the user for a list of cities, where
each city has a name and x and y coordinates. After all cities
have been entered, the program should use a recursive algorithm to print the length of 
all possible routes that start at the first city entered, end at the last city entered, 
and visit every city in the list. For each route, the program should print the name
of each city visited, followed by length of the route.*/

//NOTE: this program uses ArrayPermutator class, which was modified to return
//		a 2D array of all possible combinations (permutations) of a 1D int array
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Calculates all possible routes between cities their distance.
 * More than 2 cities must be provided. Tab is used as delimiter in input.
 * Example of entry (can copy-paste):
Harelwood	20	38
Gelerail	56	152	
Notoolay	80	212
Brisbane	15	302
Gingolder	280	152
Moontrail	192	28
 * 
 */

public class CityRoutesMain {

	
	public static void main(String[]args)
	{
		
		Scanner scan = new Scanner (System.in);	
		
		ArrayList<String> myCityInfo = new ArrayList<String>();
		
		System.out.println("Enter city name and its X, Y coordinates: ");
		System.out.println("(Use tab instead of a space between entries). Min 3 entries");
		System.out.println("End with an empty line.");
		
		String a = scan.nextLine();
		if (!a.equals(""))
			do
			{
				myCityInfo.add(a);
				a = scan.nextLine();
			
			}while (!a.equals(""));
		
		//sorts information after end of input is reached
		if(!myCityInfo.isEmpty() && myCityInfo.size()>2)
		{
			
		RouteCalculator myCalculator = new RouteCalculator(myCityInfo);
		System.out.println();
			
			
		System.out.println("\nTotal distance from first to last city linearly is: "+myCalculator.linearDistance(0,myCityInfo.size()-1));
		System.out.println("Total possible routes from first to last city: "+myCalculator.totalPosibilities(myCityInfo.size()-2));
		
		//prints all possible combination of routes:
		myCalculator.printAllCities();
		}
		
		scan.close();
			
	}
}

class RouteCalculator
{
	private Scanner scan;
	private ArrayList<String> cityDatabase;
	private String[]names;
	private int[] XCoords, YCoords, middleValues;
	private boolean[] isVisited;
	private int result =0, start =0, end, current, rows=0;
	private int[][]middle;
	private ArrayPermutator myPermutator;

	
	public RouteCalculator(ArrayList<String> myCityInfo)
	{
		cityDatabase = myCityInfo;
		
		if(myCityInfo!=null)
		{
			end = myCityInfo.size()-1;
		
			names = new String [myCityInfo.size()];
			XCoords = new int [myCityInfo.size()];
			YCoords = new int [myCityInfo.size()];
			
			middleValues = new int[myCityInfo.size()-2];//for permutations
		
		//creates 2D array with column number as total number of different combinations to visit every middle city
			rows = totalPosibilities(myCityInfo.size()-2);
			middle = new int[rows][myCityInfo.size()-2];
		
		//loads arrays with particular values
			for (int x=0;x<myCityInfo.size();x++)
			{
				scan = new Scanner (myCityInfo.get(x));
				scan.useDelimiter("\t"); 
				names[x]= scan.next();
				XCoords[x]=Integer.parseInt(scan.next());
				YCoords[x]=Integer.parseInt(scan.next());	
				
				if(x<myCityInfo.size()-2)
					middleValues[x]=x+1;			
			}
			
		//finally, loads the permutator and collects all possible routes to a 2D array
		myPermutator = new ArrayPermutator(middleValues, 0);
		middle = myPermutator.ReturnResultsAs2DArray();
		}
	}
	
	//calculates distance between 2 cities
	public int calcDistance (int start, int current)
	{
		int city2X = XCoords[current];
		int city1X = XCoords[start];
		int city2Y = YCoords[current];
		int city1Y = YCoords[start];
		
		int dist = (city2X-city1X)*(city2X-city1X)+(city2Y-city1Y)*(city2Y-city1Y);
		result = (int)Math.sqrt(dist);
		
		//System.out.println("Distance between "+names[start]+" and "+names[current]+" is: "+ result+ " (pixels)");
	
		return result;
	}
	
	//calculates linear distance from first to last city
	public int linearDistance(int start, int endTown)
	{
		
		
		if (start==endTown)
			return 0;
		
		else
			
			return calcDistance(start, start+1) + linearDistance(start+1, endTown);
	}
	//calculates middle route (selected row)
	public int middleDistance(int row, int startIndex, int endIndex)
	{
		
		if (startIndex==endIndex)
			return 0;
		
		else
			return calcDistance(middle[row][startIndex], middle[row][startIndex+1])+middleDistance(row, startIndex+1, endIndex);
			
			
	}
	//adds distance from start city and distance to the end city to particular cities in the selected row (= total distance of one route)
	public int selectedRoute (int row)
	{
		return middleDistance(row, 0, middle[0].length-1)+calcDistance(0, middle[row][0])+ calcDistance(middle[row][middle[0].length-1],names.length-1);
	}
	public int totalPosibilities (int lengthOfMiddle) // = N!
	{
		result = lengthOfMiddle;
		
		if(lengthOfMiddle==1)
			return 1;
		
		else
			return result*totalPosibilities(lengthOfMiddle-1);
	}
	
	public void printInformation()
	{
		//prints out all information about the cities
			System.out.println("Total information entered about "+cityDatabase.size()+" cities:");
				for (int x=0;x<cityDatabase.size();x++)
				{
					System.out.println("City "+(x+1)+" is "+names[x]+" X:\t"+XCoords[x]+"\tY:\t"+YCoords[x]+"\tVisited: "+isVisited[x]);
				}
	}
	public void printAllCities()
	{
		//prints visual representation of cities in the middle
		System.out.println("\nAll possible routes: \n");
		
		for (int x=0;x<middle.length;x++)
		{
			System.out.print(names[0]+" ---> "); //prints first city (always the same)
			for(int y=0;y<middle[0].length;y++)
				System.out.print(names[middle[x][y]]+" ---> ");
			System.out.println(names[names.length-1]+" (distance: "+ selectedRoute(x)+")"); //prints last city and distance
		}
	}
}
