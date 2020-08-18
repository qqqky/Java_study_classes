package someCircles;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * Puts 20000 points randomly on screen, colors them
 * depending on generated coords.
 *
 */

public class TwentyThousandPoints {

	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame("20000 random points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pointsPanel panel = new pointsPanel();
		frame.getContentPane().add(panel);
		
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}

class pointsPanel extends JPanel
{
private int random1,random2;	
	
	pointsPanel()
	{
		
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		for (int x=0;x<20000;x++)
		{
			
			
			random1=(int)(Math.random()*480);
			random2=(int)(Math.random()*460);
			
			if (random1<240)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GREEN);
			g.drawLine(random1, random2, random1, random2);
		}
	}
}