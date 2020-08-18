package polygonCar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;//332

public class PolygonCarBouncy {

	private int size=45, carWidth = 332, carHeight = 126;
	private int[] carFrameX, carFrameY, frontWindowX, frontWindowY, backWindowX, 
	backWindowY, tinyWindowX, tinyWindowY,carLamp1X, carLamp1Y, carLamp2X, 
	carLamp2Y, frontDoorX, frontDoorY, backDoorX, backDoorY, backLine1X,
	backLine1Y, backLine2X, backLine2Y, bonnetX, bonnetY;
	private int[] frontDoorXtemp, frontDoorYtemp, backDoorXtemp, backDoorYtemp, backLine1Xtemp,
	backLine1Ytemp, backLine2Xtemp, backLine2Ytemp, bonnetXtemp, bonnetYtemp;
	private int spawnX, spawnY;
	private Polygon frame, frontWindow, backWindow, tinyWindow, lamp1, lamp2,
	frametemp, lamp1temp, lamp2temp, frontWindowtemp, backWindowtemp, tinyWindowtemp;
	
	
	public PolygonCarBouncy()
	{
		// Car coords translated to 0 (the bounding rect starts at 0,0... carWidth= 332, carHeight=126):
			 carFrameX = new int[]{150, 240, 240, 242, 242, 247, 251, 256, 261, 261, 266, 266, 271, 271, 274, 274, 276, 280, 282, 284, 287, 289, 290, 291, 320, 330, 323, 328, 304, 275, 241, 210, 178, 143, 98, 57, 48, 10, 1, 12, 81, 81, 84, 88, 92, 97, 104, 111, 118, 121, 125, 127, 129, 130};
			 carFrameY = new int[]{98, 98, 94, 87, 87, 82, 78, 75, 74, 74, 74, 74, 74, 74, 76, 76, 76, 78, 80, 82, 85, 89, 90, 98, 98, 74, 67, 52, 45, 38, 34, 18, 8, 3, 10, 30, 35, 33, 66, 90, 98, 91, 86, 82, 78, 75, 74, 75, 78, 80, 83, 86, 90, 98};

			 carLamp1X = new int[]{323, 317, 298, 304};
			 carLamp1Y = new int[]{55, 65, 55, 53};
			 
			 carLamp2X = new int[]{10, 21, 29, 16, 5};
			 carLamp2Y = new int[]{44, 42, 45, 54, 55};
			 
			 frontWindowX = new int[]{153, 181, 200, 214, 231, 237, 155, 147};
			 frontWindowY = new int[]{11, 17, 23, 29, 37, 42, 37, 11};
			 
			 backWindowX = new int[]{139, 142, 95, 95, 109, 139};
			 backWindowY = new int[]{10, 37, 33, 19, 15, 10};
			 
			 tinyWindowX = new int[]{93, 93, 75, 93};
			 tinyWindowY = new int[]{21, 33, 31, 20};
			 
			 frontDoorX = new int[]{236, 245, 247, 243, 235, 233, 160, 149, 156};
			 frontDoorY = new int[]{42, 51, 59, 74, 88, 94, 87, 38, 37};
	
			 backDoorX = new int[]{160, 136, 109, 78, 73, 75, 75, 150};
			 backDoorY = new int[]{87, 84, 66, 50, 40, 34, 31, 38};
	
			 backLine1X = new int[]{30, 49, 76, 91};
			 backLine1Y = new int[]{46, 58, 72, 76};
	
			 backLine2X = new int[]{80, 15, 7};
			 backLine2Y = new int[]{94, 82, 74};
			 
			 bonnetX = new int[]{325, 321, 308, 275, 249, 237, 243};
			 bonnetY = new int[]{51, 53, 50, 43, 43, 39, 34};
			
			 frame = new Polygon(carFrameX, carFrameY, carFrameX.length);
			 lamp1 = new Polygon(carLamp1X, carLamp1Y, carLamp1X.length);
			 lamp2 = new Polygon(carLamp2X, carLamp2Y, carLamp2X.length);
			 frontWindow = new Polygon(frontWindowX,frontWindowY,frontWindowX.length);
			 backWindow = new Polygon(backWindowX,backWindowY,backWindowX.length);
			 tinyWindow = new Polygon(tinyWindowX, tinyWindowY, tinyWindowX.length);
	}
	
