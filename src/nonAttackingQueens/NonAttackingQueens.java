package nonAttackingQueens;
/*PP 12.6 Design and implement a recursive program that solves the NonAttacking Queens problem. 
 That is, write a program to determine how eight queens can be positioned on an eight-by-eight
chessboard so that none of them are in the same row, column, or
diagonal as any other queen. There are no other chess pieces on
the board.*/
import java.util.Random;

/**
 * Program attempts to find the solution to non-attacking queens problem.
 * If you want to test if it works correctly, set number of queens <8.
 * 
 * (The solution for 8 queens can take a while to complete)
 * 
 * @param numberOfQueens - number of queens to be spawned on board.
 * 
 * @author qqqky
 *
 */

public class NonAttackingQueens {

	public static final int numberOfQueens = 8;
	
	public static void main(String[]args)
	{
		
	
	chessboard myBoard = new chessboard(numberOfQueens);
	System.out.println(myBoard);
	System.out.println();
	
	//new object is created every time (if queens are placed improperly)
	//for some reason directly calling the solve method recursively produces StackOverFlow error after a while
	//NOTE: the reason is probably the generation of random numbers
	//so instead of creating a new chessboard object every time, we could add a separate
	//class, which generates those random numbers and returns them to some method of the
	//chessboard class.
	while(!myBoard.solve())
		{
		myBoard = new chessboard(numberOfQueens);
		}
	
	System.out.println(myBoard);	
	System.out.println("SOLVED");
	
	
	}
}


class chessboard
{
	private int[][]emptyBoard ={	{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0}	};
	private int []queenRow, queenColumn;
	private int queens, queenCount=0, randomRow, randomColumn, columnChecker =0, 
			rowChecker=0;
	private Random generator;
	
	public chessboard(int numberOfQueens)
	{
	queens=numberOfQueens;	
	queenRow = new int [queens];
	queenColumn = new int [queens];
	generator = new Random();
	
	positionQueens();
	
	}
	public boolean isValid(int row, int column)
	{
		return row>=0 && row<emptyBoard.length && column>=0 && column<emptyBoard.length &&
				emptyBoard[row][column]==0;
	}
	public boolean isInBounds (int row, int column)
	{
		return row>=0 && row<emptyBoard.length && column>=0 && column<emptyBoard.length;
	}
	public int rollRow()
	{
		return generator.nextInt(8)+1;
	}
	public int rollColumn()
	{
		return generator.nextInt(8)+1;
	}
	public int getQueens()
	{
		return queens;
	}
	//*******************************************************************
	//**** Randomly positions selected amount of queens on the board ****
	//*******************************************************************
	public void positionQueens()
	{
		
		randomRow = rollRow();
		randomColumn = rollColumn();
		
		if (queenCount<queens && isValid(randomRow, randomColumn))
		{
			emptyBoard[randomRow][randomColumn]=1;
			
			//saves row and column of queens for later use:
			queenRow[queenCount]=randomRow;
			queenColumn[queenCount]=randomColumn;
			queenCount++;
			positionQueens();
		}
		else
			if (queenCount<queens)
				positionQueens();
		
		//resets queenCounter after finishing
		queenCount = 0;
	
	}	
	
	
	public boolean solve()
	{
		return checkAll(getQueens());
	}
	
	public boolean checkAll (int totalQueens)
	{
		boolean result = false;
		System.out.println(totalQueens);
		
		//BASE CASE - if reached, queens are positioned correctly
		if (totalQueens==0)
			result=true;
		
		if (totalQueens>0 && checkQueen(totalQueens-1)==true)
			{
			result = checkAll(totalQueens-1);
			}
		
		
		return result;
		
	}
	
