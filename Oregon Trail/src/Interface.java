/**
 * Interface.java
 * 
 * @author
 * @version 1.1.1 - 7 April 2024
 */


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Interface {

	private JFrame frame;
	private Timer clock;
	private Travel travel = new Travel();
	private JLabel milTrvlQtyLbl;
	private JLabel rationsQtyLbl;
	private JLabel trvlSpeedQtyLbl;

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
		clock = new javax.swing.Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed(evt);
			}
		});
	}

	public void clockActionPerformed(ActionEvent evt) {
		milTrvlQtyLbl.setText(travel.updateMilesTravelled() + "");
		trvlSpeedQtyLbl.setText(travel.getPace() + "");
		rationsQtyLbl.setText(travel.getRations() + "");
		// increment day
		// subtract rations
		// update miles
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1289, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		foodLbl.setBounds(10, 127, 395, 51);
		frame.getContentPane().add(foodLbl);
		
		JLabel rationsLbl = new JLabel("Rations:");
		rationsLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		rationsLbl.setBounds(10, 189, 395, 51);
		frame.getContentPane().add(rationsLbl);
		
		JLabel milesTravelledLbl = new JLabel("Miles Travelled:");
		milesTravelledLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesTravelledLbl.setBounds(10, 251, 395, 51);
		frame.getContentPane().add(milesTravelledLbl);
		
		JLabel milesToNextLbl = new JLabel("Miles to Next Landmark:");
		milesToNextLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesToNextLbl.setBounds(10, 313, 395, 51);
		frame.getContentPane().add(milesToNextLbl);
		
		JLabel travelSpeedLbl = new JLabel("Travel Speed:");
		travelSpeedLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		travelSpeedLbl.setBounds(10, 375, 395, 51);
		frame.getContentPane().add(travelSpeedLbl);
		
		JLabel weatherLbl = new JLabel("Weather:");
		weatherLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		weatherLbl.setBounds(10, 437, 395, 51);
		frame.getContentPane().add(weatherLbl);
		
		JLabel foodQtyLbl = new JLabel("food");
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		foodQtyLbl.setBounds(415, 127, 137, 51);
		frame.getContentPane().add(foodQtyLbl);
		
		rationsQtyLbl = new JLabel("rat");
		rationsQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		rationsQtyLbl.setBounds(415, 189, 137, 51);
		frame.getContentPane().add(rationsQtyLbl);
		
		milTrvlQtyLbl = new JLabel("mil");
		milTrvlQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milTrvlQtyLbl.setBounds(415, 251, 137, 51);
		frame.getContentPane().add(milTrvlQtyLbl);
		
		JLabel milToQtyLbl = new JLabel("mil");
		milToQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milToQtyLbl.setBounds(415, 313, 137, 51);
		frame.getContentPane().add(milToQtyLbl);
		
		trvlSpeedQtyLbl = new JLabel("spd");
		trvlSpeedQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		trvlSpeedQtyLbl.setBounds(415, 375, 137, 51);
		frame.getContentPane().add(trvlSpeedQtyLbl);
		
		JLabel wthrQtyLbl = new JLabel("Good");
		wthrQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		wthrQtyLbl.setBounds(415, 437, 137, 51);
		frame.getContentPane().add(wthrQtyLbl);
		
		JLabel lblNewLabel_6 = new JLabel("PICTURE HERE");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 86));
		lblNewLabel_6.setBounds(630, 208, 607, 341);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel titleLbl = new JLabel("Oregon Trail");
		titleLbl.setFont(new Font("Felix Titling", Font.PLAIN, 50));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(343, 11, 569, 86);
		frame.getContentPane().add(titleLbl);
		
		JLabel dateLbl = new JLabel("Date:");
		dateLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl.setBounds(10, 499, 395, 51);
		frame.getContentPane().add(dateLbl);
		
		JLabel dateQtyLbl = new JLabel("oot");
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(415, 499, 137, 51);
		frame.getContentPane().add(dateQtyLbl);
		
		JButton stopTrvlBtn = new JButton("Stop Travel");
		stopTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
			}
		});
		stopTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		stopTrvlBtn.setBounds(278, 594, 258, 125);
		frame.getContentPane().add(stopTrvlBtn);
	}
}