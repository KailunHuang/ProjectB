package automail;

import java.util.LinkedList;
/*GROUP 31
Kailun Huang
Weijia Wang
Shizhan Xu*/

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class StandardRobot extends Robot {
	private final int MAX_TAKE=4;
	
	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Standard);
	}
	
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) 
			throws TubeFullException, FragileItemBrokenException{
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube(MAX_TAKE);
		
		
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
