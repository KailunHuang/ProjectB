package automail;

import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;

import java.util.Stack;

/*GROUP 31
Kailun Huang
Weijia Wang
Shizhan Xu*/

/**
 * The storage tube carried by the robot.
 */
public class StorageTube {

    public int MAXIMUM_CAPACITY;
    public Stack<MailItem> tube;

    /**
     * Constructor for the storage tube
     */
    public StorageTube(int max_cap){
        this.tube = new Stack<MailItem>();
        this.MAXIMUM_CAPACITY = max_cap;
    }

    /**
     * @return if the storage tube is full
     */
    public boolean isFull(){
        return tube.size() == MAXIMUM_CAPACITY;
    }

    /**
     * @return if the storage tube is empty
     */
    public boolean isEmpty(){
        return tube.isEmpty();
    }
    
    /**
     * @return the first item in the storage tube (without removing it)
     */
    public MailItem peek() {
    	return tube.peek();
    }

    /**
     * Add an item to the tube
     * @param item The item being added
     * @throws TubeFullException thrown if an item is added which exceeds the capacity
     */
    public void addItem(MailItem item) throws TubeFullException, FragileItemBrokenException {
        if(tube.size() < MAXIMUM_CAPACITY){
        	if (tube.isEmpty()) {
        		tube.add(item);
        	//} else if (item.getFragile() || tube.peek().getFragile()) {
        	//we don't need to check if the first item is fragile because 
        	//the fragile mail has its own addItem method which is addAllItem. 
        	} else if (item.getFragile()) {
        		throw new FragileItemBrokenException();
        	} else {
        		tube.add(item);
        	}
        } else {
            throw new TubeFullException();
        }
    }
    
    //only careful robot will use this method 
    //add item into careful item, we don't need to consider FrafileItemBrokenException
    //and the controller of if the careful robot already consists a fragile mail is built in the 
    //Robot class
    public void addFragileItem(MailItem item) throws TubeFullException{
    	if(tube.size() < MAXIMUM_CAPACITY) {
    		tube.add(item);
    	}else{
    		throw new TubeFullException();	
    	}
    }

    /** @return the size of the tube **/
    public int getSize(){
    	return tube.size();
    }
    
    /** 
     * @return the first item in the storage tube (after removing it)
     */
    public MailItem pop(){
        return tube.pop();
    }

}
