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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Interface {

	private JFrame frame;
	private JFrame frameImage;
	private Timer clock;
	private JLabel milTrvlQtyLbl;
	private JLabel rationsQtyLbl;
	private JLabel trvlSpeedQtyLbl;
	private JLabel foodQtyLbl;
	private JFrame frameTwo;
	private JFrame frameThree; 
	private JFrame frameFour;
	private JLabel dateQtyLbl;
	private JLabel dateQtyLbl_2;
	private JLabel dateQtyLbl_3;
	private JLabel milToQtyLbl;
	private JLabel fortName;
	private JLabel riverName;
	private JTextArea inventory;
	private JLabel heightNumLbl;
	private JLabel widthNumLbl;
	private JLabel flowNumLbl;
	
	private Trade offer			= new Trade();
	private Travel travel 		= new Travel();
	private Wagon wagon	  		= new Wagon();
	private River river 		= new River();
	private Equipment wagWheel 	= new Equipment("Wagon Wheel", 45, 0);
	private Equipment wagAxle 	= new Equipment("Wagon Axle", 45, 0);
	private Equipment wagTong 	= new Equipment("Wagon Tongue", 45, 0);
	private Equipment clothes	= new Equipment("Clothes", 2, 0);
	private Equipment blankets	= new Equipment("Blankets", 2, 0);
	private Equipment water		= new Equipment("Water", 1, 0);
	private Equipment money		= new Equipment("Money", 0, 800);
	private Food food	        = new Food("Food", 1, 0, true);
	private Fort fort1			= new Fort("Kanesville", 100);
	private Fort fort2			= new Fort("Mormon Graveyard", 200);
	private River river1  		= new River("Platte", 100);
	private River river2 		= new River("Bear River", 400);
	

	private Store store;;

	private ArrayList<Location> locations = new ArrayList<>();
	
	//initalize forts, rivers and landmarks here in order of appearance on map
	
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
		
		
		
		//Preloaded wagon 
		wagon.addItem(wagWheel);
		wagon.addItem(wagAxle);
		wagon.addItem(wagTong);
		wagon.addItem(clothes);
		wagon.addItem(blankets);
		wagon.addItem(water);
		wagon.addItem(food);
		wagon.addItem(money);
		
		
		//locations.add(new Fort("name", 100, null));
		locations.add(fort1);
		locations.add(river1);
		locations.add(fort2);
		locations.add(river2);
				
		// this will break of the order of the array changes
		store = new Store(wagon.getItems().get(7), wagon.getItems(), wagon);
		
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
		
		for (Location location : locations) {
			if (location.hasvisited()) continue; 							  // moves to next object in ArrayList if it was already visited
		    location.updateMilesAway(travel.getPace());						  // updates the distance to the landmark
		    if (!location.arrivedAtLandmark()) {							  // checks to see if user arrived yet
		    	milToQtyLbl.setText(location.getMilesAway() + ""); 			  // if the user hasn't arrived update how far away the wagon is 
		    	break;
		    } else { 														  // checks to see if the user has arrived at a landmark/fort/river
		        location.updatevisited();									  // updates the object/landmark to be visited by the user 
		        clock.stop();												  // stops the days from passing
		        dateQtyLbl_3.setText(travel.getDate()); 					  // corrects the date 
		        if(location instanceof River){ 								  // checks to see if it is an instance of fort 
		        	frameFour.setVisible(true); 							  // displays river name 
		        	riverName.setText("Welcome to " + location.getName());    // displays welcome message 
		        	heightNumLbl.setText(((River) location).getHeight()+ ""); // displays height of river user is at 
		        	flowNumLbl.setText(((River) location).getFlow()); 		  // displays flow of river the user is at 
		        	widthNumLbl.setText(((River) location).getWidth()+ "");   // displays width of the river the user is at
		        	break;
		        } else {													  // since the object wasn't an instance of river, it must be an instance of fort
		        	frameThree.setVisible(true); 							  // displays frame three
		        	fortName.setText("Welcome to " + location.getName());	  // displays fort name
		        	break;
		        }
		    }
		}
    }
				
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		store.StoreWindow();
		
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
		
		foodQtyLbl = new JLabel(wagon.getConsumableWeight() + "");
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
		
		milToQtyLbl = new JLabel("100");
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
		
		// When this stop button is pushed, the clock is stopped and frame two auto pops up. 
		// Displays inventory 
		JButton stopTrvlBtn = new JButton("Stop Travel");
		stopTrvlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				frameTwo.setVisible(true);
				inventory.setText("Wagon Contents: \n" + wagon.displayingInventory());
				dateQtyLbl_2.setText(travel.getDate());
			}
		});
		stopTrvlBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		stopTrvlBtn.setBounds(294, 570, 258, 125);
		frame.getContentPane().add(stopTrvlBtn);
		
		//Picture of the trail that the wagon is travelling
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/image/trailPic.jpg"));
		JLabel trailImage = new JLabel(icon);
		trailImage.setBounds(562, 108, 684, 511);
		frame.getContentPane().add(trailImage, BorderLayout.PAGE_END);
		
		// FRAME ONE ENDS
				
		// This is frame two (wagon inventory, pace, and rations) setup
		frameTwo = new JFrame();
		frameTwo.setBounds(100,100,1289,767);
		frameTwo.setTitle("OPTIONS");
		frameTwo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTwo.setVisible(false);
		
		// ComboBox for miles per day/pace
		JComboBox<String> paceComboBox= new JComboBox<String>();
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setPace(paceComboBox);
			}
		});
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		paceComboBox.setBounds(300, 313, 152, 54);
		paceComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));

		// ComboBox for rations/how much the user would like to eat per day 
		JComboBox<String> rationsComboBox = new JComboBox<String>();
		rationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setRations(rationsComboBox);
			}
		});
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bare Bones", "Meager", "Filling"}));
		rationsComboBox.setBounds(300, 200, 220, 54);
		rationsComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));

		// Inventory of the wagon 
		inventory = new JTextArea();
		inventory.setEditable(false); // Optional: make the text area read-only
		inventory.setWrapStyleWord(true);
		inventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		inventory.setBounds(575, 108, 671, 505);
		
		// Label for frame two changing rations combobox
		JLabel changeRatLbl = new JLabel("Change Rations:");
		changeRatLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changeRatLbl.setBounds(10, 187, 275, 86);
		changeRatLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		
		// Label for frame two changing pace combobox
		JLabel changePaceLbl = new JLabel("Change Pace:");
		changePaceLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changePaceLbl.setBounds(10, 300, 275, 86);
		changePaceLbl.setHorizontalAlignment(SwingConstants.TRAILING);

		// Label for frame two date label
		JLabel dateLbl_2 = new JLabel("Date:");
		dateLbl_2.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl_2.setBounds(586, 631, 93, 51);
		
		dateQtyLbl_2 = new JLabel(travel.getDate());
		dateQtyLbl_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_2.setBounds(676, 631, 284, 51);
		
		// A button that will allow you to rest.
		// When you rest food decreases and a day will pass everytime button is pushed.
		JButton restBtn = new JButton("Rest");
		restBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		restBtn.setBounds(180, 521, 189, 62);
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4); 
				foodQtyLbl.setText(wagon.getConsumableWeight() + "");
				dateQtyLbl_2.setText(travel.getDate());
				dateQtyLbl.setText(travel.getDate());
		        
				// Update the inventory display so user can see correct food value
				inventory.setText("Wagon Contents: \n" + wagon.displayingInventory());
			}
		});
		
		JButton tradeBtn = new JButton("Trade");
		tradeBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		tradeBtn.setBounds(180, 430, 189, 62);
		tradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generates the trade offer
				offer.getTrader(travel.getMilesTravelled());
				offer.getOffer(wagon.getItems());
				
				// day increments and food decrements
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateQtyLbl.setText(travel.getDate());
				dateQtyLbl_2.setText(travel.getDate());
		        // Update the inventory display so user can see correct food value
				inventory.setText("Wagon Contents: \n" + wagon.displayingInventory());
				
				// displays the trade offer in a dialogue box
				String text = offer.displayTradeOffer();;
				String title = "Trade";
				int type = JOptionPane.QUESTION_MESSAGE;
				int response = JOptionPane.showConfirmDialog(frameTwo,  text, title, JOptionPane.YES_NO_OPTION, type);
				
				// checks if the user accepted the trade or not
		        if (response == JOptionPane.YES_OPTION) {
		        	// Yes, add/remove the items and update the inventory display
		        	offer.tradeAccepted(wagon.getItems(), wagon);
					inventory.setText("Wagon Contents: \n" + wagon.displayingInventory());
					
		        } else if (response == JOptionPane.NO_OPTION) {
		            // No, close the dialogue box
		        }
		        
		        // updates the food label on frame 1
		        foodQtyLbl.setText(wagon.getConsumableWeight() + "");
			}
		});
		
		// Panel with added components for frame two 
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(inventory);
		panel.add(changePaceLbl);
		panel.add(paceComboBox);
		panel.add(changeRatLbl);
		panel.add(rationsComboBox);
		panel.add(dateLbl_2);
		panel.add(dateQtyLbl_2);
		panel.add(restBtn);
		panel.add(tradeBtn);
		frameTwo.getContentPane().add(panel);

		// FRAME TWO ENDS
		
        // Creates the frame for fort objects and actions
		frameThree = new JFrame();
		frameThree.setBounds(100, 100, 1289, 767);
		frameThree.setTitle("FORT");
		frameThree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameThree.setVisible(false);
		
		//fort image from outside
		ImageIcon fort = new ImageIcon(this.getClass().getResource("/image/fort copy.png"));
		JLabel forts = new JLabel(fort);
		forts.setBounds(562,108,684,511);
		
		// Creates frame for fort images
		frameImage = new JFrame();
		frameImage.setBounds(100, 100, 1289, 767);
		frameImage.setTitle("Look Around");
		frameImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameImage.setVisible(false);
		
		// image for look around at fort
		ImageIcon image = new ImageIcon(this.getClass().getResource("/image/lookaround copy.png"));
		JLabel fortImage = new JLabel(image);
		fortImage.setBounds(562, 108, 684, 511);
		
		JLabel dateLbl_3 = new JLabel("Date:");
		dateLbl_3.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl_3.setBounds(586, 631, 93, 51);
		
		dateQtyLbl_3 = new JLabel(travel.getDate());
		dateQtyLbl_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_3.setBounds(676, 631, 284, 51);
		
		// Label to hold generated phrases of conversation
		JLabel Gossip = new JLabel("");
		Gossip.setBounds(187, 146, 654, 13);
		
		// player talks to other people inside fort
		// randomly selected phrases from Fort Class
		JButton Talking = new JButton("Talk to people");
		Talking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phrase = fort1.generateRandomPhrase();
               			Gossip.setText(phrase);
			}
		});
		Talking.setBounds(31, 138, 133, 21);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton Rest = new JButton("Rest");
		Rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateQtyLbl.setText(travel.getDate());
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		Rest.setBounds(31, 256, 133, 21);
		
		//player decides to look around at fort
		JButton LookAround = new JButton("Look Around");
		LookAround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameImage.setVisible(true);
			}
		});
		LookAround.setBounds(31, 194, 133, 21);

		// player decides to shop in the store
		JButton Shop = new JButton("Shop");
		Shop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.StoreWindow();
			}
		});
		Shop.setBounds(31, 310, 133, 21);
		
		// Greeting header for the fort frames
	    fortName = new JLabel(" ");
		fortName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
		fortName.setBounds(343, 11, 569, 86);
		
		// panel to hold all fort objects to the frame
		JPanel PanelThree = new JPanel();
		PanelThree.setLayout(null);
		PanelThree.add(Gossip);
		PanelThree.add(Talking);
		PanelThree.add(Rest);
		PanelThree.add(LookAround);
		PanelThree.add(dateLbl_3);
		PanelThree.add(dateQtyLbl_3);
		PanelThree.add(fortName);
		PanelThree.add(Shop);
		PanelThree.add(forts);
		frameThree.getContentPane().add(PanelThree);
		
		// panel for the fort images 
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.add(fortImage);
		frameImage.getContentPane().add(imagePanel);	
	
	
	
		// FRAME THREE ENDS 
		frameFour = new JFrame();
		frameFour.setBounds(100, 100, 1289, 767);
		frameFour.setTitle("River");
		frameFour.setVisible(false);
		frameFour.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
        frameFour.setResizable(false);
        
        //Image of a river for the frame
        ImageIcon riverImage = new ImageIcon(this.getClass().getResource("/image/river image.png"));
        JLabel riverImg = new JLabel(riverImage);
        riverImg.setBounds(562,108,684,511);
    	
		
        // displays river name
	    riverName = new JLabel(" ");
		riverName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
		riverName.setBounds(343, 11, 569, 86);
		
		// this is a height label so the user knows what is being displayed
		JLabel heightLbl = new JLabel("Height:");
		heightLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		heightLbl.setBounds(10, 160, 395, 51);
		
		// this is a flow label so the user knows what is being displayed
		JLabel flowLbl = new JLabel("Flow: ");
		flowLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		flowLbl.setBounds(10, 222, 395, 51);
		
		// this is a widht label so the user knows what is being displayed 
		JLabel widthLbl = new JLabel("Width:");
		widthLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		widthLbl.setBounds(10, 284, 395, 51);
		
		// this displays the height of the river water level 
		heightNumLbl = new JLabel("");
		heightNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		heightNumLbl.setBounds(415, 160, 137, 51);
		
		// this displays the flow of the river
		flowNumLbl = new JLabel("");
		flowNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		flowNumLbl.setBounds(415, 222, 137, 51);
		
		// this displays the width of the river
		widthNumLbl = new JLabel("");
		widthNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		widthNumLbl.setBounds(415, 284, 137, 51);
		
		JLabel crossingLbl = new JLabel("Cross the river yourself ");
		crossingLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		crossingLbl.setBounds(29, 383, 832, 83);
		
		JLabel lblOrPayThe = new JLabel("or pay the ferry($8)?");
		lblOrPayThe.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		lblOrPayThe.setBounds(39, 445, 321, 62);
		

		//button to cross river with wagon 
		JButton crossBtn = new JButton("Cross");
		crossBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, river.randomEvtCross(money)); // Displays if the user made it across safely, or with consequences
				frameFour.dispose();		  									  // closes frame after button is hit
			}
		});
		crossBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		crossBtn.setBounds(200, 508, 137, 51);
		
		// button to cross river on ferry 
		JButton ferryBtn = new JButton("Ferry");
		ferryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.removeItemQty(money, 8); 									  // removes money because user paid to cross with ferry 
				JOptionPane.showMessageDialog(null, river.randomEvtFerry(money)); // Displays if the user made it across safely, or with consequences
				frameFour.dispose();		   									  // closes frame after button hit
			}
		});
		ferryBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		ferryBtn.setBounds(10, 508, 137, 51);

		
		// panel for frame four 
		JPanel panelFour = new JPanel();
		panelFour.setLayout(null);
		panelFour.add(heightLbl);
		panelFour.add(flowLbl);
		panelFour.add(widthLbl);
		panelFour.add(heightNumLbl);
		panelFour.add(flowNumLbl);
		panelFour.add(widthNumLbl);
		panelFour.add(riverName);
		panelFour.add(crossingLbl);
		panelFour.add(lblOrPayThe);
		panelFour.add(crossBtn);
		panelFour.add(ferryBtn);
		panelFour.add(riverImg);
		frameFour.getContentPane().add(panelFour);
		
}
}
