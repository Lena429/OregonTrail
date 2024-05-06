/**
 * RandomEvents.java
 * 
 * This class controls all the random events throughout the game. 
 * 
 * @author Lillyan Stewart 
 * @version 1.1.1 - May 1, 2024
 * 
 */
import java.util.Random;
import javax.swing.JLabel;

public class RandomEvents {
	Random rnd = new Random();
	private Money bank;
	private TravelManager travel;
	private Wagon wagon;
	private WagonParty wagonParty;
	private JLabel foodMainLbl;
	private JLabel dateMainLbl;
	private JLabel wthrQtyLbl;
	private Food food; 
	private Equipment oxen;
	private int people; 

	
	/**
	 * This constructor sets us up to be able to access stuff in other classes. 
	 * @param bank - gives us the amount of money the user has 
	 * @param travel - gives us the date
	 * @param wthrQtyLbl -  gives us the current weather
	 * @param foodMainLbl - gives us the amount of food
	 * @param dateMainLbl - gives us access to the lbl so we can change the date
	 * @param wagon - gives us access to the stuff inside of wagon 
	 * @param food - gives us access to food so we can add or subtract some
	 * @param wagonParty - gives us access to the members of the wagon.
	 */
	public RandomEvents(Money bank, TravelManager travel,JLabel foodMainLbl,
			JLabel dateMainLbl, JLabel wthrQtyLbl, WagonParty wagonParty, Wagon wagon, Food food) {
		this.bank = bank;
		this.travel = travel; 
		this.foodMainLbl = foodMainLbl;
		this.dateMainLbl = dateMainLbl;
		this.wthrQtyLbl = wthrQtyLbl;
		this.wagon = wagon;
		this.food = food;
		this.wagonParty = wagonParty;
		people = wagonParty.getAmountOfMembers(); //members that are currently still alive
	}
	
	/**
	 * 
	 * @param wagon
	 * @param oxen
	 * @param food
	 */
	public RandomEvents(Wagon wagon, Equipment oxen, Food food) {
		this.oxen = oxen; 
		this.wagon = wagon;
		this.food = food;

	}

