package quadraticFormula;
/*PP 10.9 Design and implement an application that draws the graph of
the equation ax2 + bx + c, where the values of a, b, and c are set
using three sliders.*/

// ax2+bx+c=y

//Discriminant: b2-4ac
//	if disc = 0 -----> 1 real root			
// if disc >0 	-----> 2 real roots
// if disc <0	-----> 0 real roots

// real root = "crosses X axis at"
// max/min point = c-(b2/4a)

//function crosses X axis when Y=0, crosses Y axis when X=0

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * 
 * Interactive implementation of quadratic graph.
 * @author qqqky
 *
 */
public class QuadraticFormula {

	public static void main(String[]args)
	{
		
		
		JFrame frame = new JFrame("Quadratic equation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new QuadraticPanel());
		frame.setSize(545,650);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

class QuadraticPanel extends JPanel
{
	private JSlider aSlide, bSlide, cSlide;
	private JLabel aLabel, bLabel, cLabel;
	private double a=0, b=0, c=0;
	private double discriminant, x1, x2, maxPointX, maxPointY;
	private coordinateSystem coords;
	private Point[] graph;
	private final double PRECISION=0.015;		//lower precision = more points covered
	private final int totalPoints=500;
	
	
	
	public QuadraticPanel()
	{
		
		setLayout(new BorderLayout());
		coords = new coordinateSystem (1, 400, 400, true);
		coords.moveXby(78);
		graph = new Point [totalPoints];
		
		//loads array with ZEROES
		for(int i=0; i<totalPoints; i++)
		{
			graph[i]=new Point(0,0);
		}
		
		aSlide = new JSlider (SwingConstants.HORIZONTAL, -10, 10, 0);
		bSlide = new JSlider (SwingConstants.HORIZONTAL, -10, 10, 0);
		cSlide = new JSlider (SwingConstants.HORIZONTAL, -10, 10, 0);
		aLabel = new JLabel ("\"a\"");
		bLabel = new JLabel ("\"b\"");
		cLabel = new JLabel ("\"c\"");
		
		equationListener listener = new equationListener();
		aSlide.addChangeListener(listener);
		aSlide.setMajorTickSpacing(5);
		aSlide.setMinorTickSpacing(1);
		aSlide.setPaintTicks(true);
		aSlide.setPaintLabels(true);
		bSlide.addChangeListener(listener);
		bSlide.setMajorTickSpacing(5);
		bSlide.setMinorTickSpacing(1);
		bSlide.setPaintTicks(true);
		bSlide.setPaintLabels(true);
		cSlide.addChangeListener(listener);
		cSlide.setMajorTickSpacing(5);
		cSlide.setMinorTickSpacing(1);
		cSlide.setPaintTicks(true);
		cSlide.setPaintLabels(true);
		
		JPanel southPanel = new JPanel();
		JPanel innerSouthPanel1 = new JPanel();
		JPanel innerSouthPanel2 = new JPanel();
		JPanel innerSouthPanel3 = new JPanel();
		southPanel.setLayout(new GridLayout(3,0));
		
		innerSouthPanel1.add(aLabel);
		innerSouthPanel1.add(aSlide);
		innerSouthPanel2.add(bLabel);
		innerSouthPanel2.add(bSlide);
		innerSouthPanel3.add(cLabel);
		innerSouthPanel3.add(cSlide);
		
		southPanel.add(innerSouthPanel1);
		southPanel.add(innerSouthPanel2);
		southPanel.add(innerSouthPanel3);
		add(southPanel, BorderLayout.SOUTH);
		
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		coords.draw(g);
		g.setFont(new Font("lalla", Font.PLAIN, 22));
		g.drawString("y=ax2 + bx + c", 20, 50);
		g.drawString("a = "+aSlide.getValue(), 50, 320);
		g.drawString("b = "+bSlide.getValue(), 50, 350);
		g.drawString("c = "+cSlide.getValue(), 50, 380);
		
		
		String maxX = String.format("%.3f", maxPointX);
		String maxY = String.format("%.3f", maxPointY);
		g.drawString("Top point: ("+maxX+";"+maxY+")", 50, 425);
		
		
		//int counter = 0;
		for(Point a: graph)
		{
			coords.drawPoint(a, g);
			//System.out.println(counter+"\tX: "+a.getX()+"Y: "+a.getY());
			//counter++;
		}
	}
	public void fillGraph()
	{
		if(a==0)
		{
			if(b<2 && b>-2)//decrease precision when -2<x<2, otherwise the line just looks too short
			{
				double scaleIt = PRECISION*2;		
				for(int i=1; i<graph.length/2;i++)
					graph[i+1] = new Point (scaleIt*i, (b*(scaleIt*i))+c);
				for(int i=1; i<graph.length/2;i++)
					graph[i+graph.length/2] = new Point (-scaleIt*i, (b*(-scaleIt*i))+c);	
			}
			else
			{
				for(int i=1; i<graph.length/2;i++)
					graph[i+1] = new Point (PRECISION*i, (b*(PRECISION*i))+c);
				for(int i=1; i<graph.length/2;i++)
					graph[i+graph.length/2] = new Point (-PRECISION*i, (b*(-PRECISION*i))+c);
			}
			
			return;
		}
		if(discriminant==0)
			{
				for(int i=1; i<graph.length/2;i++)
				graph[i+2] = new Point(maxPointX+PRECISION*i, (a*((maxPointX+PRECISION*i)*(maxPointX+PRECISION*i))+(b*(maxPointX+PRECISION*i))+c));
			
				for(int i=1; i<graph.length/2;i++)
				graph[i+graph.length/2] = new Point(maxPointX-PRECISION*i, (a*((maxPointX-PRECISION*i)*(maxPointX-PRECISION*i))+(b*(maxPointX-PRECISION*i))+c));
			}
		
		if(discriminant<0)
		{
			for(int i=1; i<graph.length/2;i++)
			graph[i+1] = new Point(maxPointX+PRECISION*i, (a*((maxPointX+PRECISION*i)*(maxPointX+PRECISION*i))+(b*(maxPointX+PRECISION*i))+c));
		
			for(int i=1; i<graph.length/2;i++)
			graph[i+graph.length/2] = new Point(maxPointX-PRECISION*i, (a*((maxPointX-PRECISION*i)*(maxPointX-PRECISION*i))+(b*(maxPointX-PRECISION*i))+c));
		}
		
		if(discriminant>0)
		{
			for(int i=1; i<graph.length/2;i++)
			graph[i+3] = new Point(maxPointX+PRECISION*i, (a*((maxPointX+PRECISION*i)*(maxPointX+PRECISION*i))+(b*(maxPointX+PRECISION*i))+c));
		
			for(int i=1; i<graph.length/2;i++)
			graph[i+graph.length/2] = new Point(maxPointX-PRECISION*i, (a*((maxPointX-PRECISION*i)*(maxPointX-PRECISION*i))+(b*(maxPointX-PRECISION*i))+c));
		}

		
	}
	public double discriminant()
	{
		 
		discriminant = (b*b)-(4*a*c);
		System.out.println("D:" +discriminant);
		return discriminant;
	}
	
	public void maxPoint() //MAX/MIN point is stored in graph[0]
	{
		
			maxPointX = -b/(2*a);
			maxPointY =  c-(b*b/(4*a));
			graph[0]=new Point (maxPointX, maxPointY);
	}
	public void calculateX ()
	{
		
		if(a==0)
		{
			return;
		}
		if(discriminant == 0)
			{
			x1 = -b/(2*a);
			graph[1] = new Point (x1, 0);
			graph[2] = new Point (0, c); //adds a point where function crosses Y axis
			}
		if(discriminant>0)
		{
			x1 = (-b + Math.sqrt(discriminant))/2*a;
			x2 = (-b - Math.sqrt(discriminant))/2*a;
			graph[1] = new Point (x1, 0);
			graph[2] = new Point (x2, 0);
			graph[3] = new Point (0, c);//adds a point where function crosses Y axis
		}
		if(discriminant<0)
			{
			System.out.println("Does not cross X axis");
			graph[1] = new Point (0, c);
			}
		
	
	}
	private class equationListener implements ChangeListener
	{
		public void stateChanged (ChangeEvent e)
		{
			
			a = aSlide.getValue();
			b = bSlide.getValue();
			c = cSlide.getValue();
			
			discriminant();
			maxPoint();
			calculateX();
			fillGraph();
			repaint();
			
		}
	}
	
}
class coordinateSystem
{
	private int width, height, startX=2, spacing;
	private boolean labels=false;
	private double myX, myY;
	
	public coordinateSystem(int spacing, int width, int height, boolean labels)
	{
		this.spacing = spacing;
		this.width = width;
		this.height = height;
		this.labels=labels;
		
	}
	public void draw(Graphics g)
	{
		g.drawLine(startX, height/2, startX+width, height/2);	//X axis
		g.drawLine(startX+width, height/2, startX+width-20, height/2-20);
		g.drawLine(startX+width, height/2, startX+width-20, height/2+20);
		g.drawString("X", startX+width-2, height/2+20);
		
		g.drawLine(startX+width/2, 2, startX+width/2, height);	//Y axis
		g.drawLine(startX+width/2, 2, startX+width/2-20, 2+20);
		g.drawLine(startX+width/2, 2, startX+width/2+20, 2+20);
		g.drawString("Y", startX+width/2-20, 10+2);
		
		g.drawString("0", startX+width/2-10,  height/2+10+2); //0
		
		//draws ticks 1 tick spacing = width/21
		g.setFont(new Font("Ariel", Font.PLAIN, 8));
		for (int i=1; i<11; i++)
		{
			g.drawLine(startX+width/2+((width/21)*i), height/2-5, startX+width/2+((width/21)*i), height/2+5);//+x
			g.drawLine(startX+width/2-((width/21)*i), height/2-5, startX+width/2-((width/21)*i), height/2+5);//-x
			
			g.drawLine(startX+width/2-5, height/2-((height/21)*i), startX+width/2+5, height/2-((height/21)*i));//+y
			g.drawLine(startX+width/2-5, height/2+((height/21)*i), startX+width/2+5, height/2+((height/21)*i));//-y
		
			//draws labels on ticks (if set to true in constructor):
			if (labels==true && i<10)
				{
				if (spacing*i<10)
					{
					g.drawString(""+spacing*i, startX+width/2+((width/21)*i)-2, height/2+14);//+x 
					g.drawString("-"+spacing*i, startX+width/2-((width/21)*i)-5, height/2+14);//-x 
					g.drawString(""+spacing*i, startX+width/2-12, height/2-((height/21)*i)+3);//+y
					g.drawString("-"+spacing*i, startX+width/2-14, height/2+((height/21)*i)+3);//-y
					}
				if (spacing*i>=10)
					{
					g.drawString(""+spacing*i, startX+width/2+((width/21)*i)-5, height/2+14);//+x 
					g.drawString("-"+spacing*i, startX+width/2-((width/21)*i)-8, height/2+14);//-x
					g.drawString(""+spacing*i, startX+width/2-16, height/2-((height/21)*i)+3);//+y
					g.drawString("-"+spacing*i, startX+width/2-19, height/2+((height/21)*i)+3);//-y
					}
				
				}
		}
		
	}
	public void moveXby (int startPointX)
	{
		startX=startPointX;	
	}
	public void setSpacing(int spacing)
	{
		this.spacing = spacing;
	}
	public void drawPoint (Point p, Graphics g)
	{
	
	
	myX = p.getX();
	myY = p.getY();
		
	g.drawLine((int)(startX+width/2+((width/21)*myX)), (int)(height/2-((height/21)*myY)), (int)(startX+width/2+((width/21)*myX)), (int)(height/2-((height/21)*myY)));
	}
	

}
class Point
{
	double x, y;
	
	public Point (double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
}
