/**
 * Food.java
 * 
 * The Food class extends the Item class to store if the food is nutritional or not.
 * 
 * Note: This class is not super beneficial for our current set-up but future 
 * iterations of the game would ideally have different foods
 * 
 * @author - Lena Frate
 * @version 1.1.1 - April 7 2024
 */
public class Food extends Equipment {

	boolean isNutritional;
	
	/**
	 * Creates a Food object containing a name, weight, and if its nutritional or not
	 * @param name - name of the food
	 * @param weight - weight of the food
	 * @param quantity - quantity of the food
	 * @param isNutritional - if the food is nutritional or not
	 */
	public Food(String name, int weight, int quantity, boolean isNutritional) {
		super(name, weight, quantity);
		this.isNutritional = isNutritional;
	}
	
	/**
	 * returns if the item is nutritional or not 
	 * @return isNutritional - true if the item is nutritional and false otherwise
	 */
	public boolean getNutrition() {
		return isNutritional;
	}
	
	/**
	 * returns if the user is out of food or not
	 * @return true - user is out of food
	 * @return false - user still has food
	 */
	public boolean isOutOfFood() {
		if(getQuantity() == 0) return true;
		return false;
	}
}
