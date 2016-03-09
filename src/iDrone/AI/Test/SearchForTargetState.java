package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;
import iDrone.AI.Test.TestStrategy.state_e;

public class SearchForTargetState extends State{

	public SearchForTargetState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.hasTarget()){
			return state_e.KILL_STATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("Searching for target...");
		
		spinLeft(ait, 10000);
		
		backward(ait, 1000);
	}
	
}
