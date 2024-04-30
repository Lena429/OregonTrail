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
import javax.swing.JOptionPane;
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
	private JLabel milesToNextLbl;
	private JLabel healthQtyLbl;
	
	private Weather weather		= new Weather();
	private Travel travel 		= new Travel();
	private Wagon wagon	  		= new Wagon();
	private WagonParty health   = new WagonParty();
	private Store store;
	// Equipment
	private Equipment wagWheel 	= new Equipment("Wagon Wheel", 45, 0);
	private Equipment wagAxle 	= new Equipment("Wagon Axle", 45, 0);
	private Equipment wagTong 	= new Equipment("Wagon Tongue", 45, 0);
	private Equipment clothes	= new Equipment("Clothes", 2, 0);
	private Equipment blankets	= new Equipment("Blankets", 2, 0);
	private Equipment water		= new Equipment("Water", 1, 0);
	private Food food	        = new Food("Food", 1, 0, true);
	private Money bank			= new Money(80000);
	// Locations
	private Fort fort1			= new Fort("Kanesville", 83, 1);
	private Fort fort2			= new Fort("Mormon Graveyard", 97, 2);
	private Fort fort3          = new Fort("Fort Boise", 91, 2);
	private Fort fort4          = new Fort("Fort Laramie", 129, 3);
	private River river1  		= new River("Grand River", 85);			
	private River river2 		= new River("Missouri River", 86);
	private River river3 		= new River("Loup Fork", 188);
	private River river4 		= new River("Elkhorn River", 106);
	private River river5 		= new River("Platte River", 80);
	private River river6 		= new River("Raft River", 207);
	private River river7 		= new River("Snake River", 294);
	private River river8 		= new River("Columbia River", 93);
	private Landmarks landmark1 = new Landmarks("Chimney Rock", 101);
	private Landmarks landmark2 = new Landmarks("Scott's Bluff", 48);
	private Landmarks landmark3 = new Landmarks("Independence Rock", 327);
	private Location house		= new Location("New House", 53);
	// Wagon Members
	private WagonMember one		= new WagonMember("Amy");
	private WagonMember two		= new WagonMember("Bonnie");
	private WagonMember three	= new WagonMember("Cora");
	private WagonMember four	= new WagonMember("DeeDee");
	
	private ArrayList<Location> locations = new ArrayList<>();
	
	private FortFrame fortFrame 	    = new FortFrame(travel, wagon, food, locations, bank);
	private StopFrame trvlStoppedFrame  = new StopFrame(travel, wagon, food, bank);
	private RiverFrame riverFrame 		= new RiverFrame(locations, bank, travel); 
	private LandmarkFrame landmarkFrame = new LandmarkFrame(travel, wagon, food, locations);
	
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
		locations.add(river1);
		locations.add(fort1);
		locations.add(river2);
		locations.add(fort2);
		locations.add(river4);
		locations.add(river3);
		locations.add(river5);
		locations.add(landmark1);
		locations.add(landmark2);
		locations.add(fort4);
		locations.add(landmark3);
		locations.add(river6);
		locations.add(fort3);
		locations.add(river7);
		locations.add(river8);
		locations.add(house);
		
		// adding members to the wagon party
		health.addMember(one);
		health.addMember(two);
		health.addMember(three);
		health.addMember(four);

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
		dateQtyLbl.setText(travel.updateDate() + "");
		
		// update health and food
		if(!food.outOfFood()) {
			// if there is food to remove, remove it
			wagon.removeItemQty(food, travel.getRations() * health.getAmountOfMembers());
		} 
		// set the food label
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		
		// regenerate health then lose some
		health.recoverDailyHealth();
		health.loseHealth(travel, food.outOfFood(), weather.displayTemperature(), clothes);
		
		// check if the health is deadly
		if(health.isHealthDeadly()) {
			// update the health label and kill a random member
			healthQtyLbl.setText("Deadly");
			String name = health.removeRandomMember();
			
			if(!health.membersStillAlive()) {
				// if they are the last member remaining, end the game
				String text = "All members of the wagon have perished :(";
				String title = "Game Over!";
				int type = JOptionPane.ERROR_MESSAGE;
				int response = JOptionPane.showConfirmDialog(frame,  text, title, JOptionPane.DEFAULT_OPTION, type);
				if(response == JOptionPane.OK_OPTION) System.exit(1);
			} else {
				// notify user of the member that died
				String text = name + " has died.";
				String title = "OH NO!";
				int type = JOptionPane.ERROR_MESSAGE;
				JOptionPane.showConfirmDialog(frame,  text, title, JOptionPane.DEFAULT_OPTION, type);
			}
		} else healthQtyLbl.setText(health.displayHealth()); // update health
		
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
		    	milesToNextLbl.setText("Miles to " + location.getName() + ":");
		    	milToQtyLbl.setText(location.getMilesAway() + ""); 			  // if the user hasn't arrived update how far away the wagon is 
		    	break;
		    } else { 														  // checks to see if the user has arrived at a landmark/fort/river
		        location.updatevisited();									  // updates the object/landmark to be visited by the user 
		        clock.stop();												  // stops the days from passing
		        //dateQtyLbl_3.setText(travel.getDate()); 					  // corrects the date 
		        if(location instanceof River){ 								  // checks to see if it is an instance of river 
		        	riverFrame.openRiverFrame((River) location, dateQtyLbl, foodQtyLbl); 							  // displays river frame 
		        	break;

		        } else if (location instanceof Fort){						  // checks to see if it is an instance of fort 
		        	fortFrame.openFortFrame((Fort) location, store);		  // displays fort frame
		        	break;
		        }else if(location instanceof Landmarks) {
		        	landmarkFrame.openLandmarkFrame((Landmarks)location);
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
		
		JLabel HealthLbl = new JLabel("Health:");
		HealthLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		HealthLbl.setBounds(10, 110, 395, 51);
		frame.getContentPane().add(HealthLbl);
		
		JLabel foodLbl = new JLabel("Food (lbs):");
		foodLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		foodLbl.setBounds(10, 163, 395, 51);
		frame.getContentPane().add(foodLbl);
		
		JLabel rationsLbl = new JLabel("Rations:");
		rationsLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		rationsLbl.setBounds(10, 222, 395, 51);
		frame.getContentPane().add(rationsLbl);
		
		JLabel milesTravelledLbl = new JLabel("Miles Travelled:");
		milesTravelledLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesTravelledLbl.setBounds(10, 284, 395, 51);
		frame.getContentPane().add(milesTravelledLbl);
		
		milesToNextLbl = new JLabel("Miles to " + river1.getName() + ":");
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
		
		healthQtyLbl = new JLabel(health.displayHealth());
		healthQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		healthQtyLbl.setBounds(415, 110, 137, 51);
		frame.getContentPane().add(healthQtyLbl);
		
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		foodQtyLbl.setBounds(415, 163, 137, 51);
		frame.getContentPane().add(foodQtyLbl);
		
		rationsQtyLbl = new JLabel(travel.displayRations());
		rationsQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		rationsQtyLbl.setBounds(415, 222, 200, 51);
		frame.getContentPane().add(rationsQtyLbl);
		
		milTrvlQtyLbl = new JLabel(travel.getMilesTravelled() + "");
		milTrvlQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milTrvlQtyLbl.setBounds(415, 284, 137, 51);
		frame.getContentPane().add(milTrvlQtyLbl);
		
		milToQtyLbl = new JLabel(river1.getMilesAway() + "");
		milToQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milToQtyLbl.setBounds(415, 346, 137, 51);
		frame.getContentPane().add(milToQtyLbl);
		
		trvlSpeedQtyLbl = new JLabel(travel.getPace() + "");
		trvlSpeedQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		trvlSpeedQtyLbl.setBounds(415, 408, 137, 51);
		frame.getContentPane().add(trvlSpeedQtyLbl);
		
		wthrQtyLbl = new JLabel("Warm");
		wthrQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		wthrQtyLbl.setBounds(415, 470, 150, 51);
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
