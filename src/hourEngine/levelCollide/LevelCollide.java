package hourEngine.levelCollide;

import hourEngine.core.*;
import hourEngine.core.SimulationBody;

public class LevelCollide extends SimulationBody
{
	Level level;
	boolean[][] checked;
	public LevelCollide(Level l)
	{
		level = l;
		checked = new boolean[l.xSize + 1][l.ySize + 1];
	}
	
	public void generate()
	{
		for(int y = 0; y < level.ySize + 1; y++)
		{
			for(int x = 0; x < level.xSize + 1; x++)
			{
				if(level.getCollide(x, y) != 0 || level.getCollide(x, y-1) != 0)
				{
					Robot r = new Robot(x,y);
				}
			}
		}
	}
}
