/**
 * River.java
 * 
 * The river class gets the name, height, flow, and width of a river from a txt file. It also has random events for crossing the river, 
 * as well as different options for crossing the river. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @author - Sarah Slusher
 * @version 1.1.1 - April 17 2024
 */
import java.util.Random;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;

public class River extends Location {
	private static Scanner scr;
	private String river;
	private Wagon wagon = new Wagon();
	private static InputStreamReader reader = null;
	private Scanner in = null;
	
	// Phrases for conversations
    private String[] phraseRiver1 = {" "};
    private String[] phraseRiver2 = {" "};
    private String[] phraseRiver3 = {" "};
    private String[] phraseRiver4 = {" "};
    private String[] phraseRiver5 = {" "};
    private String[] phraseRiver6 = {" "};
    private String[] phraseRiver7 = {" "};
    private String[] phraseRiver8 = {" "};
    private String[] phraseRiver9 = {" "};

	/**
	 * Creates a River object containing the name and miles
	 * @param name - the name of the River
	 * @param miles - how many miles until the River
	 */
	public River(String name, int miles) {
		super(name, miles);
		  // InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream("River.txt")); // reads in a text file
		   //scr = new Scanner(isr);
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
	public int getHeight() {
		int height = scr.nextInt();
		return height;
	}
	
	/**
	 * returns the speed of the river, or error if the speed is out of bounds
	 * @return Slow/Steady/Fast - the speed of the river associated with the number
	 * @return Error - the speed of the river was out of bounds
	 */
	public String getFlow() {
		int flow = scr.nextInt(); // reads in next integer
		// checks to see what number speed the river flow is at
		switch(flow) {
		case 1: 
			return "Slow";
		case 2:
			return "Steady";
		case 3: 
			return "Fast";
		}
		return "Error";
	}
	
	/**
	 * returns the width of the river
	 * @return width - this is the width of the river the user is at 
	 */
	public int getWidth() {
		int width = scr.nextInt();
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
	public String randomEvtCross(Equipment money) {
		Random rnd = new Random();
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
			wagon.removeItemQty(money, 22); 													   // subtracts from money total 
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
	public String randomEvtFerry(Equipment money) {
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
					wagon.removeItemQty(money, 22);	 // subtracts from money total 
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

