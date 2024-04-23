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

	public Fort(String name, int miles) {
		super(name, miles);
		// TODO Auto-generated constructor stub
	}
	// Phrases for chattering, categorized by fort names
    private String[] phrasesForFortA = {"We are sleeping in a lane while waiting to cross a river nearby and still can't feed our cows properly." };
    private String[] phrasesForFortB = {"Unfortunaly we passed a wagon train we saw a few days ago, with the father gone. I feel horrible for the children."};
    private String[] phrasesForFortC = {"I feel bad for all the cattle we've seen along the way. There are so many dead along the road to here."};
    private String[] phrasesForFortD = {"It's rather warm in this summer heat, at least my son seems to be regaining his health."};
    // Add more fort-specific phrases as needed

    public String generatePhrase() {
        String phrase = "";

        // Check the fort's name and select phrases accordingly
        // More phrases can be added and selected later
        if (getName().equals("Kanesvill")) {
            phrase = phrasesForFortA[0]; // Select the first phrase for Fort A
        } else if (getName().equals("Mormon Graveyard")) {
            phrase = phrasesForFortB[0]; // Select the first phrase for Fort B
        }
        else if(getName().equals("Fort Hall")) {
        	phrase = phrasesForFortC[0]; // Select the first phrase for Fort C
        }else if(getName().equals("Fort Boise")) {
        	phrase = phrasesForFortD[0]; //Select the first phrase for Fort D
        }
        
        // Add more conditions for other forts as needed

        return phrase;
    }

}
