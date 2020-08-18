package interactiveFractals;
/*PP 12.14 A Sierpinski Triangle is a fractal formed by drawing a triangle,
and then using the midpoints of each side of triangle to form
another triangle. This inner triangle is then removed. The result
is three smaller triangles (one at the top and one in each corner)
on which the process is repeated. After iteration N, the image
will contain 3N triangles, each of which is similar to the original triangle.
Write a program that implements a recursive algorithm for
drawing a Sierpinski Triangle. The user interface for the program should include 
a JSlider that allows the user to select a
value for N. The slider should allow the user to pick a value
for N between 0 and the maximum value of N possible based
on the size of the program window. The maximum slider value
should change as appropriate when the window is resized.*/
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * 
 * Interactive example of one of the famous fractals - Sierpinski Triangle.
 * @author qqqky
 *
 */

public class SierpinskiTriangleMain {

	
	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame("Sierpinski triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new TrianglePanel(frame));

		
		frame.setSize(420,400);
		frame.setVisible(true);
	}
}

class TrianglePanel extends JPanel
{
	private JLabel north;
	private SouthPanel south;
	private Triangle triangle;
	private JFrame frame;
	private int iteration=1;
	
	public TrianglePanel(JFrame frame)
	{
		this.frame=frame;
		
		setLayout(new BorderLayout());
		north = new JLabel("Sierpinski triangle", SwingConstants.CENTER);
		north.setFont(new Font("Ariel", Font.PLAIN, 22));
		south = new SouthPanel(this);
		triangle = new Triangle (this);
		
		
		
		add(south, BorderLayout.SOUTH);
		add(north, BorderLayout.NORTH);
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(400,400));
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		triangle.drawTriangle(iteration, triangle.getX(), triangle.getY(), g);
		
		//passes coords to other classes if window is resized:
		triangle.setNewCoords(getWindowSize());
		south.changeSlider(getWindowSize());
		
	}
	public Dimension getWindowSize()
	{
		return frame.getSize();
	}
	public void setIteration (int newIteration)
	{
		iteration=newIteration;
	}
	
}

class Triangle
{
	private int topX=200, topY=50, leftX=20, leftY=300, rightX=380, rightY=300;
	private TrianglePanel parentPanel;
	private int[] pX, pY;
	
	public Triangle(TrianglePanel p1)
	{
		parentPanel = p1;
		pX = new int[] {topX, leftX, rightX};
		pY = new int[] {topY, leftY, rightY};

		
	}
	
	public void drawTriangle(int iteration, int[] xCoords, int[] yCoords, Graphics g)
	{
		if(iteration==1)
			g.fillPolygon(xCoords, yCoords, xCoords.length);
		
		else
				
		{
			//sets new coords and draws triangle at the top:
			int[] newX1 = {xCoords[0], (xCoords[0]+xCoords[1])/2, (xCoords[0]+xCoords[2])/2};
			int[] newY1 = {yCoords[0], (yCoords[0]+yCoords[1])/2, (yCoords[0]+yCoords[2])/2};
			
			drawTriangle(iteration-1, newX1, newY1, g);
			
			//sets coords and draws triangle from lower left:
			int[] newX2 = {xCoords[1], (xCoords[0]+xCoords[1])/2, (xCoords[1]+xCoords[2])/2};
			int[] newY2 = {yCoords[1], (yCoords[0]+yCoords[1])/2, (yCoords[1]+yCoords[2])/2};
			
			drawTriangle(iteration-1, newX2, newY2, g);
			
			//sets coords and draws triangle from lower right:
			int[] newX3 = {xCoords[2], (xCoords[0]+xCoords[2])/2, (xCoords[1]+xCoords[2])/2};
			int[] newY3 = {yCoords[2], (yCoords[0]+yCoords[2])/2, (yCoords[1]+yCoords[2])/2};
			
			drawTriangle(iteration-1, newX3, newY3, g);
		
			
		}			
			
	}

		//Math part for new polygon generation:
	
		//int[] newX1 = {topX, (topX+leftX)/2, (topX+rightX)/2};
		//int[] newY1 = {topY, (topY+leftY)/2, (topY+rightY)/2};
		
		//int[] newX2 = {leftX, (topX+leftX)/2, (leftX+rightX)/2};
		//int[] newY2 = {leftY, (topY+leftY)/2, (leftY+rightY)/2};
		
		//int[] newX3 = {rightX, (topX+rightX)/2, (leftX+rightX)/2};
		//int[] newY3 = {rightY, (topY+rightY)/2, rightY};
		
		
		
	//updates X/Y coords if window is resized:
	
	public void setNewCoords (Dimension d)
	{
		topX = d.width/2-5;
		topY = 40;
		leftX = 20;
		leftY = d.height-100;
		rightX = d.width-30;
		rightY = d.height-100;
		
		//must be included:
		pX = new int[]{topX, leftX, rightX};
		pY = new int[] {topY, leftY, rightY};
		
	}
	//getters for X/Y arrays to initially generate the first triangle
	public int[] getX ()
	{
		return pX;
	}
	public int[] getY ()
	{
		return pY;
	}
		
}

class SouthPanel extends JPanel
{
	private TrianglePanel parent;
	private JSlider Nslider;
	private JLabel iteration;
	private Dimension windowSize;
	
	public SouthPanel(TrianglePanel panel)
	{
		parent = panel;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Nslider = new JSlider(1,5);
		Nslider.setMajorTickSpacing(1);
		Nslider.setPaintTicks(true);
		Nslider.setPaintLabels(true);
		Nslider.setValue(1);
		Nslider.addChangeListener(new sliderListener());
		
		iteration = new JLabel ("Iteration: ");
		
		add(Box.createRigidArea(new Dimension(60,0)));
		add(iteration);
		add(Box.createRigidArea(new Dimension(5,0)));
		add(Nslider);
		add(Box.createRigidArea(new Dimension(60,0)));
		
		
		
	}
	
	//changes slider value based on size of the window
	public void changeSlider(Dimension d)
	{
		if(d.height!=0)
		{
			Nslider.setMaximum((d.height+d.width)/200);
		}
		
	}
	//sets iteration for the triangle based on slider value
	private class sliderListener implements ChangeListener
	{
		public void stateChanged (ChangeEvent e)
		{
			parent.setIteration(Nslider.getValue());
			parent.repaint();
		}
	}
}

