package quilts;
import javax.swing.*;
import java.awt.*;

public class Quilt1Main {

	public static void main (String[] args)
	   {
		
		JFrame frame = new JFrame("Quilt on grid pattern");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		Quilt1Panel panel = new Quilt1Panel();
		
		frame.add(panel);
		frame.setSize(556,399);
		
		//System.out.println(frame.getWidth());
		frame.setVisible(true);
	   }
}

class Quilt1Panel extends JPanel
{
	
	int startX=1;
	int startY=1;
	int squareSize=30;
	
	//QuiltPattern pattern17 = new QuiltPattern(1,1,70,Color.RED);
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.WHITE);
	//	pattern17.draw(g);
		//draws a grid:
	//*************************************************	
		for (int y=0; y<12;y++)				//rows (Y coord gets changed every cycle)
		{
			for(int x=0; x<18;x++)			//lines (X coord gets changed every inner cycle)
				{//g.setColor(Color.BLACK);
				g.drawRect(startX+(squareSize*x), startY+(squareSize*y), squareSize, squareSize);
			//	g.setColor(Color.LIGHT_GRAY);	//inline of squares
			//	g.drawRect((startX+2)+(squareSize*x), (startY+2)+(squareSize*y), squareSize-4, squareSize-4);
				}
		}
	//**************************************************
	
		//draws a simple pattern:
	//**************************************************
	
		//4 lines for a single rotated cube (for easier pattern reading symmetrical lines start from same Y point)
		//g.drawLine(15+startX, 45+startY, 45+startX, 15+startY);
		//g.drawLine(15+startX, 45+startY, 45+startX, 75+startY);
		//g.drawLine(75+startX, 45+startY, 45+startX, 15+startY);
		//g.drawLine(75+startX, 45+startY, 45+startX, 75+startY);
	int density = 90; // = 70 default (squareSize*2.5) (spacing between diamonds in the pattern)
		
		for (int y=0; y<4;y++)		//int Y just for easier differentiation			
			for (int x=0;x<6;x++)	//Y must be tied to Y, X to X like below
				{
				g.drawLine(15+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 15+startY+(y*density));
				g.drawLine(15+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 75+startY+(y*density));
				g.drawLine(75+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 15+startY+(y*density));
				g.drawLine(75+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 75+startY+(y*density));
				if (y<3 && x<5)
				g.drawOval((70-((90-density)/4))+startX+(x*density), 70-((90-density)/4)+startY+(y*density), 40-((90-density)/2), 40-((90-density)/2));
				}
			
	}
}
