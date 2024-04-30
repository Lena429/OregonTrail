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
		storeWindow.setBounds(100, 100, 1289, 767);
		storeWindow.setTitle("Store");
		storeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
		storeWindow.setVisible(true);
		storeWindow.getContentPane().setLayout(null);
		
		JLabel storeLabel = new JLabel("Store");
		storeLabel.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		storeLabel.setBounds(547, 11, 140, 44);
		storeWindow.getContentPane().add(storeLabel);
		
		JLabel foodItem = new JLabel("Food $0.20/ lbs");
		foodItem.setHorizontalAlignment(SwingConstants.RIGHT);
		foodItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		foodItem.setBounds(44, 490, 171, 24);
		storeWindow.getContentPane().add(foodItem);
		
		JLabel ox = new JLabel("Ox $20");
		ox.setHorizontalAlignment(SwingConstants.RIGHT);
		ox.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		ox.setBounds(25, 79, 190, 29);
		storeWindow.getContentPane().add(ox);
		
		JLabel clothes = new JLabel("Clothes $0.25 / pair");
		clothes.setHorizontalAlignment(SwingConstants.RIGHT);
		clothes.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		clothes.setBounds(10, 156, 205, 24);
		storeWindow.getContentPane().add(clothes);
		
		JLabel waterItem = new JLabel("Water $1/ gal");
		waterItem.setHorizontalAlignment(SwingConstants.RIGHT);
		waterItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		waterItem.setBounds(44, 571, 171, 24);
		storeWindow.getContentPane().add(waterItem);
		
		JLabel dollarLabel = new JLabel("Total $: ");
		dollarLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dollarLabel.setBounds(180, 662, 133, 39);
		storeWindow.getContentPane().add(dollarLabel);
		
		JLabel wheel = new JLabel("Wagon Wheel $10 ");
		wheel.setHorizontalAlignment(SwingConstants.RIGHT);
		wheel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		wheel.setBounds(26, 239, 190, 29);
		storeWindow.getContentPane().add(wheel);
		
		JLabel tongue = new JLabel("Wagon Tongue $10 ");
		tongue.setHorizontalAlignment(SwingConstants.RIGHT);
		tongue.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		tongue.setBounds(10, 326, 206, 29);
		storeWindow.getContentPane().add(tongue);
		
		JLabel axle = new JLabel("Wagon Axle $10 ");
		axle.setHorizontalAlignment(SwingConstants.RIGHT);
		axle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		axle.setBounds(26, 410, 190, 29);
		storeWindow.getContentPane().add(axle);
		
		JLabel moneyAvailLbl = new JLabel("Available money:");
		moneyAvailLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		moneyAvailLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		moneyAvailLbl.setBounds(5, 15, 150, 29);
		storeWindow.getContentPane().add(moneyAvailLbl);
		
		JLabel moneyLbl = new JLabel (bank.displayMoney());
		moneyLbl.setHorizontalAlignment(SwingConstants.LEFT);
		moneyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		moneyLbl.setBounds(165, 15, 150, 29);
		storeWindow.getContentPane().add(moneyLbl);
		
		amountOwed = new JLabel("0.00");
		amountOwed.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		amountOwed.setBounds(300, 657, 130, 49);
		storeWindow.getContentPane().add(amountOwed);
		
		foodSlider = new JSlider();
		foodSlider.setSnapToTicks(true);
		foodSlider.setMajorTickSpacing(300);
		foodSlider.setMaximum(1500);
		foodSlider.setValueIsAdjusting(true);
		foodSlider.setPaintLabels(true);
		foodSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodSlider.setValue(0);
		foodSlider.setPaintTicks(true);
		foodSlider.setMinorTickSpacing(20);
		foodSlider.setBounds(226, 490, 990, 44);
		foodSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates food cost and total owed
				foodCost = foodSlider.getValue() * foodPrice;
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(foodSlider);
		
		oxSlider = new JSlider();
		oxSlider.setMajorTickSpacing(2);
		oxSlider.setPaintLabels(true);
		oxSlider.setSnapToTicks(true);
		oxSlider.setMaximum(10);
		oxSlider.setValue(0);
		oxSlider.setPaintTicks(true);
		oxSlider.setMinorTickSpacing(1);
		oxSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oxSlider.setBounds(234, 77, 982, 44);
		oxSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				oxCost = oxSlider.getValue() * oxPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(oxSlider);
		
		clothesSlider = new JSlider();
		clothesSlider.setMajorTickSpacing(2);
		clothesSlider.setValue(0);
		clothesSlider.setSnapToTicks(true);
		clothesSlider.setPaintTicks(true);
		clothesSlider.setPaintLabels(true);
		clothesSlider.setMinorTickSpacing(1);
		clothesSlider.setMaximum(20);
		clothesSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clothesSlider.setBounds(234, 156, 982, 44);
		clothesSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				clothesCost = clothesSlider.getValue() * clothesPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(clothesSlider);
		
		wheelSlider = new JSlider();
		wheelSlider.setValue(0);
		wheelSlider.setSnapToTicks(true);
		wheelSlider.setPaintTicks(true);
		wheelSlider.setPaintLabels(true);
		wheelSlider.setMinorTickSpacing(1);
		wheelSlider.setMaximum(10);
		wheelSlider.setMajorTickSpacing(2);
		wheelSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wheelSlider.setBounds(234, 245, 982, 44);
		wheelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates wheel cost and total owed
				wheelCost = wheelSlider.getValue() * wheelPrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(wheelSlider);
		
		tongueSlider = new JSlider();
		tongueSlider.setValue(0);
		tongueSlider.setSnapToTicks(true);
		tongueSlider.setPaintTicks(true);
		tongueSlider.setPaintLabels(true);
		tongueSlider.setMinorTickSpacing(1);
		tongueSlider.setMaximum(10);
		tongueSlider.setMajorTickSpacing(2);
		tongueSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tongueSlider.setBounds(234, 330, 982, 44);
		tongueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				tongueCost = tongueSlider.getValue() * tonguePrice;
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(tongueSlider);
		
		axleSlider = new JSlider();
		axleSlider.setValue(0);
		axleSlider.setSnapToTicks(true);
		axleSlider.setPaintTicks(true);
		axleSlider.setPaintLabels(true);
		axleSlider.setMinorTickSpacing(1);
		axleSlider.setMaximum(10);
		axleSlider.setMajorTickSpacing(2);
		axleSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		axleSlider.setBounds(234, 410, 982, 44);
		axleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				axleCost = axleSlider.getValue() * axlePrice;
				updateTotalOwed();	
			}
		});		storeWindow.getContentPane().add(axleSlider);
		
		waterSlider = new JSlider();
		waterSlider.setValueIsAdjusting(true);
		waterSlider.setValue(0);
		waterSlider.setSnapToTicks(true);
		waterSlider.setPaintTicks(true);
		waterSlider.setPaintLabels(true);
		waterSlider.setMinorTickSpacing(20);
		waterSlider.setMaximum(1500);
		waterSlider.setMajorTickSpacing(300);
		waterSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		waterSlider.setBounds(226, 571, 990, 44);
		waterSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates food cost and total owed
				waterCost = waterSlider.getValue() * waterPrice;
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(waterSlider);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// subtracts cost from user, updates inventory, and closes store window
				if(!removeMoney()) {
					JOptionPane.showMessageDialog(null, " ", null, JOptionPane.WARNING_MESSAGE);
				} else {
					updateSupplies();
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					resetPrices();
					storeWindow.dispose();
				}
			}
		});
		buyButton.setBounds(450, 657, 201, 43);
		storeWindow.getContentPane().add(buyButton);
		
		
		if (!initialStore) {
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// closes store window without purchasing anything
	
					storeWindow.dispose();
					resetPrices();
				}
			});
			cancelButton.setBounds(707, 657, 201, 43);
			storeWindow.getContentPane().add(cancelButton);
		}
		
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
	 * removes the total cost of the items from the user's money
	 */
	public boolean removeMoney(){
		if(bank.isMoneyAvailable(totalCost)) {
			bank.spendMoney(totalCost);
			return true;
		} else {
			return false;
		}
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
