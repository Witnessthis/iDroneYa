package iDrone;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.yadrone.base.video.ImageListener;

public class GUI extends JFrame implements ImageListener{
	
	BufferedImage image = null;
	
	
	public GUI(){
		add(new GUIPanel());
	}

	
	class GUIPanel extends JPanel{
		
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			//System.out.println("paint");
			
			if(image != null){
				g.drawImage(image, 0, 0, null);
			}
		}
	}


	@Override
	public void imageUpdated(BufferedImage image) {
		this.image = image;
		repaint();
	}
}
