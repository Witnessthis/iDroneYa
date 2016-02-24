package iDrone;

import java.awt.image.BufferedImage;

import de.yadrone.base.*;
import de.yadrone.base.video.*;


public class ImageAnalysis implements ImageListener{
	DroneData model;
	
	public ImageAnalysis(DroneData model){
		this.model = model;
		
		//model.drone.getVideoManager().addImageListener(this);
		
	}

	@Override
	public void imageUpdated(BufferedImage arg0) {
		
		
	}
}
