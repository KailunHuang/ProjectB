package strategies;

import java.util.ArrayList;
import java.util.List;

import automail.BigRobot;
import automail.CarefulRobot;
import automail.IMailDelivery;
import automail.Robot;
import automail.Simulation;
import automail.StandardRobot;
import automail.WeakRobot;

/*GROUP 31
Kailun Huang
Weijia Wang
Shizhan Xu*/

public class Automail {
	      
    public ArrayList<Robot> robots;
    public IMailPool mailPool;
    
    public Automail(IMailPool mailPool, IMailDelivery delivery, List<Simulation.RobotType> robotTypes) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
    	/** Initialize robots */
    	robots=new ArrayList<>();
    	for(Simulation.RobotType type:robotTypes) {
    		switch(type) {
    		case Big:
    			robots.add(new BigRobot(delivery,mailPool));
    			break;
    		case Careful:
    			robots.add(new CarefulRobot(delivery,mailPool));
    			break;
    		case Standard:
    			robots.add(new StandardRobot(delivery,mailPool));
    			break;
    		case Weak:
    			robots.add(new WeakRobot(delivery,mailPool));
    			break;
    		}
    	}
    }
    
}
