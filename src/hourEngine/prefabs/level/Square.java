package hourEngine.prefabs.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Link;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import hourEngine.core.SimulationBody;

public class Square extends SimulationBody
{
	private int x;
	private int y;
	private BufferedImage b;
	public Square(BufferedImage bi, int x, int y)
	{
		b = bi;
		//addFixture(Geometry.createRectangle(1,1));
		Vector2[] points = new Vector2[4];
		points[0] = new Vector2(x,y);
		points[1] = new Vector2(x,y-1);
		points[2] = new Vector2(x+1,y-1);
		points[3] = new Vector2(x+1,y);
		this.x = x; 
		this.y = y;
		for(Link l : Geometry.createLinks(points, true))
		{
			this.addFixture(l);
		}
		setMass(MassType.INFINITE);
	}
	
	@Override
	public void render(Graphics2D g, double scale, Color color)
	{
		AffineTransform ot = g.getTransform();
			
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
		lt.rotate(this.transform.getRotation());

		g.transform(lt);
//		for (BodyFixture fixture : this.fixtures)
//		{
//			this.renderFixture(g, scale, fixture, color);
//		}
		g.setColor(Color.red);
		g.fillRect((int)Math.ceil((x * scale)), (int)Math.ceil(y * scale - scale), (int)Math.ceil(scale +1), (int)Math.ceil(scale +1));
		g.setTransform(ot);
	}

	@Override
	protected void renderFixture(Graphics2D g, double scale, BodyFixture fixture, Color color)
	{
		AffineTransform oTransform = g.getTransform();
		if (b != null)
		{
			Convex convex = fixture.getShape();
			// check the shape type
			if (convex instanceof Rectangle)
			{
				Rectangle r = (Rectangle)convex;
				Vector2 c = r.getCenter();
				double w = r.getWidth();
				double h = r.getHeight();
				g.drawImage(b, 
						(int)Math.ceil((c.x - w / 2.0) * scale),
						(int)Math.ceil((c.y - h / 2.0 + h) * scale),
						(int)Math.ceil(w * scale),
						(int)Math.ceil(-h * scale),
						null);
			}
		}
		g.setTransform(oTransform);
		//super.renderFixture(g, scale, fixture, color);
	}
}
