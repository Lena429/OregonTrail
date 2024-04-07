/**
 * Item.java
 * 
 * The Item class stores an item including its name and weight. 
 * 
 * @author - Lena Frate
 * @version 1.1.1 - 25 March 2024
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
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void removeQuantity(int quantity) {
		this.quantity -= quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
