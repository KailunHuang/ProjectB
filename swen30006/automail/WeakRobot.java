package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class WeakRobot extends Robot {
	private final int MAX_TAKE=4;

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Weak);
	}
	
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) {
		
	}

}
