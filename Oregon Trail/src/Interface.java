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
import java.awt.Color;
import java.awt.Cursor;

public class Interface {

	private JFrame frame;
	private Timer clock;
	private JLabel milTrvlQtyLbl;
	private JLabel rationsQtyLbl;
	private JLabel trvlSpeedQtyLbl;
	private JLabel foodQtyLbl = new JLabel();
	private JLabel dateQtyLbl;
	private JLabel milToQtyLbl;
	private JLabel wthrQtyLbl;
	private JLabel milesToNextLbl;
	private JLabel healthQtyLbl;
	
	private Weather weather		 = new Weather();
	private TravelManager travel = new TravelManager();
	private Wagon wagon	  		 = new Wagon();
	private WagonParty health    = new WagonParty();
	private Store store;
	// Equipment
	private Equipment wagWheel 	= new Equipment("Wagon Wheel", 300, 0);
	private Equipment wagAxle 	= new Equipment("Wagon Axle", 45, 0);
	private Equipment wagTong 	= new Equipment("Wagon Tongue", 45, 0);
	private Equipment clothes	= new Equipment("Clothes", 5, 0);
	private Equipment oxen		= new Equipment("Oxen", 1000, 0);
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
		
		// wagon items
		wagon.addItem(wagWheel);
		wagon.addItem(wagAxle);
		wagon.addItem(wagTong);
		wagon.addItem(clothes);
		wagon.addItem(oxen);
		wagon.addItem(water);
		wagon.addItem(food);
		
		// initialize forts, rivers and landmarks here in order of appearance on map
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
			health.removeRandomMember(frame);
			
