/**
 * changePace, displayMilesTraveled, displayDate
 * 
 * 
 */
public class Travel {
	private int rations;
	private int speed;
	private int milesTravelled = 0;
	private int month = 3;
	private int day = 1;
	private int year = 1860;
	
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
	public void setPace(int speed) {
		this.speed = speed;
	}
	
	/**
	 * 
	 */
	public int getPace() {
		return speed;
	}
	
	/**
	 * sets the rations per person
	 * @param rations - how much food (lbs) a person consumes per day
	 */
	public void setRations(int rations) {
		this.rations = rations;
	}
	
	/**
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	public int getRations() {
		return rations;
	}
	
	/**
	 * 
	 * @return
	 */
	public int updateMilesTravelled() {
		milesTravelled += speed;
		return milesTravelled;
	}
	
	
	public void incrementDate() {
		
	}
}
