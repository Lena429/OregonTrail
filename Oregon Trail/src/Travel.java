/**
 * Travel.java
 * 
 * The Travel class changes speed, rations, updates the date, and updates miles travelled. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @version 1.1.1 - April 7 2024
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;

public class Travel {
	private int rations;
	private int speed;
	private int milesTravelled = 0;
	private LocalDate startDate = LocalDate.of(1853, 3, 1);
	private LocalDate nextDate;
	
	/*
	 * Creates a Travel object containing default rations (3) and speed (12) values
	 */
	public Travel() {
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
	 *  @return speed that it is set at. 
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
	 * @return ratWords which is the ration setting. 
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
	 * @return rations which is food setting 
	 */
	public int getRations() {
		return rations;
	}
	
	/**
	 * This updates the miles travelled. 
	 * @return the total milesTravelled. 
	 */
	public int updateMilesTravelled() {
		milesTravelled += speed;
		return milesTravelled;
	}
	
	/**
	 * This updates the date as the clock continues.
	 * @return the date correctly formatted MMMM, d, yyyy
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
	 * @return the date correctly formatted MMMM, d, yyyy
	 */
	public String getDate() {
		// format the date and return the string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
	    String formattedDate = startDate.format(formatter);
	    
	    return formattedDate;
	}
}
