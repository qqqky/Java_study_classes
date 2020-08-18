package hanoiTowersVisualization;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * 
 * Interactive visualization of Hanoi Towers puzzle.
 * @author qqqky
 *
 */
public class HanoiTowersVisualisedMain {


		public static void main(String[]args)
		{
			
			JFrame frame = new JFrame ("Hanoi Towers Visualised");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			HanoiPegsPanel panel = new HanoiPegsPanel(6);
			
			frame.getContentPane().add(panel);
			frame.setSize(640,520);
			frame.setVisible(true);
		}
	}

class HanoiPegsPanel extends JPanel implements MouseListener
{
	private int totalDisks, stepCounter=0, selected=0;
	private EmptyPegs setup = new EmptyPegs();
	private HanoiDiskCollection myDisks;
	private JButton reset, next, previous;
	private JLabel title;
	private JPanel right, bottom;
	private int[]startM, endM;
	private Point current;
	private boolean diskIsSelected = false;

	
	public HanoiPegsPanel (int totalDisks)
	{
		setLayout(new BorderLayout());
		this.totalDisks = totalDisks;
		selected = this.totalDisks;
		setBackground(Color.WHITE);
		myDisks = new HanoiDiskCollection (totalDisks);
		
		//internally solves towers puzzle and puts results to 2 arrays
		//which will later be used to calculate correct moves
		//************************************************************
		towersQuick myTowers = new towersQuick(totalDisks);
		myTowers.solve();
		startM = myTowers.getStartArray();	//array of source pegs
		endM = myTowers.getEndArray();		//array of destination pegs
		
		//Prints out all moves to console:
		//for(int i = 0; i<startM.length; i++)
		//	System.out.println("Move one disk from "+startM[i]+" to "+endM[i]);
		
		addMouseListener(this);
		
		title = new JLabel ("Towers of Hanoi visualised", SwingConstants.CENTER);
		title.setFont(new Font("Ariel", Font.PLAIN, 25));
		
		right = new rightPanel(this);
		bottom = new bottomPanel(this);
		
		
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);
		
		add(title, BorderLayout.NORTH);
		
	}
	private class rightPanel extends JPanel implements ActionListener
	{
		
		private JPanel parent;
		
		public rightPanel(JPanel myParentPanel)
		{
			parent = myParentPanel;
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			reset = new JButton("   Reset   ");
			reset.addActionListener(this);
			next = new JButton ("    Next    ");
			next.addActionListener(this);
			previous = new JButton ("Previous");
			previous.addActionListener(this);
			
			add(Box.createRigidArea(new Dimension(0,20)));
			add(next);
			add(Box.createRigidArea(new Dimension(0,20)));
			add(previous);
			add(Box.createRigidArea(new Dimension(0,20)));
			add(reset);
			
			
			setBackground(Color.WHITE);
		}
		public void actionPerformed (ActionEvent e)
		{
			
			Object source = e.getSource();
			
			if(source == next && stepCounter<startM.length)
			{
				
				myDisks.moveDisk(myDisks.findTop(startM[stepCounter], totalDisks), endM[stepCounter]);
				parent.repaint();
				stepCounter++;
				selected = totalDisks; // clears selection (if any) after a successful move
			}
			
			if(source == previous && stepCounter>0)
			{
				myDisks.moveDisk(myDisks.findTop(endM[stepCounter-1], totalDisks), startM[stepCounter-1]);
				parent.repaint();
				stepCounter--;
			}
			if(source == reset)
			{
				myDisks.resetDisks();
				stepCounter=0;
				parent.repaint();
			}
			
		}
		
	}	
	
	public void clickNext ()	//used to be "next.doClick()", but this looks better
	{
		if(stepCounter<startM.length)
			{
			myDisks.moveDisk(myDisks.findTop(startM[stepCounter], totalDisks), endM[stepCounter]);
			repaint();
			stepCounter++;
			selected = totalDisks; // clears selection (if any) after a successful move
			}
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		setup.draw(g);
		myDisks.drawCurrent(g);
		

		if(selected!=totalDisks)
			{
				myDisks.selectDisk(selected, g);	
			}
		
		
	}
	public void mouseClicked (MouseEvent e) {}
	public void mousePressed (MouseEvent e) 
	{
		current = e.getPoint();
		
	if(current.x>35 && current.x<520 && current.y > 280 && current.y <400)
	{
		
		int diskOnPeg1 = myDisks.findTop(1, totalDisks);
		int diskOnPeg2 = myDisks.findTop(2, totalDisks);
		int diskOnPeg3 = myDisks.findTop(3, totalDisks);
		
		if(	diskOnPeg1 !=totalDisks && myDisks.diskIsSelected(current.x, current.y, diskOnPeg1) ||
			diskOnPeg2 !=totalDisks && myDisks.diskIsSelected(current.x, current.y, diskOnPeg2) ||
			diskOnPeg3 !=totalDisks && myDisks.diskIsSelected(current.x, current.y, diskOnPeg3))
			{
			selected = myDisks.getSelectedDisk();
			diskIsSelected = true;
			repaint();
			}
		else diskIsSelected = false;
			
	}
	
	if (diskIsSelected==true) 
				{
				int correctDisk = myDisks.findTop(startM[stepCounter], totalDisks);
				
				if(setup.isSelected(1, current.x, current.y) && endM[stepCounter]==1 &&
						correctDisk==selected)
						{
						next.doClick();//simply clicks next button if a correct destination peg and disk is selected
						}
				
				if(setup.isSelected(2, current.x, current.y) && endM[stepCounter]==2 &&
						correctDisk==selected)
						{
						next.doClick();
						}
				
				if(setup.isSelected(3, current.x, current.y) && endM[stepCounter]==3 &&
						correctDisk==selected)
						{
						next.doClick();
						}
				}
	}
	public void mouseReleased (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}

	
}
class bottomPanel extends JPanel implements ActionListener
{
	private HanoiPegsPanel parent;
	private JSlider speed;
	private JButton play, pause;
	private JLabel speedLabel;
	private Timer timer;
	private timeListener myTime;
	private boolean timerWasRunning = false;
	
