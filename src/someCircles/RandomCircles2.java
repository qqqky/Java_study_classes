package someCircles;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * Draws 10 random circles, colors the biggest one red.
 *
 */

public class RandomCircles2 {

	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame("10 Random sized circles. Biggest one is red");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CirclesPanel panel = new CirclesPanel();
		frame.getContentPane().add(panel);
		
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
class CirclesPanel extends JPanel
{
	private int randomX, randomY, randomSize;
	private int max, biggestX, biggestY;
	
public CirclesPanel()
	{	
		
	}
	
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	
	max=0;					//reset the max size (if window is resized)
	for (int x=0;x<10;x++)
	{
		randomX=(int)(Math.random()*410);
		randomY=(int)(Math.random()*410);
		randomSize=(int)(Math.random()*120);
	
		if (randomSize > max)
			{
			max=randomSize;		//remembers which circle is the biggest
			biggestX=randomX;	// and it's X/Y coords
			biggestY=randomY;
			}
		g.setColor(Color.black);
		g.drawOval(randomX, randomY, randomSize, randomSize);
	
	}	
	//draws the biggest circle (overlay):
		g.setColor(Color.RED);
		g.fillOval(biggestX, biggestY, max, max);
		
	
}
		
		
}
