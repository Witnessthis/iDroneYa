package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.FiniteStateMachine;
import iDrone.AI.State;

public class PositionTestStrategy extends FiniteStateMachine{
	public enum state_e {START, FLYFORWARD, GETLOST, GOHOME, LANDINGSTATE, FINNISHSTATE};

	public PositionTestStrategy(DroneData model) {
		super(new State[]{
				new StartState(model),
				new FlyForwardState(model),
				new GetLostState(model),
				new GoHomeState(model),
				new LandingState(model),
				new FinnishState(model)
		});
	}

}
