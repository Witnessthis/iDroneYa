package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class FinnishState extends State{

	public FinnishState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		awaitTransition();
	}

}
