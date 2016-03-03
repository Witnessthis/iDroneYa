package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;

public class StartState extends State{
	
	public StartState(DroneData model) {
		
		super(model, new AIThread(model) {
			@Override
			protected void act() {
				System.out.println("start");
				
				model.drone.setHorizontalCamera();
				
				if(!model.isFlying()){
					model.drone.takeOff();
					AIwait(4000);
				}
				
				model.drone.hover();
				
				running = false;
				
				model.setFlying(true);
			}
		});
	}

	@Override
	public int nextTransition() {
		if(!stateAI.isAlive() || !stateAI.isRunning()){
			return TestStrategy.state_e.SEARCH_STATE.ordinal();
		}
		
		return -1;
	}
}
