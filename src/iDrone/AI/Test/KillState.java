package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;
import iDrone.AI.Test.TestStrategy.state_e;

public class KillState extends State{

	public KillState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.getMissiles() == 0){
			return state_e.FIND_LANDING_SPOT.ordinal();
		}
		else if(!model.hasTarget()){
			return state_e.SEARCH_STATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("Firing Missiles");
		
		freeze(ait, 0);
		
		model.fireMissile();
		
		AIwait(ait, 500);
	}

}
