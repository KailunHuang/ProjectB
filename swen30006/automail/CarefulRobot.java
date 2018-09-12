package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

public class CarefulRobot extends Robot {
	private final int MAX_TAKE=3;

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Careful);
	}
	
	private void moveTowards(int destination) throws FragileItemBrokenException {
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

	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) {
		
	}
}
