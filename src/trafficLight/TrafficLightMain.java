package trafficLight;

import javax.swing.*;

/**
 *
 * Change the color of traffic light
 * 
 */
public class TrafficLightMain {

	
	public static void main(String[]args)
	{
		
		JFrame frame = new JFrame ("Traffic director");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().add(new TrafficLightPanel());
		frame.setVisible(true);
}}