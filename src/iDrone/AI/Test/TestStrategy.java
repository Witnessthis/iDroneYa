package iDrone.AI.Test;

import iDrone.AI.*;

public class TestStrategy extends FiniteStateMachine{
	public enum state_e{START_STATE, KILL_STATE, FINNISH_STATE}

	public TestStrategy() {
		//State array should correspond to the ordering of the enum
		super(new State[]{
				new StartState(),
				new KillState(),
				new FinnishState()
		});
	}

}
