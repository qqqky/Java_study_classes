package polygonCar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*PP 8.13 Design a class that represents the visual representation of a car.
		Use polylines and polygons to draw the car in any graphics context and at any 
		location. Create a main driver to display the car.*/
/*PP 8.14 Modify the solution to PP 8.13 so that it uses the Polygon class
to represent all polygons used in the drawing.*/

/*
 * Draws a car using polygones
 */

public class PolygonCarStaticMain {

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Polygon Car");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new CarPanel());
		
		frame.setSize(400,350);
		frame.setVisible(true);
	}
}
class CarPanel extends JPanel
{
	private int pointXstart = 0, pointYstart= 0, pointXfinish=0, pointYfinish=0;
	private PolygonCar car1;
	
	
	public CarPanel()
	{
		car1=new PolygonCar(100);
		setBackground(Color.BLACK);
		
		//implements mouse listener for easier coords sniffing (prints coords upon pressing and releasing of mouse button) 
		addMouseListener(new listener1());
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//***draws a line based on mouse click inputs (to help with coords)
				g.drawLine(pointXstart, pointYstart, pointXfinish, pointYfinish);
				
		car1.drawCar(g);		
	}
	
	private class listener1 implements MouseListener
	{
		
		public void mousePressed (MouseEvent mouse)
		{
			pointXstart = mouse.getX();
			pointYstart = mouse.getY();
			System.out.println(pointXstart + "\t"+pointYstart);
		}
		public void mouseClicked (MouseEvent mouse) {}
		public void mouseReleased (MouseEvent mouse)
		{
			pointXfinish = mouse.getX();
			pointYfinish = mouse.getY();
			System.out.println(pointXfinish + "\t"+pointYfinish);
			repaint();
		}
		public void mouseEntered (MouseEvent mouse) {}
		public void mouseExited (MouseEvent mouse) {}
	}
}