			if(!health.membersStillAlive())
				// if they are the last member remaining, end the game
				health.displayGameOver(frame);

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
		        } else if(location instanceof Landmarks) {					  // checks to see if it is an instance of landmark 
		        	landmarkFrame.openLandmarkFrame((Landmarks)location);	  // displays landmark frame
		        	break;
		        } else {
		        	// THE USER HAS WON THE GAME (arrived at the house in Oregon)
		    		String text = "Congratulations! You successfully traveled the Oregon Trail.";
		    		String title = "You made it to Oregon!";
		    		int type = JOptionPane.PLAIN_MESSAGE;
		    		int response = JOptionPane.showConfirmDialog(frame,  text, title, JOptionPane.DEFAULT_OPTION, type);
		    		if(response == JOptionPane.OK_OPTION) System.exit(1);
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
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.toBack();
		frame.setTitle("Trail");
		frame.setBounds(100, 100, 1289, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Start travelling again button for frame one
		JButton startTrvlBtn = new JButton("Start Travel");
		startTrvlBtn.setForeground(new Color(255, 255, 255));
		startTrvlBtn.setBackground(new Color(0, 128, 0));
		startTrvlBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		startTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if the user doesn't have any oxen to pull the wagon then they cannot travel
				if(oxen.getQuantity() == 0) {
		    		String text = "You do not have any oxen to pull the wagon. Try trading for some...";
		    		String title = "Uh-oh";
		    		int type = JOptionPane.ERROR_MESSAGE;
		    		JOptionPane.showConfirmDialog(frame,  text, title, JOptionPane.DEFAULT_OPTION, type);
				} else clock.start();
			}
		});
		startTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 40));
		startTrvlBtn.setBounds(20, 425, 298, 125);
		frame.getContentPane().add(startTrvlBtn);
		
		JLabel healthLbl = new JLabel("Health: ");
		healthLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		healthLbl.setForeground(new Color(255, 255, 255));
		healthLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		healthLbl.setBounds(314, 430, 298, 51);
		frame.getContentPane().add(healthLbl);
		
		JLabel foodLbl = new JLabel("Food (lbs): ");
		foodLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		foodLbl.setForeground(new Color(255, 255, 255));
		foodLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		foodLbl.setBounds(314, 499, 298, 51);
		frame.getContentPane().add(foodLbl);
		
		JLabel rationsLbl = new JLabel(" Rations: ");
		rationsLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		rationsLbl.setForeground(new Color(255, 255, 255));
		rationsLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		rationsLbl.setBounds(712, 430, 298, 51);
		frame.getContentPane().add(rationsLbl);
		
		JLabel milesTravelledLbl = new JLabel("Miles Travelled: ");
		milesTravelledLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		milesTravelledLbl.setForeground(new Color(255, 255, 255));
		milesTravelledLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesTravelledLbl.setBounds(314, 575, 298, 51);
		frame.getContentPane().add(milesTravelledLbl);
		
		milesToNextLbl = new JLabel("Miles to Grand River: ");
		milesToNextLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		milesToNextLbl.setForeground(new Color(255, 255, 255));
		milesToNextLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		milesToNextLbl.setBounds(313, 644, 550, 51);
		frame.getContentPane().add(milesToNextLbl);
		
		JLabel travelSpeedLbl = new JLabel("Travel Speed: ");
		travelSpeedLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		travelSpeedLbl.setForeground(new Color(255, 255, 255));
		travelSpeedLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		travelSpeedLbl.setBounds(712, 501, 298, 51);
		frame.getContentPane().add(travelSpeedLbl);
		
		JLabel weatherLbl = new JLabel("Weather: ");
		weatherLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		weatherLbl.setForeground(new Color(255, 255, 255));
		weatherLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		weatherLbl.setBounds(712, 575, 298, 51);
		frame.getContentPane().add(weatherLbl);
		
		healthQtyLbl = new JLabel(health.displayHealth());
		healthQtyLbl.setForeground(new Color(255, 255, 255));
		healthQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		healthQtyLbl.setBounds(622, 430, 137, 51);
		frame.getContentPane().add(healthQtyLbl);
		foodQtyLbl.setForeground(new Color(255, 255, 255));
		
		foodQtyLbl.setText(wagon.getConsumableWeight() + "");
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		foodQtyLbl.setBounds(622, 499, 137, 51);
		frame.getContentPane().add(foodQtyLbl);
		
		rationsQtyLbl = new JLabel(travel.displayRations());
		rationsQtyLbl.setForeground(new Color(255, 255, 255));
		rationsQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		rationsQtyLbl.setBounds(1020, 430, 245, 51);
		frame.getContentPane().add(rationsQtyLbl);
		
		milTrvlQtyLbl = new JLabel(travel.getMilesTravelled() + "");
		milTrvlQtyLbl.setForeground(new Color(255, 255, 255));
		milTrvlQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milTrvlQtyLbl.setBounds(622, 575, 137, 51);
		frame.getContentPane().add(milTrvlQtyLbl);
		
		milToQtyLbl = new JLabel(river1.getMilesAway() + "");
		milToQtyLbl.setForeground(new Color(255, 255, 255));
		milToQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		milToQtyLbl.setBounds(873, 644, 137, 51);
		frame.getContentPane().add(milToQtyLbl);
		
		trvlSpeedQtyLbl = new JLabel(travel.getPace() + "");
		trvlSpeedQtyLbl.setForeground(new Color(255, 255, 255));
		trvlSpeedQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		trvlSpeedQtyLbl.setBounds(1020, 501, 137, 51);
		frame.getContentPane().add(trvlSpeedQtyLbl);
		
		wthrQtyLbl = new JLabel("Warm");
		wthrQtyLbl.setForeground(new Color(255, 255, 255));
		wthrQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		wthrQtyLbl.setBounds(1020, 575, 235, 51);
		frame.getContentPane().add(wthrQtyLbl);
		
		JLabel titleLbl = new JLabel("Oregon Trail");
		titleLbl.setForeground(new Color(255, 255, 255));
		titleLbl.setFont(new Font("Felix Titling", Font.PLAIN, 55));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(351, 0, 569, 86);
		frame.getContentPane().add(titleLbl);
		
		JLabel dateLbl = new JLabel("Date: ");
		dateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLbl.setForeground(new Color(255, 255, 255));
		dateLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 25));
		dateLbl.setBounds(20, 48, 74, 51);
		frame.getContentPane().add(dateLbl);
		
		dateQtyLbl = new JLabel(travel.getDate());
		dateQtyLbl.setForeground(new Color(255, 255, 255));
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		dateQtyLbl.setBounds(99, 48, 320, 51);
		frame.getContentPane().add(dateQtyLbl);
		
		// When this stop button is pushed, the clock is stopped and frame two auto pops up. 
		// Displays inventory 
		JButton stopTrvlBtn = new JButton("Stop Travel");
		stopTrvlBtn.setForeground(new Color(255, 255, 255));
		stopTrvlBtn.setBackground(new Color(185, 0, 0));
		stopTrvlBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stopTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				trvlStoppedFrame.openStopFrame(dateQtyLbl, foodQtyLbl, rationsQtyLbl, trvlSpeedQtyLbl);
			}
		});
		stopTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 40));
		stopTrvlBtn.setBounds(20, 582, 298, 125);
		frame.getContentPane().add(stopTrvlBtn);
		
		//Picture of the trail that the wagon is travelling
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/image/trailPic.jpg"));
		JLabel trailImage = new JLabel(icon);
		trailImage.setOpaque(true);
		trailImage.setBounds(20, 90, 1233, 305);
		frame.getContentPane().add(trailImage, BorderLayout.PAGE_END);
	}
}
