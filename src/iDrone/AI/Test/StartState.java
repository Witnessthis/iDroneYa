package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;

public class StartState extends State{
	
	public StartState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		System.out.println("start test " + model.isFlying());
		
		if(model.isFlying()){
			return TestStrategy.state_e.SEARCH_STATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("start");
		
		model.drone.setHorizontalCamera();
		
		System.out.println("start test " + model.isFlying());
		if(!model.isFlying()){
			System.out.println("start test takeOff");
			takeOff(ait, 8000);
		}
		
		hover(ait, 0);
		
		awaitTransition();
	}
}
