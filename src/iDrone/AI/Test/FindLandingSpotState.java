package iDrone.AI.Test;

import com.sun.org.apache.regexp.internal.recompile;

import iDrone.DroneData;
import iDrone.AI.AIThread;
import iDrone.AI.State;
import iDrone.AI.Test.TestStrategy.state_e;

public class FindLandingSpotState extends State{

	public FindLandingSpotState(DroneData model) {
		super(model, new AIThread(model) {
			
			@Override
			protected void act() {
				model.drone.setVerticalCamera();
				AIwait(1000);
				
				//lol whatever
				
				
				model.drone.landing();
				AIwait(2000);
				model.setFlying(false);
			}
		});
	}

	@Override
	public int nextTransition() {
		if(!model.isFlying()){
			return state_e.FINNISH_STATE.ordinal();
		}
		
		return -1;
	}

}
