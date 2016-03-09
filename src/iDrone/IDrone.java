package iDrone;

import de.yadrone.base.*;
import de.yadrone.base.command.VideoCodec;
import iDrone.AI.AI;
import iDrone.AI.DroneController;
import iDrone.GUI.GUIController;

public class IDrone {
	DroneData model;
	
	GUIController guiController;
	ImageAnalysis analysis;
	DroneController AIController;
	
	public IDrone(){
		model = new DroneData();
		
		guiController = new GUIController(model);
		analysis = new ImageAnalysis(model);
		AIController = new DroneController(model);
		
		//AI ai = new AI(model);
		//ai.start();


	}

	public static void main(String[] args) {
		new IDrone();
	}

}
