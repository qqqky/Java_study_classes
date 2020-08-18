package trafficLight;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TrafficLightPanel extends JPanel
{
private TrafficLight lightRed;
private JButton controller;
private Boolean drawRED=true;


	public TrafficLightPanel()
	
	{
	
		setPreferredSize(new Dimension(400,300));
		setBackground(Color.CYAN);
		lightRed = new TrafficLight();
		
		controller = new JButton("Change lights!");
		controller.addActionListener(new controlListener());
		add(controller);
			
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		int BASE =100;
		
			
		g.fillRect(BASE-100, BASE+110, BASE+300, BASE-45);		//ground
		g.setColor(Color.DARK_GRAY);
		g.fillRect(BASE+70,BASE+10, BASE-65,BASE);				//traffic light
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(BASE+74,BASE+19, BASE-74,BASE-74);			//frames of lights
		g.fillRect(BASE+74,BASE+49, BASE-74,BASE-74);
	
		if (drawRED)
		lightRed.drawRed(g);
		else
		lightRed.drawGreen(g);
	
	}
	private class controlListener implements ActionListener
	{
		public void actionPerformed (ActionEvent ee)
		{	
			drawRED=!drawRED;
			repaint();		
		}
			
	}
}

class TrafficLight extends JPanel
{

private int BASE = 100;


	public TrafficLight()
	{
		
	}
	public void drawRed(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(BASE+75,BASE+20, BASE-75,BASE-75);
	}
	public void drawGreen(Graphics g) 
	{
		g.setColor(Color.GREEN);
		g.fillRect(BASE+75,BASE+50, BASE-75,BASE-75);
	}
}