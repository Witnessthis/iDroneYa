package iDrone.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.yadrone.base.video.ImageListener;

public class GUI extends JFrame implements ImageListener{
	
	public static int fWidth = 1280;
	public static int fHeight = 720;
	
	BufferedImage image = null;
	GUIController controller;
	
	JButton redAlertButton;
	JButton takeOffButton;
	JButton startButton;
	JButton stopButton;
	JButton spinRightButton;
	JButton spinLeftButton;
	JButton landButton;
	JButton hoverButton;
	
	JButton testTargetAcquiredButton;
	
	JPanel mainPanel;
	JPanel topRightPanel;
	JPanel topLeftPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	
	public GUI(GUIController ctrlr){
		this.controller = ctrlr;
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		//mainPanel.setBackground(Color.CYAN);
		
		add(mainPanel);
		
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		//topPanel.setBackground(Color.BLACK);
		mainPanel.add(topPanel);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		//bottomPanel.setBackground(Color.WHITE);
		mainPanel.add(bottomPanel);
		
		topLeftPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);

				if(image != null){
					g.drawImage(image, 0, 0, null);
				}
			}
		};
		//topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.PAGE_AXIS));
		//topLeftPanel.setBackground(Color.GREEN);
		topLeftPanel.setMinimumSize(new Dimension(fWidth/2, fHeight/ 2));
		topPanel.add(topLeftPanel);
		
		topRightPanel = new JPanel();
		//topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.PAGE_AXIS));
		//topRightPanel.setBackground(Color.RED);
		topRightPanel.setMaximumSize(new Dimension(250, fHeight));
		topPanel.add(topRightPanel);
		
		BufferedImage emergencyImage = null;
		String path = "src" + File.separator + "iDrone" + File.separator + "GUI" + File.separator + "panic.png";
		System.out.println(path);
		try {
			emergencyImage = ImageIO.read(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			emergencyImage = null;
		}
		
		if(emergencyImage != null){
			redAlertButton = new JButton(new ImageIcon(emergencyImage));
		}
		else{
			redAlertButton = new JButton("Emergency");
		}
		redAlertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redAlert();		
			}
		});;
		redAlertButton.setAlignmentX(RIGHT_ALIGNMENT);
		topRightPanel.add(redAlertButton);
		
		testTargetAcquiredButton = new JButton("Focus Target");
		testTargetAcquiredButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.testTarget();
			}
		});
		bottomPanel.add(testTargetAcquiredButton);
	}
	
	@Override
	public void imageUpdated(BufferedImage image) {
		this.image = image;
		repaint();
	}

	/*
	class GUIPanel extends JPanel{
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
		
	}*/


	
}
