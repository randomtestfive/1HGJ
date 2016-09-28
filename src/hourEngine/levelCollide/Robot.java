package hourEngine.levelCollide;

public class Robot
{
	private int x;
	private int y;
	public enum Direction 
	{ 
		North
		{
			@Override
			public Direction last()
			{
				return values()[3];
			};
		},
		East, South, 
		West 
		{
			@Override
			public Direction next()
			{
				return values()[0];
			};
		};
		
		public Direction next()
		{
			return values()[ordinal() + 1];
		}
		public Direction last()
		{
			return values()[ordinal() - 1];
		}
	}
	private Direction dir;
	
	public Robot()
	{
		x = 0;
		y = 0;
		dir = Direction.East;
	}
	
	public Robot(int x, int y)
	{
		this.x = x;
		this.y = y;
		dir = Direction.East;
	}
	
	public void moveDir(Direction d)
	{
		dir = d;
		move();
	}
	
	public void turnRight()
	{
		dir = dir.next();
	}
	
	public void turnLeft()
	{
		dir = dir.last();
	}
	
	public void move()
	{
		if(dir.equals(Direction.North))
		{
			if(y > 0)
			{
				y--;
			}
		}
		else if(dir.equals(Direction.South))
		{
			y++;
		}
		else if(dir.equals(Direction.East))
		{
			x++;
		}
		if(dir.equals(Direction.West))
		{
			if(x > 0)
			{
				x--;
			}
		}
	}
}
