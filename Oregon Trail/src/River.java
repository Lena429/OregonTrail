/**
 * River.java
 * 
 * The river class gets the name, height, flow, and width of a river from a txt file. It also has random events for crossing the river, 
 * as well as different options for crossing the river. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
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
	
	// Phrases for conversations
    private String[] phraseRiver1 = {"Amelia Knight says, \"We passed Pisgah and will cross Grand River soon. My head aches, but I will make tea tonight to cure it.\""};
    private String[] phraseRiver2 = {"Amelia Knight says, \"I hope there are grassy lands nearby. We have run out of feed for the stock.\""};
    private String[] phraseRiver3 = {"Amelia Knight says, \"A man drowned at Elkhorn River today. I pity his wife and children who are mourning near their wagon.\""};
    private String[] phraseRiver4 = {"Amelia Knight says, \"We hear there are nearly 700 wagons here! I hope I'll be able to find a spot to wash and cook this afternoon.\""};
    private String[] phraseRiver5 = {"Amelia Knight says, \"Platte is a beautiful river about a mile across, full of islands and sand bars. There are also an abundance of prickly pears growing along it.\""};
    private String[] phraseRiver6 = {"Amelia Knight says, \"We plan to ford the river late this afternoon by raising the wagon beds a foot, to prevent the water from running in. I wish you luck on your journey.\""};
    private String[] phraseRiver7 = {"Amelia Knight says, \"Make sure you keep an eye on the children. My son gave me a scare the other day when he fell under the wagon. Somehow he kept from under the wheels and escaped uninjured. I never was so much frightened in my life.\""};
    private String[] phraseRiver8 = {"Amelia Knight says, \"The trip so far has not been kind to me. Last night, I helped get supper and went to bed too sick to eat any myself. We suspect that the water here is bad, so watch what you drink.\""};
    private String[] phraseRiver9 = {" bruh"};

	/**
	 * Creates a River object containing the name and miles
	 * @param name - the name of the River
	 * @param miles - how many miles until the River
	 */
	public River(String name, int miles) {
		super(name, miles);
	}
	
	public River() {}  

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
	public double getHeight(JLabel wthrQtyLbl) {
		int height = scr.nextInt();
		if (wthrQtyLbl.getText().equals("Rainy")) {
			height = (height + (rnd.nextInt(3)+1)); //increments the height of the river 1-3 feet if raining
		}
		else if (wthrQtyLbl.getText().equals("Very Hot")) {
			height = (height - (rnd.nextInt(3))+1); //decrements the height of the river 1-3 feet if raining
		}
		return height; 
	}
	
	/**
	 * returns the speed of the river, or error if the speed is out of bounds
	 * @return Slow/Steady/Fast - the speed of the river associated with the number
	 * @return Error - the speed of the river was out of bounds
	 */
	public String getFlow(JLabel wthrQtyLbl) {
		int flow = scr.nextInt(); // reads in next integer
		if (wthrQtyLbl.getText().equals("Rainy")) {
			flow = flow + (rnd.nextInt(3)+1); //increments the flow of the river
			if (flow > 3) {
				flow = 3;
			}
		} else if (wthrQtyLbl.getText().equals("Snowy")){
			flow = flow - (rnd.nextInt(3)+1); //decrements the flow of the river
			if (flow < 1) {
				flow = 1;
			}
		} else if (wthrQtyLbl.getText().equals("Very Hot")) {
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
	public double getWidth(JLabel label) {
		double width = scr.nextInt();
		if (label.getText().equals("Rainy")) {
			width = (width + (rnd.nextInt(3)+1)); //increments the width of the river 1 to 3 feet if raining
		}
		else if (label.getText().equals("Very Hot")) {
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
	
	




    // Add more river-specific phrases as needed

    public String generatePhrase() {
        String phrase = "";

        // Check the river's name and select phrases accordingly
        if (getName().equals("Grand River")) {
            phrase = phraseRiver1[0]; 
        } else if (getName().equals("Missouri River")) {
            phrase = phraseRiver2[0]; 
        } else if(getName().equals("Loup Fork")) {
        	phrase = phraseRiver3[0];
        } else if(getName().equals("Elkhorn River")) {
        	phrase = phraseRiver4[0];
        } else if(getName().equals("Platte River")) {
        	phrase = phraseRiver5[0];
        } else if(getName().equals("Raft River")) {
        	phrase = phraseRiver6[0];
        } else if(getName().equals("Salmon River")) {
        	phrase = phraseRiver7[0];
        } else if(getName().equals("Snake River")) {
        	phrase = phraseRiver8[0];
        } else if(getName().equals("Columbia River")) {
        	phrase = phraseRiver9[0];
        }
        
        // Add more conditions for other forts as needed

        return phrase;
    }
	

//Open the text file and initialize the scanner
    public static void openFile() {
        try {
            InputStream inputStream = River.class.getResourceAsStream("River.txt");
            
				reader = new InputStreamReader(inputStream);
			
            scr = new Scanner(reader);
        } catch (Exception e) {
            System.out.println("Couldn't open file: " + e.getMessage());
        }
    }

// Close the scanner
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


}

