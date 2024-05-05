

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class RandomEvents {
	Random rnd = new Random();
	private Money bank;
	private ArrayList<Location> locations;
	private TravelManager travel;
	private Wagon wagon;
	private WagonParty wagonParty;
	private JLabel foodMainLbl;
	private JLabel dateMainLbl;
	private JLabel wthrQtyLbl;
	private Equipment food; 
	private int people; //change this 

	
	/**
	 * 
	 * @param locations
	 * @param bank
	 */

	public RandomEvents(ArrayList<Location> locations, Money bank, TravelManager travel,JLabel foodMainLbl,
			JLabel dateMainLbl, JLabel wthrQtyLbl, WagonParty wagonParty, Wagon wagon) {
		this.locations = locations;
		this.bank = bank;
		this.travel = travel; 
		this.foodMainLbl = foodMainLbl;
		this.dateMainLbl = dateMainLbl;
		this.wthrQtyLbl = wthrQtyLbl;
		this.wagon = wagon;
		this.wagonParty = wagonParty;
		people = wagonParty.getAmountOfMembers(); 
	}

	
	
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
				
				return "Oh, no! A member broke a leg :(";
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
				wagonParty.loseHealthQty(5);
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
        double doubleBalance = Double.parseDouble(balance);
		int random = rnd.nextInt(100)+1;
		if (random <= 2) {
			if (!balance.equals("0")){
				if (doubleBalance > 20.0) {
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
				wagon.addItemQty(food, 30);  //adds 30lbs of food; 
				return "Local natives have decided to gift you 30lbs of food";
					}
			}
		 return "ignore";
		}
	
	//help me pls
	public boolean illness() {
		int random = rnd.nextInt(5)+1;
		if (random <= 5) {
			//return true;
			//fever
			//scarlet fever
			//heat stroke??
			//poison ivy 
			//cholera
		}
		return false;
	}
	
	
	public String generateRandomEvent() {
		int random = rnd.nextInt(10)+1;
		String result = "ignore";
		switch(random)
		{
		case 1: result = thunderstorm();
		//case 1: result = "1";
			break;
		case 2: result = blizzard();
		//case 2: result = "2";
			break;
		case 3: result = heavyFogOrHail();
		//case 3: result = "3";
			break;
		case 4: result = findBerry();
		//case 4: result = "4";
			break;
		case 5: result = injured();
		//case 5: result = "5";
			break;
		case 6: result = snakeBite();
		//case 6: result = "6";
			break;
		case 7: result = thiefArrives();
		//case 7: result = "7";
			break;
		case 8: result = lostMember();
		//case 8: result = "8";
			break;
		case 9: result = outOfFood();
		//case 9: result = "9";
			break;
		case 10: result = badWater();
		//case 10: result = "10";
			break;
		//case 11: result =  illness();
		//	break;
		}
		return result; 
	}

}

