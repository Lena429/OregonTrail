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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * TO-DO
 * add all items to store
 * read in a text file to instantiate locations and equipment
 * make store at beginning of game
 */

public class Store {
	private Equipment money;
	private ArrayList<Equipment> inventory = new ArrayList<>();
	private Wagon wagon = new Wagon();
	private float foodCost = 0;
	private float wheelCost = 0;
	private float clothesCost = 0;
	private JSlider ClothesAmount = new JSlider();
	private JSlider FoodAmount = new JSlider();
	private JSlider WheelAmount = new JSlider();
	private JLabel AmountOwed = new JLabel("00");
	
	/**
	 * Creates a store object containing the wagon inventory and an object of wagon
	 * @param money - the amount of money in the wagon
	 * @param inventory - where all of the items in the wagon are stored
	 * @param wagon - where the inventory arrayList is stored
	 */
	public Store(Equipment money, ArrayList<Equipment> inventory, Wagon wagon) {
		this.money = money;
		this.inventory = inventory;
		this.wagon = wagon;
	}
	
	 /**
     * Simulates shopping for items at the fort.
     * @return A list of equipment items available for sale at the fort
     */
	public void StoreWindow() {
		JFrame StoreWindow = new JFrame();
		StoreWindow.setBounds(100, 100, 424, 481);
		StoreWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		StoreWindow.setVisible(true);
		StoreWindow.getContentPane().setLayout(null);
		
		JLabel StoreLabel = new JLabel("Fort Store");
		StoreLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		StoreLabel.setBounds(167, 10, 93, 44);
		StoreWindow.getContentPane().add(StoreLabel);
		
		JLabel FoodItem = new JLabel("Food $1/ lbs");
		FoodItem.setBounds(31, 64, 87, 13);
		StoreWindow.getContentPane().add(FoodItem);
		
		JLabel Wheels = new JLabel("Wagon Wheel $10 ");
		Wheels.setBounds(31, 138, 115, 13);
		StoreWindow.getContentPane().add(Wheels);
		
		JLabel Clothes = new JLabel("Clothes $0.20 / pair");
		Clothes.setBounds(31, 209, 134, 13);
		StoreWindow.getContentPane().add(Clothes);
		
		
		FoodAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates food cost and total owed
				foodCost = FoodAmount.getValue() * 1; // Assuming 1 lb of food costs $1
				updateTotalOwed();			
			}
		});
		FoodAmount.setValueIsAdjusting(true);
		FoodAmount.setPaintLabels(true);
		FoodAmount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		FoodAmount.setValue(0);
		FoodAmount.setPaintTicks(true);
		FoodAmount.setMajorTickSpacing(50);
		FoodAmount.setMinorTickSpacing(10);
		FoodAmount.setBounds(149, 48, 212, 44);
		StoreWindow.getContentPane().add(FoodAmount);
		
		
		WheelAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates wheel cost and total owed
				wheelCost = WheelAmount.getValue() * 10; // Assuming 1 wheel costs $10
				updateTotalOwed();	
			}
		});
		WheelAmount.setPaintLabels(true);
		WheelAmount.setSnapToTicks(true);
		WheelAmount.setMaximum(6);
		WheelAmount.setValue(0);
		WheelAmount.setPaintTicks(true);
		WheelAmount.setMinorTickSpacing(1);
		WheelAmount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		WheelAmount.setBounds(149, 120, 212, 44);
		StoreWindow.getContentPane().add(WheelAmount);
		
		
		ClothesAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// updates clothes cost and total owed
				clothesCost = (float) (ClothesAmount.getValue() * 0.20); // Assuming 1 pair of clothes costs $0.20
				updateTotalOwed();	
			}
		});
		ClothesAmount.setMajorTickSpacing(25);
		ClothesAmount.setValue(0);
		ClothesAmount.setSnapToTicks(true);
		ClothesAmount.setPaintTicks(true);
		ClothesAmount.setPaintLabels(true);
		ClothesAmount.setMinorTickSpacing(1);
		ClothesAmount.setMaximum(10);
		ClothesAmount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ClothesAmount.setBounds(149, 198, 212, 44);
		StoreWindow.getContentPane().add(ClothesAmount);
		
		JLabel DollarLabel = new JLabel("Total $: ");
		DollarLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		DollarLabel.setBounds(31, 293, 71, 19);
		StoreWindow.getContentPane().add(DollarLabel);
		
		AmountOwed = new JLabel("00");
		AmountOwed.setBounds(101, 292, 64, 24);
		StoreWindow.getContentPane().add(AmountOwed);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// subtracts cost from user, updates inventory, and closes store window
				removeMoney();
				updateSupplies();
				StoreWindow.dispose();
			}
		});
		buyButton.setBounds(175, 294, 85, 21);
		StoreWindow.getContentPane().add(buyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// closes store window without purchasing anything
				StoreWindow.dispose();
			}
		});
		cancelButton.setBounds(276, 295, 85, 21);
		StoreWindow.getContentPane().add(cancelButton);
	}
	
	/**
	 * updates the amount owed label to be properly formatted
	 */
	private void updateTotalOwed() {
        float totalCost = foodCost + wheelCost + clothesCost;
        AmountOwed.setText(String.format("%.2f", totalCost));
    }
	
	/**
	 * removes the total cost of the items from the user's money
	 */
	public void removeMoney(){
		float totalCost = foodCost + wheelCost + clothesCost;
				wagon.removeItemQty(money, totalCost );
	}
	
	/**
	 * updates the wagon inventory in accordance with how much the user bought
	 */
	private void updateSupplies() {
	    // Get the values from the sliders
	    int foodAmount = FoodAmount.getValue();
	    int wheelAmount = WheelAmount.getValue();
	    int clothesAmount = ClothesAmount.getValue();


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
	}

}
