package iDrone.GUI;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.yadrone.base.video.ImageListener;

public class GUI extends JFrame implements ImageListener{
	
	BufferedImage image = null;
	GUIController controller;
	
	public GUI(GUIController controller){
		this.controller = controller;
		add(new GUIPanel());
	}

	
	class GUIPanel extends JPanel{
		JButton redAlertButton;
		JButton takeOffButton;
		JButton startButton;
		JButton stopButton;
		JButton spinRightButton;
		JButton spinLeftButton;
		JButton landButton;
		JButton hoverButton;
		
		
		public GUIPanel(){
			hoverButton = new JButton("Hover");
			hoverButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.hover();
				}
			});
			add(hoverButton);
			
			spinRightButton = new JButton("Spin Right");
			spinRightButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.spinRight();
				}
			});
			add(spinRightButton);
			
			spinLeftButton = new JButton("Spin Left");
			spinLeftButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.spinLeft();
				}
			});
			add(spinLeftButton);
			
			landButton = new JButton("Land");
			landButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.land();
				}
			});
			add(landButton);
			
			redAlertButton = new JButton("Red Alert");
			redAlertButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.redAlert();		
				}
			});;
			add(redAlertButton);
			
			
			takeOffButton = new JButton("Take Off");
			takeOffButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.takeOff();
				}
			});
			add(takeOffButton);
			
			startButton = new JButton("Start");
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.start();
				}
			});
			add(startButton);
			
			stopButton = new JButton("Stop");
			stopButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.stop();
				}
			});
			add(stopButton);
		}
		
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			//System.out.println("paint");
			
			if(image != null){
				g.drawImage(image, 0, 150, null);
			}
		}
	}


	@Override
	public void imageUpdated(BufferedImage image) {
		this.image = image;
		repaint();
	}
}