	public bottomPanel(HanoiPegsPanel myParent)
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		parent = myParent;
		play = new JButton("Play/Resume");
		play.addActionListener(this);
		pause = new JButton("Pause");
		pause.addActionListener(this);
		speedLabel = new JLabel ("Speed: ");
		
		speed = new JSlider (1,3);
		speed.setValue(1);
		
		sliderListener mySlide = new sliderListener();
		speed.addChangeListener(mySlide);
		speed.setMaximumSize(new Dimension(200, 50));
		
		speed.setMajorTickSpacing(1);
		
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		speed.setMinorTickSpacing(1);
	
		
		myTime = new timeListener();
		timer = new Timer (900, myTime);
		
		
		add(Box.createRigidArea(new Dimension(40,0)));
		add(play);
		add(Box.createRigidArea(new Dimension(40,0)));
		add(pause);
		add(Box.createRigidArea(new Dimension(40,0)));
		add(speedLabel);
		add(Box.createRigidArea(new Dimension(10,0)));
		add(speed);
		add(Box.createRigidArea(new Dimension(40,0)));
	}
	
	public void actionPerformed (ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == play && !timer.isRunning())
			{
			timer.start();
			timerWasRunning = true;
			}
		if (source == play && timer.isRunning())
			{}//do nothing
		if (source == pause)
		{
			timer.stop();
			timerWasRunning = false;
		}
		
			
		
	}
	private class timeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			parent.clickNext();
		}
	}
	private class sliderListener implements ChangeListener
	{
		public void stateChanged (ChangeEvent e)
		{
			
			if(timer.isRunning())
				{
					timerWasRunning = true;		//remember animation state before slider was clicked
					timer.stop();
				}
			
			if(speed.getValue()==1)
				timer = new Timer (900, myTime);
			if(speed.getValue()==2)
				timer = new Timer (600, myTime);
			if(speed.getValue()==3)
				timer = new Timer (300, myTime);
			
			if(timerWasRunning==true)
				{
				timerWasRunning=false;
				timer.start();
				}
		}
	}
}
//********************************************************************************
//**** Defines a collection of HanoiDisk objects *********************************
//********************************************************************************
class HanoiDiskCollection
{
	private int total, Yposition = 0, selected=0;
	private HanoiDisk[] collection;
	private int[]pegCounters = new int[3];
	private Rectangle[] diskCoords;
	private int[][]disksOnPeg; //peg 1 at index 0
	
	
	public HanoiDiskCollection (int total)
	{
		this.total = total;
		collection = new HanoiDisk[total];
		diskCoords = new Rectangle[total];
		
		disksOnPeg = new int [3][total];
		
		
		for(int x=0; x<total; x++)
			{
			collection[x]=new HanoiDisk(x);
			diskCoords[x] = collection[x].getDiskCoordinates();
			pegCounters[0]+=1;
			disksOnPeg[0][x]=x;
			disksOnPeg[1][x]=total;	//if peg is empty, every value is "total" (6 in this case)
			disksOnPeg[2][x]=total; //can't be 0, because there is a zero-th disk (the biggest one)
			}
	}
	
