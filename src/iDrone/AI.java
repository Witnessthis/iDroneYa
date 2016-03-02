package iDrone;

public class AI extends Thread{
	DroneData model;
	DroneController droneControl;
	boolean running = true;
	
	public AI(DroneData model){
		this.model = model;
		this.droneControl = new DroneController();
		
		
		
	}
	
	@Override
	public void run(){
		
		System.out.println("start");
		//model.drone.reset();
		model.drone.takeOff();
		System.out.println("hover");
		model.drone.hover();
		
		try {
			currentThread();
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(running){
			if(!model.hasTarget()){
				lookForTarget();
			}
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			running = false;
		}	
		
		model.drone.landing();
		
		try {
			currentThread();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.drone.stop();
		
	}
	
	private void lookForTarget(){
		model.drone.spinRight();
	}
}
