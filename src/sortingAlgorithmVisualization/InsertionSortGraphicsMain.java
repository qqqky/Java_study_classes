package sortingAlgorithmVisualization;
/*PP 10.7 Repeat PP 10.6 using an insertion sort.*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * 
 * Insertion sort (descending) step-by-step visualization
 *
 */


public class InsertionSortGraphicsMain {

	
	public static void main(String []args)
	{
		
		JFrame frame = new JFrame("Insertion sort process");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().add(new InsertionSortPanel());
		
		frame.setSize(550,300);
		frame.setVisible(true);
	}
}

class InsertionSortPanel extends JPanel implements Comparable
{
	private JLabel str1, str2, str3, str4, str5, str6, empty1, empty2, empty3, empty4, 
	empty5, emptyFirst, emptyLast, eastLabel, westLabel;
	private JButton next, reset;
	private Comparable[] words;
	private Comparable temp;
	private String a, b, c, d, e, f;
	private myCustomCenter2 centerPanel;
	private int max=0, stepCount=1, whileCounter=0;
	private Timer time;
	
	
	public InsertionSortPanel()
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
		
		//adds all words to panel and later that panel to NORTH position
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
		
		//SOUTHERN panel with 2 buttons
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
		
		centerPanel = new myCustomCenter2();
		
		
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
			
			if(whileCounter>0 || centerPanel.getDrawCounter()>8 && centerPanel.getDrawCounter()<11)
			{
				centerPanel.drawThisStep();
				//updates the list upon final step
				if (centerPanel.getDrawCounter()==11)
					centerPanel.updateBottom(words);
				
			}
			
			if(stepCount<words.length && whileCounter==0)
			{
				sortingStep();
			}
			else
				{
				//***do nothing*** (=reached final step)
				}
			
			
			if(whileCounter>0)
			{
				whileCounter=whileCounter-1;
				
			}
			
		}
	}
	private class resetListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			
			unsort();	// resets the array (other methods by saving the array and pointing to the saved one didn't work (more than once)
			stepCount=1; //resets steps
			centerPanel.cleanAll(); //cleans all labels
			centerPanel.updateBottom(words); // resets words in the bottom to original
			whileCounter=0;
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
		
		
			centerPanel.updateBottom(words);
	 		Comparable key = words[stepCount]; //= "temp"	

	 		centerPanel.drawThisStep();
	 		int position = stepCount;			
	 		
	 		while (position > 0 && key.compareTo(words[position-1]) > 0)
	 		{
	 			
	 			words[position] = words[position-1];  
	 			position--;	
	 			whileCounter++;
	 					
	 		}
	 		// INSERTION:
	 	words[position] = key; // <-- the KEY value takes the current position being checked
	 	

	 	
	 	stepCount++;
	 	
		
	}
	private class whileListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			
		}
	}
	//provides empty definition
	public int compareTo (Object c)
	{
	return 0;
	}
}
class myCustomCenter2 extends JPanel
{
	private JLabel c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
	private String stepOne1, stepOne2, stepTwo1, stepTwo2, stepTwo3, stepThree1, 
	stepThree2, stepFour1, stepFour2, stepFour3, stepFive1, stepFive2, stepFive3, 
	stepSix1, stepSix2, stepSix3, stepSeven1, stepSeven2, stepSeven3, stepEight1,
	stepEight2, stepEight3, stepNine1, stepNine2, stepNine3, stepNine4, stepNine5,
	stepNine6, stepNine7, stepNine8, stepNine9, stepNine61;
	private Arrow arrow1;
	private int[] arrowPosition; // possible positions of the arrow (6 total)
	private JLabel[] uplabelList, downlabelList;
	private int drawCounter;
	
	public myCustomCenter2()
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
		
		/*arrowPosition = new int[6];
		arrowPosition[0]=22;
		arrowPosition[1]=96;
		arrowPosition[2]=170;
		arrowPosition[3]=244;
		arrowPosition[4]=318;
		arrowPosition[5]=392;*/
		
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
		stepOne1 = "Firstly, second word \"Must\" is compared to the";
		stepOne2 = "first one. \"Why\" is higher, so the list doesn't change.";
		stepTwo1 = "Now the 3rd word \"I\" is compared to 2nd (\"Must\").";
		stepTwo2 = "\"Must\" should be higher and it is. List stays the same";
		
