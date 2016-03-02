package iDrone;

import de.yadrone.base.*;
import de.yadrone.base.command.VideoCodec;

public class DroneData {
	ARDrone drone;
	
	private boolean hasTarget;
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
	
	public DroneData(){
		hasTarget = false;
		
		drone = new ARDrone();

	}

	public boolean hasTarget() {
		return hasTarget;
	}
}
