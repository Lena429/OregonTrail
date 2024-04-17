/**
 * Location.java
 * Holds all information for different locations throughout the trail
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version- 1.1 April 8 2024 
 */


public class Location {
	private Wagon wagon;
	private String name;        // The name of the fort
	private boolean visited;    // Indicates if the fort has been visited or not
	private int milesAway;      // Distance of the fort from the wagon party
	


    /**
     * Constructs a new Fort object with the specified name, distance, and associated wagon.
     * @param name The name of the fort
     * @param miles Distance of the fort from the wagon party
     * @param wagon The wagon associated with the fort
     */
	public Location(String name, int miles, Wagon wagon) {
		this.name = name;
		this.visited = false;
		this.milesAway = miles;
		this.wagon = wagon;
	}
	
	/**
     * Updates the distance of the fort from the wagon party based on the pace.
     * @param pace The pace at which the wagon party is traveling
     */
	public void updateMilesAway(int pace) {
		milesAway -= pace;
		if (milesAway < 0) milesAway = 0;
	}

   /**
    * Retrieves the distance of the fort from the wagon party.
    * @return The distance of the fort from the wagon party
    */
	public int getMilesAway() {
		return milesAway;
	}
	
	/**
	 * Checks if the wagon party has arrived at the fort.
	 * @return true if they have and false otherwise
	 */
	public boolean arrivedAtLandmark() {
		if (milesAway == 0) return true;
		
		return false;
	}
	
	/**
     * Checks if the wagon party has already been to the fort
     * @return True if the wagon party has already been there, otherwise false
     */
	public boolean hasvisited() {
		return visited;
	}

	/**
     * Marks the fort as visited by the wagon party.
     */
	public void updatevisited() {
		visited = true;
	}

	/**
     * Retrieves the name of the fort.
     * @return The name of the fort
     */
	public String getName() {
		return name;
	}
	
	/**
     * Simulates the wagon party visiting the fort.
     * @param wagon The wagon object associated with the fort
     */ 
	public void visit(Wagon wagon) {
		this.visited = true;
	}
  
}

