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
import javax.swing.JOptionPane;

public class WagonParty {
	
	private boolean starvedPreviousDay = false;
	private boolean dehydratedPreviousDay = false;
	private final int STARVE_AND_DEHYDRATE_CONST = 4;
	private int starveFactor = 1;
	private int dehydrateFactor = 1;
	private int health = 0;
	private ArrayList<WagonMember> people = new ArrayList<>();
	
	/**
	 * creates a wagon party object
	 */
	public WagonParty() {}
	
	/**
	 * adds a member to the people arrayList
	 * @param person - the person to be added
	 */
	public void addMember(WagonMember person) {
		people.add(person);
	}
	
	/**
	 * removes a member from the people arrayList and displays death
	 * @param person - the person to be removed
	 */
	public void removeMember(WagonMember person) {
		person.displayMemberDeath();
		people.remove(person);
	}
	
	/**
	 * removes a random member from the people arrayList and displays
	 * death message
	 */
	public void removeRandomMember() {
		// generates a random index
		Random rnd = new Random();
		int index = rnd.nextInt(people.size());
		
		// displays who died and remove that person
		people.get(index).displayMemberDeath();
		people.remove(index);
	}
	
	/**
	 * gets a random member
	 * @return people.get(index) - the random party member
	 */
	public WagonMember getRandomMember() {
		Random rnd = new Random();
		int index = rnd.nextInt(people.size());
		
		return people.get(index);
	}
	
	/**
	 * checks if there are still people alive
	 * @return true - if there are still others alive
	 * @return false - if there is no one left
	 */
	public boolean membersStillAlive() {
		if(people.size() == 0) return false;
		return true;
	}
	
	/**
	 * gets the amount of people still alive
	 * @return the amount of people alive
	 */
	public int getAmountOfMembers() {
		return people.size();
	}
	
	/**
	 * gets the list of people in the wagon
	 * @return people - the list of people in the wagon party
	 */
	public ArrayList<WagonMember> getMembers() {
		return people;
	}
	
	/**
	 * recovers 10% of the wagon health
	 * recovers the diseases/injuries of wagon members
	 */
	public void recoverDailyHealth() {
		health = (int) (health * .9);
		for(WagonMember person: people) {
			person.recoverHealth();
		}
	}
	
	/**
	 * recovers the specified amount of health
	 * @param recoveredHealth - the value to recover
	 */
	public void recoverHealth(int recoveredHealth) {
		if(recoveredHealth > health) health = 0;
		else health -= recoveredHealth;
	}
	
	/**
	 * loses a specific amount of health
	 * @param amountToLose - the amount of health to lose
	 */
	public void loseHealthQty(int amountToLose) {
		health += amountToLose;
	}
	
	/**
	 * loses health when disease/injury first strikes a member
	 * Note: value of 20 is taken from "You Have Died of Dysentery"
	 */
	public void loseHealthToAilment() {
		health += 20;
	}
	
	/**
	 * calculates how much health the wagon party loses based on factors like food, 
	 * weather, and diseases/injuries
	 * Note: recoverHealth should be called first
	 * @param travel  - the reference to the travel object to access pace and rations
	 * @param hasFood - if the user has food or not
	 * @param weather - the current weather for the day
	 * @param clothes - the sets of clothes the user has
	 * @param water	  - the amount of water the user has
	 */
	public void loseHealth(TravelManager travel, boolean outOfFood, String weather, Equipment clothes, Equipment water) {
		// loses health based on food status
		if(!outOfFood) {
			switch (travel.getRations()) {
				case 1: health +=4; break; // Bare Bones
				case 2: health +=2; break; // Meager
				case 3: break;			   // Filling
			}
			// reset the starve count
			starvedPreviousDay = false;
			starveFactor = 1;
		} else {
			// starving
			// amount of health lost will increase based on how long the user is starving
			if(starvedPreviousDay) {
				health = 6 + (STARVE_AND_DEHYDRATE_CONST * starveFactor);
				starveFactor = starveFactor + 2;
			} else {
				starvedPreviousDay = true;
				health += 6;
			}
		}
		
		// loses health if there is no water
		// amount of health lost will increase based on how long the user is dehydrated
		if(water.getQuantity() == 0) {
			// dehydrated
			if(dehydratedPreviousDay) {
				health = 4 + (STARVE_AND_DEHYDRATE_CONST * dehydrateFactor);
				dehydrateFactor = dehydrateFactor + 2;
			} else {
				dehydratedPreviousDay = true;
				health += 4;
			}
		} else {
			// reset the dehydration count
			dehydratedPreviousDay = false;
			dehydrateFactor = 1;
		}
		
		// loses health based on the weather
		switch (weather) {
			case "Very Hot":  health += 2; break;
			case "Hot":		  health += 1; break;
			case "Cool":							// cool and warm are optimal temps
			case "Warm": 	  break;
			case "Cold":
				// adjust health based on clothing quantity
				if(2 * people.size() < clothes.getQuantity())
					// adequate clothing
					break;
				else if(people.size() < clothes.getQuantity()) {
					// only 1 set of clothes per person
					health += 1;
					break;
				} else {
					// not enough clothes for everyone
					health += 2;
					break;
				}
			case "Very Cold": health += 4; break;
		}
		
		// loses health based on pace
		if(travel.getPace() < 14) 		health += 2;	// steady
		else if (travel.getPace() < 16) health += 3;
		else if (travel.getPace() < 18) health += 4;	// strenuous
		else if (travel.getPace() < 19) health += 5;
		else 							health += 6;	// grueling
		
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
	 * displays the health as a string from Good to Very Poor
	 * @return displayHealth - the string describing health
	 */
	public String displayHealth() {
		String displayHealth;
		
		if(health < 35) 	  displayHealth = "Good";
		else if(health < 70)  displayHealth = "Fair";
		else if(health < 105) displayHealth = "Poor";
		else 				  displayHealth = "Very Poor";
		
		return displayHealth;
	}
	
	/**
	 * returns the health of the wagon
	 * @return health - the overall health of the wagon
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * displays a dialogue box that lets the user know all the members have died.
	 * also ends the game
	 * @param frame - the frame to center the message on
	 */
	public void displayGameOver() {
		String text = "All members of the wagon have perished :(";
		String title = "Game Over!";
		int type = JOptionPane.ERROR_MESSAGE;
		int response = JOptionPane.showConfirmDialog(null, text, title, JOptionPane.DEFAULT_OPTION, type);
		if(response == JOptionPane.OK_OPTION) System.exit(1);
	}
}
