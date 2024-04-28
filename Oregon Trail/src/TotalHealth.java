import java.util.ArrayList;

/**
 * TotalHealth.java
 * 
 * make sure to say something about the chapter 16 reference to health
 * 
 * @author 
 * @version
 */

public class TotalHealth {
	
	private int health = 0;
	private ArrayList<WagonMember> people = new ArrayList<>();
	
	public TotalHealth() {
		
	}
	
	public void recoverHealth() {
		
	}
	
	public void loseHealth() {
		
	}
	
	/**
	 * determines is the group's health is deadly
	 * @return true - health is deadly
	 * @return false - health is not deadly
	 */
	public boolean isHealthDeadly() {
		if(health > 140) {
			return true;
		}
		return false;
	}
}
