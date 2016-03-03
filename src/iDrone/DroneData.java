package iDrone;

import java.util.Observable;

import de.yadrone.base.*;
import de.yadrone.base.command.VideoCodec;

public class DroneData extends Observable{
	public ARDrone drone;
	
	private strategy_e currentStrategy;
	public enum strategy_e{TEST_STRATEGY, MANUAL_CONTROL, EMERGENCY}
	
	public DroneData(){
		drone = new ARDrone();
		drone.start();
		drone.reset();		
		
		currentStrategy = strategy_e.MANUAL_CONTROL;
	}
	
	private void notifyOfChange(){
		setChanged();
		notifyObservers();
	}

	public synchronized strategy_e getStrategy(){
		return currentStrategy;
	}
	
	public synchronized void setStrategy(strategy_e strategy){
		currentStrategy = strategy;
		notifyOfChange();
	}
	
	
	
	
	
	
	//old state
	private state_e state = state_e.DEFAULT;
	public enum state_e {
	    START, STOP, LIFT_OFF, HOVER,
	    SPIN_RIGHT, SPIN_LEFT, LAND,
	    STATE_SHIFT, EMERGENCY, DEFAULT
	}
	
	public synchronized state_e getState(){
		return this.state;
	}
	
	public synchronized void setState(state_e state){
		this.state = state;
	}
}
