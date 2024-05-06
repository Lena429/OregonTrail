import java.util.Random;

/**
 * Fort.java
 * 
 * The Fort class represents a fort on the journey, 
 * which the wagon party can visit during their journey. 
 * It contains information about the fort's name, distance away from the wagon party, 
 * and whether it has been visited or not. Along with possible conversations to be had in each fort.
 * 
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version- 1.1 April 8 2024 
 */

public class Fort extends Location {
	private int priceFactor; // the multiplier for how much the price changes from base prices at a given fort
	public Fort(String name, int miles, int priceFactor) {
		super(name, miles);
		this.priceFactor = priceFactor;
	}
	
	public int getPriceFactor() {
		return priceFactor;
	}
	
	
	
	private String[] phrasesForFortA = {
	        "Amelia says to the group, \"We are sleeping in a lane while waiting to cross a river nearby and still can't feed our cows properly. I hope we can find feed or grass for them soon.\"",
	        "Amelia says, this is a trail run for multiple conversations at a given place"
	        // Add more phrases for Fort A as needed
	    };
	    private String[] phrasesForFortB = {
	        "Amelia points ahead, \"We saw that wagon a few days ago, didn’t we? It looks like the father is gone, probably drowned in the upcoming river. I feel terrible for the children and wife.\"",
	        // Add more phrases for Fort B as needed
	    };
	    private String[] phrasesForFortC = {
	        "Amelia complains, \"It's rather warm in this summer heat, and there is nothing in this Fort at all, just a couple of mud houses.\"",
	        // Add more phrases for Fort C as needed
	    };
	    private String[] phrasesForFortD = {
	        "Amelia excitedly said, \"There’s a shower at this fort! It’s only a couple of cents. I’m happy to get the dust from our journey off of me.\"",
	        // Add more phrases for Fort D as needed
	    };

	    public String generatePhrase() {
	        String[] selectedPhrases = null;

	        // Randomly select phrases based on the fort's name
	        Random random = new Random();
	        switch (getName()) {
	            case "Kanesville":
	                selectedPhrases = phrasesForFortA;
	                break;
	            case "Mormon Graveyard":
	                selectedPhrases = phrasesForFortB;
	                break;
	            case "Fort Boise":
	                selectedPhrases = phrasesForFortC;
	                break;
	            case "Fort Walla Walla":
	                selectedPhrases = phrasesForFortD;
	                break;
	            // Add cases for other forts as needed
	        }

	        // Select a random phrase from the selected phrases
	        if (selectedPhrases != null) {
	            int index = random.nextInt(selectedPhrases.length);
	            return selectedPhrases[index];
	        } else {
	            return "No phrases available for this fort.";
	        }
	    }
	

}
