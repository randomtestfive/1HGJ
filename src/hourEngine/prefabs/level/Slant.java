package hourEngine.prefabs.level;

import hourEngine.core.SimulationBody;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Vector2;

public class Slant extends SimulationBody
{
	private BufferedImage b;
	public Slant(BufferedImage bi)
	{
		b = bi;
		addFixture(Geometry.createRightTriangle(1, 1));
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
		for (BodyFixture fixture : this.fixtures)
		{
			this.renderFixture(g, scale, fixture, color);
		}
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
			if (convex instanceof Triangle)
			{
				Triangle r = (Triangle)convex;
				Vector2 c = r.getCenter();
				g.drawImage(b, 
						(int)Math.ceil((this.getTransform().getTranslationX()+0.75) * scale),
						(int)Math.ceil((this.getTransform().getTranslationY()+0.55) * scale),
						(int)Math.ceil(-1 * scale),
						(int)Math.ceil(-1 * scale),
						null);
			}
		}
		g.setTransform(oTransform);
	}
}
