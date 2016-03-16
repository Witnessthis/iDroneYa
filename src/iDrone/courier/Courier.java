package iDrone.courier;

import iDrone.DroneData;
import iDrone.AI.*;

public class Courier extends FiniteStateMachine{
	public enum state_e{START_STATE, ASCEND_STATE, SEARCH_STATE, APPROACH_STATE, DESCEND_STATE, LANDING_STATE, FINNISH_STATE}
	
	public Courier(DroneData model){
		super(new State[]{
				new StartState(model),
				new AscendState(model),
				new SearchState(model)
		});
	}
	
}
