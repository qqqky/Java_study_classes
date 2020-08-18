package brickWallPattern;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * 
 * Just a boring brick wall
 *
 */


public class BrickWallSimple {


	public static void main (String[] args)
	   {
		
		JFrame frame = new JFrame("A simple brick wall");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		BrickWallPatternPanel panel = new BrickWallPatternPanel();
		
		frame.add(panel);
		frame.setSize(496,360);
		
		frame.setVisible(true);
	   }
}
class BrickWallPatternPanel extends JPanel
{
	private int startX=0;
	private int startY=0;

public BrickWallPatternPanel()
{

}

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	setBackground(Color.WHITE);
	
	
	for (int y=0;y<8;y++)	//y=number of columns of bricks (Y)
	{
		switch(y%2)
		{
		case 1: startX=startX-40;
		break;
		default: startX=0;
		}
		for (int x=0; x<7; x++)	//x=number of bricks in one line (X)
		{
			g.setColor(Color.BLACK);
			g.drawRect(startX+(80*x), startY+(40*y), 80, 40);		//1 brick (outline)
			g.setColor(Color.RED);
			g.fillRect((startX+2)+(80*x), (startY+2)+(40*y), 78, 38);		//inside of every brick is colored for fun
		}
		
	}		
			
}

}
