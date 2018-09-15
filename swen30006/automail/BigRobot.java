package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class BigRobot extends StandardRobot {
	
	private final int MAX_TAKE=6;
	private final static Simulation.RobotType robotT = Simulation.RobotType.Big;
	
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
	}	

}
