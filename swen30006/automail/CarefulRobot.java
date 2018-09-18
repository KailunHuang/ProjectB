package automail;

import java.util.LinkedList;

import automail.Simulation.RobotType;
import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.IMailPool;
import strategies.MyMailPool.Item;

/**
 * 
 * @GROUP 31
Kailun Huang
Weijia Wang
Shizhan Xu
 *
 */
public class CarefulRobot extends Robot implements AddFragileMail{
	private final int MAX_TAKE=3;
	
	
	private boolean move = false;
	

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Simulation.RobotType.Careful);
		super.max_cap = MAX_TAKE;
		super.setTube();
	}
	
	
	
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
	
	@Override
	public void fillStorageTube(LinkedList<Item> pool,LinkedList<Item> fragilePool,int lightCount) throws FragileItemBrokenException, TubeFullException {
		
		
		
		StorageTube tube = super.getTube();
		StorageTube temp = new StorageTube(MAX_TAKE);
		
		//if there are some fragile mails, we put one fragile mail into the tube
		addFragileMail(fragilePool, temp, MAX_TAKE);
		
		//the fill the tube with other normal mails
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
	
	@Override
	public void addFragileMail(LinkedList<Item> fragilePool,StorageTube temp, int MAX_TAKE) throws TubeFullException {
		if (temp.isEmpty() && !fragilePool.isEmpty() ) {
			Item item = fragilePool.remove();
			temp.addFragileItem(item.getMailItem());
		}
	}
}
