package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class StandardRobot extends Robot {
	private final int MAX_TAKE=4;
	private final static Simulation.RobotType robotT = Simulation.RobotType.Standard;
	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, robotT);
	}
	
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) 
			throws TubeFullException, FragileItemBrokenException{
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube();
		
		
		while (temp.getSize() < MAX_TAKE && !pool.isEmpty() ) {
			Item item = pool.remove();
			if (!item.getHeavy()) lightCount--;
			temp.addItem(item.getMailItem());
		}
		
		if (temp.getSize() > 0) {
			while (!temp.isEmpty()) tube.addItem(temp.pop()); 
			super.dispatch();
		}
	}

}
