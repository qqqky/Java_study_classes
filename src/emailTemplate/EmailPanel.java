package emailTemplate;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EmailPanel extends JPanel
{
	private JLabel TOlabel, CClabel, BCClabel, TITLElabel, BODYlabel;
	private JTextField TO,CC,BCC,TITLE, BODY;
	private JButton button1;


	
	public EmailPanel()
	{
	
	TOlabel = new JLabel("To:   ");
	TO = new JTextField(30);
	CClabel = new JLabel("CC:   ");
	CC = new JTextField(30);
	BCClabel = new JLabel("BCC: ");
	BCC = new JTextField(30);
	TITLElabel = new JLabel ("Title: ");
	TITLE = new JTextField(30);
	BODYlabel = new JLabel("Body: ");
	BODY = new JTextField(30);
	button1 = new JButton("SEND");
	button1.addActionListener(new ListenerGG());
			
	add(TOlabel);
	add(TO);
	add(CClabel);
	add(CC);
	add(BCClabel);
	add(BCC);
	add(TITLElabel);
	add(TITLE);
	add(BODYlabel);
	add(BODY);
	add(button1);
	setSize(new Dimension(370,300));
	setBackground(Color.LIGHT_GRAY);
	
}
	private class ListenerGG implements ActionListener
	{
		public void actionPerformed (ActionEvent hh)
		{
			
			String to1, cc1,bcc1,title1,body1;
			to1= TO.getText();
			cc1 = CC.getText();
			bcc1= BCC.getText();
			title1=TITLE.getText();
			body1= BODY.getText();
			
			System.out.println("Message sent!");
			System.out.println("");
			System.out.println("Full information below:");
			System.out.println("");
			System.out.println("To: "+to1);
			System.out.println("CC: "+cc1 );
			System.out.println("BCC: "+bcc1);
			System.out.println("Title: "+title1);
			System.out.println("Message: "+body1);
		}
	}
}
