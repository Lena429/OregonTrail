/**
 * RiverFrame.java
 * 
 * Creates the GUI for the river frame that is to be displayed when the user arrives
 * at a river
 * 
 * @author - Lillyan Stewart
 * @author - Lena Frate
 * @version 1.1.5 - May 4 2024
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class RiverFrame {
	
	private Money bank;
	private TravelManager travel;
	private Wagon wagon;
	private Food food; 
	private Equipment water;
	private Equipment oxen;

	/**
	 * Creates an object of riverFrame that stores instances of Money, TravelManager, Wagon, and Equipment
	 * @param bank - the amount of money the user has
	 * @param travel - an object that stores date, rations, miles, and pace
	 * @param wagon - an object that stores/manages the inventory
	 * @param food - the amount of food the user has
	 */
	public RiverFrame(Money bank, TravelManager travel, Wagon wagon, Food food, Equipment oxen, Equipment water) {
		this.bank = bank;
		this.travel = travel; 
		this.wagon = wagon;
		this.food = food;
		this.water = water;
		this.oxen = oxen;
	}
	
	/**
	 * creates a river frame that cannot be closed until a way to cross the river has been chosen
	 * @param currentRiver - the river being crossed
	 * @param dateMainLbl - the date label from the main frame to update
	 * @param foodMainLbl - the food label from the main frame to update
	 * @param membersAlive - the mount of members alive
	 */
	public void openRiverFrame(River currentRiver, JLabel dateMainLbl, JLabel foodMainLbl, JLabel wthrQtyLbl, int membersAlive) {
		TradeManager offer	= new TradeManager();
		RandomEvents randomEvents   = new RandomEvents(wagon, oxen, food);

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1289, 767);
		frame.setTitle("River");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
        frame.setResizable(false);
        
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setLayout(null);
        
        // Image of a river for the frame
        ImageIcon riverImage = new ImageIcon(this.getClass().getResource("/image/river image.png"));
        JLabel riverImg = new JLabel(riverImage);
        riverImg.setText("");
        riverImg.setBounds(601, 184, 645, 391);
		panel.add(riverImg);
		
        // displays river name
	    JLabel riverName = new JLabel("Welcome to " + currentRiver.getName());
	    riverName.setForeground(new Color(255, 255, 255));
	    riverName.setHorizontalAlignment(SwingConstants.CENTER);
	    riverName.setBounds(10, 11, 1255, 79);
		riverName.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		panel.add(riverName);
		
		// this is a height label so the user knows what is being displayed
		JLabel heightLbl = new JLabel("Height:");
		heightLbl.setForeground(new Color(255, 255, 255));
		heightLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		heightLbl.setBounds(83, 90, 158, 51);
		heightLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		panel.add(heightLbl);
		
		// this is a flow label so the user knows what is being displayed
		JLabel flowLbl = new JLabel("Flow:");
		flowLbl.setForeground(new Color(255, 255, 255));
		flowLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		flowLbl.setBounds(444, 90, 158, 51);
		flowLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		panel.add(flowLbl);
		
		// this is a width label so the user knows what is being displayed 
		JLabel widthLbl = new JLabel("Width:");
		widthLbl.setForeground(new Color(255, 255, 255));
		widthLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		widthLbl.setBounds(851, 90, 158, 51);
		widthLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		panel.add(widthLbl);
		
		// this displays the height of the river water level 
		JLabel heightNumLbl = new JLabel("");
		heightNumLbl.setForeground(new Color(255, 255, 255));
		heightNumLbl.setBounds(251, 90, 137, 51);
		heightNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(heightNumLbl);
		
		// this displays the flow of the river
		JLabel flowNumLbl = new JLabel("");
		flowNumLbl.setForeground(new Color(255, 255, 255));
		flowNumLbl.setBounds(612, 90, 137, 51);
		flowNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(flowNumLbl);

		// this displays the width of the river
		JLabel widthNumLbl = new JLabel("");
		widthNumLbl.setForeground(new Color(255, 255, 255));
		widthNumLbl.setBounds(1019, 90, 137, 51);
		widthNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(widthNumLbl);
		
		// this asks the users what how they would like to traverse the river. 
		JLabel crossingLbl = new JLabel("How would you like to traverse the river?");
		crossingLbl.setForeground(new Color(255, 255, 255));
		crossingLbl.setBounds(23, 592, 741, 58);
		crossingLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(crossingLbl);
		
		// This displays the actual date 
		JLabel 	dateQtyLbl = new JLabel(travel.getDate());
		dateQtyLbl.setForeground(new Color(255, 255, 255));
		dateQtyLbl.setHorizontalAlignment(SwingConstants.LEFT);
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(902, 592, 333, 51);
		panel.add(dateQtyLbl);
		
		// this tells the user what the number represent
		JLabel dateLbl = new JLabel ("Date:");
		dateLbl.setForeground(new Color(255, 255, 255));
		dateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl.setBounds(734, 592, 158, 51);
		panel.add(dateLbl);
		
		//button to cross river with wagon 
		JButton fordBtn = new JButton("Ford the river");
		fordBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fordBtn.setBounds(370, 654, 267, 51);
		fordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, randomEvents.oxAndFood(currentRiver.fording())); // Displays if the user made it across safely, or with consequences
				frame.dispose();		  											                 // closes frame after button is hit
			}
		});
		fordBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(fordBtn);
		
		// button to cross river on ferry 
		JButton ferryBtn = new JButton("Pay the Ferry ($8)");
		ferryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ferryBtn.setBounds(40, 654, 320, 51);
		ferryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bank.spendMoney(800);									  	  // removes money because user paid to cross with ferry 
				JOptionPane.showMessageDialog(null, randomEvents.oxJumped()); // Displays if the user made it across safely, or with consequences
				frame.dispose();		   				                      // closes frame after button hit
			}
		});
		ferryBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		// the user does not have enough money to pay for the ferry so disable the option
		if(!bank.isMoneyAvailable(8)) ferryBtn.setEnabled(false);
		panel.add(ferryBtn);

		JButton caulkBtn = new JButton("Caulk the wagon");
		caulkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		caulkBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		caulkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, randomEvents.oxAndFood(currentRiver.caulking())); // Displays if the user made it across safely, or with consequences
				frame.dispose();		   											                  // closes frame after button hit
			}
		});
		caulkBtn.setBounds(647, 654, 308, 51);
		panel.add(caulkBtn);
		
		JButton waitBtn = new JButton("Wait");
		waitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		waitBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		waitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Passes one day and does everything for when a day passes. 
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * membersAlive); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateQtyLbl.setText(travel.getDate());
				dateMainLbl.setText(travel.getDate());
	
				River.openFile();
		    	heightNumLbl.setText(currentRiver.setHeight(wthrQtyLbl)+ ""); // displays height of river user is at 
		    	flowNumLbl.setText(currentRiver.setFlow(wthrQtyLbl)); 		  // displays flow of river the user is at 
		    	widthNumLbl.setText(currentRiver.setWidth(wthrQtyLbl)+ "");   // displays width of the river the user is at
		    	River.closeFile();	
				
				}
		});
		waitBtn.setBounds(982, 654, 253, 51);
		panel.add(waitBtn);
		
		JTextPane conversationPane = new JTextPane();
		conversationPane.setEditable(false);
		conversationPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		conversationPane.setBounds(178, 382, 394, 204);
		panel.add(conversationPane);
		
		JButton talkBtn = new JButton("Talk");
		talkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		talkBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		talkBtn.setBounds(10, 382, 158, 60);
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generates a random phrase for the specific fort
				conversationPane.setText(currentRiver.generatePhrase());
				// disables the button after first click
				talkBtn.setEnabled(false);
			}
		});
		panel.add(talkBtn);
		
		JButton inventoryBtn = new JButton("Inventory");
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// creates a new small frame to display the inventory
				JFrame riverInventory = new JFrame();
				riverInventory.setTitle("Inventory");
				riverInventory.setBounds(470, 317, 450, 375);
				riverInventory.setVisible(true);
				
				JTextArea currentInventory = new JTextArea(wagon.displayingInventory() + bank.displayMoney());
				currentInventory.setEditable(false);
				currentInventory.setWrapStyleWord(true);
				currentInventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
				
				JPanel riverInventoryPanel = new JPanel();
				riverInventoryPanel.setLayout(null);
				riverInventoryPanel.add(currentInventory);
				riverInventory.getContentPane().add(currentInventory);
			}
		});
		inventoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		inventoryBtn.setBounds(10, 170, 562, 80);
		panel.add(inventoryBtn);
		
		JButton tradeBtn = new JButton("Trade");
		tradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generates the trade offer
				offer.getTrader(travel.getMilesTravelled());
				offer.getOffer(wagon.getItems());
				
				// displays the trade offer in a dialogue box
				int response = offer.displayOffer();
				// checks if the user accepted the trade or not
		        if (response == JOptionPane.YES_OPTION) {
		        	// Yes, add/remove the items and update the inventory display
		        	offer.tradeAccepted(wagon.getItems(), wagon);
		        }
		        
				// day increments and food/water decrements
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * membersAlive);
				wagon.removeItemQty(water, membersAlive);
		        
				// Update the labels so user can see correct values
				dateMainLbl.setText(travel.getDate());
				dateQtyLbl.setText(travel.getDate());
		        
		        // updates the food label on the main frame
		        foodMainLbl.setText(wagon.getConsumableWeight() + "");
			}
		});
		tradeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tradeBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		tradeBtn.setBounds(10, 270, 562, 80);
		panel.add(tradeBtn);
		
		frame.getContentPane().add(panel);
		
		// reads the river file and updates the river flow, height, and width 
    	River.openFile();
    	heightNumLbl.setText(currentRiver.setHeight(wthrQtyLbl)+ ""); // displays height of river user is at 
    	flowNumLbl.setText(currentRiver.setFlow(wthrQtyLbl)); 		  // displays flow of river the user is at 
    	widthNumLbl.setText(currentRiver.setWidth(wthrQtyLbl)+ "");   // displays width of the river the user is at
    	River.closeFile();	
	}
}
