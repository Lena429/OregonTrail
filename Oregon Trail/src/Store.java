import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Store extends Location {
	
	
	public Store(String name, int miles, Wagon wagon) {
		super(name, miles, wagon);
	}
	
	 /**
     * Simulates shopping for items at the fort.
     * @return A list of equipment items available for sale at the fort
     */
	public List<Equipment> shop(){
		List<Equipment> itemsForSale = new ArrayList<>();
     	itemsForSale.add(new Food("Apple", 10, 1, true));
		itemsForSale.add(new Food("Bread", 5, 1, true));
		itemsForSale.add(new Equipment("Water", 20, 1));
		return itemsForSale;
	}

	
	 /**
     * Displays the items available for sale at the fort in a graphical window.
     */
	public void displayItemsForSale() {
        List<Equipment> itemsForSale = shop();
        JFrame shopWindow = new JFrame("Items for Sale at " + getName());
        shopWindow.setSize(300, 400);
        shopWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Equipment item : itemsForSale) {
            JLabel label = new JLabel(item.getName() + ": " + item.getQuantity());
            JButton Add = new JButton("Add: ");
            Add.addActionListener(new ActionListener() {
            	public void actionPerformed (ActionEvent e) {
            		wagon.addItem(item);
            	}
            });
            panel.add(Add);
            panel.add(label);
        }

        shopWindow.add(panel);
        shopWindow.setVisible(true);
    }

}
