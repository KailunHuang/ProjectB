package automail;

import java.util.LinkedList;

import exceptions.TubeFullException;
import strategies.MyMailPool.Item;

public interface AddFragileMail {
	
	void addFragileMail(LinkedList<Item> fragilePool,StorageTube temp, int MAX_TAKE) throws TubeFullException;
	
}
