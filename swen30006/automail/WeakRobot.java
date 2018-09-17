package automail;

import java.util.LinkedList;
import java.util.ListIterator;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class WeakRobot extends Robot {
	private final int MAX_TAKE=4;

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Weak);
		
	}
	
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) 
			throws TubeFullException, FragileItemBrokenException {
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube();
		
		ListIterator<Item> i = pool.listIterator();
		
		while(temp.getSize() < MAX_TAKE && !pool.isEmpty() && lightCount > 0) {
			Item item = i.next();
			if (!item.getHeavy()) {
				temp.addItem(item.getMailItem());
				i.remove();
				lightCount--;
			}
			
		}
		
		if(temp.getSize() > 0) {
			while(!temp.isEmpty()) tube.addItem(temp.pop());
			super.dispatch();
		}
	}

}
