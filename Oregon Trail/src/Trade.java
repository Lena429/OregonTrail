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
import javax.swing.JLabel;

public class Trade {
	
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
	Trade(){}
	
	/**
	 * randomly determines who the user will trade with
	 * @param milesTravelled - how far the user has travelled so far
	 * @return trader - a string containing who the user is trading with
	 */
	public String getTrader(int milesTravelled) {
		
		int value = rnd.nextInt(3);

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
	public void getOffer(ArrayList<Equipment> wagon) {
				
		int size = wagon.size();
		
		// chooses a random item and quantity to trade
		index = rnd.nextInt(size);
		qtyLost = (rnd.nextInt(wagon.get(index).getQuantity()) + 1);
		
		// formats the name to have a space and be all lowercase
		String name = wagon.get(index).getName();
		if (name.equals("Food") || name.equals("Water")) {
			name = "lbs " + name;
		} else name = " " + name;
		
		lose = qtyLost + name.toLowerCase();
		
		// chooses a random item to trade for
		int value = rnd.nextInt(5);
		
		// trapper will only offer food
		if (trader.equals("A trapper")) value = 0; 
		
		// formats the random item and generates a random quantity to trade for
		switch(value){
			case 0: 
				qtyGained = rnd.nextInt(90) + 10;
				gainName = "food";
				gain = qtyGained + "lbs of " + gainName;
				break;
			case 1:
				qtyGained = rnd.nextInt(4) + 1;
				gainName = "clothes";
				if (qtyGained == 1) gain = qtyGained + " set of " + gainName;
				else gain = qtyGained + " sets of " + gainName;
				break;
			case 2:
				qtyGained = rnd.nextInt(3) + 1;
				gainName = "wagon wheel";
				if (qtyGained == 1) gain = qtyGained + " " + gainName;
				else gain = qtyGained + " " + gainName + "s";
				break;
			case 3:
				qtyGained = rnd.nextInt(3) + 1;
				gainName = "wagon axle";
				if (qtyGained == 1) gain = qtyGained + " " + gainName;
				else gain = qtyGained + " " + gainName + "s";
				break;
			case 4:
				qtyGained = rnd.nextInt(3) + 1;
				gainName = "wagon tongue";
				if (qtyGained == 1) gain = qtyGained + " " + gainName;
				else gain = qtyGained + " " + gainName + "s";
				break;
		}
	}
	
	/**
	 * displays the trade offer on a label
	 * @param tradeLbl - the label to display the trade offer on
	 */
	public void displayTradeOffer(JLabel tradeLbl) {
		tradeLbl.setText(trader + " wants to give you " + gain + " for " + lose);
	}
	
	/**
	 * Adds and removes the appropriate item quantities for the accepted trade offer
	 * @param wagon - what the user has in their inventory
	 */
	public void tradeAccepted(ArrayList<Equipment> wagon) {
		// remove
		wagon.get(index).removeQuantity(qtyLost);
		
		// add
		for (Equipment item : wagon) {
			if (item.getName().toLowerCase().equals(gainName)) {
				item.addQuantity(qtyGained);
				break;
			}
		}
	}
	
	/**
	 * clears the trade label and resets the offer variables
	 * @param tradeLbl - the label that displays the trade offer
	 */
	public void tradeDeclined(JLabel tradeLbl) {
		tradeLbl.setText("");
		trader = "";
		gain = "";
		lose = "";
		qtyGained = 0;
		qtyLost = 0;
	}
}
