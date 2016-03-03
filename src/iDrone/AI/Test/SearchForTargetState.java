package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.AIThread;
import iDrone.AI.State;
import iDrone.AI.Test.TestStrategy.state_e;

public class SearchForTargetState extends State{

	public SearchForTargetState(DroneData model) {
		super(model, new AIThread(model) {
			
			@Override
			protected void act() {
				System.out.println("Searching for target...");
				
				model.drone.spinLeft();
				AIwait(3000);
				
				model.drone.backward();
				AIwait(2000);
			}
		});
	}

	@Override
	public int nextTransition() {
		if(model.hasTarget()){
			return state_e.KILL_STATE.ordinal();
		}
		
		return -1;
	}
	
}
