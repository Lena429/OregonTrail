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
    private String[] phrasesForFortA = {"I'm hoping the weather stays nice" };
    private String[] phrasesForFortB = {"It's been a long journey"};
    // Add more fort-specific phrases as needed

    public String generatePhrase() {
        String phrase = "";

        // Check the fort's name and select phrases accordingly
        if (getName().equals("Kanesvill")) {
            phrase = phrasesForFortA[0]; // Select the first phrase for Fort A
        } else if (getName().equals("Mormon Graveyard")) {
            phrase = phrasesForFortB[0]; // Select the first phrase for Fort B
        }
        // Add more conditions for other forts as needed

        return phrase;
    }

}
