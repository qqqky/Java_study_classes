package movingObjects;
/*PP 8.25 Design and implement a class that represents a spaceship, which
can be drawn (side view) in any particular location. Create a
program that displays the spaceship so that it follows the movement of the mouse. When the mouse button is pressed down,
have a laser beam shoot out of the front of the spaceship (one
continuous beam, not a moving projectile) until the mouse button is released.*/
import java.awt.*;

public class Spaceship {
private int[]shipBodyX, shipBodyY, shipWindow1X, shipWindow1Y, shipWindow2X, shipWindow2Y,
shipFlameX, shipFlameY;
private Polygon body, window1, window2, flame;
private Polygon[] spaceShip;
private int originalX=85, originalY=10, resetX, resetY, moveX, moveY;
	
	public Spaceship()
	{
		
		shipBodyX=new int[]{85, 73, 68, 63, 61, 60, 58, 58, 59, 60, 
				63, 67, 71, 81, 90, 98, 105, 111, 114, 115, 116, 115, 
				113, 111, 108, 107, 104, 102, 97, 92};
			
		shipBodyY=new int[]{10, 24, 34, 49, 57, 65, 75, 85, 97, 107, 
				110, 115, 120, 119, 119, 119, 116, 105, 100, 92, 83, 72, 
				60, 52, 45, 38, 33, 27, 21, 15};
		
		shipWindow1X=new int[] {85, 83, 81, 79, 78, 79, 80, 83, 86, 89, 
				92, 95, 93, 91, 88};
		shipWindow1Y= new int[] {40, 41, 42, 44, 49, 52, 54, 55, 56, 54, 
				52, 48, 44, 42, 40};
		shipWindow2X=new int[] {85, 83, 81, 79, 78, 79, 80, 83, 86, 89, 
				92, 95, 93, 91, 88};
		shipWindow2Y= new int[] {67, 68, 69, 71, 76, 79, 81, 82, 83, 81, 
				79, 75, 71, 69, 67};
		shipFlameX= new int[] {71, 60, 79, 87, 98, 108, 99};
		shipFlameY= new int[] {118, 143, 131, 151, 132, 138, 118};
		
		flame = new Polygon(shipFlameX, shipFlameY, shipFlameX.length);
		body = new Polygon(shipBodyX, shipBodyY, shipBodyX.length);	
		window1 = new Polygon(shipWindow1X, shipWindow1Y, shipWindow1X.length);
		window2 = new Polygon(shipWindow2X, shipWindow2Y, shipWindow2X.length);
		
		
		spaceShip = new Polygon[]{flame, body, window1, window2};
	}
	
	public void drawShip(Graphics g)
	{
			g.setColor(Color.RED);
			g.fillPolygon(spaceShip[0]);
			g.setColor(Color.DARK_GRAY);
			g.fillPolygon(spaceShip[1]);
			g.setColor(Color.CYAN);
			g.fillPolygon(spaceShip[2]);
			g.fillPolygon(spaceShip[3]);
			resetShip();
	}
	public void moveTo(int x, int y) //default x is 85 , y=10;
	{
		moveX=x-originalX;
		moveY=y-originalY;
		
		for(int a=0; a<spaceShip.length;a++)
			spaceShip[a].translate(moveX,moveY);
		
		resetX= -moveX;
		resetY= -moveY;
		
	}
	public void resetShip() // arrays have to be reset to original by translating them back
	{						// otherwise the effects will be additive
		for(int a=0; a<spaceShip.length;a++)
			spaceShip[a].translate(resetX,resetY);
	}
	public void shootLaser(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(moveX+originalX-2,moveY+originalY-198, 5, 200);
	}
	public void resetLaser()
	{
		
	}
}
