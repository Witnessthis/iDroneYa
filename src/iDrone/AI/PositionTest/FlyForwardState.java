package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class FlyForwardState extends State {

	public FlyForwardState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.getXPos() > 1000){
			return PositionTestStrategy.state_e.GETLOST.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		forward(ait, 500);
	}

}