	/**
	 * 
	 * @return
	 */
	public String oxJumped() {
		int random = rnd.nextInt(100)+1;
		if (random <= 3) {
			wagon.removeItemQty(oxen, 1);
			return "Oh no, one of your ox jumped overboard and drowned";
		}
		return "You made it across safely.";
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public String oxAndFood(int number) {
		int random = rnd.nextInt(100)+1;
		int randomQty = 0;
		if (!food.isOutOfFood()) {
			randomQty = (rnd.nextInt(food.getQuantity()));
		}
		if (number == 1) {
			return "You made it across safely.";
		}
		else if (number == 2) {
			if (random <= 10) {
				wagon.removeItemQty(oxen, 1);
				return "Oh no, one of your ox drowned.";
			}
			if (random <= 20 && !food.isOutOfFood()) {
				wagon.removeItemQty(food, (int) (randomQty * 0.2));
				return "Oh no, some of your food got soggy and was ruined";
			}
		}
		else if (number == 3) {
			if (random <= 20) {
				wagon.removeItemQty(oxen, 1);
				return "Oh no, one of your ox drowned.";
			}
			if (random <= 30 && !food.isOutOfFood()) {
				wagon.removeItemQty(food, (int) (randomQty * 0.3));
				return "Oh no, some of your food got soggy and was ruined.";
			}	
		}
		return "You made it across safely.";
	}
	
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
			if (random <= 20) {          //random num less than or equal to 20, lose a days time
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
			if (random <= 20) {          //random num less than or equal to 20, lose a days time
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * people); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateMainLbl.setText(travel.getDate());
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
		if (wthrQtyLbl.getText().equals("Hot") || wthrQtyLbl.getText().equals("Very Hot")) {
			if (random <= 20 ) {
				random = rnd.nextInt(100)+1;
				if (random <= 10) { 	 //random num less than or equal to 10, lose a days time
					travel.updateDate();
					wagon.removeItemQty(food, travel.getRations() * people); 
					foodMainLbl.setText(wagon.getConsumableWeight() + "");
					dateMainLbl.setText(travel.getDate());
					return "Uh-oh, heavy hail. You lost a days time!";
				}
				else if (random <= 20) { //random num less than or equal to 20, lose a days time
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
	 * @return "ignore" - nothing to display.
	 */
	private String findBerry(){
		int random = rnd.nextInt(100)+1; //random number 1 - 100
		if (random <= 15) {  			 //random num less than or equal to 15, get food. 
			wagon.addItemQty(food, 30);  //adds 30lbs of food. 
			return "Congrats, you found some berries.";
			}
	return "ignore";
	}
	
	
	/**
	 * This random event, injures a member. If they are injured twice in a row they die. 
	 * @return "ignore" - nothing to display, becasue either nothing happpened or another method is displaying it.  
	 * @return "Oh, no! [member] broke a leg" - a member in the wagon broke a leg. 
	 */
	private String injured() {
		int random = rnd.nextInt(100)+1; 				  //random number 1 - 100
			if (random <= 10) {
				WagonMember index = wagonParty.getRandomMember();
				if (index.isAilmentDeadly()) {
					wagonParty.removeMember(index);	
					if (!wagonParty.membersStillAlive()) {//if they are the last member remaining, end the game. 
						wagonParty.displayGameOver();
					}
					return "ignore";
				} else {  								  //not the second time getting sick then display illness
					index.setInjury();
					wagonParty.loseHealthToAilment();
					return "Oh, no! " + index.getName() + " broke a leg :(";
				}
			}
		return "ignore";
	}
	 	
	/**
	 * This randomly generates a member being bitten by the snake.
	 * @return "[member] was bitten by a snake" - This alerts the user someone was bitten.
	 * @return "ignore" - nothing happened.
	 */
	private String snakeBite() {
		int random = rnd.nextInt(100)+1;
		if (random <= 20) {
	        String marvinTheSnake = "\uD83D\uDC0D";
			String name = wagonParty.getRandomMember().getName();
			wagonParty.loseHealthQty(2);
			return name + " was bitten by a snake. " + marvinTheSnake; 
		}
		 return "ignore";
		}
	
	
	/**
	 * This is the random event that they drink bad water. Health is incremented 5 pts for the whole wagon.
	 * @return "ignore" - nothing happened. 
	 * @return "Oh, no! Bad water was consumer." - this alerts the user that the random event has occurred. 
	 */
	private String badWater() {
		int random = rnd.nextInt(100)+1;{
		if (wthrQtyLbl.getText().equals("Hot") || wthrQtyLbl.getText().equals("Very Hot"))
			if (random <= 25) {
				wagonParty.loseHealthQty(10);
				return "Oh, no! Bad water was consumed.";
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
		int moneyBalance = bank.getMoney();
		int random = rnd.nextInt(100)+1;
		if (random <= 25) {
			if (moneyBalance > 20){
					bank.spendMoney(200); //subtracts $20 
					return "A thief stole $20 from you.";
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
		if (random == 30) {
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
	private String nativesHelp() {
		int random = rnd.nextInt(100)+1;
		if (food.isOutOfFood()){
			if (random <= 25) {
				wagon.addItemQty(food, 30);  //adds 30lbs of food; 
				return "Local natives have decided to gift you 30lbs of food";
					}
			}
		 return "ignore";
		}
	
	/**
	 * This random event gives a member an illness. If they get it twice then they die.
	 * @return "Ignore" 
	 * @return "Oh, dear... [member] has a fever" - a member in the wagon just has a random fever. 
	 * @return "Oh, WOW... [member] appears to heave scarlet fever" - a member in the wagon has a disease/illness
	 * @return "Holy moly! [member] has cholera." - a random member has a disease/illness.
	 * @return "Bummer... [member] has poison ivy." - a random member has a rash. 
	 * @return "Goodness me, [member] has [frostbite or heatstroke]" - these are both temperature related ailments. 
	 * @return "Oh, no! [member] has dystentery." - a random member has a disease/illness.
	 */
	private String illness() {
		int random = rnd.nextInt(100)+1;
		if (random <= 10) {
			WagonMember index = wagonParty.getRandomMember();
			if (index.isAilmentDeadly()) {
				wagonParty.removeMember(index);	
				if (!wagonParty.membersStillAlive()) {
					//if they are the last member remaining, end the game. 
					wagonParty.displayGameOver();
				}
				return "ignore";
			} else {
				index.setDisease();
				wagonParty.loseHealthToAilment();
				switch(random) {
				case 1: return "Oh, dear... " + index.getName() + " has a fever.";
					
				case 2: return "Oh, WOW... " + index.getName() + " appears to have scarlet fever";
					     
				case 3: return "Holy moly! " + index.getName() + " has cholera.";
						
				case 4: return "Bummer... " + index.getName() + " has poison ivy";
						
				case 5: 
					if (wthrQtyLbl.getText().equals("Hot") || wthrQtyLbl.getText().equals("Very Hot")) {
						return "Goodness me, " + index.getName() + " has heat stroke.";
					} else if (wthrQtyLbl.getText().equals("Snowy") || (wthrQtyLbl.getText().equals("Cold") || wthrQtyLbl.getText().equals("Very Cold")) ) {
						return "Goodness me, " + index.getName() + "  has frostbite.";
					} else {
						return "Oh, no! " + index.getName() + " has dysentery.";
					}	
				}
			}
		}
		return "ignore";
	}
	
	/**
	 * This picks one of the random event methods to do.
	 * @return result- the result holds the return of the selected method. This is then displayed to the user. 
	 */
	public String generateRandomEvent() {
		int random = rnd.nextInt(9)+1;
		String result = "ignore";
		switch(random)
		{
		case 1: if (wthrQtyLbl.getText().equals("Rainy")) thunderstorm(); 
				else if (wthrQtyLbl.getText().equals("Snowy")) blizzard();
				else if (wthrQtyLbl.getText().equals("Hot") || (wthrQtyLbl.getText().equals("Very Hot"))) heavyFogOrHail();
			break;
		case 2: result = findBerry();
			break;
		case 3: result = injured();
			break;
		case 4: result = snakeBite();
			break;
		case 5: result = thiefArrives();
			break;
		case 6: result = lostMember();
			break;
		case 7: result = nativesHelp();
			break;
		case 8:result = badWater();
			break;
		case 9:result =  illness();
			break;
		
		}
		return result; 
	}

}

