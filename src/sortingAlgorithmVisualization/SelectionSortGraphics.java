package sortingAlgorithmVisualization;
/*PP 10.6 Design and implement a program that graphically displays the
processing of a selection sort. Use bars of various heights to represent the values being sorted. Display the set of bars after each
swap. Put a delay in the processing of the sort to give the human
observer a chance to see how the order of the values changes.*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Selection sort (descending) step-by-step visualization
 */

public class SelectionSortGraphics {

	
	public static void main(String []args)
	{
		
		JFrame frame = new JFrame("Selection sort process");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().add(new SelectionSortPanel());
		
		frame.setSize(550,300);
		frame.setVisible(true);
	}
}

class SelectionSortPanel extends JPanel implements Comparable
{
	private JLabel str1, str2, str3, str4, str5, str6, empty1, empty2, empty3, empty4, 
	empty5, WORDSlabel, emptyFirst, emptyLast, eastLabel, westLabel;
	private JButton next, reset;
	private Comparable[] words, wordsCopy;
	private Comparable temp;
	private String a, b, c, d, e, f;
	private myCustomCenter centerPanel;
	private int max=0, stepCount=0;
	
	
	public SelectionSortPanel()
	{
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(550,300));
		setBackground(Color.LIGHT_GRAY);
		
		str1 = new JLabel ("Why");
		str2 = new JLabel ("Must");
		str3 = new JLabel ("I");
		str4 = new JLabel ("Sort");
		str5 = new JLabel ("This");
		str6 = new JLabel ("Nonsense");
		empty1= new JLabel ("             \t");
		empty2= new JLabel ("               \t");
		empty3= new JLabel ("                \t");
		empty4= new JLabel ("             \t");
		empty5= new JLabel ("           \t");
		emptyFirst = new JLabel("          ");
		emptyLast = new JLabel("  ");
		
		//array of strings, which are presented in labels:
		words = new Comparable[6];
		words[0] = str1.getText();
		words[1] = str2.getText(); 
		words[2] = str3.getText();
		words[3] = str4.getText();
		words[4] = str5.getText();
		words[5] = str6.getText();
		
		
		//SortingDescending.selectionSort(words);
		
		//for (int a=0; a<words.length; a++)
		//	System.out.println(words[a]);
		
		
		JPanel stringPanel = new JPanel();
		
		stringPanel.add(emptyFirst);
		stringPanel.add(str1);
		stringPanel.add(empty1);
		stringPanel.add(str2);
		stringPanel.add(empty2);
		stringPanel.add(str3);
		stringPanel.add(empty3);
		stringPanel.add(str4);
		stringPanel.add(empty4);
		stringPanel.add(str5);
		stringPanel.add(empty5);
		stringPanel.add(str6);
		stringPanel.add(emptyLast);
		
		JPanel southPanel = new JPanel();
		next = new JButton ("See next step");
		reset = new JButton ("Restart");
		next.addActionListener(new buttonListener());
		reset.addActionListener(new resetListener());
		southPanel.add(next);
		southPanel.add(reset);
		
		JPanel eastPanel = new JPanel();
		eastLabel = new JLabel ("          ");
		eastLabel.setBackground(Color.GREEN);
		eastPanel.add(eastLabel);
		
		JPanel westPanel = new JPanel();
		westLabel = new JLabel ("          ");
		westLabel.setBackground(Color.GREEN);
		westPanel.add(westLabel);
		
		centerPanel = new myCustomCenter();
		
		
		add(stringPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
	
	}
	
	private class buttonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			
			if(stepCount<words.length)
			{
				centerPanel.cleanLabel(stepCount);
				sortingStep();
			}
			else
				{
				//***do nothing*** (=reached final step)
				}
			
