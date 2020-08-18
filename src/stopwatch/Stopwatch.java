package stopwatch;
/*PP 9.10 Design and implement an application that works as a stopwatch.
Include a display that shows the time (in seconds) as it increments. Include buttons that allow the user to start and stop the
time, and reset the display to zero. Arrange the components to
present a nice interface. Hint: use the Timer class to control the
timing of the stopwatch.*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * A primitive stopwatch
 */
public class Stopwatch {

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Stopwatch");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new StopwatchPanel());
		
		frame.setSize(120,200);
		frame.setResizable(false);
		frame.setVisible(true);
}
}

class StopwatchPanel extends JPanel
{
	private Timer watchtimer;
	private JLabel minutes, seconds, ms, empty;
	private JButton stop, start, reset;
	private int msCounter, secCounter, minCounter;
	private ActionListener buttonListener;
	
	public StopwatchPanel()
	{
		
		//*** timer at 10ms delay is not exactly accurate to 0.01secs
		watchtimer = new Timer(10, new timeListener());
		minutes = new JLabel("0");
		minutes.setBackground(Color.GREEN);
		seconds = new JLabel(":00");
		seconds.setBackground(Color.GREEN);
		ms = new JLabel(":00");
		ms.setBackground(Color.GREEN);
		seconds.setFont(new Font("lalala", Font.PLAIN, 22));
		minutes.setFont(new Font("lalala", Font.PLAIN, 22));
		
		
		stop = new JButton("STOP");
		start = new JButton("START/RESUME");
		reset = new JButton("RESET");
		empty = new JLabel("  ");//<---to take up space on the right
		
		buttonListener = new listenButtons();
		stop.addActionListener(buttonListener);
		start.addActionListener(buttonListener);
		reset.addActionListener(buttonListener);
		
		add(minutes);
		add(seconds);
		
		add(ms);
		add(empty);
		add(stop);
		add(start);
		add(reset);
		
		
		setPreferredSize(new Dimension (50, 250));
		setBackground(Color.GRAY);
		//watchtimer.start();
		
	}
	
	private class timeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			
			ms.setText(":"+msCounter+"");
			if(secCounter<10)
			{
				seconds.setText(":0"+secCounter+"");
			}
			if(secCounter>=10)
			{
				seconds.setText(":"+secCounter+"");
			}
			minutes.setText(minCounter+"");
			msCounter++;
			
			if(msCounter==100)
				{
				msCounter=0;
				secCounter++;
				
				if(secCounter==60)
				{
					secCounter=0;
					minCounter++;
					
				}
				}
		}
	}

	private class listenButtons implements ActionListener
	{
		public void actionPerformed(ActionEvent buttonevent)
		{
			Object source = buttonevent.getSource();
			
			if(source==stop)
				watchtimer.stop();
			if(source==start)
				watchtimer.start();
			if(source==reset)
				{
				watchtimer.stop();
				msCounter=0;
				secCounter=0;
				minCounter=0;
				ms.setText(":0"+msCounter+"");
				seconds.setText(":0"+secCounter+"");
				minutes.setText(minCounter+"");
				}
			
		}
	}
}
