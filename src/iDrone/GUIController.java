package iDrone;

import javax.swing.JFrame;

public class GUIController {
	DroneData model;
	GUI gui;
	
	public GUIController(DroneData model){
		this.model = model;
		
		gui = new GUI();
		gui.setTitle("iDrone");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1280, 720);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		
		
		model.drone.getVideoManager().addImageListener(gui);
		
		//lol
		
	}
}
