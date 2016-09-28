package hourEngine.levelCollide.robotRules;

import java.util.ArrayList;

import hourEngine.levelCollide.Robot;
import hourEngine.levelCollide.Shape;

public abstract class RobotRuleBase
{
	public class RobotIntruction
	{
		public ArrayList<Integer> robotDirs;
		int pos;
		public RobotIntruction(ArrayList<Integer> dirs, int p)
		{
			robotDirs = dirs;
			pos = p;
		}
	}
	
	abstract RobotIntruction move(boolean left, boolean right, Shape LShape, Shape RShape);
}
