/**
 * Money.java
 * 
 * The money class keeps track of the users money as an integer and 
 * provides methods for spending money and displaying money.
 * 
 * @author - Lena Frate
 * @version 1.1.1 April 24 2024
 */
public class Money {
	
	private int total = 0;
	
	/**
	 * creates an object of money with an initial value
	 * @param total - the initial starting money the user has
	 */
	public Money(int total) {
		this.total = total;
	}
	
	/**
	 * checks if the user has enough money to pay
	 * @param spent - the amount of money the user wants to spend
	 * @return true - the user has enough money
	 * @return false - the user does not have enough money
	 */
	public boolean isMoneyAvailable(int spent) {
		if(spent > total) return false;
		else return true;
	}
	
	/**
	 * removes the amount of money the user spent from their total
	 * Note: isMoneyAvailable should be checked first
	 * @param spent - the amount of money spent
	 */
	public void spendMoney(int spent) {
		total -= spent;
	}
	
	/**
	 * displays the money in the format $#.##
	 * @return formattedTotal - the total formatted to be displayed
	 */
	public String displayMoney() {
        double formattedAmount = total / 100.0; // Convert to double for decimal formatting
		String formattedTotal = String.format("$%.2f", formattedAmount);
		return formattedTotal;
	}
}
