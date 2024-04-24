/**
 * Money.java
 * 
 * akhvbkaebv
 * 
 * @author
 * @version
 */
public class Money {
	
	private int total = 0;
	
	/**
	 * 
	 * @param total
	 */
	public Money(int total) {
		this.total = total;
	}
	
	/**
	 * 
	 * @param spent
	 * @return
	 */
	public boolean isMoneyAvailable(int spent) {
		if(spent > total) return false;
		else return true;
	}
	
	/**
	 * 
	 * @param spent
	 */
	public void spendMoney(int spent) {
		total -= spent;
	}
	
	/**
	 * 
	 * @return
	 */
	public String displayMoney() {
        double formattedAmount = total / 100.0; // Convert to double for decimal formatting
		String formattedTotal = String.format("$%.2f", formattedAmount);
		return formattedTotal;
	}
}
