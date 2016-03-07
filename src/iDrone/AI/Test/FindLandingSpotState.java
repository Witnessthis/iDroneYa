package iDrone.AI.Test;

import com.sun.org.apache.regexp.internal.recompile;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;
import iDrone.AI.Test.TestStrategy.state_e;

public class FindLandingSpotState extends State{

	public FindLandingSpotState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(!model.isFlying()){
			return state_e.FINNISH_STATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		model.drone.setVerticalCamera();
		AIwait(ait, 1000);
		
		//lol whatever
		
		landing(ait, 4000);
		
		awaitTransition();
	}

}
