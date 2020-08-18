package quilts;
import javax.swing.*;
import java.awt.*;

public class Quilt2Main {


	public static void main (String[]args)
	{
		
		JFrame frame = new JFrame("Quilt pattern drawing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new QuiltPanel2());
		
		
		frame.setSize(580,400);
		frame.setVisible(true);
	}
}

class QuiltPanel2 extends JPanel{

private QuiltPattern pattern1, pattern2, pattern3, pattern4,pattern5,pattern6,pattern7,pattern8,pattern9;
private Color circleColor = Color.BLUE;

	public QuiltPanel2()
	{
		//parameters for QuiltPattern are: startX, startY, density, circle color)
	
		pattern1 = new QuiltPattern(1,1,90,circleColor);	//only X coord changes
		pattern2 = new QuiltPattern(4,1,90,circleColor);
		pattern3 = new QuiltPattern(7,1,90,circleColor);
		pattern4 = new QuiltPattern(10,1,90,circleColor);
		pattern5 = new QuiltPattern(13,1,90,circleColor);
		pattern6 = new QuiltPattern(16,1,90,circleColor);
		pattern7 = new QuiltPattern(19,1,90,circleColor);
		pattern8 = new QuiltPattern(22,1,90,circleColor);
		pattern9 = new QuiltPattern(25,1,90,circleColor);
		setPreferredSize(new Dimension (400,500));
		setBackground(Color.WHITE);
	
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		pattern1.draw(g);
		pattern2.draw(g);
		pattern3.draw(g);
		pattern4.draw(g);
		pattern5.draw(g);
		pattern6.draw(g);
		pattern7.draw(g);
		pattern8.draw(g);
		pattern9.draw(g);
	
	}
}
