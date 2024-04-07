	/**
	 * Food.java
	 * 
	 * The Food class extends the Item class to store if the food is nutritional or not.
	 * 
	 * @author - Lena Frate
	 * @version 1.1.1 - March 25 2024
	 */
public class Food extends Equipment {

		boolean isNutritional;
		
		/*
		 * Creates an Food object containing a name, weight, and if its nutritional or not
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
}
