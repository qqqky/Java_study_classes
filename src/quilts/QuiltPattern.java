package quilts;
import javax.swing.*;
import java.awt.*;

public class QuiltPattern extends JPanel {

	private int startX;
	private int startY;
	private int density;// = 70 default (squareSize*2.5) (spacing between diamonds in the pattern)
	private Color c;
	
	public QuiltPattern(int X, int Y, int size, Color color)
	{
		
	startX=X;
	startY=Y;
	density=size;
	c=color;	
		
	}

	public void draw (Graphics g)
	{
	
		
		for (int y=0; y<4;y++)		//int Y just for easier differentiation			
			for (int x=0;x<6;x++)	//Y must be tied to Y, X to X like below
				{
				g.setColor(Color.BLACK);
				g.drawLine(15+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 15+startY+(y*density));
				g.drawLine(15+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 75+startY+(y*density));
				g.drawLine(75+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 15+startY+(y*density));
				g.drawLine(75+startX+(x*density), 45+startY+(y*density), 45+startX+(x*density), 75+startY+(y*density));
				if (y<3 && x<5) //circle amount: x-1 and y-1
					{
					g.setColor(c);	
					g.drawOval((70-((90-density)/4))+startX+(x*density), 70-((90-density)/4)+startY+(y*density), 40-((90-density)/2), 40-((90-density)/2));
					}
				}
			
	}
}

