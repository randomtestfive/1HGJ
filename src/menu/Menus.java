package menu;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class Menus
{
	private Map<String, Menu> menus;
	private String menu = "main";
	public Menus()
	{
		menus = new HashMap<String, Menu>();
	}
	
	public void addMenu(String name, Menu m)
	{
		menus.put(name, m);
	}
	
	public void init()
	{
		menus.get(menu).init();
	}
	JPanel hold;
	public void addMouseListeners(JPanel jp)
	{
		hold = jp;
		for (Entry<String, Menu> m : menus.entrySet())
		{
			m.getValue().AddMouseListeners(jp);
		}
	}
	
	public void loop(Graphics2D g2d)
	{
		menus.get(menu).loop();
		menus.get(menu).Render(g2d);
		if(!menu.equals(menus.get(menu).getTarget()))
		{
			menu = menus.get(menu).getTarget();
			menus.get(menu).reset();
			menus.get(menu).init();
			menus.get(menu).AddMouseListeners(hold);
		}
	}
}
