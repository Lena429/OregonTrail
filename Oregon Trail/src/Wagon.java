import java.util.ArrayList;

public class Wagon {
	
	// 	private ArrayList<MemeberHealth> wagon = new ArrayList<>();
	private ArrayList<Equipment> wagon = new ArrayList<>();
	private int foodWeight = 0;
	private int totalWeight = 0;
	
	
	public void addItem(Equipment item) {
		// add the item to the array and get its weight
		int weight = item.getWeight();
		wagon.add(item);
		
		// check if the item is food/consumable and add that weight to the respective totals
		if (item instanceof Food) {
			foodWeight += weight;
		}
		
		// add the item weight to the total wagon weight
		totalWeight += weight;
	}
	
	/**
	 * returns the total weight of the wagon
	 * @return totalWeight - the total weight of the wagon
	 */
	public int getTotalWeight() {
		return totalWeight;
	}
	
	/**
	 * returns the food weight of the wagon
	 * @return foodWeight - the weight of the food in the wagon
	 */
	public int getFoodWeight() {
		return foodWeight;
	}
}
