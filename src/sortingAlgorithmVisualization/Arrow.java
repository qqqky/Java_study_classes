package sortingAlgorithmVisualization;
import java.awt.Graphics;
import java.awt.Polygon;

/*
 * Arrow pointer used in visualizations
 */

public class Arrow
{
	private int[] arrowX, arrowY, arrowYdown;
	private Polygon arrowUp, arrowDown;
	
	public Arrow()
	{
		arrowX = new int[] {16,0,11,11,21,21,32};
		arrowY = new int[] {0,25,23,50,50,23,25};
		arrowYdown = new int[] {50,25,27,0,0,27,25};
		arrowUp = new Polygon (arrowX, arrowY, arrowX.length);
		arrowDown = new Polygon(arrowX,arrowYdown, arrowX.length);
	}
	public void drawArrowUp(int x, int y, Graphics g)
	{
		arrowUp.translate(x, y);
		g.fillPolygon(arrowUp);
		arrowUp.translate(-x,-y);
	}
	public void drawArrowDown(int x, int y, Graphics g)
	{
		arrowDown.translate(x, y);
		g.fillPolygon(arrowDown);
		arrowDown.translate(-x,-y);
	}
}