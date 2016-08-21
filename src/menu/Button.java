package menu;

import java.awt.Graphics2D;

public class Button 
{
	String texturename;
	int x, y, x2, y2;
	
	Button(int xloc, int yloc, int xloc2, int yloc2, String tn)
	{
		x = xloc;
		y = yloc;
		x2 = xloc2;
		y2 = yloc2;
		texturename = tn;
	}
	
	void Render(Graphics2D g)
	{
		g.drawRect(x, y, x2-x, y2-y);
	}
}
