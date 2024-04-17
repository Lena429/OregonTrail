import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Store{
	private ArrayList<Equipment> wagon = new ArrayList<>();
	private double foodCost = 0;
	private double wheelCost = 0;
	private double clothesCost = 0;
	private JLabel AmountOwed = new JLabel("00");
	
	
	public Store() {
		
	}
	
	 /**
     * Simulates shopping for items at the fort.
     * @return A list of equipment items available for sale at the fort
     */
	public void StoreWindow() {
	JFrame StoreWindow = new JFrame();
	StoreWindow.setBounds(100, 100, 424, 481);
	StoreWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	
	JSlider FoodAmount = new JSlider();
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
	
	JSlider WheelAmount = new JSlider();
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
	
	JSlider ClothesAmount = new JSlider();
	ClothesAmount.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			clothesCost = ClothesAmount.getValue() * 0.20; // Assuming 1 pair of clothes costs $0.20
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
	
	JLabel AmountOwed = new JLabel("00");
	AmountOwed.setBounds(101, 292, 64, 24);
	StoreWindow.getContentPane().add(AmountOwed);
	
	JButton buyButton = new JButton("Buy");
	buyButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	buyButton.setBounds(175, 294, 85, 21);
	StoreWindow.getContentPane().add(buyButton);
	
	JButton cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	cancelButton.setBounds(276, 295, 85, 21);
	StoreWindow.getContentPane().add(cancelButton);
	}
	private void updateTotalOwed() {
        double totalCost = foodCost + wheelCost + clothesCost;
        AmountOwed.setText(String.format("%.2f", totalCost));
    }
	
	
	 /**
     * Displays the items available for sale at the fort in a graphical window.
     */
	//public void displayItemsForSale() {
    //    List<Equipment> itemsForSale = shop();
    //    JFrame shopWindow = new JFrame("Items for Sale at " + getName());
    //    shopWindow.setSize(300, 400);
   //     shopWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //    JPanel panel = new JPanel();
    //    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

     //   for (Equipment item : itemsForSale) {
     //       JLabel label = new JLabel(item.getName() + ": " + item.getQuantity());
     //       JButton Add = new JButton("Add: ");
     //       Add.addActionListener(new ActionListener() {
     //       	public void actionPerformed (ActionEvent e) {
     //       		wagon.addItem(item);
     //       	}
     //       });
     //       panel.add(Add);
     //       panel.add(label);
     //   }

     //   shopWindow.add(panel);
     //   shopWindow.setVisible(true);
   // }

}