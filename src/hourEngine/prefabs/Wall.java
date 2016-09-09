package hourEngine.prefabs;

import java.awt.Color;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import hourEngine.core.SimulationBody;

public class Wall extends SimulationBody
{
	public Wall(double x, double y, double width, double height, Color color)
	{
		super(color);
		addFixture(Geometry.createRectangle(width, height));
		translate(x, y);
		setMass(MassType.INFINITE);
	}
	
	public Wall(double x, double y, double width, double height)
	{
		super();
		addFixture(Geometry.createRectangle(width, height));
		translate(x, y);
		setMass(MassType.INFINITE);
	}
}
