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
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.yadrone.base.video.ImageListener;
import javafx.util.Pair;

public class GUI extends JFrame implements ImageListener {

	public static int fWidth = 1280;
	public static int fHeight = 720;

	LinkedList<Pair<Double, Double>> path = new LinkedList<Pair<Double, Double>>();

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
	JButton forwardButton;
	JButton backwardButton;
	JButton leftButton;
	JButton rightButton;
	JButton upButton;
	JButton downButton;

	JButton testTargetAcquiredButton;

	JPanel mainPanel;
	JPanel topRightPanel;
	JPanel topLeftPanel;
	JPanel topPanel;
	JPanel bottomPanel;

	public GUI(GUIController ctrlr) {
		this.controller = ctrlr;
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBackground(Color.CYAN);

		add(mainPanel);

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		topPanel.setBackground(Color.BLACK);
		mainPanel.add(topPanel);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.setBackground(Color.WHITE);
		mainPanel.add(bottomPanel);

		topLeftPanel = new JPanel() {
			int axisLength = 250;
			int xO = 750;
			int yO = 300;
			double factor = axisLength / 5000.0;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (image != null) {
					g.drawImage(image, 0, 0, null);
				}

				// draw background
				g.setColor(Color.WHITE);
				g.fillRect(xO - axisLength, yO - axisLength, axisLength * 2, axisLength * 2);

				// draw axes
				g.setColor(Color.BLACK);
				g.drawLine(xO - axisLength, yO, xO + axisLength, yO);
				g.drawLine(xO, yO - axisLength, xO, yO + axisLength);

				// draw labels
				g.setColor(Color.BLACK);
				g.drawString("Y", xO + 5, yO - axisLength + 10);
				g.drawString("X", xO + axisLength - 10, yO - 10);

				// draw path
				g.setColor(Color.RED);
				path.add(new Pair<Double, Double>(controller.getXPos(), controller.getYPos()));

				System.out.println("pathSize: " + path.size());
				System.out.println("coordinates: X:" + path.getLast().getKey() + " Y:" + path.getLast().getValue());

				if (path.size() > 1) {
					for (int i = 0; i < path.size() - 1; i++) {
						g.drawLine((int) (path.get(i).getKey() * factor + xO),
								(int) (path.get(i).getValue() * factor + yO),
								(int) (path.get(i + 1).getKey() * factor + xO),
								(int) (path.get(i + 1).getValue() * factor + yO));
					}
				}

				// g.fillRect(xO + (int)(controller.getXPos() * factor) - 2, yO
				// - (int)(controller.getYPos() * factor)- 2, 5, 5);
				// g.drawLine(xO, yO, xO + (int)(controller.getXPos() * factor),
				// yO - (int)(controller.getYPos() * factor));
			}
		};
		// topLeftPanel.setLayout(new BoxLayout(topLeftPanel,
		// BoxLayout.PAGE_AXIS));
		topLeftPanel.setBackground(Color.GREEN);
		topLeftPanel.setMinimumSize(new Dimension(fWidth / 2, fHeight / 2));
		topPanel.add(topLeftPanel);

		topRightPanel = new JPanel();
		// topRightPanel.setLayout(new BoxLayout(topRightPanel,
		// BoxLayout.PAGE_AXIS));
		topRightPanel.setBackground(Color.RED);
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

		if (emergencyImage != null) {
			redAlertButton = new JButton(new ImageIcon(emergencyImage));
		} else {
			redAlertButton = new JButton("Emergency");
		}
		redAlertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redAlert();
			}
		});
		;
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

		hoverButton = new JButton("Hover");
		hoverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.hover();
			}
		});
		bottomPanel.add(hoverButton);

		spinRightButton = new JButton("Spin Right");
		spinRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.spinRight();
			}
		});
		bottomPanel.add(spinRightButton);

		spinLeftButton = new JButton("Spin Left");
		spinLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.spinLeft();
			}
		});
		bottomPanel.add(spinLeftButton);

		landButton = new JButton("Land");
		landButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.land();
			}
		});
		bottomPanel.add(landButton);

		takeOffButton = new JButton("Take Off");
		takeOffButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.takeOff();
			}
		});
		bottomPanel.add(takeOffButton);

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start();
			}
		});
		bottomPanel.add(startButton);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.stop();
			}
		});
		bottomPanel.add(stopButton);

		forwardButton = new JButton("Forward");
		forwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.forward();
			}
		});
		bottomPanel.add(forwardButton);

		backwardButton = new JButton("backward");
		backwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.backward();
			}
		});
		bottomPanel.add(backwardButton);

		leftButton = new JButton("left");
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.left();
			}
		});
		bottomPanel.add(leftButton);

		rightButton = new JButton("right");
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.right();
			}
		});
		bottomPanel.add(rightButton);

		upButton = new JButton("Up");
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.up();
			}
		});
		bottomPanel.add(upButton);

		downButton = new JButton("down");
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.down();
			}
		});
		bottomPanel.add(upButton);
		
		mainPanel.addKeyListener(controller);
		topPanel.addKeyListener(controller);
		topRightPanel.addKeyListener(controller);
		topLeftPanel.addKeyListener(controller);
		bottomPanel.addKeyListener(controller);
	}

	@Override
	public void imageUpdated(BufferedImage image) {
		this.image = image;
		repaint();
	}

	/*
	 * class GUIPanel extends JPanel{ public GUIPanel(){
	 * 
	 * hoverButton = new JButton("Hover"); hoverButton.addActionListener(new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * controller.hover(); } }); add(hoverButton);
	 * 
	 * spinRightButton = new JButton("Spin Right");
	 * spinRightButton.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * controller.spinRight(); } }); add(spinRightButton);
	 * 
	 * spinLeftButton = new JButton("Spin Left");
	 * spinLeftButton.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * controller.spinLeft(); } }); add(spinLeftButton);
	 * 
	 * landButton = new JButton("Land"); landButton.addActionListener(new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { controller.land();
	 * } }); add(landButton);
	 * 
	 * takeOffButton = new JButton("Take Off");
	 * takeOffButton.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * controller.takeOff(); } }); add(takeOffButton);
	 * 
	 * startButton = new JButton("Start"); startButton.addActionListener(new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * controller.start(); } }); add(startButton);
	 * 
	 * stopButton = new JButton("Stop"); stopButton.addActionListener(new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { controller.stop();
	 * } }); add(stopButton);
	 * 
	 * }
	 * 
	 * }
	 */

}
