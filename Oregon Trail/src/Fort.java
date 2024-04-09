import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//why is nothing working :(
public class Fort {
	private Wagon wagon;
	private String name;
	private boolean visited;
	private int milesAway;
	//phrases for talking to people
	private String[] phrases = {
			"I'm hoping the weather stays nice", "Have you seen that large caravan up ahead?", "I heard the flu is going around, so be careful!"
	};
	
	public Fort(String name, int miles, Wagon wagon) {
		this.name = name;
		this.visited = false;
		this.milesAway = miles;
		this.wagon = wagon;
	}
	
	/**
	 * 
	 */
	public void updateMilesAway(int pace) {
		milesAway -= pace;
		if (milesAway < 0) milesAway = 0;
	}
	
	public int getMilesAway() {
		return milesAway;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean arrivedAtLandmark() {
		if (milesAway == 0) return true;
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasvisited() {
		return visited;
	}
	
	public void updatevisited() {
		visited = true;
	}
	
	public String getName() {
		return name;
	}
	
	//hoping this commit works 
	public void visit(Wagon wagon) {
		this.visited = true;
		
		}
	}
	
	public String generateRandomPhrase() {
	        Random random = new Random();
	        int index = random.nextInt(phrases.length);
	        return phrases[index];
	 }

	public List<Equipment> shop(){
		List<Equipment> itemsForSale = new ArrayList<>();
		itemsForSale.add(new Food("Apple", 10, 1, true));
		itemsForSale.add(new Food("Bread", 5, 1, true));
		itemsForSale.add(new Equipment("Water", 20, 1));
		return itemsForSale;
	}
	
	public void displayItemsForSale() {
        List<Equipment> itemsForSale = shop();
        JFrame shopWindow = new JFrame("Items for Sale at " + name);
        shopWindow.setSize(300, 400);
        shopWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Equipment item : itemsForSale) {
            JLabel label = new JLabel(item.getName() + ": " + item.getQuantity());
            JButton Add = new JButton("Add all items");
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

