package iDrone;

import java.util.Observable;

import com.sun.org.apache.regexp.internal.recompile;

import de.yadrone.base.*;

public class DroneData extends Observable{
	public ARDrone drone;
	
	private strategy_e currentStrategy;
	public enum strategy_e{TEST_STRATEGY, MANUAL_CONTROL, EMERGENCY}
	
	private boolean isFlying = false;
	
	//test fields
	private boolean targetFound = false;
	private int missiles = 18;
	
	public DroneData(){
		drone = new ARDrone();
		drone.start();
		drone.reset();
		
		setStrategy(strategy_e.TEST_STRATEGY);
	}
	
	private void notifyModelChanged(){
		System.out.println("model notifying");
		
		setChanged();
		notifyObservers();
	}

	public synchronized strategy_e getStrategy(){
		return currentStrategy;
	}
	
	public synchronized void setStrategy(strategy_e strategy){
		currentStrategy = strategy;		
		notifyModelChanged();
	}
	
	public synchronized boolean isFlying() {
		return isFlying;
	}

	public synchronized void setFlying(boolean isFlying) {
		System.out.println("model flying");
		
		this.isFlying = isFlying;
		notifyModelChanged();
	}

	
	//test methods
	public synchronized boolean hasTarget() {
		return targetFound;
	}

	public synchronized void setHasTarget(boolean targetFound) {
		this.targetFound = targetFound;
		notifyModelChanged();
	}
	
	public synchronized int getMissiles(){
		return missiles;
	}
	
	public synchronized void fireMissile(){
		if(missiles > 0){
			missiles--;
		}
		notifyModelChanged();
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
