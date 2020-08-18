package interactiveFractals;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Recursive method for generating both fractals were taken from book:
 * "Java Software Solutions: Foundations of Program Design"
 * 
 */

public class KochSnowflakeMod extends JPanel
{
	private leftPanel left;
	private fractalPanel right;
	private Color currentBackground = Color.pink;
	
	public KochSnowflakeMod()
	{
		setLayout(new BorderLayout());
		
		
		JLabel title = new JLabel ("Koch's snowflake and Levy's C-curve", SwingConstants.CENTER);
		title.setFont(new Font("Ariel", Font.BOLD, 20));
		title.setPreferredSize(new Dimension(0,50));
		add(title, BorderLayout.NORTH);
		
		left = new leftPanel();
		add(left, BorderLayout.WEST);
		
		right = new fractalPanel();
		add(right, BorderLayout.CENTER);
		
	
		left.changeBackground(currentBackground);
	}
	public int getOrder()
	{
		return left.getOrder();
	}
	public void setOrder()
	{
		right.setOrder(getOrder());
	}
	public void changeNorth(Color color)
	{
		currentBackground = color;
		setBackground(currentBackground);
	}
	


class leftPanel extends JPanel implements ActionListener
{
	private JButton inc, dec;
	private JRadioButton Cfractal, Sfractal, blackF, whiteF, magentaF, blackB, whiteB, pinkB;
	private JLabel fractalColor, backgroundColor, leftTitle, colorTitle, orderTitle,
	backgroundTitle;
	private JPanel orderPanel;
	private int order=1;
	private final int MIN = 1, MAX = 9;


	
	public leftPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.pink);
		
		//buttons
		Sfractal = new JRadioButton ("Koch's snowflake");
		Sfractal.addActionListener(this);
		Cfractal = new JRadioButton ("Levy's C-curve");
		Cfractal.addActionListener(this);
		
		blackF = new JRadioButton ("Black");
		blackF.addActionListener(this);
		whiteF = new JRadioButton ("White");
		whiteF.addActionListener(this);
		magentaF = new JRadioButton ("Magenta");
		magentaF.addActionListener(this);
		
		blackB = new JRadioButton ("Black");
		blackB.addActionListener(this);
		whiteB = new JRadioButton ("White");
		whiteB.addActionListener(this);
		pinkB = new JRadioButton ("Pink");
		pinkB.addActionListener(this);
		//---------------------------------------------------
		leftTitle = new JLabel ("Select fractal: ");
		orderTitle = new JLabel ("Order: 1");
		colorTitle = new JLabel ("Select color: ");
		
		
		backgroundTitle = new JLabel ("Select background: ");
	
		orderPanel = new JPanel();
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.X_AXIS));
	
		inc = new JButton("+");
		dec = new JButton(" -");
		inc.addActionListener(this);
		dec.addActionListener(this);
		
		//fractal button group
		ButtonGroup selection = new ButtonGroup();
		Sfractal.setSelected(true);
		selection.add(Sfractal);
		selection.add(Cfractal);
		
		//shape color button group
		ButtonGroup colorSelect = new ButtonGroup();
		blackF.setSelected(true);
		colorSelect.add(blackF);
		colorSelect.add(whiteF);
		colorSelect.add(magentaF);
		
		//background color button group
		ButtonGroup background = new ButtonGroup();
		background.add(blackB);
		background.add(whiteB);
		background.add(pinkB);
		pinkB.setSelected(true);
		
		
		
		add(leftTitle);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(Sfractal);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(Cfractal);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(orderTitle);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(inc);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(dec);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(colorTitle);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(blackF);
		add(whiteF);
		add(magentaF);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(backgroundTitle);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(blackB);
		add(whiteB);
		add(pinkB);
		
		
		setPreferredSize(new Dimension(160,400));
		
	}
	public void actionPerformed (ActionEvent event)
	 {
	 
	 order = getOrder();
	 
	 if (event.getSource() == inc && order<MAX)
		 order++;
		 
	 if(event.getSource() == dec && order>MIN)
		 order--;
	 
	//sets color of the fractals
	 if(event.getSource() == blackF)
		 {
		 right.setColor(Color.black);
		 right.repaint();
		 }
	 
	 if(event.getSource() == whiteF)
		 {
		 right.setColor(Color.white);
		 right.repaint();
		 }
	 
	 if(event.getSource() == magentaF)
	 	{
		 right.setColor(Color.magenta);
		 right.repaint();
	 	}
	 
	 //sets background
	 if(event.getSource() == blackB)
	 {
		 changeBackground(Color.BLACK);
	 }
	 if(event.getSource() == whiteB)
	 {
		 changeBackground(Color.WHITE);
	 }
	 if(event.getSource() == pinkB)
	 {
		 changeBackground(Color.PINK);
	 }
	 
	 if (order >= MIN && order <= MAX)
	 	{
		 orderTitle.setText ("Order: " + order+" ");
		 right.setOrder(order);
		 right.repaint();
	 	}
	 
	 if (event.getSource() == Sfractal || event.getSource() == Cfractal)
	 {
		
		 right.repaint();
	 }
	  
	 }
	
	public int getOrder ()
	{
		return order;
	}
	public void setOrder (int order)
	{
		this.order=order;
	}
	public void changeBackground (Color color)
	{
		currentBackground = color;
		
		setBackground(currentBackground);
		blackF.setBackground(currentBackground);
		blackB.setBackground(currentBackground);
		whiteF.setBackground(currentBackground);
		whiteB.setBackground(currentBackground);
		magentaF.setBackground(currentBackground);
		pinkB.setBackground(currentBackground);
		Sfractal.setBackground(currentBackground);
		Cfractal.setBackground(currentBackground);
		right.setBackground(currentBackground);
		changeNorth(currentBackground);
		
	}
	
}

