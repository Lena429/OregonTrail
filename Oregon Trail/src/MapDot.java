/**
 * MapDot.java
 * 
 * This class handles the movement of the dot on the map so the user can see
 * how far they have travelled and how far they need to go
 * 
 * @author - Lena Frate
 * @version 1.1.1 - May 4 2024
 */
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JLabel;

public class MapDot {
	
	/**
	 * creates an instance of mapDot
	 */
	public MapDot() {}
	
	/**
	 * updates the location of the dot on the map in the main interface
	 * @param dotLbl - the dot label that needs to be moved
	 * @param milesTravelled - how far the user has travelled
	 */
	public void moveDot(JLabel dotLbl, int milesTravelled) {
		Scanner scr;
		try {
			// open up data file
			InputStreamReader isr = new InputStreamReader(MapDot.class.getResourceAsStream("mapData.csv"));
			
			scr = new Scanner (isr);
			
			// read in file
			// first two line is headers so ignore it
			// column 1 is miles
			// column 2 is the x-bound
			// column 3 is the y-bound
			
			// headers
			scr.nextLine();
			
			while (scr.hasNextLine()) {				
				Scanner mapData = new Scanner(scr.nextLine());
				mapData.useDelimiter(",");
				
				// store the first column of the line containing the month + zone
				int miles = mapData.nextInt();
				
				// search for the right month and zone in the data
				if(milesTravelled <= miles) {
					
					// store the appropriate weather data and break out of the loop
					int xBound = mapData.nextInt();
					int yBound = mapData.nextInt();

					dotLbl.setBounds(xBound, yBound, 47, 67);
					break;
				}			
			}
			// close the file
			scr.close();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Map data error - exiting...");
			System.exit(1);
		}
	}
}
