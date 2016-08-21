package menu;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Menu 
{
	public Menu()
	{
		buttons = new ArrayList<Button>();
	}
	ArrayList<Button> buttons;

	public void Add(Button b)
	{
		buttons.add(b);
	}

	public void Render(Graphics2D g)
	{
		for (Button b : buttons)
		{
			b.Render(g, b.clicked);
		}
	}
	
	public void AddMouseListeners(JPanel jp)
	{
		for (Button b : buttons)
		{
			jp.addMouseListener(b);
		}
	}
}
