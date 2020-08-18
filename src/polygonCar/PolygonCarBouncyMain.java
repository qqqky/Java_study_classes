package polygonCar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*PP 9.7 Design and implement an application that displays an animation
of a car (side view) moving across the screen from left to right.
Create a Car class that represents the car (or use one that was
created for a programming project in Chapter 8).*/

/*
 * The polygon car is now moving
 */

public class PolygonCarBouncyMain {

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Polygon Car");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new DynamicCarPanel());
		
		frame.setSize(800,350);
		frame.setVisible(true);
	}
}
class DynamicCarPanel extends JPanel
{
	private int pointXstart = 0, pointYstart= 0, pointXfinish=0, pointYfinish=0,
			moveX=8, moveY = 0, carWidth = 332, currentX=10;
	private PolygonCarBouncy car1;
	private Timer timer;
	private boolean flipper = false;
	
	
	public DynamicCarPanel()
	{
		car1=new PolygonCarBouncy();
		setBackground(Color.BLACK);
		timer = new Timer (30, new carListener());
		
		//implements mouse listener for easier coords sniffing (prints coords upon pressing and releasing of mouse button) 
		addMouseListener(new listener1());
		timer.start();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//***draws a line based on mouse click inputs (to help with coords)
				g.drawLine(pointXstart, pointYstart, pointXfinish, pointYfinish);
	
		//car1.drawCar(g);
		car1.spawnCar(currentX, 180, g); // draws car at specified starting point
			
	}
	
	private class listener1 implements MouseListener
	{
		
		public void mousePressed (MouseEvent mouse)
		{
			pointXstart = mouse.getX();
			pointYstart = mouse.getY();
			System.out.println(pointXstart + "\t"+pointYstart);
			timer.stop();		
		
		}
		public void mouseClicked (MouseEvent mouse) {}
		public void mouseReleased (MouseEvent mouse)
		{
			pointXfinish = mouse.getX();
			pointYfinish = mouse.getY();
			System.out.println(pointXfinish + "\t"+pointYfinish);
			timer.start();
		}
		public void mouseEntered (MouseEvent mouse) {}
		public void mouseExited (MouseEvent mouse) {}
	}
	private class carListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
		
		if (carWidth+currentX+20>800 || currentX-10<0)
		{
			moveX=moveX*-1;
			
		}
		currentX+=moveX;
		repaint();
		}
	}
}
