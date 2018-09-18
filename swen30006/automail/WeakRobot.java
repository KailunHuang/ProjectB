package automail;

import java.util.LinkedList;
import java.util.ListIterator;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class WeakRobot extends Robot implements AddLightMail{
	private final int MAX_TAKE=4;
	
	//constructor 
	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Weak);
		
	}
	
	@Override
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) 
			throws TubeFullException, FragileItemBrokenException {
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube(MAX_TAKE);
		
		addLightMail(temp, pool, lightCount);
		
		if(temp.getSize() > 0) {
			while(!temp.isEmpty()) tube.addItem(temp.pop());
			super.dispatch();
		}
	}
	
	@Override
	public void addLightMail(StorageTube temp, LinkedList<Item> pool, int lightCount) 
			throws TubeFullException, FragileItemBrokenException {
		ListIterator<Item> i = pool.listIterator();
		//Iterate the mailpool if there are light mail in the mailpool and add it to the weakRobot's tube
		while(temp.getSize() < MAX_TAKE && !pool.isEmpty() && lightCount > 0 && i.hasNext()) {
			Item item = i.next();
			if (!item.getHeavy()) {
				temp.addItem(item.getMailItem());
				i.remove();
				lightCount--;
			}
			
		}
	}
}
