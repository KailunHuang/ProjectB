package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class CarefulRobot extends Robot {
	private final int MAX_TAKE=3;

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Careful);
	}

	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) {
		
	}
}
