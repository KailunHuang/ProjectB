package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class CarefulRobot extends Robot {
	private final int MAX_TAKE=3;
	
	// Added on 14th Sep, 2018 *************
	private MailItem deliveryItem;
	private StorageTube tube;
	private boolean move = false;
	// *************************************

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Careful);
		super.max_cap = MAX_TAKE;
		super.setTube();
	}
	
	
	// Modified on 14th Sep, 2018 *************
	// Changed into protected!!! *****************
	
	
	@Override
	protected void moveTowards(int destination) throws FragileItemBrokenException {
        
		if (move) {
	        if(super.current_floor < destination){
	            super.current_floor++;
	        }else {
	        	super.current_floor--;
	        }
	        move=false;
        }
        // Otherwise, move in the next round
        else {
            move = true;
        }

    }
	// *************************************
	
	

	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) throws FragileItemBrokenException, TubeFullException {
		
		// ****************************************
		// Sometimes throws null exception! That's why I added tube != null
		
		
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube(MAX_TAKE);
		
		if (temp.getSize() < MAX_TAKE && !fragilePool.isEmpty() ) {
			Item item = fragilePool.remove();
			temp.addFragileItem(item.getMailItem());
		}
		
		while(temp.getSize() < MAX_TAKE && !pool.isEmpty()) {
			Item item = pool.remove();
			if (!item.getHeavy()) lightCount--;
			temp.addItem(item.getMailItem());
		}		
		
		if (temp.getSize() > 0) {
			while (!temp.isEmpty()) tube.addFragileItem(temp.pop()); 
			super.dispatch();
		}
		
	}
}
