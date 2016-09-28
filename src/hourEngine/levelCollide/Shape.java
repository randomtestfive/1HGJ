package hourEngine.levelCollide;

public class Shape
{
	private ShapeEnum shape;
	private int rotation;
	
	public Shape(ShapeEnum s, int r)
	{
		shape = s;
		rotation = r;
	}
	
	public ShapeEnum getShape()
	{
		return shape;
	}
	
	public int getRotation()
	{
		return rotation;
	}
}
