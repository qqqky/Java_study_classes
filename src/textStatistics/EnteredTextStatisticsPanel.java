package textStatistics;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

//***************************
//for notes, see driver class
//***************************

public class EnteredTextStatisticsPanel extends JPanel
{
	private JButton button1;
	private JTextArea textArea1;
	private JTextField box1, box2, box3;
	private JLabel label1,label2,label3;
	private String test;
	private int counter, totalSpaces, totalWords;
	private double averageLength, totalCharacters;
	
	public EnteredTextStatisticsPanel()
	{
			
		textArea1 = new JTextArea(9,48);
		textArea1.setBackground(Color.WHITE);
		
		button1 = new JButton("Recalculate");
		button1.addActionListener(new Listener1());
		
		JPanel panel2 = new JPanel();				// 2nd panel added
		
		box1 = new JTextField ("Words", 5);			// objects created for 2nd panel
		box2 = new JTextField ("Avg", 5);
		box3 = new JTextField ("Chars ", 5);
		label1 = new JLabel("Words");
		label2 = new JLabel("Average length");
		label3 = new JLabel("Characters");
		panel2.add(label1);
		panel2.add(box1);
		panel2.add(label2);
		panel2.add(box2);
		panel2.add(label3);
		panel2.add(box3);
		panel2.add(button1);
		panel2.setBackground(Color.PINK);
		
		add(textArea1);
		add(panel2);
		
		
		counter = 0;							//variables initiated
		totalSpaces = 0;
		
		setBackground(Color.LIGHT_GRAY);
		//setSize(new Dimension(200,200));		does not work anyways????
	
	}

	private class Listener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent yy)
		{
			String a = textArea1.getText();		//reads text from text area
	//		System.out.print(a+" ");			//for easier testing
			int MAX = a.length();
	//		System.out.print(MAX+" ");			//for testing
			
			
			test=a.substring(counter,counter+1);
	//		System.out.println(test);	
			
			
				
				while ((!test.equals(" ")||test.equals(" "))&&counter<MAX)	//tests characters one by one
				{
					if(test.equals(" "))
						{
						test=a.substring(counter,counter+1);
						counter++;
						totalSpaces++;						//will be used for word count
						System.out.print(test);				//prints to console
						}
						
					else
						{
						test=a.substring(counter,counter+1);
						//label1.setText(test);
						System.out.print(test);				//prints to console
						counter++;
						}
					
				}	
			
		//prints out to console		
				System.out.println();
				System.out.println((counter-totalSpaces)+" (total characters without spaces)");
				System.out.println((totalSpaces+1)+ " total words");
				
				totalCharacters=counter-totalSpaces;
				averageLength=(double)(totalCharacters/(totalSpaces+1));
				totalWords = totalSpaces+1;
				
				System.out.println("Average word length: "+ averageLength);
				
				//prints results to GUI
				DecimalFormat xxx = new DecimalFormat("0.##");	//formats the double
				box1.setText(""+totalWords);
				box2.setText(""+xxx.format(averageLength));
				box3.setText(""+(int)(totalCharacters));
				
				
				counter=0;				//sets everything back to zero and waits
				totalSpaces=0;			//for new button click to update info again
		}
		}
	}

	
	

