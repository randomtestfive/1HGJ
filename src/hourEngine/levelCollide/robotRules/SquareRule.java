package hourEngine.levelCollide.robotRules;

import java.util.ArrayList;

import hourEngine.levelCollide.Shape;

public class SquareRule extends RobotRuleBase
{
	@Override
	RobotIntruction move(boolean left, boolean right, Shape LShape, Shape RShape)
	{
		ArrayList<Integer> move = new ArrayList<Integer>();
		if(left)
		{
			move.add(-1);
		}
		else if(right)
		{
			move.add(0);
		}
		else
		{
			move.add(1);
		}
		return null;
	}

}
