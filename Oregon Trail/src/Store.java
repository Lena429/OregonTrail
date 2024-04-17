import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Store{
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
	
	
	public Store(Equipment money) {
		this.money = money;
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
			clothesCost = (float) (ClothesAmount.getValue() * 0.20); // Assuming 1 pair of clothes costs $0.20
			updateTotalOwed();	
		}
	});
	ClothesAmount.setMajorTickSpacing(25);
	ClothesAmount.setValue(0);
	ClothesAmount.setSnapToTicks(true);
	ClothesAmount.setPaintTicks(true);
	ClothesAmount.setPaintLabels(true);
	ClothesAmount.setMinorTickSpacing(10);
	ClothesAmount.setMaximum(50);
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
			removeMoney(inventory, wagon);
			updateSupplies(inventory, wagon );
			StoreWindow.dispose();
		}
	});
	buyButton.setBounds(175, 294, 85, 21);
	StoreWindow.getContentPane().add(buyButton);
	
	JButton cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			StoreWindow.dispose();
		}
	});
	cancelButton.setBounds(276, 295, 85, 21);
	StoreWindow.getContentPane().add(cancelButton);
	}
	private void updateTotalOwed() {
        float totalCost = foodCost + wheelCost + clothesCost;
        AmountOwed.setText(String.format("%.2f", totalCost));
    }
	
	public void removeMoney(ArrayList<Equipment> inventory2, Wagon wagon){
		float totalCost = foodCost + wheelCost + clothesCost;
		for (Equipment item : inventory) {
			if (item.getName().equals("Money")) {
				wagon.removeItemQty(item, totalCost );
				break;
			}
		}
	}
	
	private void updateSupplies(ArrayList<Equipment> inventory, Wagon wagon) {
	    // Get the values from the sliders
	    int foodAmount = FoodAmount.getValue();
	    int wheelAmount = WheelAmount.getValue();
	    int clothesAmount = ClothesAmount.getValue();

	    // Create and populate the ArrayList to be added to the wagon
	    ArrayList<Equipment> purchasedItems = new ArrayList<>();

	    // Populate purchasedItems based on the slider values
	    if (foodAmount > 0) {
	        Equipment food = new Equipment("Food", 1, foodAmount);
	        purchasedItems.add(food);
	    }
	    if (wheelAmount > 0) {
	        Equipment wheel = new Equipment("Wagon Wheel", 45, wheelAmount);
	        purchasedItems.add(wheel);
	    }
	    if (clothesAmount > 0) {
	        Equipment clothes = new Equipment("Clothes", 2, clothesAmount);
	        purchasedItems.add(clothes);
	    }

	    // Add purchased items to the wagon's inventory
	    for (Equipment item : purchasedItems) {
	        wagon.addItem(item);
	    }
	}

}
