import java.util.Random;
import java.util.Scanner;
import java.io.InputStreamReader;

/**
 * Weather.java
 * 
 * 
 * @author 
 * @version
 */

public class Weather {

	private int zone = 0;
	private String displayTemp;
	private int tempValue;
	private double rainfall;
	private int snowfall;
	
	/*
	 * 
	 */
	public Weather() {}
	
	/**
	 * determines if the weather is different from the previous day
	 * @return true - different weather
	 * @return false - same weather
	 */
	public boolean isWeatherDifferent() {
		// check if a new weather pattern should be generated (50% chance)
		Random rnd = new Random(); 
		
		if(rnd.nextDouble() <= .5) {
			// yes
			return true;
		} else {
			// no
			return false;
		}
	}
	
	
	/*
	 * Reads the csv file and stores the appropriate rainfall, snowfall, and temp based
	 * on the month and the zone the user is in
	 */
	public void calculateWeather(String month) {
				
			Scanner scr;
			try {
				// open up data file
				InputStreamReader isr = new InputStreamReader(Weather.class.getResourceAsStream("WeatherData.csv"));
				
				scr = new Scanner (isr);
				
				// read in file
				// first two lines are headers so ignore them
				// column 1 is the month followed by the 3 zones
					// ex: January1
					//	   January2
				// column 2 is rainfall
				// column 3 is snowfall
				// column 4 is temperature
				
				// headers
				scr.nextLine();
				scr.nextLine();
				
				while (scr.hasNextLine()) {				
					Scanner tempData = new Scanner(scr.nextLine());
					tempData.useDelimiter(",");
					
					// store the first column of the line containing the month + zone
					String comparedMonth = tempData.next();
					
					// search for the right month and zone in the data
					if(comparedMonth.equals(month + zone)) {
						
						// store the appropriate weather data and break out of the loop
						rainfall = tempData.nextDouble();
						snowfall = tempData.nextInt();
						tempValue = tempData.nextInt();
	
						break;
					}			
				}
				// close the file
				scr.close();
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "Weather data error - exiting...");
				System.exit(1);
			}
	}
	
	/**
	 * Determines the zone based on how many miles the user has travelled
	 * @param milesTravelled - the distance the user has travelled
	 */
	public void setZone(int milesTravelled) {
		
		if(milesTravelled <= 700) zone = 1;
		else if (milesTravelled <= 1400) zone = 2;
		zone = 3;
	}
	
	/**
	 * Displays the temperature as a string from Very Cold to Very Hot
	 * @return displayTemp - the string describing the weather
	 */
	public String displayTemperature() {
		
		// generates a random number -20 to 20 and adds it to the avg temp of the month
		Random rnd = new Random(); 
		int random = rnd.nextInt(41) - 20;
		
		tempValue += random;
		
		// determines the correct description of the weather
		if(tempValue <= 10) displayTemp = "Very Cold";
		else if(tempValue <= 30) displayTemp = "Cold";
		else if(tempValue <= 50) displayTemp = "Cool";
		else if(tempValue <= 70) displayTemp = "Warm";
		else if(tempValue <= 90) displayTemp = "Hot";
		else displayTemp = "Very Hot";

		return displayTemp;
	}
	
	/**
	 * determines if the day is rainy, snowy, or neither
	 * @return true - it is rainy or snowy
	 * @return false - it is not rainy or snowy
	 */
	public boolean willItRainOrSnow() {
		Random rnd = new Random(); 
		
		if (tempValue <= 30) {
			double probabilityOfSnow = snowfall / 14.0;
			
			return rnd.nextDouble() <= probabilityOfSnow;

		} else {
			double probabilityOfRain = rainfall / 8.0;
			
			return rnd.nextDouble() <= probabilityOfRain;
		}
	}
	
	/**
	 * displays the weather if it is rainy or snowy
	 * @return Snowy - the weather is below 30 
	 * @return Rainy - the weather is above 30
	 */
	public String displayRainOrSnow() {
		if (tempValue <= 30) {
			return "Snowy";
		} else {
			return "Rainy";
		}
	}
	

}

