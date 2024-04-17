/**
 * Wagon.java
 * 
 * The wagon class keeps track of the total food, total weight, adds items, removes items, and displays total weight. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @author - Sarah Slusher
 * @version 1.1.1 - April 7 2024
 */
import java.util.ArrayList;

public class Wagon {
	
	// 	private ArrayList<MemeberHealth> wagon = new ArrayList<>();
	private ArrayList<Equipment> wagon = new ArrayList<>();
	private float foodWeight = 0;
	private float totalWeight = 0;
	private float consumableWeight = 0;
	
	/**
	 * Creates an Wagon object
	 */
	public Wagon() {}
	
	/**
	 * adds an item to the wagon array and adds its weight to the total weight of the wagon. If the item is also nutritional,
	 * the weight gets added to the food weight as well
	 * @param item - the item being added to the array
	 */
	public void addItem(Equipment item) {
		// add the item to the array and get its weight
		int weight = item.getWeight();
		float quantity = item.getQuantity();
		wagon.add(item);
		
		// check if the item is food/consumable and add that weight to the respective totals
		if (item instanceof Food) {
			foodWeight = foodWeight + (weight*quantity);
			if (((Food) item).getNutrition()) 
			consumableWeight = consumableWeight + (weight*quantity);
		}
		
		// add the item weight to the total wagon weight
		totalWeight = totalWeight + (weight*quantity);
	}
	
	/**
	 * removes an item from the wagon array and removes its weight from the total weight of the wagon. If the item is also nutritional,
	 * the weight gets removed from the food weight as well
	 * @param item - the item being removed from the array
	 */
	public void removeItem(Equipment item, float quantity) {
		// remove the item from the array and get its weight
		int weight = item.getWeight();
		
		// check if the item is food/consumable and remove that weight from the respective totals
		if (item instanceof Food) {
			foodWeight = foodWeight - (weight*quantity);
			if (((Food) item).getNutrition()) {
			consumableWeight = consumableWeight - (weight*quantity);
			item.removeQuantity(quantity);
			}
		}
		
		// remove the item weight from the total wagon weight
		totalWeight = totalWeight - (weight*quantity);
	}
	
	/**
	 * returns the inventory of the wagon 
	 * @return wagon - the array list of everything in the wagon inventory
	 */
	public ArrayList<Equipment> getItems(){		
		return wagon;
	}
	
	/**
	 * makes all the variable names and weight into a string for display. 
	 * @return inventory - a string containing the formatted contents of the wagon to be displayed
	 */
	public String displayingInventory() {
		String inventory = "";
		for(int i = 0; i < wagon.size(); i++) { 
		    inventory = inventory +  wagon.get(i).getName() + ": " + wagon.get(i).getQuantity() + "\n";
		} 
		return inventory;
	}
	
	/**
	 * returns the total weight of the wagon
	 * @return totalWeight - the total weight of the wagon
	 */
	public float getTotalWeight() {
		return totalWeight;
	}
	
	/**
	 * returns the food weight of the wagon
	 * @return foodWeight - the weight of the food in the wagon
	 */
	public float getConsumableWeight() {
		return consumableWeight;
	}
}