	public void resetDisks()
	{
		pegCounters[0]=0;
		pegCounters[1]=0;
		pegCounters[2]=0;
		
		for(int x=0; x<total; x++)
		{
		collection[x]=new HanoiDisk(x);
		diskCoords[x] = collection[x].getDiskCoordinates();
		pegCounters[0]+=1;
		disksOnPeg[0][x]=x;
		disksOnPeg[1][x]=total;
		disksOnPeg[2][x]=total;
		}	
		
	}
	

	public void drawCurrent(Graphics g)
	{
		for (int x=0; x<collection.length;x++)
			{
			collection[x].drawDisk(g);
			}
		
	// Prints number of disks each peg has
	
		
		//System.out.println("Peg 1 has: "+pegCounters[0]+" disks");
		//System.out.println("Peg 2 has: "+pegCounters[1]+" disks");
		//System.out.println("Peg 3 has: "+pegCounters[2]+" disks");
	
	// Prints visual representation of disks in console (disks are numbered from 0)
	//	for(int i=0; i<3;i++)
	//		{
	//		for(int j=0;j<disksOnPeg[0].length;j++)
	//		System.out.print(disksOnPeg[i][j]+" ");
	//		System.out.println();
	//		}
				
	}
	
	public void moveDisk (int diskNumber, int peg)
	{
		
		if(pegCounters[peg-1]>total)
			System.out.println("ERROR in moveDisk");
		
		
		//Chooses Y position for disk based on how many disks a peg already has: 
		switch(pegCounters[peg-1])
		{
		case 0:
			Yposition = 1;
			break;
		case 1:
			Yposition = 2;
			break;
		case 2: 
			Yposition = 3;
			break;
		case 3:
			Yposition = 4;
			break;
		case 4:
			Yposition = 5;
			break;
		case 5:
			Yposition = 6;
			break;
		default:
			System.out.println("ERROR in switch statement");
			
		}
		
		//before moving the disk, the source peg counter is decremented by 1:
		pegCounters[collection[diskNumber].getCurrentPeg()-1]=pegCounters[collection[diskNumber].getCurrentPeg()-1]-1;
		disksOnPeg[collection[diskNumber].getCurrentPeg()-1][findIndexOfTopDisk(collection[diskNumber].getCurrentPeg(),total)]=total;
		
		//a disk is moved to the desired position:
		collection[diskNumber].moveDisk(peg, Yposition);
		
		//disk's coordinates are saved to array of Rectangles:
		diskCoords[diskNumber]=collection[diskNumber].getDiskCoordinates();
		
		//the destination peg is incremented by 1:
		pegCounters[peg-1]+=1;
		disksOnPeg[peg-1][pegCounters[peg-1]-1]=diskNumber;
		
		
		
	}
	
	//returns the array of current locations of every disk
	public Rectangle[] getLocations()
	{
		return diskCoords;
		
		
	}
	//*******************************************************************************
	//**** Methods to find WHICH disk (number) is on top of a particular peg ********
	//****																	 ********
	//**** and at which INDEX was it found									 ********
	//*******************************************************************************
	public int findTop (int sourcePeg, int totalDisks) //finds which disk (number) is on top
	{
		if(totalDisks==0)
			return total; //returns the number of disks if peg is empty (can't return a 0, because it marks the biggest disk)
			
		
		int a = disksOnPeg[sourcePeg-1][totalDisks-1];
	
		if (a != total)
			return a;
			
		else
			return findTop(sourcePeg, totalDisks-1);
		
	}
	//*** method to find index of the disk is same, but different return value
	public int findIndexOfTopDisk (int sourcePeg, int totalDisks) //finds INDEX of topmost disk
	{
		if(totalDisks==-1)
			return 0;
		
		int a = disksOnPeg[sourcePeg-1][totalDisks-1];
	
		if (a != total)
		return totalDisks-1;
			
		else
			return findIndexOfTopDisk(sourcePeg, totalDisks-1);
		
	}
	public boolean diskIsSelected(int x, int y, int diskNumber)
	{
		if(diskCoords[diskNumber].contains(x, y))
			{
				selected = diskNumber;
				return true;
			}
			else 
				{
				
				return false;
				}
	}
	public int getSelectedDisk()
	{
		return selected;
	}
	public boolean isOnTop(int diskNumber)
	{
		int peg = collection[diskNumber].getCurrentPeg();
		if (findTop(peg, total)==diskNumber)
			return true;
		
		else return false;
	}
	
	public void selectDisk(int diskNumber, Graphics g)
	{
		collection[diskNumber].highlightDisk(g);
	}
	public void deselectDisk(int diskNumber, Graphics g)
	{
		collection[diskNumber].drawDisk(g);
	}
}

//*************************************************************************
//*** Defines a single disk, size of which is determined by its number ***
//*************************************************************************
class HanoiDisk //assumes spacing between pegs is 180 pixels
{
	private int number, startX, startY, size = 120,
			pegNumber = 1, Yposition, savedX = 43, savedY =385;			
	private final int height = 14;
	private Rectangle x;
	
