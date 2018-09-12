package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class BigRobot extends Robot {
	private final int MAX_TAKE=6;
	
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Big);
	}
	
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) {
		
	}


}
