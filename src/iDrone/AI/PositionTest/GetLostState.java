package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class GetLostState extends State{

	public GetLostState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(!mayAct()){
			return PositionTestStrategy.state_e.GOHOME.ordinal();
		}
		
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		goRight(ait, 2000);
		
		spinRight(ait, 3000);
		
		freeze(ait, 2000);
		
		awaitTransition();
	}

}
