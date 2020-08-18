package emailTemplate;
import javax.swing.*;

/**
 * 
 * Imitation of email program. Prints fields to console
 *
 */

public class EmailTemplateMain {

	
	public static void main(String[]args)
	{
		JFrame frame1 = new JFrame("Email message");
		frame1.setSize(370,300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().add(new EmailPanel());
		frame1.setResizable(false);
		frame1.setVisible(true);
	}
}
