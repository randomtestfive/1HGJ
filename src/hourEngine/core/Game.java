package hourEngine.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Force;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

import hourEngine.prefabs.Wall;
import hourEngine.prefabs.level.Slant;
import hourEngine.prefabs.level.Square;

public class Game extends SimulationFrame {
	/** The serial version id */
	private static final long serialVersionUID = -805418642620588619L;
	
	public int count;
	public int direction = 0;
	
	public final class Contacts extends ContactAdapter
	{
		
		
		public Contacts()
		{
			super();
			count = 10;
			System.out.println("fejs");
		}
		
		public void down()
		{
			count--;
			//System.out.println(count);
		}

		@Override
		public void sensed(ContactPoint point) {
			// TODO Auto-generated method stub
			
			if(point.getBody1() instanceof Entity)
			{
				//System.out.println("bnrioe");
				Entity e = (Entity)point.getBody1();
				if(e.texturename.equals("fuel"))
				{
					System.out.println(count);
					if(point.getBody2() instanceof Entity)
					{
						Entity e2 = (Entity)point.getBody2();
						if(e2.texturename.equals("player") && count <= 0)
						{
							score++;
						}
					}
					count = 50;
					world.removeBody(e);
				}
			} 
			if(point.getBody2() instanceof Entity)
			{
				//System.out.println("bnrioe");
				Entity e = (Entity)point.getBody2();
				if(e.texturename.equals("fuel"))
				{
					System.out.println(count);
					if(point.getBody1() instanceof Entity)
					{
						Entity e2 = (Entity)point.getBody1();
						if(e2.texturename.equals("player") && count <= 0)
						{
							score++;
						}
					}
					count = 50;
					world.removeBody(e);
				}
			}
		}
	}
	
	public static TextureLoader tl;
	
	/** The controller body */
	//private SimulationBody controller;
	static Listeners l = new Listeners();
	Listeners.Keys k = l.new Keys();
	public static Listeners.Mouse m = l.new Mouse();
	Contacts c = new Contacts();
	Entity body2;
	Entity fuel;
	int score = 0;
	
