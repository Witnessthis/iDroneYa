package iDrone.MotionInterpretation;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

/*
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.TermCriteria;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;
*/

import de.yadrone.base.*;
import de.yadrone.base.video.*;
import iDrone.DroneData;

//import javafx.scene.image.Image;


public class ImageAnalysis implements ImageListener{
	DroneData model;
	
	// *** OPENCV START ***
	boolean firsttime = true;
	int maxPoints = 150;
	double minDist = 20;
	double qualitylevel = 0.01;
	
	/*
	Mat oldFrame = new Mat();
	MatOfPoint oldPts = new MatOfPoint();
	
	MatOfByte status = new MatOfByte();
	int maxlevel=2;
	
	Scalar scalar = new Scalar(255,0,0); 
	*/
	
	// *** OPENCV SLUT ***
	
	public ImageAnalysis(DroneData model){
		this.model = model;
		
		//model.drone.getVideoManager().addImageListener(this);
		
	}

	@Override
	public void imageUpdated(BufferedImage arg0) {
		/*
		// Buffered image til matrice.
		Mat frame = new Mat();
		byte[] pixels = ((DataBufferByte) arg0.getRaster().getDataBuffer()).getData();
		frame.put(0, 0, pixels);
		
		
		// init det hele
				BufferedImage endpic;
				Mat matFlowThis = new Mat();
				
				
				//optc flow relateret
				MatOfFloat err = new MatOfFloat();
				
				
				TermCriteria optical_flow_termination_criteria =new TermCriteria();
				optical_flow_termination_criteria.epsilon =.03;
				optical_flow_termination_criteria.maxCount = 20;
				
				
				MatOfPoint newPts = new MatOfPoint();
							
							if(firsttime){
								Imgproc.cvtColor(frame, matFlowThis, Imgproc.COLOR_RGBA2GRAY,0);
								Imgproc.goodFeaturesToTrack(matFlowThis, newPts ,maxPoints, qualitylevel, minDist);
								
								oldFrame = matFlowThis;
								oldPts = newPts;
								firsttime = false;
							
								
							}
							else
							{
								Imgproc.cvtColor(frame, matFlowThis, Imgproc.COLOR_RGBA2GRAY,0);
								Imgproc.goodFeaturesToTrack(matFlowThis, newPts ,maxPoints, qualitylevel, minDist);

								MatOfPoint2f  newPts2f = new MatOfPoint2f( newPts.toArray() );
								MatOfPoint2f  oldPts2f = new MatOfPoint2f( oldPts.toArray());
								Video.calcOpticalFlowPyrLK(oldFrame, matFlowThis,oldPts2f,newPts2f,status,err);
								
								 List<Point> cornersPrev = oldPts2f.toList();
								 List<Point> cornersThis = newPts2f.toList();
								 List<Byte> byteStatus = status.toList();
								 
								 int right = 0;
								 int left = 0;
								 int down= 0;
								 int up = 0;
								 int holdx = 0;
								 int holdy = 0;
								 int errormargin = 10;
								  for (int x = 0; x < byteStatus.size() - 1; x++) {  
								        if (byteStatus.get(x) == 1) {  
								        Point pt = cornersThis.get(x);  
								        Point pt2 = cornersPrev.get(x);  
								                      
								        Imgproc.circle(frame, pt, 5, scalar , 1);  
								        //Imgproc.line(frame, pt, pt2, scalar, 2); 
								        //Imgproc.arrowedLine(frame, pt, pt2, scalar);
								        Imgproc.arrowedLine(frame, pt, pt2, scalar, 2, Imgproc.LINE_4,0 , 0.4);
								        
								        if(Math.abs(pt.x-pt2.x) < 0.5){
								        	holdx++;
								        }
								        if(Math.abs(pt.y-pt2.y) < 0.5){
								        	holdy++;
								        }
								        if(pt.x>pt2.x){
								        	left++;
								        }
								        else if(pt.x<pt2.x){
								        	right++;
								        }
								        if(pt.y>pt2.y){
								        	down++;
								        }
								        else if(pt.y<pt2.y){
								        	up++;
								        }
								        }    
								        
								   }  
								 
								  if(holdx > errormargin){
									  System.out.println("Holder stille på x");
								  }
								  else if(left>=right){
									  System.out.println("Bevæger mod venstre");  
								  }
								  else{
									  System.out.println("Bevæger mod højre");
								  }
								  if(holdy > errormargin){
									  System.out.println("Holder stille på y");
								  }
								  else if(down>=up){
									  System.out.println("Bevæger nedad");  
								  }
								  else{
									  System.out.println("Bevæger opad");
								  }
								  
								              
								       
								
								
								
								oldFrame = matFlowThis;
								oldPts = newPts;
							}
							
							
							
							
							
							//Scalar scalar = new Scalar(255,0,0); 
							
											
							
							
							// convert the image to gray scale (Til test!)
							//Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
							//Imgproc.blur(frame,detected_edges,new Size(3,3));
							//Imgproc.Canny(detected_edges, detected_edges, threshold, threshold*3);
							
				
							
							// convert the Mat object (OpenCV) to BufferedImage (Java)
							// Vi gør dette for at vi kan få fremvist det nye billede i den givne format.
							
							endpic = mat2Img(frame);
							
							// TODO send endpic -> GUI.
							
							
							
		*/
	}
	
	/*
	
	// Kilde: http://www.codeproject.com/Tips/752511/How-to-Convert-Mat-to-BufferedImage-Vice-Versa
	public static BufferedImage mat2Img(Mat in)
    {
        BufferedImage out;
        byte[] data = new byte[320 * 240 * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if(in.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else
            type = BufferedImage.TYPE_3BYTE_BGR;

        out = new BufferedImage(320, 240, type);

        out.getRaster().setDataElements(0, 0, 320, 240, data);
        return out;
    } 
    */
}