			centerPanel.updateBottom(words);
		}
	}
	private class resetListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			
			unsort();	// resets the array (other methods by saving the array and pointing to the saved one didn't work (more than once)
			stepCount=0; //resets steps
			centerPanel.cleanAll(); //cleans all labels
			centerPanel.updateBottom(words); // resets words in the bottom to original
		}
	}
	private void unsort()
	{
		words[0] = str1.getText();
		words[1] = str2.getText(); 
		words[2] = str3.getText();
		words[3] = str4.getText();
		words[4] = str5.getText();
		words[5] = str6.getText();
	}
	
	//method does sorting one step at a time based on button clicks
	private void sortingStep()
	{
		
		
		Comparable temp;
		max = stepCount;
		
		
			for (int j=stepCount+1; j<words.length; j++)
				{
				if(words[j].compareTo(words[max]) >0)
					max = j;
				}
					
			
			temp = words[max];
			words[max]=words[stepCount];
			words[stepCount]=temp;
			
			
			//sets label in center panel (to value which is being compared)
			centerPanel.setLabel(0, (String)words[stepCount]);
			
		
		stepCount++;
		
		//Prints out current list:
		//for (int a=0; a<words.length;a++)
		//	System.out.print(words[a]+" ");
		//System.out.println();
	}
	
	//provides empty definition
	public int compareTo (Object c)
	{
	return 0;
	}
}
class myCustomCenter extends JPanel
{
	private JLabel c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
	private String stepOne1, stepOne2, stepTwo1, stepTwo2, stepTwo3, stepThree1, 
	stepThree2, stepFour1, stepFour2, stepFour3, stepFive1, stepFive2, stepFive3, 
	stepSix1, stepSix2, stepSix3, stepSeven1, stepSeven2, stepSeven3;
	private Arrow arrow1;
	private int[] arrowPosition; // possible positions of the arrow (6 total)
	private JLabel[] uplabelList, downlabelList;
	private int drawCounter;
	
	myCustomCenter()
	{
		
		c1 = new JLabel ("");
		c1.setHorizontalAlignment(SwingConstants.CENTER);
		c2 = new JLabel ("");
		c2.setHorizontalAlignment(SwingConstants.CENTER);
		c3 = new JLabel ("");
		c3.setHorizontalAlignment(SwingConstants.CENTER);
		c4 = new JLabel ("");
		c4.setHorizontalAlignment(SwingConstants.CENTER);
		c5 = new JLabel ("");
		c5.setHorizontalAlignment(SwingConstants.CENTER);
		c6 = new JLabel ("");
		c6.setHorizontalAlignment(SwingConstants.CENTER);
		c7 = new JLabel ("Why");
		c7.setHorizontalAlignment(SwingConstants.CENTER);
		c8 = new JLabel ("Must");
		c8.setHorizontalAlignment(SwingConstants.CENTER);
		c9 = new JLabel ("I");
		c9.setHorizontalAlignment(SwingConstants.CENTER);
		c10 = new JLabel ("Sort");
		c10.setHorizontalAlignment(SwingConstants.CENTER);
		c11 = new JLabel ("This");
		c11.setHorizontalAlignment(SwingConstants.CENTER);
		c12 = new JLabel ("Nonsense");
		c12.setHorizontalAlignment(SwingConstants.CENTER);
		
		uplabelList = new JLabel[6];
		uplabelList[0]=c1;
		uplabelList[1]=c2;
		uplabelList[2]=c3;
		uplabelList[3]=c4;
		uplabelList[4]=c5;
		uplabelList[5]=c6;
		
		downlabelList = new JLabel[6];
		downlabelList[0]=c7;
		downlabelList[1]=c8;
		downlabelList[2]=c9;
		downlabelList[3]=c10;
		downlabelList[4]=c11;
		downlabelList[5]=c12;
		
		arrowPosition = new int[6];
		arrowPosition[0]=22;
		arrowPosition[1]=96;
		arrowPosition[2]=170;
		arrowPosition[3]=244;
		arrowPosition[4]=318;
		arrowPosition[5]=392;
		
		setLayout(new GridLayout(2,6));
		add(c1);
		add(c2);
		add(c3);
		add(c4);
		add(c5);
		add(c6);
		add(c7);
		add(c8);
		add(c9);
		add(c10);
		add(c11);
		add(c12);
		
		setPreferredSize(new Dimension(450,250));
		setBackground(Color.LIGHT_GRAY);
		arrow1 = new Arrow();
		
		//**** Initializing all strings describing steps in detail
		stepOne1 = "Firstly, word \"Why\" is compared to every other";
		stepOne2 = "to find the highest one";
		stepTwo1 = "Highest is \"Why\", so the list doesn't change";
		stepTwo2 = "(it is already first). Now the 2nd word is compared.";
		stepTwo3 = "second word is \"Must\" (see below):";
		stepThree1 = "Highest is \"This\", so 2nd word \"Must\" is sent to";
		stepThree2 = "it's position (5) and \"This\" is sent to 2nd place";
		stepFour1 = "Now the 3rd word (\"I\") is compared to every other.";
		stepFour2 = "\"Sort\" is the highest, so \"I\" is sent to";
		stepFour3 =  "it's place and \"Sort\" is sent to 3rd place";
		stepFive1 = "Now the 4th word (it is \"I\" again) is compared.";
		stepFive2 = "\"Nonsense\" is highest, so it takes the 4th position";
		stepFive3 = "and \"I\" takes it's position (6th).";
		stepSix1 = "Finally, 5th word \"Must\" is compared to the last one";
		stepSix2 = "It is the higher of the two, so the list stays the same";
		stepSix3 = "Sorting is complete! (see next)";
		stepSeven1 = "Actually, algorithm still selects the last element";
		stepSeven2 = "in this case - word \"I\", but nothing is left to";
		stepSeven3 = "compare it to, so no swap is executed!";
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.GRAY);
		g.setFont(new Font("Ariel", Font.ITALIC, 16));
		
