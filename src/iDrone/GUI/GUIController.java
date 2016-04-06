package iDrone.GUI;

import javax.swing.JFrame;

import iDrone.DroneData;
import iDrone.DroneData.state_e;

public class GUIController {
	DroneData model;
	GUI gui;
	
	public GUIController(DroneData model){
		this.model = model;
		
		gui = new GUI(this);
		gui.setTitle("iDrone");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(GUI.fWidth, GUI.fHeight);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		
		model.drone.getVideoManager().addImageListener(gui);
	}
	
	public void redAlert(){
		System.out.println("Red Alert");
		//model.setState(DroneData.state_e.EMERGENCY);
		model.setStrategy(DroneData.strategy_e.EMERGENCY);
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
	
	public void forward() {
		System.out.println("Forward");
		model.setState(DroneData.state_e.FORWARD);
	}

	public void backward() {
		System.out.println("Backward");
		model.setState(DroneData.state_e.BACKWARD);
	}
	
	public void right() {
		System.out.println("right");
		model.setState(DroneData.state_e.RIGHT);
	}
	
	public void left() {
		System.out.println("left");
		model.setState(DroneData.state_e.LEFT);
	}
	
	public void up() {
		System.out.println("Up");
		model.setState(DroneData.state_e.UP);
	}
	
	public void down() {
		System.out.println("down");
		model.setState(DroneData.state_e.DOWN);
	}

	public void hover() {
		System.out.println("Hover");
		model.setState(DroneData.state_e.HOVER);
	}

	public void testTarget() {
		model.setHasTarget(!model.hasTarget());
	}
	
	public double getXPos(){
		return model.getXPos();
	}
	
	public double getYPos(){
		return model.getYPos();
	}
}
