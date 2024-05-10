/**
 * StopFrame.java
 * 
 * The stop frame class handles the GUI for the frame that appears
 * when the user chooses to stop travelling
 * 
 * @author Lena Frate
 * @version 1.2.1 May 2 2024
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class StopFrame {
	
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private Equipment water;
	private Money bank;
	private WagonParty health;
	
	/**
	 * Creates an object of stop frame containing the necessary variables for the frame
	 * @param travel - needed to access pace, rations, and date
	 * @param wagon - needed for access to the inventory
	 * @param bank - needed to display the user's money
	 * @param health - needed to access the amount of members
	 * @param food - needed to update the food total
	 * @param water - needed to update the water total
	 */
	public StopFrame(TravelManager travel, Wagon wagon, Money bank, WagonParty health, Food food, Equipment water) {
		this.travel = travel;
		this.wagon = wagon;
		this.bank = bank;
		this.health = health;
		this.food = food;
		this.water = water;
	}
	
	/**
	 * opens the stop frame so the user can access things like inventory, change pace, change rations, and rest or make tea
	 * @param dateMainLbl - the date label from the main frame
	 * @param foodMainLbl - the food label from the main frame
	 * @param rationsMainLbl - the rations label from the main frame
	 * @param paceMainLbl - the pace label from the main frame
	 * @param teatime - the mini-game from the TeaTime class
	 */
	public void openStopFrame(JLabel dateMainLbl, JLabel foodMainLbl, JLabel rationsMainLbl, JLabel paceMainLbl, TeaTime teatime) {
		TradeManager offer	= new TradeManager();
				
		JFrame frame = new JFrame();
		frame.setBounds(100,100,1289,767);
		frame.setTitle("OPTIONS");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setLayout(null);
		
		// ComboBox for miles per day/pace
		JComboBox<String> paceComboBox= new JComboBox<String>();
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setPace(paceComboBox);
				paceMainLbl.setText(travel.getPace() + "");
			}
		});
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		paceComboBox.setBounds(930, 541, 190, 54);
		paceComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(paceComboBox);

		// ComboBox for rations/how much the user would like to eat per day 
		JComboBox<String> rationsComboBox = new JComboBox<String>();
		rationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setRations(rationsComboBox);
				rationsMainLbl.setText(travel.displayRations());
			}
		});
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bare Bones", "Meager", "Filling"}));
		rationsComboBox.setBounds(360, 541, 220, 54);
		rationsComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		panel.add(rationsComboBox);

		// Inventory of the wagon 
		JTextArea inventory = new JTextArea(wagon.displayingInventory() + "\n" + bank.displayMoney());
		inventory.setForeground(new Color(255, 255, 255));
		inventory.setOpaque(false);
		inventory.setEditable(false);
		inventory.setWrapStyleWord(true);
		inventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 34));
		inventory.setBounds(107, 145, 484, 375);
		panel.add(inventory);
		
		JLabel inventoryLbl = new JLabel("Inventory:");
		inventoryLbl.setHorizontalAlignment(SwingConstants.LEFT);
		inventoryLbl.setForeground(Color.WHITE);
		inventoryLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 36));
		inventoryLbl.setBounds(107, 88, 200, 54);
		panel.add(inventoryLbl);
		
		// Label for frame two changing rations combobox
		JLabel changeRatLbl = new JLabel("Change Rations:");
		changeRatLbl.setForeground(new Color(255, 255, 255));
		changeRatLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changeRatLbl.setBounds(75, 541, 275, 54);
		changeRatLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(changeRatLbl);
		
		// Label for frame two changing pace combobox
		JLabel changePaceLbl = new JLabel("Change Pace:");
		changePaceLbl.setForeground(new Color(255, 255, 255));
		changePaceLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changePaceLbl.setBounds(645, 541, 275, 54);
		changePaceLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(changePaceLbl);

		// Label for frame two date label
		JLabel dateLbl = new JLabel("Date:");
		dateLbl.setForeground(new Color(255, 255, 255));
		dateLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl.setBounds(726, 469, 93, 51);
		panel.add(dateLbl);
		
		JLabel dateQtyLbl = new JLabel(travel.getDate());
		dateQtyLbl.setForeground(new Color(255, 255, 255));
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(821, 469, 318, 51);
		panel.add(dateQtyLbl);
		
		// A button that will allow the user to rest.
		// When you rest food decreases and a day will pass every time button is pushed.
		JButton restBtn = new JButton("Rest");
		restBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		restBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		restBtn.setBounds(771, 360, 295, 86);
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * health.getAmountOfMembers());
				wagon.removeItemQty(water, health.getAmountOfMembers());				
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateQtyLbl.setText(travel.getDate());
				dateMainLbl.setText(travel.getDate());
		        
				// Update the inventory display so user can see correct food value
				inventory.setText(wagon.displayingInventory() + "\n" + bank.displayMoney());
			}
		});
		panel.add(restBtn);
		
		// a button that allows the user to trade
		JButton tradeBtn = new JButton("Trade");
		tradeBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		tradeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tradeBtn.setBounds(771, 248, 295, 86);
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
				wagon.removeItemQty(food, travel.getRations() * health.getAmountOfMembers());
				wagon.removeItemQty(water, health.getAmountOfMembers());
		        
				// Update the labels so user can see correct values
				dateMainLbl.setText(travel.getDate());
				dateQtyLbl.setText(travel.getDate());
				inventory.setText(wagon.displayingInventory() + "\n" + bank.displayMoney());
		        
		        // updates the food label on the main frame
		        foodMainLbl.setText(wagon.getConsumableWeight() + "");
			}
		});
		panel.add(tradeBtn);
		
		JLabel titleLbl = new JLabel("What would you like to do?");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		titleLbl.setBounds(227, 21, 797, 69);
		panel.add(titleLbl);
		
		// a button that allows the user to go back to the main frame
		JButton btnContinueTrail = new JButton("Continue Trail");
		btnContinueTrail.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		btnContinueTrail.setBounds(48, 630, 1170, 75);
		btnContinueTrail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContinueTrail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// closes the frame
				frame.dispose();
			}
		});
		panel.add(btnContinueTrail);
		frame.getContentPane().add(panel);		
		
		JButton teaBtn = new JButton("Tea Time");
		teaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teatime.openTeaTime();
				// disable button to prohibit spamming of health 
				teaBtn.setEnabled(false);
			}
		});
		teaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		teaBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		teaBtn.setBounds(771, 133, 295, 86);
		panel.add(teaBtn);
	}
}
