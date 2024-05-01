/**
 * Store.java
 * 
 * The store class creates a window with sliders for the user to purchase certain items. The
 * user can either buy the items or cancel the purchase. If bought, the money gets subtracted 
 * from the users total and the items get added to the inventory. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @author - Sarah Slusher
 * @version 1.1.1 - April 17 2024
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Store {
	private Money bank;
	private ArrayList<Equipment> inventory = new ArrayList<>();
	private Wagon wagon;
	private int foodCost = 0;
	private int wheelCost = 0;
	private int clothesCost = 0;
	private int oxCost = 0;
	private int tongueCost = 0;
	private int axleCost = 0;
	private int waterCost = 0;
	private int totalCost;
	
	//trying something with prices
	private int foodPrice = 20;
	private int oxPrice = 2000;
	private int clothesPrice = 25;
	private int wheelPrice = 1000;
	private int tonguePrice = 1000;
	private int axlePrice = 1000;
	private int waterPrice = 100;
	
	
	private JSlider clothesSlider;
	private JSlider foodSlider;
	private JSlider wheelSlider;
	private JSlider waterSlider;
	private JSlider axleSlider;
	private JSlider tongueSlider;
	private JSlider oxSlider;
	private JLabel amountOwed;
	private JLabel foodMainLbl;
	
	/**
	 * Creates a store object containing the wagon inventory, an object of wagon, the user's money, and a label
	 * @param money - the amount of money in the wagon
	 * @param inventory - where all of the items in the wagon are stored
	 * @param wagon - where the inventory arrayList is stored
	 * @param foodMainLbl - the food label on the main frame that needs to be updated
	 */
	public Store(Money bank, ArrayList<Equipment> inventory, Wagon wagon, JLabel foodMainLbl) {
		this.bank = bank;
		this.inventory = inventory;
		this.wagon = wagon;
		this.foodMainLbl = foodMainLbl;
	}
	
	/**
	 * 
	 * @param fort
	 */
	public void adjustPrices(Fort fort) {
		foodPrice *= fort.getPriceFactor();
		oxPrice *= fort.getPriceFactor();
		clothesPrice *= fort.getPriceFactor();
		wheelPrice  *= fort.getPriceFactor();
		tonguePrice  *= fort.getPriceFactor();
		axlePrice *= fort.getPriceFactor();
		waterPrice *= fort.getPriceFactor();
	}
	
	 /**
     * Simulates shopping for items at the fort.
     * @param initialStore - if true then the window does not have a cancel button, false it does
     * @return A list of equipment items available for sale at the fort
     */
	public void openStoreWindow(boolean initialStore) {
		JFrame storeWindow = new JFrame();
		storeWindow.getContentPane().setBackground(new Color(0, 0, 0));
		storeWindow.setBounds(100, 100, 1289, 767);
		storeWindow.setTitle("Store");
		storeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
		storeWindow.setVisible(true);
		storeWindow.getContentPane().setLayout(null);
		
		JLabel storeLabel = new JLabel("Store");
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeLabel.setForeground(new Color(255, 255, 255));
		storeLabel.setFont(new Font("Felix Titling", Font.PLAIN, 44));
		storeLabel.setBounds(494, 11, 278, 55);
		storeWindow.getContentPane().add(storeLabel);
		
		JLabel foodItem = new JLabel("Food $" + (foodPrice / 100.0) + "0/ lbs");
		foodItem.setForeground(new Color(255, 255, 255));
		foodItem.setHorizontalAlignment(SwingConstants.RIGHT);
		foodItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		foodItem.setBounds(44, 490, 171, 24);
		storeWindow.getContentPane().add(foodItem);
		
		JLabel ox = new JLabel("Ox $" + (oxPrice / 100.0));
		ox.setForeground(new Color(255, 255, 255));
		ox.setHorizontalAlignment(SwingConstants.RIGHT);
		ox.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		ox.setBounds(25, 79, 190, 29);
		storeWindow.getContentPane().add(ox);
		
		JLabel clothes = new JLabel("Clothes $" + (clothesPrice / 100.0) + " / pair");
		clothes.setForeground(new Color(255, 255, 255));
		clothes.setHorizontalAlignment(SwingConstants.RIGHT);
		clothes.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		clothes.setBounds(10, 156, 205, 24);
		storeWindow.getContentPane().add(clothes);
		
		JLabel waterItem = new JLabel("Water $" + (waterPrice / 100.0) + "/ gal");
		waterItem.setForeground(new Color(255, 255, 255));
		waterItem.setHorizontalAlignment(SwingConstants.RIGHT);
		waterItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		waterItem.setBounds(44, 571, 171, 24);
		storeWindow.getContentPane().add(waterItem);
		
		JLabel dollarLabel = new JLabel("Total $");
		dollarLabel.setForeground(new Color(255, 255, 255));
		dollarLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dollarLabel.setBounds(272, 660, 129, 39);
		storeWindow.getContentPane().add(dollarLabel);
		
		JLabel wheel = new JLabel("Wagon Wheel $" + (wheelPrice / 100.0));
		wheel.setForeground(new Color(255, 255, 255));
		wheel.setHorizontalAlignment(SwingConstants.RIGHT);
		wheel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		wheel.setBounds(26, 239, 200, 29);
		storeWindow.getContentPane().add(wheel);
		
		JLabel tongue = new JLabel("Wagon Tongue $" + (tonguePrice / 100.0));
		tongue.setForeground(new Color(255, 255, 255));
		tongue.setHorizontalAlignment(SwingConstants.RIGHT);
		tongue.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		tongue.setBounds(10, 326, 226, 29);
		storeWindow.getContentPane().add(tongue);
		
		JLabel axle = new JLabel("Wagon Axle $" + (axlePrice / 100.0));
		axle.setForeground(new Color(255, 255, 255));
		axle.setHorizontalAlignment(SwingConstants.RIGHT);
		axle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		axle.setBounds(26, 410, 190, 29);
		storeWindow.getContentPane().add(axle);
		
		JLabel moneyAvailLbl = new JLabel("Available money:");
		moneyAvailLbl.setForeground(new Color(255, 255, 255));
		moneyAvailLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		moneyAvailLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		moneyAvailLbl.setBounds(25, 640, 170, 29);
		storeWindow.getContentPane().add(moneyAvailLbl);
		
		JLabel moneyLbl = new JLabel (bank.displayMoney());
		moneyLbl.setForeground(new Color(255, 255, 255));
		moneyLbl.setHorizontalAlignment(SwingConstants.LEFT);
		moneyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		moneyLbl.setBounds(25, 670, 150, 29);
		storeWindow.getContentPane().add(moneyLbl);
		
		JLabel oxQtyLbl = new JLabel("0");
		oxQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		oxQtyLbl.setForeground(Color.WHITE);
		oxQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		oxQtyLbl.setBounds(1176, 79, 89, 29);
		storeWindow.getContentPane().add(oxQtyLbl);
		
		JLabel clothesQtyLbl = new JLabel("0");
		clothesQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		clothesQtyLbl.setForeground(Color.WHITE);
		clothesQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		clothesQtyLbl.setBounds(1176, 156, 89, 29);
		storeWindow.getContentPane().add(clothesQtyLbl);
		
		JLabel wheelQtyLbl = new JLabel("0");
		wheelQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		wheelQtyLbl.setForeground(Color.WHITE);
		wheelQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		wheelQtyLbl.setBounds(1176, 239, 89, 29);
		storeWindow.getContentPane().add(wheelQtyLbl);
		
		JLabel tongueQtyLbl = new JLabel("0");
		tongueQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tongueQtyLbl.setForeground(Color.WHITE);
		tongueQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		tongueQtyLbl.setBounds(1176, 326, 89, 29);
		storeWindow.getContentPane().add(tongueQtyLbl);
		
		JLabel axleQtyLbl = new JLabel("0");
		axleQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		axleQtyLbl.setForeground(Color.WHITE);
		axleQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		axleQtyLbl.setBounds(1176, 410, 89, 29);
		storeWindow.getContentPane().add(axleQtyLbl);
		
		JLabel foodQtyLbl = new JLabel("0");
		foodQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		foodQtyLbl.setForeground(Color.WHITE);
		foodQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		foodQtyLbl.setBounds(1176, 490, 89, 29);
		storeWindow.getContentPane().add(foodQtyLbl);
		
		JLabel waterQtyLbl = new JLabel("0");
		waterQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		waterQtyLbl.setForeground(Color.WHITE);
		waterQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		waterQtyLbl.setBounds(1176, 571, 89, 29);
		storeWindow.getContentPane().add(waterQtyLbl);
		
		amountOwed = new JLabel("0.00");
		amountOwed.setForeground(new Color(255, 255, 255));
		amountOwed.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		amountOwed.setBounds(387, 655, 165, 49);
		storeWindow.getContentPane().add(amountOwed);
		
		foodSlider = new JSlider();
		foodSlider.setForeground(new Color(255, 255, 255));
		foodSlider.setOpaque(false);
		foodSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		foodSlider.setSnapToTicks(true);
		foodSlider.setMajorTickSpacing(300);
		foodSlider.setMaximum(1500);
		foodSlider.setValueIsAdjusting(true);
		foodSlider.setPaintLabels(true);
		foodSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodSlider.setValue(0);
		foodSlider.setPaintTicks(true);
		foodSlider.setMinorTickSpacing(20);
		foodSlider.setBounds(226, 490, 950, 44);
		foodSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				foodQtyLbl.setText("" + foodSlider.getValue());
				// updates food cost and total owed
				foodCost = foodSlider.getValue() * foodPrice;
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(foodSlider);
		
		oxSlider = new JSlider();
		oxSlider.setForeground(new Color(255, 255, 255));
		oxSlider.setOpaque(false);
		oxSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		oxSlider.setMajorTickSpacing(2);
		oxSlider.setPaintLabels(true);
		oxSlider.setSnapToTicks(true);
		oxSlider.setMaximum(10);
		oxSlider.setValue(0);
		oxSlider.setPaintTicks(true);
		oxSlider.setMinorTickSpacing(1);
		oxSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oxSlider.setBounds(234, 77, 934, 44);
		oxSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				oxQtyLbl.setText("" + oxSlider.getValue());
				// updates clothes cost and total owed
				oxCost = oxSlider.getValue() * oxPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(oxSlider);
		
		clothesSlider = new JSlider();
		clothesSlider.setForeground(new Color(255, 255, 255));
		clothesSlider.setOpaque(false);
		clothesSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clothesSlider.setMajorTickSpacing(2);
		clothesSlider.setValue(0);
		clothesSlider.setSnapToTicks(true);
		clothesSlider.setPaintTicks(true);
		clothesSlider.setPaintLabels(true);
		clothesSlider.setMinorTickSpacing(1);
		clothesSlider.setMaximum(20);
		clothesSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clothesSlider.setBounds(234, 156, 934, 44);
		clothesSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				clothesQtyLbl.setText("" + clothesSlider.getValue());
				// updates clothes cost and total owed
				clothesCost = clothesSlider.getValue() * clothesPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(clothesSlider);
		
		wheelSlider = new JSlider();
		wheelSlider.setForeground(new Color(255, 255, 255));
		wheelSlider.setOpaque(false);
		wheelSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		wheelSlider.setValue(0);
		wheelSlider.setSnapToTicks(true);
		wheelSlider.setPaintTicks(true);
		wheelSlider.setPaintLabels(true);
		wheelSlider.setMinorTickSpacing(1);
		wheelSlider.setMaximum(10);
		wheelSlider.setMajorTickSpacing(2);
		wheelSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wheelSlider.setBounds(234, 245, 934, 44);
		wheelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				wheelQtyLbl.setText("" + wheelSlider.getValue());
				// updates wheel cost and total owed
				wheelCost = wheelSlider.getValue() * wheelPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(wheelSlider);
		
		tongueSlider = new JSlider();
		tongueSlider.setForeground(new Color(255, 255, 255));
		tongueSlider.setOpaque(false);
		tongueSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tongueSlider.setValue(0);
		tongueSlider.setSnapToTicks(true);
		tongueSlider.setPaintTicks(true);
		tongueSlider.setPaintLabels(true);
		tongueSlider.setMinorTickSpacing(1);
		tongueSlider.setMaximum(10);
		tongueSlider.setMajorTickSpacing(2);
		tongueSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tongueSlider.setBounds(234, 330, 934, 44);
		tongueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tongueQtyLbl.setText("" + tongueSlider.getValue());
				// updates clothes cost and total owed
				tongueCost = tongueSlider.getValue() * tonguePrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(tongueSlider);
		
		axleSlider = new JSlider();
		axleSlider.setForeground(new Color(255, 255, 255));
		axleSlider.setOpaque(false);
		axleSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		axleSlider.setValue(0);
		axleSlider.setSnapToTicks(true);
		axleSlider.setPaintTicks(true);
		axleSlider.setPaintLabels(true);
		axleSlider.setMinorTickSpacing(1);
		axleSlider.setMaximum(10);
		axleSlider.setMajorTickSpacing(2);
		axleSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		axleSlider.setBounds(234, 410, 934, 44);
		axleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				axleQtyLbl.setText("" + axleSlider.getValue());
				// updates clothes cost and total owed
				axleCost = axleSlider.getValue() * axlePrice;
				updateTotalOwed();	
			}
		});		storeWindow.getContentPane().add(axleSlider);
		
		waterSlider = new JSlider();
		waterSlider.setForeground(new Color(255, 255, 255));
		waterSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		waterSlider.setOpaque(false);
		waterSlider.setValueIsAdjusting(true);
		waterSlider.setValue(0);
		waterSlider.setSnapToTicks(true);
		waterSlider.setPaintTicks(true);
		waterSlider.setPaintLabels(true);
		waterSlider.setMinorTickSpacing(20);
		waterSlider.setMaximum(1500);
		waterSlider.setMajorTickSpacing(300);
		waterSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		waterSlider.setBounds(226, 571, 950, 44);
		waterSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				waterQtyLbl.setText("" + waterSlider.getValue());
				// updates food cost and total owed
				waterCost = waterSlider.getValue() * waterPrice;
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(waterSlider);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setBackground(new Color(252, 252, 252));
		buyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buyButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// subtracts cost from user, updates inventory, and closes store window
				if(bank.isMoneyAvailable(totalCost)) {
					bank.spendMoney(totalCost);
					updateSupplies();
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					resetPrices();
					totalCost = 0;
					storeWindow.dispose();
				} else {
					JOptionPane.showMessageDialog(storeWindow, "You do not have enough money.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buyButton.setBounds(573, 655, 283, 51);
		storeWindow.getContentPane().add(buyButton);
		
		
		if (!initialStore) {
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setForeground(new Color(255, 255, 255));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelButton.setBackground(new Color(206, 0, 0));
			cancelButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// closes store window without purchasing anything
					totalCost = 0;
					resetPrices();
					storeWindow.dispose();
				}
			});
			cancelButton.setBounds(877, 655, 283, 51);
			storeWindow.getContentPane().add(cancelButton);
		}
		
		// moves the frame in front of all other frames
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				storeWindow.toFront();
				storeWindow.repaint();
			}
		});
	}
	
	/**
	 * 
	 */
	public void resetPrices() {
		foodPrice = 20;
		oxPrice = 2000;
		clothesPrice = 25;
		wheelPrice = 1000;
		tonguePrice = 1000;
		axlePrice = 1000;
		waterPrice = 100;
	}
	
	/**
	 * updates the amount owed label to be properly formatted
	 */
	private void updateTotalOwed() {
        totalCost = foodCost + wheelCost + clothesCost + oxCost + tongueCost + axleCost + waterCost;
        double formattedAmount = totalCost / 100.0; // Convert to double for decimal formatting
        amountOwed.setText(String.format("%.2f", formattedAmount));
    }
	
	/**
	 * updates the wagon inventory in accordance with how much the user bought
	 */
	private void updateSupplies() {
	    // Get the values from the sliders
	    int foodAmount = foodSlider.getValue();
	    int wheelAmount = wheelSlider.getValue();
	    int clothesAmount = clothesSlider.getValue();
	    int tongueAmount = tongueSlider.getValue();
	    int axleAmount = axleSlider.getValue();
	    int waterAmount = waterSlider.getValue();
	    int oxAmount = oxSlider.getValue();


	    // Determines if any of a certain item was bought
	    if (foodAmount > 0) {
	    	// Food was bought, so add it to inventory
	        String food = "Food";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (food.equals(item.getName())) {
					wagon.addItemQty(item, foodAmount);
					break;
				}
	    }
	    if (waterAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String water = "Water";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (water.equals(item.getName())) {
					wagon.addItemQty(item, waterAmount);
					break;
				}
	    }
	    if (wheelAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String wheel = "Wagon Wheel";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (wheel.equals(item.getName())) {
					wagon.addItemQty(item, wheelAmount);
					break;
				}
	    }
	    if (tongueAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String tongue = "Wagon Tongue";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (tongue.equals(item.getName())) {
					wagon.addItemQty(item, tongueAmount);
					break;
				}
	    }
	    if (axleAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String axle = "Wagon Axle";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (axle.equals(item.getName())) {
					wagon.addItemQty(item, axleAmount);
					break;
				}
	    }
	    if (clothesAmount > 0) {
	    	// Clothes were bought, so add it to inventory
	        String clothes = "Clothes";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (clothes.equals(item.getName())) {
					wagon.addItemQty(item, clothesAmount);
					break;
				}
	    }
	    if (oxAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String ox = "Oxen";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (ox.equals(item.getName())) {
					wagon.addItemQty(item, oxAmount);
					break;
				}
	    }
	}

}