	/**
	 * Default constructor.
	 */
	public Game()
	{
		super("Doughnut Grab", 45.0);
		//this.setIconImage(tl.textureFromName("player"));

		this.canvas.addKeyListener(k);
		this.canvas.addMouseListener(m);
		this.canvas.addMouseMotionListener(m);
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld()
	{
		this.setLocationRelativeTo(null);
	    this.world.setGravity(new Vector2(0, -5));
	    if(c == null)
	    {
	    	System.out.println("frwejga");
	    	c = new Contacts();
	    }
	    world.addListener(c);
	    lev.addWorld(world);
	    body2 = new Entity("player");
	    //BodyFixture f = new BodyFixture(Geometry.createRectangle(2,2));
	    BodyFixture f = new BodyFixture(Geometry.createCircle(0.45));
	    f.setFriction(0);
	    f.setRestitution(0);
	    body2.setTextureName("player");
	    body2.addFixture(f);
	    body2.translate(3, 2);
	    body2.setLinearVelocity(new Vector2(0.0, 0.0));
	    body2.setAngularVelocity(0.0);
	    body2.setMass(MassType.FIXED_ANGULAR_VELOCITY);
	    body2.setAutoSleepingEnabled(false);
	    world.addBody(body2);
	    
	    fuel = new Entity("fuel");
	    //BodyFixture f = new BodyFixture(Geometry.createRectangle(2,2));
	    BodyFixture f2 = new BodyFixture(Geometry.createSquare(1));
	    f2.setFriction(0);
	    f2.setRestitution(0);
	    f2.setSensor(true);
	    fuel.setTextureName("fuel");
	    fuel.addFixture(f2);
	    fuel.translate(-3, 0);
	    fuel.setLinearVelocity(new Vector2(0.0, 0.0));
	    fuel.setAngularVelocity(0.0);
	    fuel.setMass(new Mass(new Vector2(0,0), 0, 0.0000001));
	    fuel.setAutoSleepingEnabled(false);
	    world.addBody(fuel);
	    //Square test = new Square(lev.tilesets.get(1).tiles.get(0));
	    Square sq = new Square(lev.tilesets.get(1).tiles.get(0), 0, 0);
	    Slant test2 = new Slant(lev.tilesets.get(1).tiles.get(1));
	    sq.translate(-5, 0);
	    test2.translate(0,-2);
	    //world.addBody(test2);
	    //world.addBody(sq);
	    //world.addBody(new Square(lev.tilesets.get(1).tiles.get(0)));
//		SimulationBody wallb = new SimulationBody(Color.green);
//		wallb.addFixture(Geometry.createRectangle(30, 0.2));
//		wallb.translate(0, -6.6);
//		wallb.setMass(MassType.INFINITE);
//		world.addBody(wallb);
	    Wall wallb = new Wall(0, -6.6, 30, 0.2, Color.green);
	    world.addBody(wallb);
		
//		Wall wallr = new Wall(5, 0, 0.2, 10);
//		world.addBody(wallr);
//		
//		Wall plat = new Wall(-3, -3, 5, 0.2);
//		world.addBody(plat);
//		
//		Wall plat2 = new Wall(4, 2, 2, 0.2);
//		world.addBody(plat2);
//		
//		Wall owalll = new Wall(-9, 0, 0.2, 40);
//		world.addBody(owalll);
		
//		Wall owallr = new Wall(9, 0, 0.2, 40);
//		world.addBody(owallr);
	}
	
	@Override
	protected void update(Graphics2D g, double elapsedTime)
	{
		c.down();
		//System.out.println(c.count);
		if(count > 0)
		{
			fuel.visible = false;
		}
		else
		{
			fuel.visible = true;
		}
		//System.out.println(score);
		if(!world.containsBody(fuel))
		{
			Transform t = new Transform();
			t.setTranslation((Math.random() - 0.5) * 14, (Math.random() - 0.5) * 10);
			fuel.setTransform(t);
			world.addBody(fuel);
		}
		
		body2.setAngularVelocity(0);
		body2.getTransform().setRotation(0);
		//System.out.println(body2.getContacts(false));
		List<ContactPoint> l = body2.getContacts(false);
		boolean grounded = false;
		for(ContactPoint b : l)
		{
			if(b.getPoint().y - body2.getTransform().getTranslationY() < 0)
			{
				grounded = true;
			}
		}
		System.out.println(grounded);
		if(grounded && (k.up))
		{
			body2.setLinearVelocity(new Vector2(body2.getLinearVelocity().x, 2500 * elapsedTime));
		}
		else if(k.up)
		{
			body2.applyForce(new Force(new Vector2(0, 50 * elapsedTime)));
		}
		if(k.left && body2.getChangeInPosition().x > -0.2)
		{
			body2.setLinearVelocity(new Vector2(-5, body2.getLinearVelocity().y));
		}
		if(k.right && body2.getChangeInPosition().x < 0.2)
		{
			body2.setLinearVelocity(new Vector2(5, body2.getLinearVelocity().y));
			System.out.println("right");
		}
		if(!(k.right) && !(k.left))
		{
			if(Math.abs(body2.getLinearVelocity().x) > 0.005)
			{
				body2.setLinearVelocity((body2.getLinearVelocity().x/(1.1)),body2.getLinearVelocity().y);
			}
			else
			{
				body2.setLinearVelocity(0,body2.getLinearVelocity().y);
			}
		}
		super.update(g, elapsedTime);
	}

	double camera = 0;

	@Override
	protected void render(Graphics2D g, double elapsedTime)
	{
		camera = body2.getTransform().getTranslationX() * 45;
		
		final int w = this.canvas.getWidth();
		final int h = this.canvas.getHeight();
		
		g.setColor(new Color(135, 206, 250));
		g.fillRect(-w / 2, -h / 2, w, h);
		

		g.translate(-camera, -body2.getTransform().getTranslationY() * 45);
		super.render(g, elapsedTime);
		g.translate(camera, body2.getTransform().getTranslationY() * 45);
		g.scale(1, -1);
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, -400, -290);
		g.drawString("Use SPACE to move!", -400, -275);
		g.scale(1,  -1);
		g.rotate(Math.toRadians(-k.count*90), 0, 254);
		g.drawImage(tl.textureFromName("arrow"), -16, 270, 32, -32, null);
	}
	
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
	static Level lev;
	/**
	 * Entry point for the example application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) 
	{
		tl = new TextureLoader();
		addTextures();
		Scanner s = new Scanner(System.in);
		String test = tl.getClass().getClassLoader().getResource("wow2/level.hl").getPath();
		System.out.println(test);
		lev = Level.readFromFile(new File(test));
		s.close();
		Game simulation = new Game();
		//simulation.setIconImage(tl.textureFromName("player"));
		simulation.run();
	}
}