		//draws every step
		switch(drawCounter)
		{
		case 0:
			arrow1.drawArrowDown(22, 74, g); //+74 x
			g.drawString(stepOne1, 80, 95);
			g.drawString(stepOne2, 80, 115);
			break;
		case 1:
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepTwo1, 80, 95);
			g.drawString(stepTwo2, 80, 115);
			g.drawString(stepTwo3, 80, 135);
			break;
		case 2:
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepThree1, 80, 95);
			g.drawString(stepThree2, 80, 115);
			break;
		case 3:
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepFour1, 80, 95);
			g.drawString(stepFour2, 80, 115);
			g.drawString(stepFour3, 80, 135);
			break;
		case 4:
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepFive1, 80, 95);
			g.drawString(stepFive2, 80, 115);
			g.drawString(stepFive3, 80, 135);
			break;
		case 5:
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepSix1, 80, 95);
			g.drawString(stepSix2, 80, 115);
			g.drawString(stepSix3, 80, 135);
			break;
		case 6:
			g.setColor(Color.RED);
			arrow1.drawArrowUp(22, 74, g); //+74 x
			g.drawString(stepSeven1, 80, 95);
			g.drawString(stepSeven2, 80, 115);
			g.drawString(stepSeven3, 80, 135);
			g.setColor(Color.GRAY);
			
		}
		
		
		
		g.setFont(new Font("Ariel", Font.BOLD, 11));
		if(drawCounter!=0)
		g.drawString("<--- chosen word (highest)", 80, 55);
		g.drawString("Original list of words", 160, 25);
		g.drawString("Updated list (after each step)", 130, 185);
		
	}
	public void setLabel(int a, String text)
	{
		uplabelList[a].setText(text);
		drawCounter++;	//counter for drawing specific steps 
		repaint();
	}
	public void cleanLabel(int a)
	{
		uplabelList[a].setText("");
	}
	public void updateBottom(Comparable[] list)
	{
		
		for(int a=0; a<list.length;a++)
			downlabelList[a].setText(((String)list[a]));
		
	}
	public void cleanAll()
	{
		for(int a=0; a<uplabelList.length; a++)
			uplabelList[a].setText("");
		for(int a=0; a<downlabelList.length; a++)
			downlabelList[a].setText("");
		drawCounter=0;
		repaint();
	}
	
}


