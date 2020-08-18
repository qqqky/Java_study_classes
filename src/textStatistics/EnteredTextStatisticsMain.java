package textStatistics;
import javax.swing.*;
/**
 * Program calculates the number of characters and words in text, 
 * shows average word length. 
 * Punctuation marks and numbers are counted as characters and 
 * are NOT omitted from counting of the average length of a word!
 * 
 */


public class EnteredTextStatisticsMain {

	
	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame("Entered Text Statistics");
		frame.setSize(500,230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new EnteredTextStatisticsPanel());
		
		frame.setVisible(true);
	}
}
