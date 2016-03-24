package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class GoHomeState extends State{

	public GoHomeState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.distFromInitialPos() < 300){
			return PositionTestStrategy.state_e.LANDINGSTATE.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		double angle = model.angleFromInitialPos() + 180.0 - model.getMagnetoData().getHeadingFusionUnwrapped();
		
		if(angle > 10){
			spinRight(ait, 0);
		}
		else if(angle < -10){
			spinLeft(ait, 0);
		}
		else{
			forward(ait, 0);
		}
	}

}
