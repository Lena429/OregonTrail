/**
 * River.java
 * 
 * The river class gets the name, height, flow, and width of a river from a txt file. It also has random events for crossing the river, 
 * as well as different options for crossing the river. 
 * 
 * @author - Lillyan Stewart 
 * @author - Lena Frate
 * @version 1.1.1 - April 17 2024
 */
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;
import java.io.InputStream;
import java.io.InputStreamReader;

public class River extends Location {
	
	private static Scanner scr;
	private static InputStreamReader reader = null;
	Random rnd = new Random();
	private int height;
	private int flow;
	private int width;

	
	// Phrases for conversations
    private String[] phraseRiver1 = {"Amelia says, \"We passed Pisgah and will cross Grand River soon. My head aches, but I will make tea tonight to cure it.\"",
    								 "Almira says, \"I already miss my old home. I hope this long journey is worth the wait\"",
    								 "Plutarch says, \"Getting stuck in mud is the worst. It takes a lot of work to get the wagon moving again.\""};
    
    private String[] phraseRiver2 = {"Amelia says, \"I hope there are grassy lands nearby. We have run out of feed for the stock.\"",
    								 "Plutarch says, \"I can't wait to take the Hindoo across the river. I hope our cattle cooperates\"",
    								 "Lucy says, \"I was very sad about the young calf we had to leave behind a few days ago\""};
    
    private String[] phraseRiver3 = {"Amelia says, \"We hear there are nearly 700 wagons here! I hope I'll be able to find a spot to wash and cook this afternoon.\",",
    								 "Plutarch says, \"I hope are cattle are more cooperative this time around. One jump into the water the last time we tried to cross a river and it came out looking like a drowned rat\"",
    								 "Lucy says, \"My mom told me we have to wait our turn to cross the river. I hope it is our turn soon.\""};
    
    private String[] phraseRiver4 = {"Amelia says, \"A man drowned at Elkhorn River today. I pity his wife and children who are mourning near their wagon.\"",
	 								 "Seneca says, \"We plan to make a ferry out of our wagon using our water-proof wagon bed.\"",
	 								 "Lucy says, \"I am glad the Native Americans let us use their bridge to cross that small creek a couple of days ago. It saved us from having to wade through the water.\""};
    
    private String[] phraseRiver5 = {"Amelia says, \"Platte is a beautiful river about a mile across, full of islands and sand bars. There are also an abundance of prickly pears growing along it.\"",
    								 "Plutarch says, \"I finally got over my sickness. I can drive the wagon again now.\"",
    								 "Seneca says, \"My brothers and I helped our mom do some washing today while we have access to water.\""};
    
    private String[] phraseRiver6 = {"Amelia says, \"We plan to ford the river late this afternoon by raising the wagon beds a foot, to prevent the water from running in. I wish you luck on your journey.\"",
    								 "Lucy says, \"I worry about my mom. She hasn't been feeling well, and she passed out earlier today after taking care of my younger brother Chatfield.\"",
    								 "Almira says, \"I have so many bug bites. The air seems to be filled with gnats and mosquitoes."};
    
    private String[] phraseRiver7 = {"Amelia says, \"Make sure you keep an eye on the children. My son gave me a scare the other day when he fell under the wagon. Somehow he kept from under the wheels and escaped uninjured. I never was so much frightened in my life.\"",
    								 "Seneca says, \"We had to travel down a steep bank earlier to get to water, but the cattle were so tired they couldn't even drink\"",
    								 "Plutarch says, \"We have to swim the stock across the river. It is a very hard job for such a wide river.\""};
    
    private String[] phraseRiver8 = {"Amelia says, \"The trip so far has not been kind to me. Last night, I helped get supper and went to bed too sick to eat any myself. We suspect that the water here is bad, so watch what you drink.\"",
    								 "Lucy says, \"My mom told be to be wary of the water here. The bottom may be full of poison water.\"",
    								 "Almira says, \"One of our best milk cows died today. It seems like our cattle has been dying off very fast recently.\""};
    
