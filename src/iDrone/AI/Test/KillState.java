package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;
import iDrone.AI.Test.TestStrategy.state_e;

public class KillState extends State{

	public KillState(DroneData model) {
		super(model, new AIThread(model) {
			
			@Override
			protected void act() {
				System.out.println("Firing Missiles");
				model.fireMissile();
				AIwait(500);
			}
		});
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

}
