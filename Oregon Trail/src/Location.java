/**
 * Location.java
 * Holds all information for different locations throughout the trail
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version- 1.1 April 8 2024 
 */
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

public class Location {
	private Wagon wagon;        // The wagon associated with the fort
	private String name;        // The name of the fort
	private boolean visited;    // Indicates if the fort has been visited or not
	private int milesAway;      // Distance of the fort from the wagon party
	
	//phrases for talking to people
	//private String[] phrases = {
			//"I'm hoping the weather stays nice", "Have you seen that large caravan up ahead?", "I heard the flu is going around, so be careful!"
	//};


    /**
     * Constructs a new Fort object with the specified name, distance, and associated wagon.
     * @param name The name of the fort
     * @param miles Distance of the fort from the wagon party
     * @param wagon The wagon associated with the fort
     */
	public Location(String name, int miles, Wagon wagon) {
		this.name = name;
		this.visited = false;
		this.milesAway = miles;
		this.wagon = wagon;
	}
	
	/**
     * Updates the distance of the fort from the wagon party based on the pace.
     * @param pace The pace at which the wagon party is traveling
     */
	public void updateMilesAway(int pace) {
		milesAway -= pace;
		if (milesAway < 0) milesAway = 0;
	}

   /**
    * Retrieves the distance of the fort from the wagon party.
    * @return The distance of the fort from the wagon party
    */
	public int getMilesAway() {
		return milesAway;
	}
	
	/**
	 * Checks if the wagon party has arrived at the fort.
	 * @return true if they have and false otherwise
	 */
	public boolean arrivedAtLandmark() {
		if (milesAway == 0) return true;
		
		return false;
	}
	
	/**
     * Checks if the wagon party has already been to the fort
     * @return True if the wagon party has already been there, otherwise false
     */
	public boolean hasvisited() {
		return visited;
	}

	/**
     * Marks the fort as visited by the wagon party.
     */
	public void updatevisited() {
		visited = true;
	}

	/**
     * Retrieves the name of the fort.
     * @return The name of the fort
     */
	public String getName() {
		return name;
	}
	
	/**
     * Simulates the wagon party visiting the fort.
     * @param wagon The wagon object associated with the fort
     */ 
	public void visit(Wagon wagon) {
		this.visited = true;
	}

    /**
     * Generates a random phrase for conversation at the fort.
     * @return A randomly selected phrase for conversation
     */
	//public String generateRandomPhrase() {
	  //      Random random = new Random();
	 //       int index = random.nextInt(phrases.length);
	 //       return phrases[index];
	// }

    /**
     * Simulates shopping for items at the fort.
     * @return A list of equipment items available for sale at the fort
     */
	//public List<Equipment> shop(){
	//	List<Equipment> itemsForSale = new ArrayList<>();
	//	itemsForSale.add(new Food("Apple", 10, 1, true));
	//	itemsForSale.add(new Food("Bread", 5, 1, true));
	//	itemsForSale.add(new Equipment("Water", 20, 1));
	//	return itemsForSale;
	//}

    /**
     * Displays the items available for sale at the fort in a graphical window.
     */
	//public void displayItemsForSale() {
    //    List<Equipment> itemsForSale = shop();
    //    JFrame shopWindow = new JFrame("Items for Sale at " + name);
    //    shopWindow.setSize(300, 400);
    //    shopWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //    JPanel panel = new JPanel();
    //    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    //    for (Equipment item : itemsForSale) {
    //        JLabel label = new JLabel(item.getName() + ": " + item.getQuantity());
    //        JButton Add = new JButton("Add: ");
    //        Add.addActionListener(new ActionListener() {
    //        	public void actionPerformed (ActionEvent e) {
    //        		wagon.addItem(item);
    //        	}
    //        });
    //        panel.add(Add);
    //        panel.add(label);
    //    }

    //    shopWindow.add(panel);
    //    shopWindow.setVisible(true);
   // }
}