    private String[] phraseRiver9 = {"Amelia says, \"I was able to trade with some Native Americans to get some salmon for a nice dinner.\"",
    								 "Seneca says, \"There's no good fire wood around here, so my siblings, my mother, and I were shivering throughout last night.\"",
    								 "Almira says, \"We had to split up some of the deckboards of our wagon to make a fire. There was no good firewood otherwise.\""};


	/**
	 * Creates a River object containing the name and miles
	 * @param name - the name of the River
	 * @param miles - how many miles until the River
	 */
	public River(String name, int miles) {
		super(name, miles);
	}
	
	/**
	 * returns the name of the river. (this method is for future use)
	 * @return name - the name of the river the user is at
	 */ 
    public String getRiver() {
    	String name = scr.nextLine();
    	return name;
    
    }
	
    
    /**
	 * returns height of the water level at the river the user is at
	 * @return height - the height of the water
	 */
	public int setHeight(JLabel wthrQtyLbl) {
		height = scr.nextInt();
		int random = rnd.nextInt(3)+1;
		if (random == 1) {
			height = (height + (rnd.nextInt(3)+1)); //increments the height of the river 1-3 feet if raining
		}
		else if (random == 2) {
			height = (height - (rnd.nextInt(3))+1); //decrements the height of the river 1-3 feet if raining
		}
		return height; 
	}
	
	
	/**
	 * returns the speed of the river, or error if the speed is out of bounds
	 * @return Slow/Steady/Fast - the speed of the river associated with the number
	 * @return Error - the speed of the river was out of bounds
	 */
	public String setFlow(JLabel wthrQtyLbl) {
		flow = scr.nextInt(); // reads in next integer
		int random = rnd.nextInt(4)+1;
		if (random == 1) {
			flow = flow + (rnd.nextInt(3)+1); //increments the flow of the river
			if (flow > 3) {
				flow = 3;
			}
		} else if (random == 2){
			flow = flow - (rnd.nextInt(3)+1); //decrements the flow of the river
			if (flow < 1) {
				flow = 1;
			}
		} else if (random == 3) {
			flow = flow - (rnd.nextInt(3)+1); //decrements the flow of the river
			if (flow < 1) {
				flow = 1;
			}
		}
		// checks to see what number speed the river flow is at
		switch(flow) {
		case 1: 
			return "Slow";
		case 2:
			return "Steady";
		case 3,4,5,6: 
			return "Fast";
		}
		return "Error";
	}
	
	
	/**
	 * returns the width of the river
	 * @return width - this is the width of the river the user is at 
	 */
	public int setWidth(JLabel wthrQtyLbl) {
		width = scr.nextInt();
		int random = rnd.nextInt(3)+1;
		if (random == 1) {
			width = (width + (rnd.nextInt(3)+1)); //increments the width of the river 1 to 3 feet if raining
		}
		else if (random == 2) {
			width = (width - (rnd.nextInt(3)+1));
		}
		return width; 
		
	}
	
	
	/**
	 * Can possibly generate a random event when the user crosses the river
	 * Since the user chose to cross the river without the ferry, the chance of negative
	 * consequences is higher. 
	 * @param money - this accesses the users money so a random event can impact the user
	 * @return "You made it across safely." - This string tells the user they have no consequences
	 * @return "You made it, but $22 fell out of your pocket." - This string tells the user their consequence
	 * @return "Error" - this tells the user there has been an error in the program
	 */
	public String randomEvtCross(Money bank) {
		int random = rnd.nextInt(5)+1;															   // generates a random number 1 - 5
		switch (random)
		{	
		// you safely crossed with no consequences 
		case 1:
		case 2: 
		case 3:
		case 4:
			return "You made it across safely.";
			//JOptionPane.showMessageDialog(null, "You made it across safely"); 				   // displays message
			//break;
			
		// you safely crossed but with consequences
		case 5: 
			
			// this needs to have a check
			bank.spendMoney(2200);													   // subtracts from money total 
			
			
			return "You made it, but $22 fell out of your pocket.";
			//JOptionPane.showMessageDialog(null, "You made it, but $22 fell out of your pocket"); // displays message
			//break;
		}	
		return "Error";
	}
	
