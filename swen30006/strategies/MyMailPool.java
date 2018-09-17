package strategies;

import java.util.LinkedList;
import java.util.Comparator;
import automail.MailItem;
import automail.PriorityMailItem;
import automail.Robot;
import automail.Simulation;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;

public class MyMailPool implements IMailPool {
	// Property fragile has been added to item class and it's been set to public so that robot class can use it
	public class Item {
		int priority;
		int destination;
		boolean heavy;
		boolean fragile;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			heavy = mailItem.getWeight() >= 2000;
			fragile=mailItem.getFragile();
			destination = mailItem.getDestFloor();
			this.mailItem = mailItem;
		}
		
		// Added on 14th Sep, 2018 *************
		// Changed into protected!!! *****************
		public MailItem getMailItem(){
			return this.mailItem;
		}
		
		public boolean getHeavy() {
			return heavy;
		}


	}
	
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
	// pool and fragilePool are package privacy now since Robot is going to manipulate on them
	LinkedList<Item> pool;
	LinkedList<Item> fragilePool;
	// MAX_TAKE removed and becomes an attribute of robot subclasses
	private LinkedList<Robot> robots;
	private int lightCount;

	public MyMailPool(){
		// Start empty
		pool = new LinkedList<Item>();
		fragilePool = new LinkedList<Item>();
		lightCount = 0;
		robots = new LinkedList<Robot>();
	}

	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		if(item.fragile) {
			fragilePool.add(item);
		}else {
			pool.add(item);
			if (!item.heavy) lightCount++;
		}
		pool.sort(new ItemComparator());
	}
	
	@Override
	public void step() throws FragileItemBrokenException, TubeFullException {
		for (Robot robot: (Iterable<Robot>) robots::iterator) { fillStorageTube(robot); }
	}
	
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException, TubeFullException {
		robot.fillStorageTube(pool, fragilePool,lightCount);
	}

	@Override
	public void registerWaiting(Robot robot) { // assumes won't be there
		if (robot.getType()==Simulation.RobotType.Big || robot.getType()==Simulation.RobotType.Standard) {
			robots.add(robot); 
		} else {
			robots.addLast(robot); // weak and careful robot last as want more efficient delivery with highest priorities and general items
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		robots.remove(robot);
	}

}
