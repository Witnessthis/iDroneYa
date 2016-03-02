package iDrone;

import de.yadrone.base.*;
import de.yadrone.base.command.VideoCodec;

public class DroneData {
	ARDrone drone;
	
	private boolean hasTarget;
	
	public DroneData(){
		hasTarget = false;
		
		drone = new ARDrone();
		
		
		drone.start();
		drone.reset();
		//drone.getCommandManager().setSSIDSinglePlayer("iDroneYouDrone");
		//drone.getCommandManager().setVideoCodec(VideoCodec.H264_720P);
//		drone.setHorizontalCamera();
//		//drone.getVideoManager().reinitialize();
//		
//		
//		System.out.println("start");
//		drone.takeOff();
//		System.out.println("hover");
//		drone.hover();
//		
//		try {
//			Thread.sleep(8000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		drone.landing();
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		drone.stop();

	}

	public boolean hasTarget() {
		return hasTarget;
	}
}
