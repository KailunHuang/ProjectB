package automail;

import java.util.LinkedList;

import exceptions.FragileItemBrokenException;
import exceptions.TubeFullException;
import strategies.MyMailPool.Item;

/*GROUP 31
Kailun Huang
Weijia Wang
Shizhan Xu*/
public interface AddLightMail {
	
	void addLightMail(StorageTube temp, LinkedList<Item> pool, int lightCount) 
			throws TubeFullException, FragileItemBrokenException;

}
