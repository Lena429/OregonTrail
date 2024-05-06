/**
 * Trade.java
 * 
 * The trade class generates a random trade offer that the user can either accept or decline.
 * If accepted, the appropriate items will be added and removed from their inventory.
 * 
 * @author - Lena Frate
 * @version 1.1.1 - 16 April 2024
 */
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class TradeManager {
	
	private Random rnd = new Random();
	private String trader = "";
	private String gain = "";
	private String lose = "";
	private int index;
	private int qtyGained = 0;
	private int qtyLost = 0;
	private String gainName = "";

	/**
	 * Creates a trade object
	 */
	TradeManager(){}
	
	/**
	 * randomly determines who the user will trade with
	 * @param milesTravelled - how far the user has travelled so far
	 * @return trader - a string containing who the user is trading with
	 */
	public String getTrader(int milesTravelled) {
		// generate a random number 0-2
		int value = rnd.nextInt(3);

		// choose the trader associated with the number
		switch(value){
			case 0: trader = "A fellow traveller"; break;
			case 1: trader = "A trapper"; break;
			case 2: 
				// Native American traders are determined by miles travelled because of their territories
				if (milesTravelled < 500) trader = "A Pawnee Native American";
				else if (milesTravelled < 1000) trader = "A Souis Native American";
				else if (milesTravelled < 1500) trader = "A Siou Native American";
				else trader = "A Kayuse Native American";
				break;
		}
		
		return trader;
	}
	
	/**
	 * randomly generates a trade offer for the user
	 * @param wagon - what the user has in their inventory
	 */
	public void getOffer(ArrayList<Equipment> inventory) {
		
		// THE ITEM THE USER WILL LOSE
		int size = inventory.size();
		String formattedName;
		
		// chooses a random index and gets the name of the item
		index = rnd.nextInt(size - 1);
		String name = inventory.get(index).getName();
		
		// generates a random quantity to trade
		qtyLost = (rnd.nextInt(inventory.get(index).getQuantity()) + 1);
		
		// if the quantity is greater than 150, limit it (this is mainly for food)
		if(qtyLost > 150) {
			qtyLost = rnd.nextInt(140) + 10;
		}
				
		// formats the name to have a space and be all lowercase
		if (name.equals("Food") || name.equals("Water")) {
			// food and water will have the pounds label
			formattedName = "lbs " + name;
		} else if(name.equals("Water")){
			formattedName = "gal " + name;
		} else formattedName = " " + name;

		
		// combines the name and quantity into a single string
		lose = qtyLost + formattedName.toLowerCase();
		
		// THE ITEM THE USER WILL GAIN
		// generate a random value 0-4
		int value = rnd.nextInt(5);
		
		// trapper will only offer food
		if (trader.equals("A trapper")) value = 0; 
		
		// formats the random item and generates a random quantity to trade for
		switch(value){
			case 0: 
				// item
				gainName = "food";
				// quantity
				qtyGained = rnd.nextInt(90) + 10;

				// checks if gain and lose are the same items
				if (gainName.equals(name.toLowerCase())) {
					// yes, move to the next case
				} else {
					gain = qtyGained + "lbs of " + gainName;
					break;
				}
				
			case 1:
				// item
				gainName = "clothes";
				// quantity
				qtyGained = rnd.nextInt(4) + 1;
				
				// checks if gain and lose are the same items
				if (gainName.equals(name.toLowerCase())) {
					// yes, move to the next case
				} else {
					if (qtyGained == 1) gain = qtyGained + " set of " + gainName;
					else gain = qtyGained + " sets of " + gainName;
					break;
				}
				
			case 2:
				// item
				gainName = "wagon wheel";
				// quantity
				qtyGained = rnd.nextInt(3) + 1;

				// checks if gain and lose are the same items
				if (gainName.equals(name.toLowerCase())) {
					// yes, move to the next case
				} else {
					if (qtyGained == 1) gain = qtyGained + " " + gainName;
					else gain = qtyGained + " " + gainName + "s";
					break;
				}
				
			case 3:
				// item
				gainName = "wagon axle";
				// quantity
				qtyGained = rnd.nextInt(3) + 1;
				
				// checks if gain and lose are the same items
				if (gainName.equals(name.toLowerCase())) {
					// yes, move to the next case
				} else {
					if (qtyGained == 1) gain = qtyGained + " " + gainName;
					else gain = qtyGained + " " + gainName + "s";
					break;
				}
				
			case 4:
				// item
				gainName = "wagon tongue";
				// quantity
				qtyGained = rnd.nextInt(3) + 1;

				// checks if gain and lose are the same items
				if (gainName.equals(name.toLowerCase())) {
					// yes, move to the next case
				} else {
					if (qtyGained == 1) gain = qtyGained + " " + gainName;
					else gain = qtyGained + " " + gainName + "s";
					break;
				}
				
			case 5:
				// the trader has nothing to offer
				gainName = "";
		}
	}
	
	/**
	 * Adds and removes the appropriate item quantities for the accepted trade offer
	 * @param inventory - what the user has in their inventory
	 * @param wagon - the object that stores the inventory array list
	 */
	public void tradeAccepted(ArrayList<Equipment> inventory, Wagon wagon) {
		// remove
		wagon.removeItemQty(inventory.get(index), qtyLost);
		
		// add
		for (Equipment item : inventory) {
			if (item.getName().toLowerCase().equals(gainName)) {
				wagon.addItemQty(item, qtyGained);
				break;
			}
		}
	}
	
	/**
	 * displays the offer as a dialogue box and returns the users response
	 * @return response - which option yes/no/ok the user clicks
	 */
	public int displayOffer() {
		// generates the offer as a string
		String offer;
		int buttonsOfDialogue;
		// checks if there is no trade offer
		if (gainName.equals("") || qtyLost == 0) {
			offer = "You cannot find anyone to trade with you.";
			// sets the dialogue buttons to just ok
			buttonsOfDialogue = -1; 
		} else {
			offer = trader + " wants to give you " + gain + " for " + lose;
			// sets the dialogue buttons to yes or no
			buttonsOfDialogue = 0;
		}
		
		String text = offer;
		String title = "Trade";
		int type = JOptionPane.QUESTION_MESSAGE;
		int response = JOptionPane.showConfirmDialog(null,  text, title, buttonsOfDialogue, type);
		
		// ok and yes give the same response value of 0 so adjustments are needed
		if(buttonsOfDialogue == -1) return -1;
		
		return response;
	}

}
