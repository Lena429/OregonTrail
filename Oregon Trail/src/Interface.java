/**
 * Interface.java
 * 
 * This is the main area for the user to play the Oregon Trail game. It includes a clock that passes and 
 * is used to represent the days changing. It opens different frames depending on how to user interacts. 
 * 
 * @author - Lillyan Stewart
 * @author - Lena Frate
 * @author - Sarah Slusher
 * @version 1.1.1 - April 7 2024
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Interface {

	private JFrame frame;
	private Timer clock;
	private JLabel milTrvlQtyLbl;
	private JLabel rationsQtyLbl;
	private JLabel trvlSpeedQtyLbl;
	private JLabel foodQtyLbl = new JLabel();
	private JFrame frameFour;
	private JLabel dateQtyLbl;
	private JLabel milToQtyLbl;
	private JLabel wthrQtyLbl;
	
	private Weather weather		= new Weather();
	private Travel travel 		= new Travel();
	private Wagon wagon	  		= new Wagon();
	private Store store;
	private Equipment wagWheel 	= new Equipment("Wagon Wheel", 45, 0);
	private Equipment wagAxle 	= new Equipment("Wagon Axle", 45, 0);
	private Equipment wagTong 	= new Equipment("Wagon Tongue", 45, 0);
	private Equipment clothes	= new Equipment("Clothes", 2, 0);
	private Equipment blankets	= new Equipment("Blankets", 2, 0);
	private Equipment water		= new Equipment("Water", 1, 0);
	private Food food	        = new Food("Food", 1, 0, true);

	private Money bank			= new Money(80000);
	private Fort fort1			= new Fort("Kanesville", 100, 1);
	private Fort fort2			= new Fort("Mormon Graveyard", 100, 2);
	private Fort fort3          = new Fort("Fort Boise", 100, 2);
	private Fort fort4          = new Fort("Fort Walla Walla", 100, 3);
	private River river1  		= new River("Grand River", 100);			
	private River river2 		= new River("Missouri River", 100);
	private River river3 		= new River("Loup Fork", 100);
	private River river4 		= new River("Elkhorn River", 100);
	private River river5 		= new River("Platte River", 100);
	private River river6 		= new River("Raft River", 100);
	private River river7 		= new River("Salmon River", 100);
	private River river8 		= new River("Snake River", 100);
	private River river9 		= new River("Columbia River", 100);
	private Landmarks landmark1 = new Landmarks("Chimney Rock", 100); //I am not sure where these would go order wise
	private Landmarks landmark2 = new Landmarks("Scott's Bluff", 100);// I added these just so we'd remember
	
	private ArrayList<Location> locations = new ArrayList<>();
	
	private FortFrame fortFrame = new FortFrame(travel, wagon, food, locations);
	private StopFrame trvlStoppedFrame = new StopFrame(travel, wagon, food, bank);
	private RiverFrame riverFrame = new RiverFrame(locations, bank); 

	
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
		
		// Preloaded wagon 
		wagon.addItem(wagWheel);
		wagon.addItem(wagAxle);
		wagon.addItem(wagTong);
		wagon.addItem(clothes);
		wagon.addItem(blankets);
		wagon.addItem(water);
		wagon.addItem(food);
		
		//initalize forts, rivers and landmarks here in order of appearance on map
		// locations.add(new Fort("name", 100, null));
		locations.add(fort1);
		locations.add(river2);
		locations.add(fort3);
		locations.add(river6);
		locations.add(river1);
		locations.add(fort2);
		locations.add(river3);
		locations.add(river4);
		locations.add(river5);
		locations.add(fort4);
		locations.add(river7);
		locations.add(river8);
		locations.add(river9);
		locations.add(landmark1);
		locations.add(landmark2);
		
		store = new Store(bank, wagon.getItems(), wagon, foodQtyLbl);
		
		initialize();
		
		clock = new javax.swing.Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockActionPerformed(evt);
			}
		});
	}

	//Clock action event that updates the time
	public void clockActionPerformed(ActionEvent evt) {
		// update labels
		milTrvlQtyLbl.setText(travel.updateMilesTravelled() + "");
		trvlSpeedQtyLbl.setText(travel.getPace() + "");
		rationsQtyLbl.setText(travel.displayRations());
		wagon.removeItemQty(food, travel.getRations() * 4);
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		dateQtyLbl.setText(travel.updateDate() + "");
		
		// Determines if the weather label needs to be updated
		if (weather.isWeatherDifferent()) {
			// yes it does
			weather.setZone(travel.getMilesTravelled());
			weather.calculateWeather(travel.getMonth());
			
			// checks for rain or snow
			if (weather.willItRainOrSnow())
				// updates label w/ rain or snow
				wthrQtyLbl.setText(weather.displayRainOrSnow());
			else 
				// updates label with temperature
				wthrQtyLbl.setText(weather.displayTemperature());
		}

		
		for (Location location : locations) {
			if (location.hasvisited()) continue; 							  // moves to next object in ArrayList if it was already visited
		    location.updateMilesAway(travel.getPace());						  // updates the distance to the landmark
		    if (!location.arrivedAtLandmark()) {							  // checks to see if user arrived yet
		    	milToQtyLbl.setText(location.getMilesAway() + ""); 			  // if the user hasn't arrived update how far away the wagon is 
		    	break;
		    } else { 														  // checks to see if the user has arrived at a landmark/fort/river
		        location.updatevisited();									  // updates the object/landmark to be visited by the user 
		        clock.stop();												  // stops the days from passing
		        //dateQtyLbl_3.setText(travel.getDate()); 					  // corrects the date 
		        if(location instanceof River){ 								  // checks to see if it is an instance of river 
		        	riverFrame.openRiverFrame((River) location); 							  // displays river frame 
		        	break;

		        } else if (location instanceof Fort){						  // checks to see if it is an instance of fort 
		        	fortFrame.openFortFrame((Fort) location, store);		  // displays fort frame
		        	break;
		        }
		        else {
		        	frameFour.setVisible(true); 							  // is this for location?
		        	//riverName.setText("testing purposes");
		        }
		    }
		}
    }
				
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		store.openStoreWindow(true);
		
		// This is frame one setup (main frame)
		frame = new JFrame();
		frame.toBack();
		frame.setBounds(100, 100, 1289, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Start travelling again button for frame one
		JButton startTrvlBtn = new JButton("Start Travel");
		startTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.start();
			}
		});
		startTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		startTrvlBtn.setBounds(26, 570, 258, 125);
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
		
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		foodQtyLbl.setBounds(415, 160, 137, 51);
		frame.getContentPane().add(foodQtyLbl);
		
		rationsQtyLbl = new JLabel(travel.displayRations());
		rationsQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		rationsQtyLbl.setBounds(415, 222, 200, 51);
		frame.getContentPane().add(rationsQtyLbl);
		
		milTrvlQtyLbl = new JLabel(travel.getMilesTravelled() + "");
		milTrvlQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milTrvlQtyLbl.setBounds(415, 284, 137, 51);
		frame.getContentPane().add(milTrvlQtyLbl);
		
		milToQtyLbl = new JLabel("100");
		milToQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milToQtyLbl.setBounds(415, 346, 137, 51);
		frame.getContentPane().add(milToQtyLbl);
		
		trvlSpeedQtyLbl = new JLabel(travel.getPace() + "");
		trvlSpeedQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		trvlSpeedQtyLbl.setBounds(415, 408, 137, 51);
		frame.getContentPane().add(trvlSpeedQtyLbl);
		
		wthrQtyLbl = new JLabel("Warm");
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
		
		dateQtyLbl = new JLabel(travel.getDate());
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(676, 631, 284, 51);
		frame.getContentPane().add(dateQtyLbl);
		
		// When this stop button is pushed, the clock is stopped and frame two auto pops up. 
		// Displays inventory 
		JButton stopTrvlBtn = new JButton("Stop Travel");
		stopTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				trvlStoppedFrame.openStopFrame(dateQtyLbl, foodQtyLbl, rationsQtyLbl, trvlSpeedQtyLbl);
			}
		});
		stopTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		stopTrvlBtn.setBounds(294, 570, 258, 125);
		frame.getContentPane().add(stopTrvlBtn);
		
		//Picture of the trail that the wagon is travelling
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/image/trailPic.jpg"));
		JLabel trailImage = new JLabel(icon);
		trailImage.setBounds(575, 108, 684, 511);
		frame.getContentPane().add(trailImage, BorderLayout.PAGE_END);
	}
}