	public void drawCar(Graphics g) // draws car at 0,0
	{
			g.setColor(Color.BLUE);
		
		
			 g.drawPolyline(frontDoorX, frontDoorY, frontDoorX.length); 
			 g.drawPolyline(backDoorX, backDoorY, backDoorX.length);
			 g.drawPolyline(backLine1X, backLine1Y, backLine1X.length);		
			 g.drawPolyline(backLine2X, backLine2Y, backLine2X.length);
			 g.drawPolyline(bonnetX, bonnetY, bonnetX.length);
			 
			//wheels
			 g.drawOval(83, 78, 45, 45);// left
			 g.fillOval(95, 90, 20, 20);
			 g.drawOval(243, 78, 45, 45);//right
			 g.fillOval(255, 90, 20, 20);

			//front and back handles
			 g.fillRect(165, 48, 12, 4); //back
			 g.fillRect(93, 43, 12, 4); //front
		
		
		
			 g.drawPolygon(frame);
			 g.fillPolygon(lamp1);
			 g.fillPolygon(lamp2);
			 g.fillPolygon(frontWindow);
			 g.fillPolygon(backWindow);
			 g.fillPolygon(tinyWindow);
		
		
		
		
	}
	public void spawnCar (int spawnX, int spawnY, Graphics g) //draws car at specified coords
	{
		this.spawnX=spawnX;
		this.spawnY=spawnY;
		
		//polylines are translated by desired x/y and stored in temp arrays
		
		frontDoorXtemp = new int[frontDoorX.length];
		frontDoorYtemp = new int[frontDoorY.length];
		backDoorXtemp = new int[backDoorX.length];
		backDoorYtemp = new int[backDoorY.length];
		backLine1Xtemp = new int[backLine1X.length];
		backLine1Ytemp = new int[backLine1Y.length];
		backLine2Xtemp = new int[backLine2X.length];
		backLine2Ytemp = new int[backLine2Y.length];
		bonnetXtemp = new int[bonnetX.length];
		bonnetYtemp = new int[bonnetY.length];
		
		
		for(int x=0;x<frontDoorX.length;x++)
		{
			frontDoorXtemp[x]=frontDoorX[x]+spawnX;
			frontDoorYtemp[x]=frontDoorY[x]+spawnY;
		}
		
		for(int x=0;x<backDoorX.length;x++)
		{
			backDoorXtemp[x]=backDoorX[x]+spawnX;
			backDoorYtemp[x]=backDoorY[x]+spawnY;
		}
		for(int x=0;x<backLine1X.length;x++)
		{
			backLine1Xtemp[x]=backLine1X[x]+spawnX;
			backLine1Ytemp[x]=backLine1Y[x]+spawnY;
		}
		for(int x=0;x<backLine2X.length;x++)
		{
			backLine2Xtemp[x]=backLine2X[x]+spawnX;
			backLine2Ytemp[x]=backLine2Y[x]+spawnY;
		}
		for(int x=0;x<bonnetX.length;x++)
		{
			bonnetXtemp[x]=bonnetX[x]+spawnX;
			bonnetYtemp[x]=bonnetY[x]+spawnY;
		}
		
		//next, polygons are translated by x/y
		frame.translate(spawnX, spawnY);
		lamp1.translate(spawnX, spawnY);
		lamp2.translate(spawnX, spawnY);
		frontWindow.translate(spawnX, spawnY);
		backWindow.translate(spawnX, spawnY);
		tinyWindow.translate(spawnX, spawnY);
		
		//all translated polygons are drawn
		g.setColor(Color.BLUE);
		g.drawPolygon(frame);
		g.fillPolygon(lamp1);
		g.fillPolygon(lamp2);
		g.fillPolygon(frontWindow);
		g.fillPolygon(backWindow);
		g.fillPolygon(tinyWindow);
		
		//all translated polylines are drawn
		 g.drawPolyline(frontDoorXtemp, frontDoorYtemp, frontDoorXtemp.length); 
		 g.drawPolyline(backDoorXtemp, backDoorYtemp, backDoorXtemp.length);
		 g.drawPolyline(backLine1Xtemp, backLine1Ytemp, backLine1Xtemp.length);		
		 g.drawPolyline(backLine2Xtemp, backLine2Ytemp, backLine2Xtemp.length);
		 g.drawPolyline(bonnetXtemp, bonnetYtemp, bonnetXtemp.length);
		
		 //wheels are drawn
		 g.drawOval(83+spawnX, 78+spawnY, 45, 45);// left
		 g.fillOval(95+spawnX, 90+spawnY, 20, 20);
		 g.drawOval(243+spawnX, 78+spawnY, 45, 45);//right
		 g.fillOval(255+spawnX, 90+spawnY, 20, 20);

		//front and back handles are drawn
		 g.fillRect(165+spawnX, 48+spawnY, 12, 4);
		 g.fillRect(93+spawnX, 43+spawnY, 12, 4);
		 
 
		 //finally, all polygons are translated back:
		 	frame.translate(-spawnX, -spawnY);
			lamp1.translate(-spawnX, -spawnY);
			lamp2.translate(-spawnX, -spawnY);
			frontWindow.translate(-spawnX, -spawnY);
			backWindow.translate(-spawnX, -spawnY);
			tinyWindow.translate(-spawnX, -spawnY);
			
		//no need to reset the polylines, as they are stored in another array
		
	}
	public void flipHorizontally(Graphics g)
	{
		//*** firstly, mirror flips all polygons in x axis
		System.out.print("carFrameXnew = new int[]{");
		for (int x=0; x<carFrameX.length; x++)
		{
			carFrameX[x]=(carWidth/2)+((carWidth/2)-carFrameX[x]);
			System.out.print(carFrameX[x] + ", ");
		}
		System.out.println();
		System.out.print("carLamp1Xnew = new int[]{");
		for (int x=0; x<carLamp1X.length; x++)
		{
			carLamp1X[x]=(carWidth/2)+((carWidth/2)-carLamp1X[x]);
			System.out.print(carLamp1X[x] + ", ");
		}
		
		System.out.println();
		System.out.print("carLamp2Xnew = new int[]{");
		for (int x=0; x<carLamp2X.length; x++)
		{
			carLamp2X[x]=(carWidth/2)+((carWidth/2)-carLamp2X[x]);
			System.out.print(carLamp2X[x] + ", ");
		}
		
		System.out.println();
		System.out.print("frontWindowXnew = new int[]{");
		for (int x=0; x<frontWindowX.length; x++)
		{
			frontWindowX[x]=(carWidth/2)+((carWidth/2)-frontWindowX[x]);
			System.out.print(frontWindowX[x] + ", ");
		}
		
		System.out.println();
		System.out.print("backWindowXnew = new int[]{");
		for (int x=0; x<backWindowX.length; x++)
		{
			backWindowX[x]=(carWidth/2)+((carWidth/2)-backWindowX[x]);
			System.out.print(backWindowX[x] + ", ");
		}
		
		System.out.println();
		System.out.print("tinyWindowXnew = new int[]{");
		for (int x=0; x<tinyWindowX.length; x++)
		{
			tinyWindowX[x]=(carWidth/2)+((carWidth/2)-tinyWindowX[x]);
			System.out.print(tinyWindowX[x] + ", ");
		}
		//**************************************************
		frame = new Polygon(carFrameX, carFrameY, carFrameX.length);
		 lamp1 = new Polygon(carLamp1X, carLamp1Y, carLamp1X.length);
		 lamp2 = new Polygon(carLamp2X, carLamp2Y, carLamp2X.length);
		 frontWindow = new Polygon(frontWindowX,frontWindowY,frontWindowX.length);
		 backWindow = new Polygon(backWindowX,backWindowY,backWindowX.length);
		 tinyWindow = new Polygon(tinyWindowX, tinyWindowY, tinyWindowX.length);
		
		//*** next flips all polylines in x axis
		System.out.println();
		System.out.print("frontDoorXnew = new int[]{");
		for (int x=0; x<frontDoorX.length; x++)
		{
			frontDoorX[x]=(carWidth/2)+((carWidth/2)-frontDoorX[x]);
			System.out.print(frontDoorX[x] + ", ");
		}
		
		System.out.println();
		System.out.print("backDoorXnew = new int[]{");
		for (int x=0; x<backDoorX.length; x++)
		{
			backDoorX[x]=(carWidth/2)+((carWidth/2)-backDoorX[x]);
			System.out.print(backDoorX[x] + ", ");
		}
		
		System.out.println();
		System.out.print("backLine1Xnew = new int[]{");
		for (int x=0; x<backLine1X.length; x++)
		{
			backLine1X[x]=(carWidth/2)+((carWidth/2)-backLine1X[x]);
			System.out.print(backLine1X[x] + ", ");
		}
		
		System.out.println();
		System.out.print("backLine2Xnew = new int[]{");
		for (int x=0; x<backLine2X.length; x++)
		{
			backLine2X[x]=(carWidth/2)+((carWidth/2)-backLine2X[x]);
			System.out.print(backLine2X[x] + ", ");
		}
		
		System.out.println();
		System.out.print("bonnetXnew = new int[]{");
		for (int x=0; x<bonnetX.length; x++)
		{
			bonnetX[x]=(carWidth/2)+((carWidth/2)-bonnetX[x]);
			System.out.print(bonnetX[x] + ", ");
		}
		
		g.setColor(Color.BLUE);
		System.out.println();
		// finally flips wheels and door handles in x axis
		//wheels
		 g.drawOval(((carWidth/2)+(carWidth/2-83))-size, 78, size, size);// right
		 g.fillOval(((carWidth/2)+(carWidth/2-95))-size*4/9, 90, size*4/9, size*4/9);
		 g.drawOval(((carWidth/2)+(carWidth/2-243))-size, 78, size, size);//left
		 g.fillOval(((carWidth/2)+(carWidth/2-255))-size*4/9, 90, size*4/9, size*4/9);

		//front and back handles
		 g.fillRect(((carWidth/2)+(carWidth/2-165))-size/3, 48, size/3, 4);
		 System.out.println(((carWidth/2)+(carWidth/2-224))-size/3);
		 g.fillRect(((carWidth/2)+(carWidth/2-93))-size/3, 43, size/3, 4);
		 
		//all translated polygons are drawn
			
			g.drawPolygon(carFrameX, carFrameY, carFrameX.length);
			g.fillPolygon(carLamp1X, carLamp1Y, carLamp1X.length);
			g.fillPolygon(carLamp2X, carLamp2Y, carLamp2X.length);
			g.fillPolygon(frontWindowX, frontWindowY, frontWindowX.length);
			g.fillPolygon(backWindowX, backWindowY, backWindowX.length);
			g.fillPolygon(tinyWindowX, tinyWindowY, tinyWindowX.length);
			
			//all translated polylines are drawn
			 g.drawPolyline(frontDoorX, frontDoorY, frontDoorX.length); 
			 g.drawPolyline(backDoorX, backDoorY, backDoorX.length);
			 g.drawPolyline(backLine1X, backLine1Y, backLine1X.length);		
			 g.drawPolyline(backLine2X, backLine2Y, backLine2X.length);
			 g.drawPolyline(bonnetX, bonnetY, bonnetX.length);
		
	}
}
/****************************************************************
 Car coords translated to 0 (the bounding rect is at 0,0):
 carFrameX = new int[]{182, 92, 92, 90, 90, 85, 81, 76, 71, 71, 66, 66, 61, 61, 58, 58, 56, 52, 50, 48, 45, 43, 42, 41, 12, 2, 9, 4, 28, 57, 91, 122, 154, 189, 234, 275, 284, 322, 331, 320, 251, 251, 248, 244, 240, 235, 228, 221, 214, 211, 207, 205, 203, 202);
 carFrameY = new int[]{98, 98, 94, 87, 87, 82, 78, 75, 74, 74, 74, 74, 74, 74, 76, 76, 76, 78, 80, 82, 85, 89, 90, 98, 98, 74, 67, 52, 45, 38, 34, 18, 8, 3, 10, 30, 35, 33, 66, 90, 98, 91, 86, 82, 78, 75, 74, 75, 78, 80, 83, 86, 90, 98);

 carLamp1X = new int[]{9, 15, 34, 28};
 carLamp1Y = new int[]{55, 65, 55, 53};
 
 carLamp2X = new int[]{322, 311, 303, 316, 327};
 carLamp2Y = new int[]{44, 42, 45, 54, 55};
 
 frontWindowX = new int[]{179, 151, 132, 118, 101, 95, 177, 185};
 frontWindowY = new int[]{11, 17, 23, 29, 37, 42, 37, 11};
 
 backWindowX = new int[]{193, 190, 237, 237, 223, 193};
 backWindowY = new int[]{10, 37, 33, 19, 15, 10};
 
 tinyWindowX = new int[]{239, 239, 257, 239};
 tinyWindowY = new int[]{21, 32, 31, 20};
 
 frontDoorX = new int[]{96, 87, 85, 89, 97, 99, 172, 183, 176};
 frontDoorY = new int[]{42, 51, 59, 74, 88, 94, 87, 38, 37};
 
 backDoorX = new int[]{172, 196, 223, 254, 259, 257, 257, 182};
 backDoorY = new int[]{87, 84, 66, 50, 40, 34, 31, 38};
 
 backLine1X = new int[]{302, 283, 256, 241};
 backLine1Y = new int[]{46, 58, 72, 76};
 
 backLine2X = new int[]{252, 317, 325};
 backLine2Y = new int[]{94, 82, 74};
 
 bonnetX = new int[]{7, 11, 24, 57, 83, 95, 89};
 bonnetY = new int[]{51, 53, 50, 43, 43, 39, 34};
 
//wheels
 g.drawOval(83, 78, 45, 45);// left
 g.fillOval(95, 90, 20, 20);
 g.drawOval(243, 78, 45, 45);//right
 g.fillOval(255, 90, 20, 20);

//front and back handles
 g.fillRect(165, 48, 12, 4); //back
 g.fillRect(93, 43, 12, 4); //front
 
*/