		stepThree1 = "First 3 words are in order (compared to each other)";
		stepThree2 = "Now 4th word \"Sort\" is compared to 3rd one (\"I\")";
		stepFour1 = "It must be higher, so 3rd word (\"I\") is moved up to 4th place";
		stepFour2 = "Afterwards, \"Sort\" is compared to 2nd word (\"Must\")";
		stepFour3 =  "Again it must be higher, so \"Must\" is moved up one spot";
		stepFive1 = "\"Sort\" is finally compared to 1st word. It is";
		stepFive2 = "not higher than \"Why\", so finally, \"Sort\" is";
		stepFive3 = "moved to 2nd place and \"Why\"'s position stays the same";
		stepSix1 = "Four words are sorted now. Time to select 5th word. ";
		stepSix2 = "\"This\" is firstly compared with 4th word (\"I\"). It is higher";
		stepSix3 = "So \"I\" is moved to 5th position. \"This\" is then compared";
		stepSeven1 = "to 3rd word \"Must\". Higher again, so \"Must\" is moved up.";
		stepSeven2 = "\"This\" is then compared to 2nd word \"Sort\". Again, ";
		stepSeven3 = "it is higher, so \"Sort\" is moved up and is 3rd now";
		stepEight1 = "Finally, our word \"This\" is compared to the very first one";
		stepEight2 = "It is not higher than \"Why\", so in the end, \"This\" takes";
		stepEight3 = "2nd spot and \"Why\" stays 1st";
		stepNine1 = "5 words are sorted now, one word left: \"Nonsense\" (6th)";
		stepNine2 = "Like before, it is first compared to 5th word (\"I\")";
		stepNine3 = "It is higher than \"I\", so \"I\" takes the last (and final) place";
		stepNine4 = "\"Nonsense\" is then compared to 4th word: \"Must\".";
		stepNine5 = "Again, being lower, \"Must\" is pushed one place up and";
		stepNine6 = "is 5th now. \"Nonsense\" is next compared to 3rd word ";
		stepNine61 = "(\"Sort\").";
		stepNine7 = "It is not higher. So \"Sort\" stays 3rd and our word ";
		stepNine8 = "\"Nonsense\" takes 4th place. No more comparisons ";
		stepNine9 = "are made. The list is now sorted!";
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
			arrow1.drawArrowDown(98, 85, g); //+74 x
			g.drawString(stepOne1, 50, 55);
			g.drawString(stepOne2, 50, 75);
			break;
		case 1:
			arrow1.drawArrowDown(173, 85, g); //+74 x
			g.drawString(stepTwo1, 45, 55);
			g.drawString(stepTwo2, 45, 75);
			//g.drawString(stepTwo3, 50, 95);
			break;
		case 2:
			arrow1.drawArrowDown(248, 85, g); //+74 x
			g.drawString(stepThree1, 45, 55);
			g.drawString(stepThree2, 45, 75);
			break;
		case 3:
			g.drawString(stepFour1, 25, 55);
			break;
		case 4:
			g.drawString(stepFour2, 45, 55);
			g.drawString(stepFour3, 45, 75);
			//g.drawString(stepFive3, 50, 95);
			break;
		case 5:
			g.drawString(stepFive1, 45, 55);
			g.drawString(stepFive2, 45, 75);
			g.drawString(stepFive3, 45, 95);
			break;
		case 6:
			arrow1.drawArrowDown(324, 90, g); //+74 x
			g.drawString(stepSix1, 45, 55);
			g.drawString(stepSix2, 45, 75);
			g.drawString(stepSix3, 45, 95);
			break;
		case 7:
			g.drawString(stepSeven1, 45, 55);
			g.drawString(stepSeven2, 45, 75);
			g.drawString(stepSeven3, 45, 95);
			break;
		case 8:
			arrow1.drawArrowDown(100, 90, g); //+74 x
			g.drawString(stepEight1, 45, 55);
			g.drawString(stepEight2, 45, 75);
			g.drawString(stepEight3, 45, 95);
			break;
		case 9:
			arrow1.drawArrowDown(396, 90, g); //+74 x
			g.drawString(stepNine1, 45, 55);
			g.drawString(stepNine2, 45, 75);
			g.drawString(stepNine3, 45, 95);
			break;
		case 10:
			g.drawString(stepNine4, 45, 55);
			g.drawString(stepNine5, 45, 75);
			g.drawString(stepNine6, 45, 95);
			g.drawString(stepNine61, 45, 115);
			break;
		case 11:
			g.drawString(stepNine7, 45, 55);
			g.drawString(stepNine8, 45, 75);
			g.drawString(stepNine9, 45, 95);
			break;
		}
		
		
		
		g.setFont(new Font("Ariel", Font.BOLD, 11));
		g.drawString("Original list of words", 160, 25);
		g.drawString("Updated list", 190, 185);
		
	}
	public void drawThisStep()
	{
		
		drawCounter++;	//counter for drawing specific steps 
		repaint();
	}
	public void cleanLabel(int a)
	{
		uplabelList[a].setText("");
	}
	public void updateBottom(Comparable[] list) //updates bottom list
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
	public int getDrawCounter()
	{
		return drawCounter;
	}
	
}

