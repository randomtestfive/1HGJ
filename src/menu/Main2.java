package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.TextureLoader;

@SuppressWarnings("serial")
public class Main2 extends JPanel
{
	static Menu m;
	public Main2()
	{
		super();
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(400,400);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		ms.loop(g2d);
		//g2d.drawRect(0, 0, 100, 100);
		//m.Render(g2d);

	}
	
	public static TextureLoader tl;
	
	public static void addTextures()
	{
		tl.addMap("/textures/guy.gif", "player");
		tl.addMap("/textures/donut.png", "fuel");
		tl.addMap("/textures/start.png", "start");
		tl.addMap("/textures/startclick.png", "startc");
		tl.addMap("/textures/startmouse.png", "startm");
		tl.addMap("/textures/arrow.png", "arrow");
		tl.loadTextures();
	}
	
	static Menus ms;
	static Menu m2;
	
	public static void main(String[] args)
	{
		tl = new TextureLoader();
		addTextures();
		JFrame frame = new JFrame("yes");		
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ms = new Menus();
		m = new Menu()
		{
			Button b;
			@Override
			public void init()
			{
				s = "main";
				b = new Button(200 - ((tl.textureFromName("start").getWidth(null)*3)/2),100,tl.textureFromName("start").getWidth(null)*3,tl.textureFromName("start").getHeight(null)*3, tl.textureFromName("start"),tl.textureFromName("startc"),tl.textureFromName("startm"));
				Add(b);
				//Add(c);
			}

			@Override
			public void loop()
			{
				if(b.getClicked())
				{
					System.out.println("ye");
					s = "main2";
				}
			}
			String s = "main";

			@Override
			public String getTarget()
			{
				return s;
			}

			@Override
			public void renderBackground(Graphics2D g) {
				// TODO Auto-generated method stub
				
			}			
		};

		Menu m2 = new Menu()
		{
			Button b;
			Button c;
			@Override
			public void init()
			{
				s = "main2";
				b = new Button(200 - ((tl.textureFromName("start").getWidth(null)*3)/2),300,tl.textureFromName("start").getWidth(null)*3,tl.textureFromName("start").getHeight(null)*3, tl.textureFromName("start"),tl.textureFromName("startc"),tl.textureFromName("startm"));
				c = new Button(150,210,25,25);
				Add(b);
				Add(c);
			}

			@Override
			public void loop()
			{
				//System.out.println("ye2");
				if(b.getClicked())
				{
					s = "main";
					System.out.println("ye2");
				}
			}
			String s = "main2";

			@Override
			public String getTarget()
			{
				return s;
			}

			@Override
			public void renderBackground(Graphics2D g)
			{
				g.setColor(Color.black);
				g.drawLine(200, 0, 200, 400);
				g.drawLine(100, 0, 100, 400);
				g.drawLine(300, 0, 300, 400);
			}			
		};
		ms.addMenu("main", m);
		ms.addMenu("main2", m2);		
		Main2 test = new Main2();
		test.setSize(400, 400);
		ms.init();
		frame.add(test);
		ms.addMouseListeners(test);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setIconImage(tl.textureFromName("arrow"));
		frame.setVisible(true);

		while(true)
		{
			frame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
