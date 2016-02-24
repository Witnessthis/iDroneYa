package iDrone;

import de.yadrone.base.*;

public class DroneData {
	ARDrone drone;
	
	public DroneData(){
		drone = new ARDrone();
		
		drone.start();
		
		drone.setHorizontalCamera();

	}
}
