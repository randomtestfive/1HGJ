package menu;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class Menu 
{
	abstract public String getTarget();
	
	public Menu()
	{
		buttons = new ArrayList<Button>();
	}
	ArrayList<Button> buttons;

	public void Add(Button b)
	{
		buttons.add(b);
	}
	
	public void reset()
	{
		for (Button b : buttons)
		{
			b.go = false;
			b.clicked = false;
		}
	}

	public void Render(Graphics2D g)
	{
		for (Button b : buttons)
		{
			b.Render(g, b.clicked);
		}
	}
	
	public abstract void init();
	public abstract void loop();
	public abstract void renderBackground(Graphics2D g);
	
	public void AddMouseListeners(JPanel jp)
	{
		for (Button b : buttons)
		{
			jp.addMouseListener(b);
			jp.addMouseMotionListener(b);
		}
	}
}
