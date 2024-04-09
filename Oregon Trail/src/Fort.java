import java.util.Random;

//why is nothing working :(
public class Fort {
	private String name;
	private boolean visited;
	private int milesAway;
	//phrases for talking to people
	private String[] phrases = {
			"I'm hoping the weather stays nice", "Have you seen that large caravan up ahead?", "I heard the flu is going around, so be careful!"
	};
	
	public Fort(String name, int miles) {
		this.name = name;
		this.visited = false;
		this.milesAway = miles;
	}
	
	/**
	 * 
	 */
	public void updateMilesAway(int pace) {
		milesAway -= pace;
		if (milesAway < 0) milesAway = 0;
	}
	
	public int getMilesAway() {
		return milesAway;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean arrivedAtLandmark() {
		if (milesAway == 0) return true;
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasvisited() {
		return visited;
	}
	
	public void updatevisited() {
		visited = true;
	}
	
	public String getName() {
		return name;
	}
	
	//hoping this commit works 
	public void visit(Wagon wagon) {
		this.visited = true;
		
		}
	}
		
		private void shop(Wagon wagon) {
			System.out.println(" ");
		}
	
	public String generateRandomPhrase() {
	        Random random = new Random();
	        int index = random.nextInt(phrases.length);
	        return phrases[index];
	    }
	
		
		private void rest(Wagon wagon) {
			
		}
	}

