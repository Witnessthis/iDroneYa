package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class LandingState extends State{

	public LandingState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.distFromInitialPos() > 400){
			return PositionTestStrategy.state_e.GOHOME.ordinal();
		}
		
		else if(!model.isFlying()){
			return PositionTestStrategy.state_e.FINNISHSTATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		if(model.getCurrentAltitude() < 500){
			landing(ait, 5000);
			awaitTransition();
		}
		
		down(ait, 0);
	}

}
