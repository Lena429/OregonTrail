

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class RandomEvents {
	Random rnd = new Random();
	private Money bank;
	private ArrayList<Location> locations;
	private Travel travel;
	private Wagon wagon;
	private JLabel foodMainLbl;
	private JLabel dateMainLbl;
	private JLabel wthrQtyLbl;
	private Equipment food; 
	private int people = 4; 
	
	/**
	 * 
	 * @param locations
	 * @param bank
	 */

	public RandomEvents(ArrayList<Location> locations, Money bank, Travel travel, JLabel foodMainLbl, JLabel dateMainLbl, JLabel wthrQtyLbl) {
		this.locations = locations;
		this.bank = bank;
		this.travel = travel; 
		this.foodMainLbl = foodMainLbl;
		this.dateMainLbl = dateMainLbl;
		this.wthrQtyLbl = wthrQtyLbl;
	}
	/**
	 * 
	 * 
	 * 
	 * 
	 * out of food 5% chance local indians give you 30 lbs
	
	severe thunderstorm based on average precipatition for currnet location and currnet month
	
	severe blizzard 15% chance each day in which the temp is cold or very cold
	
	heavy fog 6% chance each day except when temp is very hot. 50% chance losing a days travel
	
	hail storm- after fort hall a 6% chance each day ecept when the temp is very hot. 50% chance of losing a days travel
	
	injured/dead ox 2% each day on the prairie; 3.5% chance each day in the mountains. all oxen health- one is injured. one
	already injured then die. 
	
	injured party member 2% chance on prairie; 3.5% chance each day in the mountains. person who gets injured is random 
	
	snake bite - 0.7% chance each day
	
	lose trail- 2% chance each day
	
	wrong trail - 1% chance each day
	
	rough trail - 2.5% chance each day 
	
	finding wild fruit- may to sept only; 
	
	
	
	bad water - 10% chance each day in which accumulated rainfall is below 0.1 inch. 
	
	illness - 0% to 40% chance day, depending upon helath of the party. person and disease chosen randomly
	
	//everyday do a random number and then pick a random event??? or should I only give that option to do a random event if that requirement is already met. 
	*/
	
	
	//rainy image?
	/**
	 * This has the random chance of their being a thunderstorm when it is rainy. 
	 * @param foodMainLbl - reads in the current amount of food, so it can be subtracted when travelling
	 * @param dateMainLbl - reads in the date, so it can be changed according to time lost
	 * @param wthrQtyLbl - reads in the current weather to see if it is rainy. 
	 * @return "Uh-oh, thunderstorm. You lost a days time!" - alerts the user they lost time due to weather
	 * @return "ignore" - nothing happens to the user. 
	 */
	private String thunderstorm() {
		int random = rnd.nextInt(100)+1; //random number 1 - 100
		if (wthrQtyLbl.getText().equals("Rainy")) {
			if (random <= 20 ) {
					travel.updateDate();
					wagon.removeItemQty(food, travel.getRations() * people); 
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					dateMainLbl.setText(travel.getDate());				
				return "Uh-oh, thunderstorm. You lost a days time!";
			}
		}
		return "ignore";
	}
	
	/**
	 * This has the random chance of their being a blizzard when it is snowy. 
	 * @param foodMainLbl - reads in the current amount of food, so it can be subtracted when travelling
	 * @param dateMainLbl - reads in the date, so it can be changed according to time lost
	 * @param wthrQtyLbl - reads in the current weather to see if it is snowy. 
	 * @return "Uh-oh, blizzard. You lost a days time!" - alerts the user they lost time due to weather
	 * @return "ignore" - nothing happens to the user. 
	 */
	private String blizzard() {
		int random = rnd.nextInt(100)+1; //random number 1 - 100
		if (wthrQtyLbl.getText().equals("Snowy")) {
			if (random <= 20 ) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * people); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateMainLbl.setText(travel.getDate());
				//should I add something here thta says uh oh you lost a day 
				return "Uh-oh, blizzard. You lost a days time!";
			}
		}
		return "ignore";
	}
	
	/**
	 * This has the random chance of their being random fog or hail on hot days. 
	 * @param foodMainLbl - reads in the current amount of food, so it can be subtracted when travelling
	 * @param dateMainLbl - reads in the date, so it can be changed according to time lost
	 * @param wthrQtyLbl - reads in the current weather to see if it is hot. 
	 * @return "Uh-oh, heavy [insert fog or hail]. You lost a days time!" - alerts the user they lost time due to weather
	 * @return "ignore" - nothing happens to the user. 
	 */
	private String heavyFogOrHail() {
		int random = rnd.nextInt(100)+1; //random number 1 - 100
		if (wthrQtyLbl.getText().equals("Hot")) {
			if (random <= 20 ) {
				random = rnd.nextInt(100)+1;
				if (random <= 5) {
					travel.updateDate();
					wagon.removeItemQty(food, travel.getRations() * people); 
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					dateMainLbl.setText(travel.getDate());
					return "Uh-oh, heavy hail. You lost a days time!";
				}
				else if (random <= 10) {
					travel.updateDate();
					wagon.removeItemQty(food, travel.getRations() * people); 
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					dateMainLbl.setText(travel.getDate());
					return "Uh-oh, heavy fog. You lost a days time!";
				}
			}
		}
		return "ignore";
	}
	
	
	/**
	 * You randomly found berries and they are added to your food storage. 
	 * @return "Congrats, you found some berries" - alerts the user to what is going on 
	 * @return "ignore" - nothing happened to the user. 
	 */
	private String findBerry(){
		//if statement to see if it is sept - march
		int random = rnd.nextInt(100)+1; //random number 1 - 100
		if (random <= 3 ) {
			//add food into wagon again 
			wagon.addItemQty(food, 30); //adds 30lbs of food. 
			return "Congrats, you found some berries.";
			}
	return "ignore";
	}
	
	private String injured() {
		int random = rnd.nextInt(100)+1; //random number 1 - 100
			if (random <= 2 ) {
				 //how tf do i get access to the person to increase their health 
				}
		return "ignore";
	}
	 	
		 
	private String snakeBite() {
		int random = rnd.nextInt(100)+1;
		if (random == 1) {
			//how td do i get access to health 
			//random member increment health 
		}
	 	
		 return "ignore";
		}
	
	
	//make tea do opposite of heal and instead will hurt you? mini game??? or just "oh no bad water" decrement health?
	private String badWater() {
		int random = rnd.nextInt(100)+1;{
		if (wthrQtyLbl.getText().equals("Hot"))
			if (random <= 5) {
				//how td do i get access to health 
				//subtract some helath from all members
			}
	 	
		 return "ignore";
		}
	}
	
	/**
	 * This subtracts money when a theif randomly snatches your cash. 
	 * @param foodMainLbl - this is how we get the user's balance. 
	 * @return "A thief stole some of your money" this will be dispalyed for the user to see
	 * @return "ignore" this will be ignored and nothing happens to the user. 
	 */
	private String thiefArrives() {
		String balance = foodMainLbl.getText();
		int intBalance = Integer.parseInt(balance);
		int random = rnd.nextInt(100)+1;
		if (random <= 2) {
			if (!balance.equals("0")){
				if (intBalance > 20) {
					bank.spendMoney(20); //subtracts $20 
					return "A thief stole some of your money!";
				}
			}
		}
	 	
		return "ignore";
	}
	
	/**
	 * A random wagon member was lost and a day is spent trying to find them. 
	 * @param foodMainLbl - food label display that is set/updated
	 * @param dateMainLbl - date label display that is set/updated
	 * @return "You've lost a member of your wagon...go find them." - this tells the user what happend
	 * @return "ignore" nothing happens to the user. 
	 */
	private String lostMember() {
		int random = rnd.nextInt(100)+1;
		if (random == 1) {
			//do we need to add a check here to see how many people are left in the wagon 
			int daysLost = rnd.nextInt(5)+1;
				for (;daysLost > 0; daysLost--) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * people); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateMainLbl.setText(travel.getDate());
				}

			return "You've lost a member of your wagon...go find them.";
		}
	 	
		 return "ignore";
		}
		
	
	
    /**
     * If the user is out of food, then the random event of native americans giving you food is possible. 
     * @param foodMainLbl is the label that holds the current food amount. 
     * @return "Local natives have decided to gift you 30lbs of foods" this means the user just got food. 
     * @return "ignore" this will be ignored and nothing will happen to the user. 
     */
	private String outOfFood() {
		int random = rnd.nextInt(100)+1;
		if (foodMainLbl.getText().equals("0")){
			if (random <= 5) {
				//how do I add food to the wagon or whatever
				wagon.addItemQty(food, 30);  //adds 30lbs of food; 
				return "Local natives have decided to gift you 30lbs of food";
					}
			}
		 return "ignore";
		}
	
	
	public void generateRandomEvent() {
		int random = rnd.nextInt(12)+1;
		switch(random)
		{
		case 1: 
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			break;
		}
	}
	
	
	
	
	/**public String illness(get health) {
	 	if (health < #){
	 	do this 
	 	}
	 	else {
	 	do this 
	 	}
	 return "lol";
	}
	*/
	

}
