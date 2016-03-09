package iDrone;

import java.util.Observable;

import com.sun.org.apache.regexp.internal.recompile;

import de.yadrone.base.*;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.ControlState;
import de.yadrone.base.navdata.DroneState;
import de.yadrone.base.navdata.StateListener;

public class DroneData extends Observable implements StateListener, AltitudeListener{
	public ARDrone drone;
	
	public final int maxAltitude = 1500;
	
	private strategy_e currentStrategy;
	public enum strategy_e{TEST_STRATEGY, MANUAL_CONTROL, EMERGENCY, COURIER}
	public boolean strategyChanged = false;
	
	private volatile DroneState droneState = null;
	private boolean isFlying = false; //Deprecated
	private volatile int currentAltitude = 0;
	
	//test fields
	private boolean targetFound = false;
	private int missiles = 18;
	
	public DroneData(){
		drone = new ARDrone();
		drone.reset();
		drone.start();
		drone.reset();
		
		drone.getNavDataManager().addStateListener(this);
		drone.getNavDataManager().addAltitudeListener(this);
		
		setStrategy(strategy_e.COURIER);
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
		if(currentStrategy == strategy_e.EMERGENCY){
			//do not let program leave emergency state
			return;
		}
		
		currentStrategy = strategy;
		strategyChanged = true;
		
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

	@Override
	public void controlStateChanged(ControlState arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(DroneState arg0) {
		droneState = arg0;
		notifyModelChanged();
		
	}
	
	public DroneState getDroneState(){
		return droneState;
	}

	@Override
	public void receivedAltitude(int arg0) {
		System.out.println("new altitude:" + arg0);
		currentAltitude = arg0;
	}

	@Override
	public void receivedExtendedAltitude(Altitude arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCurrentAltitude(){
		return currentAltitude;
	}
}