class fractalPanel extends JPanel
{
	 //instance variables for Koch's snowflake:
	 private final int PANEL_WIDTH = 400;
	 private final int PANEL_HEIGHT = 400;
	 private final double SQ = Math.sqrt(3.0) / 6;
	 private final int TOPX = 180, TOPY = 15;
	 private final int LEFTX = 40, LEFTY = 295;
	 private final int RIGHTX = 320, RIGHTY = 295;
	 private int current=1; // current order
	 private Color currentColor= Color.black;
	
	
	public fractalPanel()
	{
		
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(400,400));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor (currentColor);
		
		if(left.Sfractal.isSelected())
		{
			drawKoch (current, TOPX, TOPY, LEFTX, LEFTY, g); 		//left side
			drawKoch (current, LEFTX, LEFTY, RIGHTX, RIGHTY, g);	//bottom
			drawKoch (current, RIGHTX, RIGHTY, TOPX, TOPY, g);		//right side
		
		}
		if(left.Cfractal.isSelected())
		{
			drawLevy (current, LEFTX+45, LEFTY-120, RIGHTX-45, RIGHTY-120, g);
		}
		
		
	}
	public void drawKoch (int order, int x1, int y1, int x5, int y5, Graphics page)
	{
		int deltaX, deltaY, x2, y2, x3, y3, x4, y4;
		
		if (order == 1)
			page.drawLine (x1, y1, x5, y5);
		
		else
			{
			deltaX = x5 - x1; // distance between end points
			deltaY = y5 - y1;
			x2 = x1 + deltaX / 3; // one third
			y2 = y1 + deltaY / 3;
			x3 = (int) ((x1+x5)/2 + SQ * (y1-y5)); // tip of projection
			y3 = (int) ((y1+y5)/2 + SQ * (x5-x1));
			x4 = x1 + deltaX * 2/3; // two thirds
			y4 = y1 + deltaY * 2/3;
			
			drawKoch (order-1, x1, y1, x2, y2, page);
			drawKoch (order-1, x2, y2, x3, y3, page);//tip of projection
			drawKoch (order-1, x3, y3, x4, y4, page);//tip of projection
			drawKoch (order-1, x4, y4, x5, y5, page);
			
			}
		
	}//end of drawKoch
	
	public void drawLevy (int order, int x1, int y1, int x3, int y3, Graphics page)
	{
		int x2, y2;
		
		if (order == 1)
			page.drawLine (x1, y1, x3, y3);
		
		else
			{
			
			x2 = (x1 + x3 + y1 - y3) / 2;
			y2 = (x3 + y1 + y3 - x1) / 2;
			
			
			
			drawLevy (order-1, x1, y1, x2, y2, page);
			drawLevy (order-1, x2, y2, x3, y3, page);
			
			
			
			}
		
	}//end of drawLevy
	
	//----------------------------------------
	//setter and getter for current order:
	public void setOrder (int order)
	{
		current = order;
	}
	
	public int getOrder ()
	{
		return current;
	}
	public void setColor(Color color)
	{
		currentColor=color;
		
	}
	//-----------------------------------------
}
}
