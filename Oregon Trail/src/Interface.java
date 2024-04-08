/**
 * Interface.java
 * 
 * @author
 * @version 1.1.1 - 7 April 2024
 */


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Interface {

	private JFrame frame;
	private Timer clock;
	private Travel travel = new Travel();
	private Wagon wagon	  = new Wagon();
	private JLabel milTrvlQtyLbl;
	private JLabel rationsQtyLbl;
	private JLabel trvlSpeedQtyLbl;
	private JLabel foodQtyLbl;
	private JFrame frameTwo;
	private JLabel inventoryLbl;
	private JLabel dateQtyLbl;
	private Equipment wagWheel 	= new Equipment("Wagon Wheel", 45, 2);
	private Equipment wagAxle 	= new Equipment("Wagon Axle", 45, 1);
	private Equipment toys		= new Equipment("Toys", 5, 5);
	private Equipment blankets	= new Equipment("Blankets", 2, 5);
	private Equipment water		= new Equipment("Water", 200, 1);
	private Food food		    = new Food("Food", 1, 900, true);
	private int counter = 1;
	private Fort fort1			= new Fort("Fort 1", 200);
	private Fort fort2			= new Fort("Fort 2", 300);
	private JLabel milToQtyLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
		
		wagon.addItem(wagWheel);
		wagon.addItem(wagAxle);
		wagon.addItem(toys);
		wagon.addItem(blankets);
		wagon.addItem(water);
		wagon.addItem(food);
		
		clock = new javax.swing.Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed(evt);
			}
		});
	}

	public void clockActionPerformed(ActionEvent evt) {
		// update labels
		milTrvlQtyLbl.setText(travel.updateMilesTravelled() + "");
		trvlSpeedQtyLbl.setText(travel.getPace() + "");
		rationsQtyLbl.setText(travel.displayRations());
		wagon.removeItem(food, travel.getRations() * 4);
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		dateQtyLbl.setText(travel.updateDate() + "");
		
		// determine if user arrived at a landmark
		fort1.updateMilesAway(travel.getPace());
		if (!fort1.arrivedAtLandmark()) {
			milToQtyLbl.setText(fort1.getMilesAway() + "");
		} else if (!fort1.hasvisited()){
			milToQtyLbl.setText("visiting");
			fort1.updatevisited();
			// we need to open a new frame here. this could possibly be in a different function for legibility
		} else {
			fort2.updateMilesAway(travel.getPace());
			milToQtyLbl.setText(fort2.getMilesAway() + "");
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1289, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frameTwo = new JFrame();
		frameTwo.setBounds(100,100,1289,767);
		frameTwo.setTitle("OPTIONS");
		frameTwo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTwo.setVisible(false);
		
		JComboBox<String> paceComboBox= new JComboBox<String>();
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setPace(paceComboBox);
			}
		});
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		paceComboBox.setBounds(10, 127, 395, 51);
		
		JComboBox<String> rationsComboBox = new JComboBox<String>();
		rationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setRations(rationsComboBox);
			}
		});
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bare Bones", "Meager", "Filling"}));
		rationsComboBox.setBounds(10, 594, 258, 125);
		
		inventoryLbl = new JLabel("Wagon Inventory: ");
		inventoryLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		inventoryLbl.setBounds(415, 189, 137, 51);
		
		JPanel panel = new JPanel();
		panel.add(paceComboBox);
		panel.add(rationsComboBox);
		panel.add(inventoryLbl);
		frameTwo.getContentPane().add(panel);
		
		JButton startTrvlBtn = new JButton("Start Travel");
		startTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.start();
			}
		});
		startTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		startTrvlBtn.setBounds(10, 594, 258, 125);
		frame.getContentPane().add(startTrvlBtn);
		
		JLabel foodLbl = new JLabel("Food (lbs):");
		foodLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		foodLbl.setBounds(10, 160, 395, 51);
		frame.getContentPane().add(foodLbl);
		
		JLabel rationsLbl = new JLabel("Rations:");
		rationsLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		rationsLbl.setBounds(10, 222, 395, 51);
		frame.getContentPane().add(rationsLbl);
		
		JLabel milesTravelledLbl = new JLabel("Miles Travelled:");
		milesTravelledLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesTravelledLbl.setBounds(10, 284, 395, 51);
		frame.getContentPane().add(milesTravelledLbl);
		
		JLabel milesToNextLbl = new JLabel("Miles to Next Landmark:");
		milesToNextLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesToNextLbl.setBounds(10, 346, 395, 51);
		frame.getContentPane().add(milesToNextLbl);
		
		JLabel travelSpeedLbl = new JLabel("Travel Speed:");
		travelSpeedLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		travelSpeedLbl.setBounds(10, 408, 395, 51);
		frame.getContentPane().add(travelSpeedLbl);
		
		JLabel weatherLbl = new JLabel("Weather:");
		weatherLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		weatherLbl.setBounds(10, 470, 395, 51);
		frame.getContentPane().add(weatherLbl);
		
		foodQtyLbl = new JLabel("900");
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		foodQtyLbl.setBounds(415, 160, 137, 51);
		frame.getContentPane().add(foodQtyLbl);
		
		rationsQtyLbl = new JLabel("Filling");
		rationsQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		rationsQtyLbl.setBounds(415, 222, 137, 51);
		frame.getContentPane().add(rationsQtyLbl);
		
		milTrvlQtyLbl = new JLabel("0");
		milTrvlQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milTrvlQtyLbl.setBounds(415, 284, 137, 51);
		frame.getContentPane().add(milTrvlQtyLbl);
		
		milToQtyLbl = new JLabel("200");
		milToQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milToQtyLbl.setBounds(415, 346, 137, 51);
		frame.getContentPane().add(milToQtyLbl);
		
		trvlSpeedQtyLbl = new JLabel("12");
		trvlSpeedQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		trvlSpeedQtyLbl.setBounds(415, 408, 137, 51);
		frame.getContentPane().add(trvlSpeedQtyLbl);
		
		JLabel wthrQtyLbl = new JLabel("Good");
		wthrQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		wthrQtyLbl.setBounds(415, 470, 137, 51);
		frame.getContentPane().add(wthrQtyLbl);
		
		JLabel titleLbl = new JLabel("Oregon Trail");
		titleLbl.setFont(new Font("Felix Titling", Font.PLAIN, 50));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(343, 11, 569, 86);
		frame.getContentPane().add(titleLbl);
		
		JLabel dateLbl = new JLabel("Date:");
		dateLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl.setBounds(586, 631, 93, 51);
		frame.getContentPane().add(dateLbl);
		
		dateQtyLbl = new JLabel("March 1, 1860");
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(676, 631, 284, 51);
		frame.getContentPane().add(dateQtyLbl);
		
		JButton stopTrvlBtn = new JButton("Stop Travel");
		stopTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				frameTwo.setVisible(true);
				//inventoryLbl.setText(wagon.displayingInventory(wagon));  //this currently does not work. Fix it. this is to display the string for the label.
			}
		});
		stopTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		stopTrvlBtn.setBounds(278, 594, 258, 125);
		frame.getContentPane().add(stopTrvlBtn);
		
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/image/trailPic.jpg"));
		JLabel trailImage = new JLabel(icon);
		trailImage.setBounds(562, 108, 684, 511);
		frame.getContentPane().add(trailImage, BorderLayout.PAGE_END);
	}
}
