package interactiveFractals;
/*PP 12.10 Design and implement an applet that generalizes the
KochSnowflake program. Allow the user to choose a fractal
design from a menu item and to pick the background and drawing colors. The buttons to increase and decrease the order of
the fractal will apply to whichever fractal design is chosen. In
addition to the Koch snowflake, include a C-curve fractal whose
order 1 is a straight line. Each successive order is created by
replacing all line segments by two line segments, both half of the
size of the original, and which meet at a right angle. Specifically,
a C-curve of order N from <x1,y1> to <x3,y3> is replaced by
two C-curves from to and from <x2,y2> to <x3,y3> where:
1. x2 = (x1 + x3 + y1 - y3) / 2;
2. y2 = (x3 + y1 + y3 - x1) / 2;*/

//note: fractal is called Levy's C-curve

import javax.swing.JFrame;

/**
 * 
 * Interactive visualization of well known fractals - Koch's Snowflake and
 * Levy's C-curve.
 * @author qqqky
 *
 */

public class KochSnowflakeCCurveMain {

	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame ("Koch snowflake modified");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		KochSnowflakeMod panel = new KochSnowflakeMod();
		
		frame.getContentPane().add(panel);
		frame.setSize(550,480);
		frame.setVisible(true);
	}
}