	//******************************************************************
	//************** Checks a single queen for obstacles ***************
	//******************************************************************
	public boolean checkQueen(int queenNumber) 
	{
		flipAQueenToZero(queenNumber); //flips selected queen to 0 (all others are still 1)
		if (checkRow(queenRow[queenNumber])==true)
			{
				if (checkColumn(queenColumn[queenNumber])==true)
					{
						System.out.println("Success. This queen has no obstacles horizontally and vertically!");
						if(checkDiagonal(queenRow[queenNumber],queenColumn[queenNumber], queenNumber)==true)
							{
							System.out.println("This queen has no obstacles diagonally");
							resetCounters();		//resets counters which were used for horizontal and vertical obstacle checking
							flipBack(queenNumber); //flips the queen back to 1
							return true;
							}
						else 
							{
							System.out.println("This queen HAS obstacles diagonally");
							return false;
							}
					}
				else 
					{
					System.out.println("Obstacle vertically found");
					return false;
					}
			}
		else
			{
			System.out.println("Obstacle horizontally found");
			return false;
			}
		
	}
	//make sure to distinguish rows and columns in array and checking of their validity!
	//!!!!!!!!!
	//rows in array go from north to south, but to check the validity of a row,
	//we have to check the columns in that row!
	public boolean checkRow(int row)
	{
		
		if (columnChecker==emptyBoard.length)
			return true;
		
		
		if (!isZero(row, columnChecker))
			return false;
		
		else
			{
			columnChecker+=1; //<--- checks every column (in that row) for other queens
			return checkRow(row);
			}
			
		
	}
	public boolean checkColumn(int column)
	{
		if (rowChecker==emptyBoard.length)
			return true;
		
		
		if (!isZero(rowChecker, column))
			return false;
		
		else
			{
			rowChecker+=1; //<--- checks every row (in that column) for other queens
			return checkColumn(column);
			}
	}
	//******************************************************************************
	//***** Calls methods which check for diagonal obstacles in all directions *****
	//******************************************************************************
	public boolean checkDiagonal(int row, int column, int queenNumber)
	{
		int queenColumnSaved = queenColumn[queenNumber];
		int queenRowSaved = queenRow[queenNumber];
		boolean result = false;
		
		result = checkLowerRight(queenRowSaved, queenColumnSaved, queenNumber);
		
		if(result == true)
			result = checkUpperRight(queenRowSaved, queenColumnSaved, queenNumber);
		
		if(result == true)
			result = checkLowerLeft(queenRowSaved, queenColumnSaved, queenNumber);
		
		if(result == true)
			result = checkUpperLeft(queenRowSaved, queenColumnSaved, queenNumber);
		
		return result;
			
		
		
	}
	//**************CHECKS DIAGONALLY to the LOWER RIGHT corner*****************
	//**************************************************************************
	public boolean checkLowerRight(int row, int column, int queenNumber)
	{
		boolean done = false;
		
		if (isInBounds(row, column))
		{
			if (!isZero(row, column))
				return false;
				
			else return checkLowerRight(row+1, column+1, queenNumber); //lower-right
				
		}
		done = true;
		
		if (!isInBounds(row, column))
			done = true;
		
		return done;
	}
	//**************CHECKS DIAGONALLY to the UPPER RIGHT corner*****************
	//**************************************************************************
	public boolean checkUpperRight(int row, int column, int queenNumber)
	{
		boolean done = false;
		
		if (isInBounds(row, column))
		{
			if (!isZero(row, column))
				return false;
				
			else return checkUpperRight(row-1, column+1, queenNumber); //upper-right
				
		}
		done =true;
		
		if (!isInBounds(row, column))
			done = true;
		
		return done;
	}
	//**************CHECKS DIAGONALLY to the LOWER LEFT corner*****************
	//*************************************************************************
	public boolean checkLowerLeft(int row, int column, int queenNumber)
	{
		boolean done = false;
			
		if (isInBounds(row, column))
		{
			if (!isZero(row, column))
				return false;
					
			else return checkLowerLeft(row+1, column-1, queenNumber); //lower-left
					
		}
		done =true;
			
		if (!isInBounds(row, column))
			done = true;
			
		return done;
	}
	//**************CHECKS DIAGONALLY to the UPPER LEFT corner*****************
	//*************************************************************************
	public boolean checkUpperLeft(int row, int column, int queenNumber)
	{
		boolean done = false;
					
		if (isInBounds(row, column))
		{
			if (!isZero(row, column))
				return false;
							
			else return checkUpperLeft(row-1, column-1, queenNumber); //upper-left
							
		}
		done =true;
					
		if (!isInBounds(row, column))
			done = true;
					
		return done;
		}
	//***** Methods to flip value of the selected queen on the board to 0 ******
	//***** and flip it back later	********************************************
	//**************************************************************************
	public void flipAQueenToZero(int i) //flips queens so all board is 0 (positions of their X:Y are still saved previously)
	{
			emptyBoard[queenRow[i]][queenColumn[i]]=0;
			System.out.println("Queen at: "+queenRow[i]+":"+queenColumn[i]+" flipped");
	}
	
	public void flipBack(int i)
	{
		emptyBoard[queenRow[i]][queenColumn[i]]=1;
		System.out.println("Queen at: "+queenRow[i]+":"+queenColumn[i]+" flipped back");
	}
	//*** Method to check if a selected square on the board is empty ***
	public boolean isZero(int row, int column)
	{
		return emptyBoard[row][column]==0;
	}
	//*** Resets counters which are used in horizontal and vertical validity checking ***
	public void resetCounters()
	{
		rowChecker =0;
		columnChecker =0;
		
	}
	//*** Resets the checkboard to be able to reposition queens properly ***
	//not used in this program!
	public void resetBoard()
	{
		for (int x=0; x<emptyBoard.length; x++)
			for (int y=0; y<emptyBoard[0].length;y++)
				emptyBoard[x][y]=0;
	}
	//prints out current configuration of the queens on board
	public String toString()
	{
		String a = "";
		
		for (int row=0; row<emptyBoard.length;row++)
		{
			for (int column=0;column<emptyBoard[0].length;column++)
			{
				a+=emptyBoard[row][column]+" ";
			}
			a+="\n";
		}
		return a;
	}
}