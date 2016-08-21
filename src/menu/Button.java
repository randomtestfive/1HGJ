package menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements MouseListener
{
	String texturename;
	int x, y, x2, y2;
	boolean clicked = false;
	boolean go = false;
	
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
		g.setColor(Color.BLACK);
		g.drawRect(x, y, x2, y2);
	}
	
	void Render(Graphics2D g, boolean click)
	{
		
		if(click)
		{
			g.setColor(Color.RED);
			g.fillRect(x, y, x2, y2);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, x2, y2);
	}
	
	public boolean getClicked()
	{
		boolean f = go;
		go = false;
		return f;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getX() > x && arg0.getX() < x + x2 && arg0.getY() > y && arg0.getY() < y + y2)
		{
			clicked = true;
		}
		//go = false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		clicked = false;
		go = true;
		
	}
}
