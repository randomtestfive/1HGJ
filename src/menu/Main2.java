package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main2 extends JPanel
{
	static Button b;
	static Menu m;
	public Main2()
	{
		super();
		b = new Button(100,100,100,100, "thing");
		addMouseListener(b);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		//g2d.drawRect(0, 0, 100, 100);
		m.Render(g2d);
		if(b.getClicked())
		{
			System.out.println("ye");
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("yes");		
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m = new Menu();
		
		frame.setVisible(true);
		frame.add(new Main2());
		m.Add(b);
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
