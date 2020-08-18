package someCircles;
import java.awt.*;

import javax.swing.*;

/**
 * 
 * Random bubbles want to bubble.
 * Resize window to generate
 * 
 */

public class RandomCircles {

	
	public static void main (String[] args)
	   {
		
		JFrame frame = new JFrame("Bubbly");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		RandomCirclesPanel panel = new RandomCirclesPanel();
		
		frame.add(panel);
		frame.setSize(420,320);
		
		//System.out.println(frame.getWidth());
		frame.setVisible(true);
	   }
}

class RandomCirclesPanel extends JPanel
{
	int MAX =100;	//max amount of circles (STATIC)
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.WHITE);
		int random1 = (int)(Math.random()*MAX);
		
		int MAX2=30; //max diameter of circles (can't be static, because it changes
					 //every loop, so cannot be declared before paintComponent
		int MaxX=350; //max X/Y(location)
		int MaxY=250;
		for (int x=0;x<=random1;x++)
			{
			int random2=(int)(Math.random()*MAX2);
			int random3=(int)(Math.random()*MaxX);
			int random4=(int)(Math.random()*MaxY);
			//g.setColor((new Color((int)(Math.random() * 0x1000000)))); //sets random color
			g.drawOval(random3, random4, random2, random2);
			//System.out.println((int)(Math.random() * 0x1000000)); //testing
			}
			
		System.out.println(random1); // WHY prints TWO TIMES????
		
	}
}