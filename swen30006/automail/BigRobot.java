package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class BigRobot extends Robot{
	
	private final int MAX_TAKE=6;	
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Big);
		super.max_cap = MAX_TAKE; 
		super.setTube();
	}
	@Override
	public void fillStorageTube(LinkedList<Item> pool, LinkedList<Item> fragilePool, int lightCount)
			throws FragileItemBrokenException, TubeFullException {
		// TODO Auto-generated method stub
		
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
