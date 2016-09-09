package hourEngine.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Button implements MouseListener, MouseMotionListener
{
	//String texturename;
	Image uc;
	Image c;
	Image m;
	int x, y, x2, y2;
	boolean clicked = false;
	boolean go = false;
	boolean mouse = false;
	
	public Button(int xloc, int yloc, int xloc2, int yloc2)
	{
		x = xloc;
		y = yloc;
		x2 = xloc2;
		y2 = yloc2;
	}
	
	public Button(int xloc, int yloc, int xloc2, int yloc2, Image unclick)
	{
		x = xloc;
		y = yloc;
		x2 = xloc2;
		y2 = yloc2;
		uc = unclick;
	}
	
	public Button(int xloc, int yloc, int xloc2, int yloc2, Image unclick, Image click)
	{
		x = xloc;
		y = yloc;
		x2 = xloc2;
		y2 = yloc2;
		uc = unclick;
		c = click;
	}
	
	public Button(int xloc, int yloc, int xloc2, int yloc2, Image unclick, Image click, Image mouse)
	{
		x = xloc;
		y = yloc;
		x2 = xloc2;
		y2 = yloc2;
		uc = unclick;
		c = click;
		m = mouse;
	}
	
	void Render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(x, y, x2, y2);
	}
	
	void Render(Graphics2D g, boolean click)
	{
		if(uc == null)
		{
			if(click)
			{
				g.setColor(Color.RED);
				g.fillRect(x, y, x2, y2);
			}
			g.setColor(Color.BLACK);
			g.drawRect(x, y, x2, y2);
		}
		else if(c == null)
		{
			g.drawImage(uc, x, y, x2, y2, null);
		}
		else if(c != null && uc != null && m == null)
		{
			if(click)
			{
				g.drawImage(c, x, y, x2, y2, null);
			}
			else
			{
				g.drawImage(uc, x, y, x2, y2, null);
			}
		}
		else if(c != null && uc != null && m != null)
		{
			if(click)
			{
				g.drawImage(c, x, y, x2, y2, null);
			}
			else
			{
				if(mouse)
				{
					g.drawImage(m, x, y, x2, y2, null);
				}
				else
				{
					g.drawImage(uc, x, y, x2, y2, null);
				}
			}
		}
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
		if(arg0.getX() > x && arg0.getX() < x + x2 && arg0.getY() > y && arg0.getY() < y + y2)
		go = true;
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(!(e.getX() > x && e.getX() < x + x2 && e.getY() > y && e.getY() < y + y2))
		{
			mouse = false;
			clicked = false;
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if(e.getX() > x && e.getX() < x + x2 && e.getY() > y && e.getY() < y + y2)
		{
			mouse = true;
		}
		else
		{
			mouse = false;
		}
	}
}
