package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;

public class TestStrategy extends FiniteStateMachine{
	public enum state_e{START_STATE, SEARCH_STATE, KILL_STATE, FIND_LANDING_SPOT, FINNISH_STATE}

	public TestStrategy(DroneData model) {
		//State array should correspond to the ordering of the enum
		super(new State[]{
				new StartState(model),
				new SearchForTargetState(model),
				new KillState(model),
				new FindLandingSpotState(model),
				new FinnishState(model)
		});
	}

}

