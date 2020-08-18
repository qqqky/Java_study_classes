package polygonCar;
import java.awt.*;

public class PolygonCar {

	private int size=0;
	private int[] carFrameX, carFrameY, frontWindowX, frontWindowY, backWindowX, 
	backWindowY, tinyWindowX, tinyWindowY,carLamp1X, carLamp1Y, carLamp2X, 
	carLamp2Y, frontDoorX, frontDoorY, backDoorX, backDoorY, backLine1X,
	backLine1Y, backLine2X, backLine2Y, bonnetX, bonnetY;
	private int location = 220;
	private Polygon frame, frontWindow, backWindow, tinyWindow, lamp1, lamp2;
	
	
	public PolygonCar(int size)
	{
		
	}
	
	public void drawCar(Graphics g)
	{
		g.setColor(Color.BLUE);
		
		//***wheels
		g.drawOval(240, 220, 45, 45);// right
		g.fillOval(252, 232, 20, 20);
		g.drawOval(80, 220, 45, 45);//left
		g.fillOval(92, 232, 20, 20);
		
		carFrameX = new int[] {218, 128, 128, 126, 126, 121, 117, 112, 107, 
				107, 102, 102, 97, 97, 94, 94, 92, 88, 86, 84, 81, 
				79, 78, 77, 48, 38, 45, 40, 64, 93, 127, 158, 190, 
				225, 270, 311, 320, 358, 367, 356, 287, 287, 284, 
				280, 276, 271, 264, 257, 250, 247, 243, 241, 239, 238};
		carFrameY = new int[] {240, 240, 236, 229, 229, 224, 220, 217, 216, 
				216, 216, 216, 216, 216, 218, 218, 218, 220, 222, 224, 227, 
				231, 232, 240, 240, 216, 209, 194, 187, 180, 176, 160, 150, 
				145, 152, 172, 177, 175, 208, 232, 240, 233, 228, 
				224, 220, 217, 216, 217, 220, 222, 225, 228, 232, 240};
		//g.drawPolygon(carFrameX, carFrameY, carFrameX.length);
		
		
		carLamp1X = new int[] {45, 51, 70, 64};
		carLamp1Y= new int[] {197, 207, 197, 195};
		//g.fillPolygon(carLamp1X, carLamp1Y, 4);
		
		carLamp2X = new int[] {358, 347, 339, 352, 363};
		carLamp2Y = new int[] {186, 184, 187, 196, 197};
		//g.fillPolygon(carLamp2X, carLamp2Y, 5);
		
		frontWindowX = new int[] {215, 187, 168, 154, 137, 131, 213, 221};
		frontWindowY = new int[] {153, 159, 165, 171, 179, 184, 179, 153};
		//g.fillPolygon(frontWindowX, frontWindowY, 8);
		
		backWindowX = new int[] {229, 226, 273, 273, 259, 229};
		backWindowY = new int[] {152, 179, 175, 161, 157, 152};
		//g.fillPolygon(backWindowX, backWindowY, 6);
		
		tinyWindowX = new int[] {275, 275, 293, 275};
		tinyWindowY = new int[] {163, 174, 173, 162};
		//g.fillPolygon(tinyWindowX, tinyWindowY, 4);
		
		frontDoorX = new int[] {132, 123, 121, 125, 133, 135, 208, 219, 212};
		frontDoorY = new int[] {184, 193, 201, 216, 230, 236, 229, 180, 179};
		g.drawPolyline(frontDoorX, frontDoorY, 9);
		
		backDoorX = new int[] {208, 232, 259, 290, 295, 293, 293, 218 };
		backDoorY = new int[] {229, 226, 208, 192, 182, 176, 173, 180 };
		g.drawPolyline(backDoorX, backDoorY, 8);
		
		backLine1X = new int[] {338, 319, 292, 277};
		backLine1Y = new int[] {188, 200, 214, 218};
		g.drawPolyline(backLine1X,backLine1Y,4);
		
		backLine2X = new int[] {288, 353, 361};
		backLine2Y = new int[] {236, 224, 216};
		g.drawPolyline(backLine2X,backLine2Y,3);
		
		bonnetX = new int[] {43, 47, 60, 93, 119, 131, 125};
		bonnetY = new int[] {193, 195, 192, 185, 185, 181, 176 };
		g.drawPolyline(bonnetX,bonnetY,7);
		
		//front and back handles:
		g.fillRect(190, 190, 12, 4);
		g.fillRect(260, 185, 12, 4);
		
		frame = new Polygon(carFrameX, carFrameY, carFrameX.length);
		lamp1 = new Polygon(carLamp1X, carLamp1Y, carLamp1X.length);
		lamp2 = new Polygon(carLamp2X, carLamp2Y, carLamp2X.length);
		frontWindow = new Polygon(frontWindowX,frontWindowY,frontWindowX.length);
		backWindow = new Polygon(backWindowX,backWindowY,backWindowX.length);
		tinyWindow = new Polygon(tinyWindowX, tinyWindowY, tinyWindowX.length);
		
		g.drawPolygon(frame);
		g.fillPolygon(lamp1);
		g.fillPolygon(lamp2);
		g.fillPolygon(frontWindow);
		g.fillPolygon(backWindow);
		g.fillPolygon(tinyWindow);
		
		
		
	}
}
