/**
 * Equipment.java
 * 
 * The Equipment class stores an item including its name, weight, and quantity. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @version 1.1.1 - April 7 2024
 */
public class Equipment {

	private String name;
	private int weight; 
	private int quantity;

	/*
	 * Creates an Item object containing a name and weight
	 */
	public Equipment(String name, int weight, int quantity) {
		this.name = name;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	/**
	 * returns the name of the item
	 * @return name - the name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the weight of the item
	 * @return weight - the weight of the item
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * adds the specified amount to the quantity of the item
	 * @param quantity - the amount to be added
	 */
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	/**
	 * removes the specified amount from the quantity of the item
	 * @param quantity
	 */
	public void removeQuantity(int quantity) {
		this.quantity -= quantity;
	}
	
	/**
	 * returns the quantity of the item
	 * @return quantity - the amount of the item in the wagon
	 */
	public int getQuantity() {
		return quantity;
	}
}
