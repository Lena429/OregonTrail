/**
 * WagonMember.java
 * 
 * The wagonMember class contains the name of a certain member of the wagon 
 * and their disease and injury status.
 * 
 * Note: how diseases and injuries are handled were taken from Chapter 16 
 * of You Have Died of Dysentery by R. Philip Bouchard
 * 
 * @author - Lena Frate
 * @version 1.1.1 - April 29 2024
 */
public class WagonMember {
	
	private String name;
	private boolean hasDisease = false;
	private boolean hasInjury = false;
	private int diseaseCount;
	private int injuryCount;
	
	/**
	 * creates a wagon member with a name
	 * @param name - the name of the member
	 */
	public WagonMember(String name) {
		this.name = name;
	}
	
	/**
	 * gets the name of the member
	 * @return name - the name of the member
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * determines if the member will die 
	 * @return true - the member already has a disease/injury
	 * @return false - the member does not have a disease/injury 
	 */
	public boolean isAilmentDeadly() {
		if(hasDisease) return true;
		else if(hasInjury) return true;
		return false;
	}
	
	/**
	 * sets the day countdown for how long the member will have the disease
	 * and sets hasDisease to true
	 * Note: isAilemntDeadly should be called before to ensure member will 
	 * survive a new disease
	 */
	public void setDisease() {
		diseaseCount = 10;
		hasDisease = true;
	}
	
	/**
	 * sets the day countdown for how long the member will have the injury
	 * and sets hasInjury to true
	 * Note: isAilemntDeadly should be called before to ensure member will 
	 * survive a new injury
	 */
	public void setInjury() {
		injuryCount = 30;
		hasInjury = true;
	}
	
	/**
	 * decrements the disease/injury countdown if the member has one. Sets 
	 * hasDisease/hasInjury to false if the countdown reaches 0
	 */
	public void recoverHealth() {
		if(hasDisease) {
			diseaseCount--;
			if(diseaseCount == 0)
				hasDisease = false;
		} else if(hasInjury) {
			injuryCount--;
			if(injuryCount == 0)
				hasInjury = false;
		}
	}
	
	/**
	 * returns if the member is injured or diseased
	 * @return true - member is injured/diseased
	 * @return false - member is not injured/diseased
	 */
	public boolean getAilmentStatus() {
		if(hasInjury || hasDisease) return true;
		return false;
	}
}
