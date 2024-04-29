/**
 * WagonParty.java
 * 
 * The wagonParty class contains an arrayList of the members of the wagon. Additionally,
 * it calculates the total health of the wagon party based on factors like pace, rations, 
 * temperature, and food available.
 * 
 * Note: math and other factors of health were taken from Chapter 16 of You Have Died of 
 * Dysentery by R. Philip Bouchard
 * 
 * @author - Lena Frate
 * @version 1.1.1 - April 29 2024
 */
import java.util.ArrayList;
import java.util.Random;

public class WagonParty {
	
	private boolean starvedPreviousDay = false;
	private final int STARVE_CONST = 2;
	private int starveFactor = 1;
	private int health = 0;
	private ArrayList<WagonMember> people = new ArrayList<>();
	
	public WagonParty() {
		
	}
	
	/**
	 * adds a member to the people arrayList
	 * @param person - the person to be added
	 */
	public void addMember(WagonMember person) {
		people.add(person);
	}
	
	/**
	 * removes a member from the people arrayList
	 * @param person - the person to be removed
	 */
	public void removeMember(WagonMember person) {
		people.remove(person);
	}
	
	/**
	 * removes a random member from the people arrayList and returns
	 * if they were the last one alive
	 * @return true - if they were the last alive
	 * @return false - if there are others alive
	 */
	public boolean removeRandomMember() {
		// generates a random index
		Random rnd = new Random();
		int index = rnd.nextInt(people.size());
		
		// removes that person
		people.remove(index);
		
		// checks if they were the last alive
		if(people.size() == 0) return true;
		return false;
	}
	
	/**
	 * gets the amount of people still alive
	 * @return the amount of people alive
	 */
	public int getAmountOfMembers() {
		return people.size();
	}
	
	/**
	 * recovers 10% of the wagon health
	 */
	public void recoverHealth() {
		health = (int) (health * .9);
	}
	
	/**
	 * calculates how much health the wagon party loses based on factors like food, 
	 * weather, and diseases/injuries
	 * Note: recoverHealth should be called first
	 * @param rations - the consumable rate chosen by the user
	 * @param hasFood - if the user has food or not
	 * @param weather - the current weather for the day
	 * @param pace    - the speed at which the party is travelling
	 */
	public void loseHealth(int rations, boolean outOfFood, String weather, int pace) {
		// loses health based on food status
		if(!outOfFood) {
			switch (rations) {
				case 1: health +=4; break; // Bare Bones
				case 2: health +=2; break; // Meager
				case 3: break;			   // Filling
			}
			// reset the starve count
			starvedPreviousDay = false;
			starveFactor = 1;
		} else {
			// starving
			if(starvedPreviousDay) {
				health = 6 + (STARVE_CONST * starveFactor);
				starveFactor++;
			} else {
				starvedPreviousDay = true;
				health += 6;
			}
		}
		
		// loses health based on the weather
		switch (weather) {
			case "Very Hot":  health += 2; break;
			case "Hot":		  health += 1; break;
			case "Cool":							// cool and warm are optimal temps
			case "Warm": 	  break;
			case "Cold": 	  health += 2; break;
			case "Very Cold": health += 4; break;
		}
		
		// loses health based on pace
		if(pace < 15) 		health += 2;	// steady
		else if (pace < 18) health += 4;	// strenuous
		else 				health += 6;	// grueling
		
		// loses health based on diseases/injuries of members
		for(WagonMember person : people) {
			// if the member is injured/diseased, add 1
			if(person.getAilmentStatus()) health++; 
		}
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
	
	/**
	 * Displays the health as a string from Good to Very Poor
	 * @return displayHealth - the string describing health
	 */
	public String displayHealth() {
		String displayHealth;
		
		if(health < 35) 	  displayHealth = "Good";
		else if(health < 70)  displayHealth = "Fair";
		else if(health < 105) displayHealth = "Poor";
		else 				  displayHealth = "Very Poor";
		
		return health + displayHealth;
	}
}
