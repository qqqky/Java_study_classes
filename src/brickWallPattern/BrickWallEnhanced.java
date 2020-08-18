package brickWallPattern;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
/**
 * A more realistic brick wall pattern with nice color schemes.
 * Press D to switch colors.
 * Resize window to shuffle the colors of bricks.
 * @author qqqky
 *
 */


public class BrickWallEnhanced {


	public static void main (String[] args)
	   {
		
		JFrame frame = new JFrame("Brick wall");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		BrickWallEnhancedPanel panel = new BrickWallEnhancedPanel();
		
		frame.add(panel);
		frame.setSize(496,360);
		
		//System.out.println(frame.getWidth());
		frame.setVisible(true);
	   }
}
class BrickWallEnhancedPanel extends JPanel implements KeyListener
{
	private int startX=0;
	private int startY=0;
	private int [][] colorList;
	private int colorCounter = 0;

public BrickWallEnhancedPanel()
{
	colorList = new int[2][16];
	fillColors();
	addKeyListener(this);
	setFocusable(true);		//<--- panel must be set to fucusable to react to keystrokes
}

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	setBackground(Color.WHITE);
	
	
	for (int y=0;y<8;y++)	//y=number of columns of bricks (Y)
	{
		switch(y%2)
		{
		case 1: startX=startX-40;
		break;
		default: startX=0;
		}
		for (int x=0; x<7; x++)	//x=number of bricks in one line (X)
		{
			g.setColor(Color.BLACK);
			g.drawRect(startX+(80*x), startY+(40*y), 80, 40);		//1 brick (outline)
			g.setColor(new Color((int)(Math.random()*colorList[0][colorCounter]+colorList[1][colorCounter])));		
			g.fillRect((startX+2)+(80*x), (startY+2)+(40*y), 78, 38);		//inside of every brick is colored for fun
		}
		
	}		
		
	
}
public void keyPressed(KeyEvent e)
	{
	if(e.getKeyCode() == KeyEvent.VK_D)
		{
		System.out.println("Colors Changed");
		colorCounter++;
		if(colorCounter==16)
			colorCounter=0;
		repaint();
		}
		
		
	
	}
public void keyReleased(KeyEvent e) {}
public void keyTyped(KeyEvent e) {}

private void fillColors()
{
	colorList[0][0]=64;
	colorList[1][0]=8000000;
	colorList[0][1]=64;
	colorList[1][1]=16708000;
	colorList[0][2]=64;
	colorList[1][2]=9240576;
	colorList[0][3]=64;
	colorList[1][3]=32800;
	colorList[0][4]=64;
	colorList[1][4]=1804032;
	colorList[0][5]=64;
	colorList[1][5]=8388738;
	colorList[0][6]=64;
	colorList[1][6]=8454259;
	colorList[0][7]=64;
	colorList[1][7]=5505081;
	colorList[0][8]=64;
	colorList[1][8]=4000000;
	colorList[0][9]=64;
	colorList[1][9]=9793536;
	colorList[0][10]=40;
	colorList[1][10]=15067096;
	colorList[0][11]=20;
	colorList[1][11]=10000000;
	colorList[0][12]=64;
	colorList[1][12]=11000000;
	colorList[0][13]=64;
	colorList[1][13]=14421350;
	colorList[0][14]=64;
	colorList[1][14]=14000000;
	colorList[0][15]=64;
	colorList[1][15]=14421440;
}

}