	/**
	 * Can possibly generate a random event when the user crosses the river
	 * Since the user chose to cross the river with the ferry, the chance of negative
	 * consequences is lower. 
	 * @param money - this accesses the users money so a random event can impact the user
	 * @return "You made it across safely." - This string tells the user they have no consequences
	 * @return "You made it, but $22 fell out of your pocket." - This string tells the user their consequence
	 * @return "Error" - this tells the user there has been an error in the program
	 */
	public String randomEvtFerry(Money bank) {
				Random rnd = new Random(); 
				int random = rnd.nextInt(9)+1; 		 // generates a random number 1 - 9
				switch (random)
				{	
					// you safely crosses with no consequences
					case 1: 
					case 2: 
					case 3: 	
					case 4: 
					case 5:
					case 6: 
					case 7:
					case 8: 
						return "You made it across safely.";
					// you crossed safely but with consequences
					case 9:
						
						
						// this needs to have a check
						bank.spendMoney(2200);													   // subtracts from money total 
						
						
						return "You made it, but $22 fell out of your pocket.";
				}
				return "Error";
		}
	
	/**
	 * generates a randomly chosen phrase based on the current river
	 * @return phrase - the phrase to be displayed
	 */
    public String generatePhrase() {
        String phrase = "";
        
        Random rnd = new Random();
        int index = rnd.nextInt(3);
        
        // Check the river's name and select phrases accordingly
        if (getName().equals("Grand River")) {
            phrase = phraseRiver1[index]; 
        } else if (getName().equals("Missouri River")) {
            phrase = phraseRiver2[index]; 
        } else if(getName().equals("Loup Fork")) {
        	phrase = phraseRiver3[index];
        } else if(getName().equals("Elkhorn River")) {
        	phrase = phraseRiver4[index];
        } else if(getName().equals("Platte River")) {
        	phrase = phraseRiver5[index];
        } else if(getName().equals("Raft River")) {
        	phrase = phraseRiver6[index];
        } else if(getName().equals("Salmon River")) {
        	phrase = phraseRiver7[index];
        } else if(getName().equals("Snake River")) {
        	phrase = phraseRiver8[index];
        } else if(getName().equals("Columbia River")) {
        	phrase = phraseRiver9[index];
        }
        
        return phrase;
    }
	

    /**
     * Open the text file and initialize the scanner
     */
    public static void openFile() {
        try {
            InputStream inputStream = River.class.getResourceAsStream("River.txt");
            
				reader = new InputStreamReader(inputStream);
			
            scr = new Scanner(reader);
        } catch (Exception e) {
            System.out.println("Couldn't open file: " + e.getMessage());
        }
    }

    /**
     * This closes the file.
     */
    public static void closeFile() {
        if (scr != null) {
            scr.close();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + e.getMessage());
            }
        }
    }
  
    /**
     * This is going to be used when the user fords the river. It gets the height of the water and splits it in to three categories.
     * @return 1- this category means the user has little chance of negative consequences. 
     * @return 2- this category means the user has a medium chance of negative consequences. 
     * @return 3- this category means the user has a high chance of negative consequences. 
     */
    public int fording() {
    	if (height < 4) {
    		return 1;
    	}
    	else if (height > 3 && height < 6) {
    		return 2;
    	}
    	else {
    		return 3; 
    	}
    }
    
    /**
     * This is the random event that is going to be used for the consequences as they caulk the wagon to cross the river. This just made
     * it so that it was less confusing setting up the method in random events. 
     * @return 1- this returns 1, which means the flow is 1. 
     * @return 2- this returns 2, which means the flow is 2. 
     * @return 3- this returns 3, which means the flow is 3. 
     */
    public int caulking() {
    	if (flow == 1) {
    		return 1;
    	}
    	else if (flow == 2) {
    		return 2;
    	}
    	else
    		return 3;
    }
}

