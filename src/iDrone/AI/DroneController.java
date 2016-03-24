package iDrone.AI;

import java.util.Observable;
import java.util.Observer;

import iDrone.DroneData;
import iDrone.AI.Courier.Courier;
import iDrone.AI.PositionTest.PositionTestStrategy;
import iDrone.AI.Test.TestStrategy;
import iDrone.DroneData.strategy_e;

public class DroneController implements Observer{
	DroneData model;
	FiniteStateMachine fsm;
	
	public DroneController(DroneData model){
		this.model = model;
		
		fsm = null;
		changeStrategy(model.getStrategy());
		
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("DroneController notified");
		//System.out.println("change strategy: " + model.strategyChanged);
		
		if(model.strategyChanged){
			changeStrategy(model.getStrategy());
		}
		else if(fsm != null){
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
				//model.drone.getCommandManager().emergency();
				model.drone.freeze();
				model.drone.landing();
				break;
			
			case MANUAL_CONTROL:
				//TODO
				model.drone.freeze();
				break;
				
			case POSITION_TEST:
				fsm = new PositionTestStrategy(model);
				break;
				
			case TEST_STRATEGY:
				fsm = new TestStrategy(model);
				break;
				
			case COURIER:
				fsm= new Courier(model);
				break;
		}
		
		model.strategyChanged = false;
	}
}
