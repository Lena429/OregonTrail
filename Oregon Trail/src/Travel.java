/**
 * Travel.java
 * 
 * The Travel class changes speed, rations, updates the date, and updates miles travelled. 
 * The resource consumption was determined by Chapter 16 of You Have Died of Dysentery.
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @author - Sarah Slusher
 * @version 1.1.1 - April 7 2024
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class Travel {
	private int rations;
	private int speed;
	private int milesTravelled = 0;
	private LocalDate startDate = LocalDate.of(1853, 3, 1);
	private LocalDate nextDate;
	private List<Location> locations = new ArrayList<>();
	
	/*
	 * Creates a Travel object containing default rations (3) and speed (12) values
	 */
	public Travel() {
		//initalize forts, rivers and landmarks here in order of appearance on map
		locations.add(new Fort("name", 100, null));
		
		rations = 3;
		speed = 12;
	}
	
	/**
	 * sets the miles traveled per day
	 * @param speed - the miles traveled per day
	 */
	public void setPace(JComboBox<String> paceComboBox) {
		//checks to see if selected. 
				String item = paceComboBox.getSelectedItem().toString();
				//Depending on what the person selects it will return the integer version of that number
				if (item.equals("12")) {
					this.speed = 12;
				}
				else if (item.equals("13")) {
					this.speed = 13;
				}
				else if (item.equals("14")) {
					this.speed = 14; 
				}
				else if (item.equals("15")) {
					this.speed = 15;
				}
				else if (item.equals("16")) {
					this.speed =  16;
				}
				else if (item.equals("17")) {
					this.speed = 17;
				}
				else if (item.equals("18")) {
					this.speed = 18;
				}
				else if (item.equals("19")) {
					this.speed = 19;
				}
				else {
					this.speed = 20;
				}
	}
	
	/**
	 *  Gets the current pace
	 *  @return speed - the speed that it is set at. 
	 */
	public int getPace() {
		return speed;
	}
	
	/**
	 * sets the rations per person
	 * @param rations - how much food (lbs) a person consumes per day
	 */
	public void setRations(JComboBox<String> rationsComboBox) {
		String item = rationsComboBox.getSelectedItem().toString();

		if (item.equals("Bare Bones")) {
			this.rations = 1;
		}
		else if (item.equals("Meager")) {
			this.rations = 2;
		}
		else {
			this.rations = 3;
		}
	}
	
	/**
	 * This allows us to properly display what ration setting the game is set to.
	 * @return ratWords - the ration setting. 
	 */
	public String displayRations() {
		String ratWords = "";
		switch(rations) {
		case 1: ratWords = "Bare Bones"; break;
		case 2: ratWords = "Meager"; break;
		case 3: ratWords = "Filling"; break;
		}
		return ratWords;
	}
	
	/**
	 * Gets the current ration setting
	 * @return rations - which is food setting 
	 */
	public int getRations() {
		return rations;
	}
	
	/**
	 * This updates the miles travelled. 
	 * @return milesTravelled - the total milesTravelled. 
	 */
	public int updateMilesTravelled() {
		milesTravelled += speed;
		return milesTravelled;
	}
	
	/**
	 * Gets the total miles travelled so far by the user
	 * @return milesTravelled - the total miles travelled so far
	 */
	public int getMilesTravelled() {
		return milesTravelled;
	}
	
	/**
	 * This updates the date as the clock continues.
	 * @return formattedDate - the date correctly formatted MMMM, d, yyyy
	 */
	public String updateDate() {
		// increment the date and store as new start date
		nextDate = startDate.plusDays(1);
		startDate = nextDate;
		
		// format the date and return the string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
	    String formattedDate = nextDate.format(formatter);
		
	    return formattedDate;
	}
	
	/**
	 * This gets the dates for the user.
	 * @return formattedDate - the date correctly formatted MMMM, d, yyyy
	 */
	public String getDate() {
		// format the date and return the string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
	    String formattedDate = startDate.format(formatter);
	    
	    return formattedDate;
	}
	
	 public void updateLocations() {
	        for (Location location : locations) {
	            location.updateMilesAway(speed);
	            if (!location.arrivedAtLandmark()) {
	                // Update UI or perform other actions based on miles away
	            } else if (!location.hasvisited()) {
	                // Update UI or perform other actions for visiting a location
	                location.updatevisited();
	                // Stop clock, update date, show frame, etc.
	                break; // Exit the loop once a location is visited
	            }
	        }
	}
}
