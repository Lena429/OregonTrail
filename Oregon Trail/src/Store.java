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
	private int blanketCost = 0;
	private int tongueCost = 0;
	private int axleCost = 0;
	private int waterCost = 0;
	private int totalCost;
	private JSlider ClothesAmount;
	private JSlider FoodAmount;
	private JSlider WheelAmount;
	private JSlider WaterAmount;
	private JSlider AxleAmount;
	private JSlider TongueAmount;
	private JSlider BlanketAmount;
	private JLabel AmountOwed;
	
	/**
	 * Creates a store object containing the wagon inventory and an object of wagon
	 * @param money - the amount of money in the wagon
	 * @param inventory - where all of the items in the wagon are stored
	 * @param wagon - where the inventory arrayList is stored
	 */
	public Store(Money bank, ArrayList<Equipment> inventory, Wagon wagon) {
		this.bank = bank;
		this.inventory = inventory;
		this.wagon = wagon;
	}
	
	 /**
     * Simulates shopping for items at the fort.
     * @return A list of equipment items available for sale at the fort
     */
	public void StoreWindow() {
		JFrame storeWindow = new JFrame();
		storeWindow.setBounds(100, 100, 1289, 767);
		storeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		storeWindow.setVisible(true);
		storeWindow.getContentPane().setLayout(null);
		
		JLabel StoreLabel = new JLabel("Store");
		StoreLabel.setFont(new Font("Felix Titling", Font.PLAIN, 40));
		StoreLabel.setBounds(547, 11, 140, 44);
		storeWindow.getContentPane().add(StoreLabel);
		
		JLabel FoodItem = new JLabel("Food $1/ lbs");
		FoodItem.setHorizontalAlignment(SwingConstants.RIGHT);
		FoodItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		FoodItem.setBounds(44, 490, 171, 24);
		storeWindow.getContentPane().add(FoodItem);
		
		JLabel Blanket = new JLabel("Blanket $2");
		Blanket.setHorizontalAlignment(SwingConstants.RIGHT);
		Blanket.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		Blanket.setBounds(25, 79, 190, 29);
		storeWindow.getContentPane().add(Blanket);
		
		JLabel Clothes = new JLabel("Clothes $0.20 / pair");
		Clothes.setHorizontalAlignment(SwingConstants.RIGHT);
		Clothes.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		Clothes.setBounds(10, 156, 205, 24);
		storeWindow.getContentPane().add(Clothes);
		
		JLabel WaterItem = new JLabel("Water $1/ gal");
		WaterItem.setHorizontalAlignment(SwingConstants.RIGHT);
		WaterItem.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		WaterItem.setBounds(44, 571, 171, 24);
		storeWindow.getContentPane().add(WaterItem);
		
		JLabel DollarLabel = new JLabel("Total $: ");
		DollarLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		DollarLabel.setBounds(180, 662, 133, 39);
		storeWindow.getContentPane().add(DollarLabel);
		
		JLabel Wheel = new JLabel("Wagon Wheel $10 ");
		Wheel.setHorizontalAlignment(SwingConstants.RIGHT);
		Wheel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		Wheel.setBounds(26, 239, 190, 29);
		storeWindow.getContentPane().add(Wheel);
		
		JLabel Tongue = new JLabel("Wagon Tongue $10 ");
		Tongue.setHorizontalAlignment(SwingConstants.RIGHT);
		Tongue.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		Tongue.setBounds(10, 326, 206, 29);
		storeWindow.getContentPane().add(Tongue);
		
		JLabel Axle = new JLabel("Wagon Axle $10 ");
		Axle.setHorizontalAlignment(SwingConstants.RIGHT);
		Axle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		Axle.setBounds(26, 410, 190, 29);
		storeWindow.getContentPane().add(Axle);
		
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
		
		AmountOwed = new JLabel("0.00");
		AmountOwed.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		AmountOwed.setBounds(300, 657, 130, 49);
		storeWindow.getContentPane().add(AmountOwed);
		
		FoodAmount = new JSlider();
		FoodAmount.setSnapToTicks(true);
		FoodAmount.setMajorTickSpacing(300);
		FoodAmount.setMaximum(1500);
		FoodAmount.setValueIsAdjusting(true);
		FoodAmount.setPaintLabels(true);
		FoodAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FoodAmount.setValue(0);
		FoodAmount.setPaintTicks(true);
		FoodAmount.setMinorTickSpacing(20);
		FoodAmount.setBounds(226, 490, 990, 44);
		FoodAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates food cost and total owed
				foodCost = FoodAmount.getValue() * 100; // Assuming 1 lb of food costs $1
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(FoodAmount);
		
		BlanketAmount = new JSlider();
		BlanketAmount.setMajorTickSpacing(2);
		BlanketAmount.setPaintLabels(true);
		BlanketAmount.setSnapToTicks(true);
		BlanketAmount.setMaximum(10);
		BlanketAmount.setValue(0);
		BlanketAmount.setPaintTicks(true);
		BlanketAmount.setMinorTickSpacing(1);
		BlanketAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BlanketAmount.setBounds(234, 77, 982, 44);
		BlanketAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				blanketCost = BlanketAmount.getValue() * 200; // Assuming 1 blanket costs $2
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(BlanketAmount);
		
		ClothesAmount = new JSlider();
		ClothesAmount.setMajorTickSpacing(2);
		ClothesAmount.setValue(0);
		ClothesAmount.setSnapToTicks(true);
		ClothesAmount.setPaintTicks(true);
		ClothesAmount.setPaintLabels(true);
		ClothesAmount.setMinorTickSpacing(1);
		ClothesAmount.setMaximum(20);
		ClothesAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ClothesAmount.setBounds(234, 156, 982, 44);
		ClothesAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				clothesCost = ClothesAmount.getValue() * 20; // Assuming 1 pair of clothes costs $0.20
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(ClothesAmount);
		
		WheelAmount = new JSlider();
		WheelAmount.setValue(0);
		WheelAmount.setSnapToTicks(true);
		WheelAmount.setPaintTicks(true);
		WheelAmount.setPaintLabels(true);
		WheelAmount.setMinorTickSpacing(1);
		WheelAmount.setMaximum(10);
		WheelAmount.setMajorTickSpacing(2);
		WheelAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		WheelAmount.setBounds(234, 245, 982, 44);
		WheelAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates wheel cost and total owed
				wheelCost = WheelAmount.getValue() * 1000; // Assuming 1 wheel costs $10
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(WheelAmount);
		
		TongueAmount = new JSlider();
		TongueAmount.setValue(0);
		TongueAmount.setSnapToTicks(true);
		TongueAmount.setPaintTicks(true);
		TongueAmount.setPaintLabels(true);
		TongueAmount.setMinorTickSpacing(1);
		TongueAmount.setMaximum(10);
		TongueAmount.setMajorTickSpacing(2);
		TongueAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TongueAmount.setBounds(234, 330, 982, 44);
		TongueAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				tongueCost = TongueAmount.getValue() * 1000; // Assuming 1 tongue costs $10
				updateTotalOwed();	
			}
		});
		storeWindow.getContentPane().add(TongueAmount);
		
		AxleAmount = new JSlider();
		AxleAmount.setValue(0);
		AxleAmount.setSnapToTicks(true);
		AxleAmount.setPaintTicks(true);
		AxleAmount.setPaintLabels(true);
		AxleAmount.setMinorTickSpacing(1);
		AxleAmount.setMaximum(10);
		AxleAmount.setMajorTickSpacing(2);
		AxleAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AxleAmount.setBounds(234, 410, 982, 44);
		AxleAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				axleCost = AxleAmount.getValue() * 1000; // Assuming 1 axle costs $10
				updateTotalOwed();	
			}
		});		storeWindow.getContentPane().add(AxleAmount);
		
		WaterAmount = new JSlider();
		WaterAmount.setValueIsAdjusting(true);
		WaterAmount.setValue(0);
		WaterAmount.setSnapToTicks(true);
		WaterAmount.setPaintTicks(true);
		WaterAmount.setPaintLabels(true);
		WaterAmount.setMinorTickSpacing(20);
		WaterAmount.setMaximum(1500);
		WaterAmount.setMajorTickSpacing(300);
		WaterAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		WaterAmount.setBounds(226, 571, 990, 44);
		WaterAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates food cost and total owed
				waterCost = WaterAmount.getValue() * 100; // Assuming 1 gal of water costs $1
				updateTotalOwed();			
			}
		});
		storeWindow.getContentPane().add(WaterAmount);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// subtracts cost from user, updates inventory, and closes store window
				if(!removeMoney()) {
					// make dialogue box appear
				} else {
					updateSupplies();
					storeWindow.dispose();
				}
			}
		});
		buyButton.setBounds(450, 657, 201, 43);
		storeWindow.getContentPane().add(buyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// closes store window without purchasing anything
				storeWindow.dispose();
			}
		});
		cancelButton.setBounds(707, 657, 201, 43);
		storeWindow.getContentPane().add(cancelButton);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				storeWindow.toFront();
				storeWindow.repaint();
			}
		});
	}
	
	/**
	 * updates the amount owed label to be properly formatted
	 */
	private void updateTotalOwed() {
        totalCost = foodCost + wheelCost + clothesCost + blanketCost + tongueCost + axleCost + waterCost;
        double formattedAmount = totalCost / 100.0; // Convert to double for decimal formatting
        AmountOwed.setText(String.format("%.2f", formattedAmount));
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
	    int foodAmount = FoodAmount.getValue();
	    int wheelAmount = WheelAmount.getValue();
	    int clothesAmount = ClothesAmount.getValue();
	    int tongueAmount = TongueAmount.getValue();
	    int axleAmount = AxleAmount.getValue();
	    int waterAmount = WaterAmount.getValue();
	    int blanketAmount = BlanketAmount.getValue();


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
	    if (blanketAmount > 0) {
	    	// Wagon wheel was bought, so add it to inventory
	        String blanket = "Blankets";
	        
	        // adds the item to the wagon
	        for (Equipment item: inventory)
		    	if (blanket.equals(item.getName())) {
					wagon.addItemQty(item, blanketAmount);
					break;
				}
	    }
	}

}
