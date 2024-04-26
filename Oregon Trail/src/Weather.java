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

	private int zone = 1;
	private String displayTemp;
	private int tempValue;
	private double rainfall;
	private int snowfall;
	
	/*
	 * 
	 */
	public Weather() {}
	
	
	
	/*
	 * 
	 */
	public void calculateWeather(String month) {
		Scanner scr;
		try {
			// open up data file
			InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream("/src/WeatherData.csv"));
			
			scr = new Scanner (isr);
			
			// read in file
			// first two lines are headers so ignore them
			// column 1 is the month followed by the 3 zones
			// column 2 is rainfall
			// column 3 is snowfall
			// column 4 is temperature
			
			String headers = scr.nextLine() + scr.nextLine();
			while(!month.equals(scr.nextLine())) {
				// search for the right month in the data
			}
			Scanner tempData = new Scanner(scr.nextLine());
			tempData.useDelimiter(",");
			
			// determine the weather data based on zone
			switch(zone){
				case 1: String zone = tempData.next();
						rainfall = tempData.nextDouble();
						snowfall = tempData.nextInt();
						tempValue = tempData.nextInt();
						break;
				case 2: String wrongZone = scr.nextLine();
						String zone2 = tempData.next();
						rainfall = tempData.nextDouble();
						snowfall = tempData.nextInt();
						tempValue = tempData.nextInt(); 
				case 3: String wrongZone2 = scr.nextLine() + scr.nextLine();
						String zone3 = tempData.next();
						rainfall = tempData.nextDouble();
						snowfall = tempData.nextInt();
						tempValue = tempData.nextInt(); 
			}
			
			scr.close();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Weather data error - exiting...");
			System.exit(1);
		}
	}
	
	public void setZone(int milesTravelled) {
		zone = milesTravelled;
	}
	
	/**
	 * 
	 * @return
	 */
	public String displayTemperature() {
		
		Random rnd = new Random(); 
		int random = rnd.nextInt(41) - 20;
		
		tempValue += random;
		
		if(tempValue < 10) displayTemp = "Very Cold";
		else if(tempValue < 30) displayTemp = "Cold";
		else if(tempValue < 50) displayTemp = "Cool";
		else if(tempValue < 70) displayTemp = "Warm";
		else if(tempValue < 90) displayTemp = "Hot";
		else displayTemp = "Very Hot";

		return displayTemp;
	}
}

