import javax.swing.JComboBox;

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
	 * 
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
