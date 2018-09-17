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
	private int current_floor;
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
        if (deliveryItem != null && deliveryItem.getFragile() || !tube.isEmpty() && tube.peek().getFragile()) throw new FragileItemBrokenException();
        if (move) {
	        if(current_floor < destination){
	            current_floor++;
	        }
	        else{
	            current_floor--;
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
		if (tube != null && tube.getSize() < MAX_TAKE) {
			
			// If fragile pool isn't empty: grab a fragile item
			if (!fragilePool.isEmpty()) {
				tube.addItem(fragilePool.poll().getMailItem());
			}
			// If pool isn't empty and tube is not full yet: grab items until tube is filled.
			while (!pool.isEmpty() && tube.getSize() < MAX_TAKE) {
				tube.addItem(pool.poll().getMailItem());
			}
		}
	}
}
