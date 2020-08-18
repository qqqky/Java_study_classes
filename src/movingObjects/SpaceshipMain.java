package movingObjects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * Spaceship follows mouse pointer and shoots laser
 *
 */

public class SpaceshipMain {

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Click to fire a laser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new SpaceshipPanel());
		
		frame.setSize(600,600);
		frame.setVisible(true);
	}
}

class SpaceshipPanel extends JPanel
{
	private Spaceship myShip;
	private int shipX=0, shipY=0;
	private boolean laser = false;
	
	public SpaceshipPanel()
	{
		
		
		myShip = new Spaceship();
		
		SpaceshipListener listener2 = new SpaceshipListener();
		addMouseMotionListener(listener2);
		addMouseListener(listener2);
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.BLACK);
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		if(shipX!=0 && shipY!=0)
			{
			myShip.moveTo(shipX,shipY);
			}
		
		myShip.drawShip(g);
		
		if(laser==true)
			{
			myShip.shootLaser(g);
			}
		
	}
	private class SpaceshipListener implements MouseListener, MouseMotionListener
	{
		public void mousePressed (MouseEvent e) 
		{
			laser = true;
			repaint();
		}
		public void mouseReleased (MouseEvent e) 
		{
			laser = false;
			repaint();	
		}
		public void mouseClicked (MouseEvent e) 
		{
			
		}
		public void mouseEntered (MouseEvent e) {}
		public void mouseExited (MouseEvent e) {}
		public void mouseMoved (MouseEvent e) 
		{
			
			shipX=e.getX();
			shipY=e.getY();
			repaint();
			
		}
		public void mouseDragged (MouseEvent e) 
		{
			shipX=e.getX();
			shipY=e.getY();
			repaint();
		}
		
	}
}
