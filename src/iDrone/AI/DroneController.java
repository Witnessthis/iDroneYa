package iDrone.AI;

import java.util.Observable;
import java.util.Observer;

import iDrone.DroneData;
import iDrone.AI.Test.TestStrategy;
import iDrone.DroneData.strategy_e;

public class DroneController implements Observer{
	DroneData model;
	FiniteStateMachine fsm;
	
	public DroneController(DroneData model){
		this.model = model;
		
		fsm = null;
		changeStrategy(model.getStrategy());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(fsm != null){
			fsm.update();
		}
	}
	
	private void changeStrategy(strategy_e strategy){
		if(fsm != null){
			//stop state AI execution
			fsm.shutDown();
			fsm = null;
		}
		
		switch(strategy){
			case EMERGENCY:
				model.drone.getCommandManager().emergency();
				break;
			
			case MANUAL_CONTROL:
				//TODO
				model.drone.freeze();
				break;
				
			case TEST_STRATEGY:
				fsm = new TestStrategy();
				break;
		}
	}
}
