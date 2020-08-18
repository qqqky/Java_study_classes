package checkerboard;
/*PP 8.17 Design and implement a program that draws a checkerboard
with five red and eight black checkers on it in various locations.
Store the checkerboard as a two-dimensional array.*/

/* Possible jumps are only displayed for black checkers.
 * Resize the window to keep regenerating the board
 * 
 * This one has a lot of notes...*/


import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

//to support any size board,  groups 5,8,9 have to be rewritten (similar to all others based on board's length)
//and their execution clauses should be rewritten based on boards length (and not a set number)

public class Checkerboard {

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Checkerboard with some checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new CheckerboardPanel());
		//frame.setSize(385,405); <-- size for checkerboard without black jumps listed
		//frame.setSize(385,550);
		frame.pack();
		frame.setVisible(true);
	}
}

class CheckerboardPanel extends JPanel
{
	private int squareSize=45, startX=5, startY=1, randomXRed, randomYRed, randomXBlack, randomYBlack;
	private int[] XRed, YRed, locationRand;
	private int[][] board;
	private int reroll, n=0;
	private boolean[]black, red;
	private ArrayList<String> jumpList;
	private int Xconverted, Yconverted;
	
	
	public CheckerboardPanel()
	{
		board = new int[8][8];
		black = new boolean[64];
		red = new boolean[64];
		jumpList = new ArrayList<String>();
		setPreferredSize(new Dimension(370,450));
		setBackground(Color.YELLOW);
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//resets the text array and rerolls (in case window is resized)
		jumpList = new ArrayList<String>();
		reroll=n=0;
		
		//***draws a colored (red/black) checkerboard (8x8)
		for(int x=0; x<8;x++)
		{
			for(int y=0; y<8;y++)
			{
				board[x][y]=(y+1)+(x*8);//<--- 2D board array is loaded with values 1-64
				black[((x*8)+(y+1))-1]=false;
				red[((x*8)+(y+1))-1]=false;
				if(y%2==0 && x%2==0 || y%2!=0 && x%2!=0)//<---conditionals would be shorter if size of the board was of odd number (eg.: 9x9)
				{
					
					g.setColor(Color.lightGray);
					g.fillRect(startX+(x*45), startY+(y*45), squareSize, squareSize);
				}
				if(y%2!=0 && x%2==0 || y%2==0 && x%2!=0)
				{
					g.setColor(Color.darkGray);
					g.fillRect(startX+(x*45), startY+(y*45), squareSize, squareSize);	
				}
			}
		}
		//***determines a checker
		//g.setColor(Color.BLACK);
		//g.fillOval(startX+squareSize/15, startY+squareSize/15, squareSize/15*13, squareSize/15*13);
		//g.setColor(Color.RED);
		//g.fillOval(startX+squareSize/15, startY+squareSize/15, squareSize/15*13, squareSize/15*13);
		
		//*** adds 5 red and 8 black checkers at random locations(can overlap)
		//
		//for(int x=0;x<5;x++)
		//{
		//	g.setColor(Color.RED);
		//	randomXRed = (int)(Math.random()*8);
		//	randomYRed = (int)(Math.random()*8);
		//	g.fillOval(startX+squareSize/15+(45*randomXRed), startY+squareSize/15+(45*randomYRed), squareSize/15*13, squareSize/15*13);
		//
		//}
		//for(int x=0;x<8;x++)
		//{
		//	g.setColor(Color.BLACK);
		//	randomXBlack = (int)(Math.random()*8);
		//	randomYBlack = (int)(Math.random()*8);
		//	g.fillOval(startX+squareSize/15+(45*randomXBlack), startY+squareSize/15+(45*randomYBlack), squareSize/15*13, squareSize/15*13);
		
		
		
		//***prints out visual representation of square numbers on board (1-64) in console
		//for(int a=0;a<board.length;a++)
		//	{
		//		for(int b=0;b<board[0].length;b++)
		//		{
		//			System.out.print(board[a][b]+ "\t");
		//		}
		//		System.out.println();
		//	}
		//****************************************************************
		//5 red and 8 black checkers ALTERNATIVE (cannot overlap)
		//****************************************************************
		//*** Rolls random numbers 1-64 and makes sure none of them are the same
		//*** this method produces "off by one error" unless array size is +1 than total amount of checkers (13)
		//*** or values are preloaded before calling this method (or last value at index 13 will always be default value (0))
		//****************************************************************
		locationRand = new int[14];	//total number of checkers +1. By default every value in array is 0
	
		while(reroll==0) // numbers are brute-forced again from the beginning if similar values are found
		{					//checkers will be assigned these random numbers (from locationRand array), which mark their place on board (1-64)
			reroll=1;
			for (int x=0;x<locationRand.length;x++)
			{
				for (int y=x+1;y<locationRand.length;y++)
					{
						
						if(locationRand[x]==locationRand[y])
							{				
							//System.out.print("Found same number while checking x="+x+", y="+y+": "+locationRand[x]+"\n");
							locationRand[x]=(int)(Math.random()*64+1);
							//System.out.println("Assigned new number: "+locationRand[x]);
							reroll=0; //x=0 (for the first loop to repeat) does not work. X is not seen, so another variable must be used to force the loop back
							}
					}
			}
		}
		//****************************************************************
		//*** Prints the locations of red and black checkers in console***
		//****************************************************************
		System.out.println("Red checkers in locations: ");
		for (int a=0;a<5;a++) //<--- 5 red checkers (#0-4)
		{	
			red[locationRand[a]-1]=true; //changes index 0 to true if locationRand value is 1 (1 is min value), similarly if 64 is rolled - value at index 63 is changed to true
			System.out.print(locationRand[a]+"("+((locationRand[a])%8)+","+((locationRand[a])/8)+") ");
		}
		
		System.out.println();
		System.out.println("\nBlack checkers in locations: ");
		for (int a=5;a<13;a++)//<--- 8 black checkers (#5-12)
		{	
			black[locationRand[a]-1]=true;
			System.out.print(locationRand[a]+"("+((locationRand[a])%8)+","+((locationRand[a])/8)+") ");
		}
		//***************************************************************************
		//*** Draws checkers on board. First the 5 red ones, then the 8 black ones***
		//***************************************************************************
		
		for(int x=0; x<5; x++) //<== reads numbered locations from random array and assigns value from indexes 0-4 (because only 5 red checkers are needed)
		{
			g.setColor(Color.RED);
			randomXRed= (locationRand[x]-1)%8; 	//lowest random number was 1 and highest is 64 (to better represent the board IRL)
			randomYRed= (locationRand[x]-1)/8;	//at value 1, checker has to go to a default location (unaltered position) so value has to be lowered by 1
			g.fillOval(startX+squareSize/15+(45*randomXRed), startY+squareSize/15+(45*randomYRed), squareSize/15*13, squareSize/15*13);
		}
		for(int x=5; x<13; x++)//<== assigns values from indexes 5-12
		{
			g.setColor(Color.BLACK);
			randomXBlack= (locationRand[x]-1)%8; //similar with number 64
			randomYBlack= (locationRand[x]-1)/8;//last position is default+squareSize *7 and not *8! (0-7)
			g.fillOval(startX+squareSize/15+(45*randomXBlack), startY+squareSize/15+(45*randomYBlack), squareSize/15*13, squareSize/15*13);
		}

		//***************************************************************************
		//*** Calculations to determine which black checkers can jump the red ones
		//*** 9 groups of checkers based on number on the board:
		//***
		//*** 1. 	1,2,9,10 - can jump if there is a checker in location at +9 (&& no checker at +9*2)
		//*** 2.	7,8,15,16 - can jump if there is a checker in +7 (&& no checker at +7*2)
		//*** 3.	49, 50, 57, 58 - can jump if there is checker in -7 (&& no checker in -7*2)
		//*** 4.	55,56,63,64 - can jump if there is a checker in -9 (&& no checker in -9*2)
		//*** 
		//*** 5.	3-6, 11-14 - can jump to both +9 and +7 (if no checkers at +9*2/+7*2)
		//*** 6.	17,18,25,26,33,34,41,42 - can jump to -7 and +9 (if no checkers at -7*2/+9*2)
		//*** 7.	23,24,31,32,39,40,43,48 - can jump to +7 and -9 (if no checkers at +7*2/-9*2)
		//*** 8.	51-54, 59-62 - can jump to -7 and -9 (if no checkers at -7*2/-9*2)
		//***
		//*** 9.	19-22, 27-30, 35-38, 43-46 -	16 squares in the center of the board
		//***										can jump to -7/+7 and -9/+9 (if no checkers at -7*2/+7*2/-9*2/+9*2 accordingly)
		//***
		//***************************************************************************
		//***	Group 1: board.length*0+1, board.length*0+2, board.length*1+1, board.length*1+2;
		//***	Group 2: board.length*1-1, board.length*1+0, board.length*2-1, board.length*2+0;
		//***	Group 3: board.length*(board.length-2)+1, board.length*(board.length-2)+2, board.length*(board.length-1)+1, board.length*(board.length-1)+2;
		//***	Group 4. board.length*(board.length-1)-1, board.length*(board.length-1), (board.length*board.length)-1, board.length*board.length;
		//***	
		//***	Group 5: board.length*0+3, board.length*0+4, board.length*0+5, board.length*0+6, 
		//***			 board.length*1+3, board.length*1+4, board.length*1+5, board.length*1+6;
		//***	Group 6: board.length*2+1, board.length*2+2,
		//***			 board.length*3+1, board.length*3+2,
		//***			 board.length*4+1, board.length*4+2,
		//***			 board.length*5+1, board.length*5+2;
		//***	Group 7: board.length*3-1, board.length*3,
		//***			 board.length*4-1, board.length*4,
		//***			 board.length*5-1, board.length*5,
		//***			 board.length*6-1, board.length*6;
		//***	Group 8: board.length*6+3, board.length*6+4, board.length*6+5, board.length*6+6,
		//***			 board.length*7+3, board.length*7+4, board.length*7+5, board.length*7+6;
		//***
		//***	Group 9: board.length*2+3, board.length*2+4, board.length*2+5, board.length*2+6,
		//***			 board.length*3+3, board.length*3+4, board.length*3+5, board.length*3+6,
		//***			 board.length*4+3, board.length*4+4, board.length*4+5, board.length*4+6,
		//***			 board.length*5+3, board.length*5+4, board.length*5+5, board.length*5+6,
		//***	
		//*****************************************************************************
		// reminder: board[8][8] <--- 0-7 each 0/0=1 0/7=8 (from 1 to 64)
		
		System.out.println();
		System.out.println();
		
		for (int x=0;x<red.length;x++)
		System.out.print("Index "+x+ ":"+ red[x]+" \n");
		
		
		//************************************************************************
		//************************************************************************
		//*** MAIN FUNCTION TO DETERMINE POSSIBLE MOVES FOR BLACK CHECKERS *******
		//************************************************************************
		for (int x=0;x<black.length;x++)
		{
			if (black[x]==true)
			{
				n=n+1; //<--- black checker counter for console
				System.out.println("Black checker: "+n+"\tposition: "+ (x+1));
				Yconverted =x/8+1;
				Xconverted =x%8+1;
				//************************************************
				//***GROUP 1-4************************************
				//*** 1 (upper left): *****************************************
				if(x+1==board.length*0+1 || x+1==board.length*0+2 || x+1==board.length*1+1 || x+1==board.length*1+2)
				{
					if (red[x+9]==true && red[x+9*2]==false && black[x+9*2]==false)//1
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x+9)/8][(x+9)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
							}
							
				}
				//*** 2 (upper right): ******************************************
				if (x+1==board.length*1-1 || x+1==board.length*1+0 || x+1==board.length*2-1 || x+1==board.length*2+0)
				{
					if (red[x+7]==true && red[x+7*2]==false && black[x+7*2]==false)//2
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x+7)/8][(x+7)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
							
				}
				//*** 3 (lower left): *******************************************
				if (x+1==board.length*(board.length-2)+1 || x+1==board.length*(board.length-2)+2 || x+1==board.length*(board.length-1)+1 || x+1==board.length*(board.length-1)+2)
				{
					if (red[x-7]==true && red[x-7*2]==false && black[x-7*2]==false)//3
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x-7)/8][(x-7)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
							}
							
				}
				//*** 4 (lower right): ********************************************
				if(x+1==(board.length*(board.length-1))-1 || x+1==board.length*(board.length-1) || x+1==(board.length*board.length)-1 || x+1==board.length*board.length)
				{
					if (red[x-9]==true && red[x-9*2]==false && black[x-9*2]==false)//4
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x-9)/8][(x-9)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
					
				}
				//********************************************************************
				//*** GROUP 5-8 ******************************************************
				//*** 5 (north segment): *********************************************
				if( x+1==board.length*0+3 || x+1==board.length*0+4 || x+1==board.length*0+5 || x+1==board.length*0+6 || 
					x+1==board.length*1+3 || x+1==board.length*1+4 || x+1==board.length*1+5 || x+1==board.length*1+6)
				{
					if (red[x+9]==true && red[x+9*2]==false && black[x+9*2]==false)
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x+9)/8][(x+9)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
					
					if (red[x+7]==true && red[x+7*2]==false && black[x+7*2]==false)
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x+7)/8][(x+7)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
							}
					
				}
				//*** 6 (west segment): **********************************************
				if( x+1==board.length*2+1 || x+1==board.length*2+2 ||
					x+1==board.length*3+1 || x+1==board.length*3+2 ||
					x+1==board.length*4+1 || x+1==board.length*4+2 ||
					x+1==board.length*5+1 || x+1==board.length*5+2 )
					{
						if (red[x+9]==true && red[x+9*2]==false && black[x+9*2]==false)
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x+9)/8][(x+9)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
						
						if (red[x-7]==true && red[x-7*2]==false && black[x-7*2]==false)
							{
						System.out.println("This black checker can jump over red in position: "+(board[(x-7)/8][(x-7)%8]));
						jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
							}
					
					}
				//*** 7 (east segment): ********************************************
				if( x+1==board.length*3-1 || x+1==board.length*3 ||
					x+1==board.length*4-1 || x+1==board.length*4 ||
					x+1==board.length*5-1 || x+1==board.length*5 ||
					x+1==board.length*6-1 || x+1==board.length*6 )
						{
							if (red[x-9]==true && red[x-9*2]==false && black[x-9*2]==false)
								{
							System.out.println("This black checker can jump over red in position: "+(board[(x-9)/8][(x-9)%8]));
							jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
								}
							
							if (red[x+7]==true && red[x+7*2]==false && black[x+7*2]==false)
								{
							System.out.println("This black checker can jump over red in position: "+(board[(x+7)/8][(x+7)%8]));
							jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
								}
						
						}
				//*** 8 (south segment):
				if( x+1==board.length*6+3 || x+1==board.length*6+4 || x+1==board.length*6+5 || x+1==board.length*6+6 ||
					x+1==board.length*7+3 || x+1==board.length*7+4 || x+1==board.length*7+5 || x+1==board.length*7+6 )
							{
								if (red[x-9]==true && red[x-9*2]==false && black[x-9*2]==false)
									{
								System.out.println("This black checker can jump over red in position: "+(board[(x-9)/8][(x-9)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
									}
								
								if (red[x-7]==true && red[x-7*2]==false && black[x-7*2]==false)
									{
								System.out.println("This black checker can jump over red in position: "+(board[(x-7)/8][(x-7)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");	
									}
							
							}
				//****************************************************************
				//*** GROUP 9 ****************************************************
				//*** 9 (center 4x4): ********************************************
				if (x+1==board.length*2+3 || x+1==board.length*2+4 || x+1==board.length*2+5 || x+1==board.length*2+6 ||
					x+1==board.length*3+3 || x+1==board.length*3+4 || x+1==board.length*3+5 || x+1==board.length*3+6 ||
					x+1==board.length*4+3 || x+1==board.length*4+4|| x+1==board.length*4+5 || x+1==board.length*4+6 ||
					x+1==board.length*5+3 || x+1==board.length*5+4|| x+1==board.length*5+5 || x+1==board.length*5+6 )
						{
							if (red[x-9]==true && red[x-9*2]==false && black[x-9*2]==false)
							{
								System.out.println("This black checker can jump over red in position: "+(board[(x-9)/8][(x-9)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
							if (red[x-7]==true && red[x-7*2]==false && black[x-7*2]==false)
							{
								System.out.println("This black checker can jump over red in position: "+(board[(x-7)/8][(x-7)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
							if (red[x+7]==true && red[x+7*2]==false && black[x+7*2]==false)
							{
								System.out.println("This black checker can jump over red in position: "+(board[(x+7)/8][(x+7)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
							if (red[x+9]==true && red[x+9*2]==false && black[x+9*2]==false)
							{
								System.out.println("This black checker can jump over red in position: "+(board[(x+9)/8][(x+9)%8]));
								jumpList.add("Black checker (" + Xconverted+","+Yconverted+ ") has a jump");
							}
						
						
						
						}
				
			}//end of 'if black==true'
		
		}//end of the very first loop
		
		//*** prints out the jumpList in console and draws Strings
		g.setColor(Color.GREEN);
		g.setFont(new Font("Ariel", Font.PLAIN, 18));
		for (int a=0; a<jumpList.size();a++)
			{
			g.drawString(jumpList.get(a), 15, 385+(a*20));
			}
		 
		
	}
	}
