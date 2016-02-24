package iDrone;

import de.yadrone.base.*;

public class IDrone {
	DroneData model;
	GUIController guiController;
	ImageAnalysis analysis;
	AI ai;
	
	public IDrone(){
		model = new DroneData();
		//GUI
		guiController = new GUIController(model);
		analysis = new ImageAnalysis(model);
		ai = new AI(model);

	}

	public static void main(String[] args) {
		new IDrone();
	}

}