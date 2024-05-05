/**
 * Fort.java
 * 
 * The Fort class represents a fort or landmark on the journey, 
 * which the wagon party can visit during their journey. 
 * It contains information about the fort's name, distance away from the wagon party, 
 * and whether it has been visited or not.
 * 
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version- 1.1 April 8 2024 
 */

public class Fort extends Location {
	private int priceFactor;
	public Fort(String name, int miles, int priceFactor) {
		super(name, miles);
		this.priceFactor = priceFactor;
	}
	
	public int getPriceFactor() {
		return priceFactor;
	}
	
	
	
	// Phrases for chattering, categorized by fort names
    private String[] phrasesForFortA = {"Amelia says to the group, \" We are sleeping in a lane while waiting to cross a river nearby and still can't feed our cows properly. I hope we can find feed or grass for them soon.\"" };
    private String[] phrasesForFortB = {"Amelia points ahead \" We saw that wagon a few days ago didn’t we? It looks like the father is gone, probably drowned in the upcoming river. I feel terrible for the children and wife.\""};
    		
    private String[] phrasesForFortC = {"Amelia complains \"It's rather warm in this summer heat,and there is nothing in this Fort at all, just a couple of mud houses.\""};
    private String[] phrasesForFortD = {"Amelia excitedly said \"There’s a shower at this fort! It’s only a couple of cents. I’m happy to get the dust from our journey off of me.\""};
    		
    // Add more fort-specific phrases as needed

    public String generatePhrase() {
        String phrase = "";

        // Check the fort's name and select phrases accordingly
        // More phrases can be added and selected later
        if (getName().equals("Kanesville")) {
            phrase = phrasesForFortA[0]; // Select the first phrase for Fort A
        } else if (getName().equals("Mormon Graveyard")) {
            phrase = phrasesForFortB[0]; // Select the first phrase for Fort B
        }
        else if(getName().equals("Fort Boise")) {
        	phrase = phrasesForFortC[0]; // Select the first phrase for Fort C
        }else if(getName().equals("Fort Walla Walla")) {
        	phrase = phrasesForFortD[0]; //Select the first phrase for Fort D
        }
        
        // Add more conditions for other forts as needed

        return phrase;
    }

	

}
