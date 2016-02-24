package iDrone;

public class AI {
	DroneData model;
	DroneController droneControl;
	
	public AI(DroneData model){
		this.model = model;
		this.droneControl = new DroneController();
		
		
		
	}
}
