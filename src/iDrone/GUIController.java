package iDrone;

import javax.swing.JFrame;

public class GUIController {
	DroneData model;
	GUI gui;
	
	public GUIController(DroneData model){
		this.model = model;
		
		gui = new GUI(this);
		gui.setTitle("iDrone");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1280, 720);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		
		
		model.drone.getVideoManager().addImageListener(gui);
		
	}
	
	public void redAlert(){
		System.out.println("Red Alert");
		model.setState(DroneData.state_e.EMERGENCY);
	}

	public void takeOff() {
		System.out.println("Lift Off");
		model.setState(DroneData.state_e.LIFT_OFF);
	}

	public void stop() {
		System.out.println("Stop");
		model.setState(DroneData.state_e.STOP);
	}

	public void start() {
		System.out.println("Start");
		model.setState(DroneData.state_e.START);
	}

	public void land() {
		System.out.println("Land");
		model.setState(DroneData.state_e.LAND);
	}

	public void spinLeft() {
		System.out.println("Spin Left");
		model.setState(DroneData.state_e.SPIN_LEFT);
	}

	public void spinRight() {
		System.out.println("Spin Right");
		model.setState(DroneData.state_e.SPIN_RIGHT);
	}

	public void hover() {
		System.out.println("Hover");
		model.setState(DroneData.state_e.HOVER);
	}
}
