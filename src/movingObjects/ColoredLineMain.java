package movingObjects;
/*PP 9.8 Design and implement an application that displays an animation
of a horizontal line segment moving across the screen, eventually
passing across a vertical line. As the vertical line is passed, the
horizontal line should change color. The change of color should
occur while the horizontal line crosses the vertical one; therefore,
while crossing, the horizontal line will be two different colors.*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*
 * Line moves, changes color when crossing the middle line
 */

public class ColoredLineMain {

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Moving line");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new MovingLinePanel());
		
		frame.setSize(600,350);
		frame.setVisible(true);
	}
}

class MovingLinePanel extends JPanel
{
	private Timer lineTimer, line2Timer;
	private int startX=20, endX = 150, startX2=290, endX2=420, moveX=0, moveX2=0;
	private boolean timerControl; //<-- controls last part of the 2nd line
	
	public MovingLinePanel()
	{
		lineTimer = new Timer(45, new lineListener());
		line2Timer = new Timer(45, new line2Listener());
	
		setBackground(Color.BLACK);
		lineTimer.start();
		
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawLine(290, 0, 290, 350);
		
		//by default draws a line when first time is running and stops timer when beginning of the line is on the middle line
		if(lineTimer.isRunning()==true)
		{
			if(endX+moveX>=290)//2. when line reaches the middle
			{
				if(line2Timer.isRunning()==false)
				{
				line2Timer.start();
				System.out.println("Timer2 started");//for error checking
				}
			g.setColor(Color.RED);
			g.drawLine(startX2, 160, startX2+moveX2, 160);//starts a new line when first one reaches the middle
			g.setColor(Color.BLUE);
			g.drawLine(startX+moveX, 160, 290, 160); // and shortens the first one by restricting end coords
			}
			if(startX+moveX>288)//3. after the beginning of the first line reaches the middle
			{
				lineTimer.stop(); //stops timer if beginning of the line reaches the middle
			}
			if(endX+moveX<290)//1. while line is running towards the middle line
				{
				g.setColor(Color.BLUE);
				g.drawLine(startX+moveX, 160, endX+moveX, 160); //<-- default drawing when timer1 is running

				}
		}
		
		if(lineTimer.isRunning()==false && line2Timer.isRunning()==true && timerControl==true)
		{
			g.setColor(Color.RED);
			g.drawLine(startX2+moveX2, 160, endX2+moveX2, 160);
		}
		
	
	}
	
	
	private class lineListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
		moveX+=3;
		repaint();
		}
	}
	private class line2Listener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
		if(lineTimer.isRunning()==false && timerControl==false)
		{
			moveX2=0; //resets the value after 2nd line's beginning reaches the middle
			timerControl=true;
		}
		moveX2+=3;
		repaint();
		}
	}
}
