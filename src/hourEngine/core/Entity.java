package hourEngine.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class Entity extends SimulationBody
{
	public String texturename;
	public boolean visible = true;
	Image image;
	
	public Entity(String tn)
	{
		super();
		texturename = tn;
		image = Game.tl.textureFromName(tn);
	}
	
	@Override
	public void render(Graphics2D g, double scale, Color color) {
		if(visible)
		{
			// point radius
			
			// save the original transform
			AffineTransform ot = g.getTransform();
			
			// transform the coordinate system from world coordinates to local coordinates
			AffineTransform lt = new AffineTransform();
			lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
			lt.rotate(this.transform.getRotation());
			
			// apply the transform
			g.transform(lt);
			
			// loop over all the body fixtures for this body
			for (BodyFixture fixture : this.fixtures) {
				this.renderFixture(g, scale, fixture, color);
			}
			
			// set the original transform
			g.setTransform(ot);
		}
	}
	boolean right = true;
	@Override
	protected void renderFixture(Graphics2D g, double scale, BodyFixture fixture, Color color) {
		// do we need to render an image?
		AffineTransform oTransform = g.getTransform();
		// translate and rotate
		//this.getLinearVelocity().x;
		g.rotate(3.1415 - (this.getLinearVelocity().x / 50));
		if (Game.tl.textureFromName(texturename) != null) {
			// get the shape on the fixture
			Convex convex = fixture.getShape();
			// check the shape type
			if (convex instanceof Rectangle) {
				Rectangle r = (Rectangle)convex;
				Vector2 c = r.getCenter();
				double w = r.getWidth();
				double h = r.getHeight();
				g.drawImage(image, 
						(int)Math.ceil((c.x - w / 2.0) * scale),
						(int)Math.ceil((c.y - h / 2.0) * scale),
						(int)Math.ceil(w * scale),
						(int)Math.ceil(h * scale),
						null);
			} else if (convex instanceof Circle) {
				// cast the shape to get the radius
				Circle c = (Circle) convex;
				double r = c.getRadius();
				Vector2 cc = c.getCenter();
				int x = (int)Math.ceil((cc.x - r) * scale);
				int y = (int)Math.ceil((cc.y - r) * scale);
				int w = (int)Math.ceil(r * 2 * scale);
//				BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB); 
//			    Graphics2D bGr = image.createGraphics();
//			    bGr.drawImage(img, 0, 0, null);
//			    bGr.dispose();
					// lets us an image instead
				if(this.getLinearVelocity().x > 0.001)
				{
					right = true;
				}
				if(this.getLinearVelocity().x < -0.001)
				{
					right = false;
				}
				if(right)
				{
					g.drawImage(image, x+w, y, -w, w, null);
				}
				else
				{
					g.drawImage(image, x, y, w, w, null);
				}
				g.setColor(Color.red);
				g.drawOval(x, y, w, w);
			}
		} else {
			// default rendering
			//super.renderFixture(g, scale, fixture, color);
		}
		g.setTransform(oTransform);
	}
	
	public void setTextureName(String t)
	{
		texturename = t;
	}
}