	public HanoiDisk(int number)
	{
		this.number = number;
		size = size-number*20;
		startX = savedX+number*10; //note: X position is also determined by disk number! (and number of a current peg it's on, of course)
		Yposition = number+1;
		
		startY = savedY-(Yposition-1)*height; //Y position is ONLY determined by calculation, of how many disks are on a particular peg
		
	}
	public void resetDisk (int number)
	{
		System.out.println("Resetting disk number: "+number);
		
		size = size-number*20;
		startX = savedX+number*10;
		startY = savedY-(Yposition-1)*height;
		Yposition = number+1;
		pegNumber = 1;
		
		
	}
	
	public void moveDisk (int pegNumber, int Yposition)
	{
		
		if (pegNumber >0 && pegNumber <4 && Yposition>0)
		{
			this.pegNumber=pegNumber;
			this.Yposition = Yposition;
			
			
			startX = savedX+number*10+180*(pegNumber-1); 	//first peg is number 1
			startY = savedY-((Yposition-1)*height); //position at base is position 1
			
			//System.out.print("startX: "+startX+"\tstartY: "+startY+"\n");
			//System.out.print("savedX: "+startX+"\tsavedY: "+startY+"\n");
			
		}
		else System.out.println("Wrong peg");
			
	}
	public void drawDisk (Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(startX, startY, size, height);//disk
		g.setColor(Color.BLACK);
		g.drawRect(startX, startY, size, height);//outer highlight
	}
	public void highlightDisk(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(startX, startY, size+1, height);//red highlight
		
	}
	
	public int getCurrentPeg ()
	{
		return pegNumber;
	}
	public int getCurrentY ()
	{
		return Yposition;
	}
	
	public int getNumber ()
	{
		return number;
	}
	public Rectangle getDiskCoordinates()
	{
		x = new Rectangle(startX, startY, size, height);
		
		return x;
	}
	
	//returns detailed information about the current disk
	public String toString()
	{
		return "NOTE: \tFirst disk is number 0 (the biggest one),\n"
				+ "\tFirst peg is peg number 1 (left one),\n"
				+ "\tFirst position for any disk is position number 1 (near the base of the peg),\n"
				+ "\tThe counters for disks start from 0 (as it should), but array for the first peg counter is at index 0\n"
				+ "------------------------------------------------------\n"
				+ "Current disk:\t"+number+"\nCurrent peg:\t"+pegNumber+
				"\nCurrent vertical position(1-6): "+Yposition+
				"\nCurrent position is \tX:"+startX+"\tY:"+startY+"\nOriginal position was: \tX:"+
				savedX+"\tY:"+savedY+"\n";
	}
	

		
}

//*** Defines a table with 3 empty pegs ********************************************
class EmptyPegs
{
	private int startX = 20, startY = 400;
	private Rectangle [] yellowButtons;
	public EmptyPegs()
	{
		yellowButtons = new Rectangle [3];
	}
	public void draw (Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(startX, startY, 540, 20);
		g.fillRect(startX+80, startY-120, 7, 120);
		g.fillRect(startX+260, startY-120, 7, 120);
		g.fillRect(startX+440, startY-120, 7, 120);
		
		g.setFont(new Font("Ariel",Font.PLAIN, 15));
		g.drawString("Select a disk and then a button of your chosen destination peg.", startX+20, startY-340);
		g.drawString("            >>>> Only a correct move will be accepted! <<<<", startX+20, startY-320);
		g.drawString("Use provided buttons to start/pause animation, change speed", startX+20, startY-280);
		g.drawString("or see a correct next/previous move", startX+110, startY-260);
		//also draws 3 circles, which let user interact with pegs
		yellowButtons[0] = new Rectangle (startX+74, startY-170, 20,20);
		yellowButtons[1] = new Rectangle (startX+74+180, startY-170, 20,20);
		yellowButtons[2] = new Rectangle (startX+74+360, startY-170, 20,20);
		
		g.fillOval(startX+74, startY-170, 20, 20);
		g.fillOval(startX+74+180, startY-170, 20, 20);
		g.fillOval(startX+74+360, startY-170, 20, 20);
		g.setColor(Color.YELLOW);//paints yellow inlines for the buttons
		g.fillOval(startX+77, startY-167, 14, 14);
		g.fillOval(startX+77+180, startY-167, 14, 14);
		g.fillOval(startX+77+360, startY-167, 14, 14);
	}
	public Rectangle[] getYellowButtons()
	{
		return yellowButtons;
	}

	public boolean isSelected(int peg, int x, int y)
	{
		return yellowButtons[peg-1].contains(x, y);
	}
	
}


