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
	
	/**
	 * gets the price multiplier from the fort to be used in the store
	 * @return the amount that the base store prices get multiplied by at each fort
	 */
	public int getPriceFactor() {
		return priceFactor;
	}
	
	
	/**
	 * The phrase arrays that hold the conversations possible at each fort
	 */
	private String[] phrasesForFortA = {
	        "Amelia says to the group, \"We are sleeping in a lane while waiting to cross a river nearby and still can't feed our cows properly. I hope we can find feed or grass for them soon.\"",
	        
	        "Frances mentions, \"We had to get rid of our pickles and a couple jars a little bit back because mom said they were too unhandy to carry. "
	        + "I’m a little sad because I like pickles and was looking forward to eating those.\"", 
	        
	        "Lucy says,\"Those people who keep coming every day asking for food or money used to scare me a lot. But I think I’m getting used to their presence.\"",
	        
	    };
	    private String[] phrasesForFortB = {
	        "Amelia points ahead, \"We saw that wagon a few days ago, didn’t we? It looks like the father is gone, probably drowned in the upcoming river. I feel terrible for the children and wife.\"",
	        
	        "Seneca observes, \"There sure are a lot of graves here, I guess that’s why it’s called the mormon graveyard. I’d bet that there are around 350 unfortunate travelers buried here.\"",
	        
	        "Amelia says, looking out at all the wagons, \" We’ve merged with another wagon company making us a rather large group, there are now 24 men, with 10 wagons in total. It’s rather noisy now but we have "
	        + "enough people to guard the cattle at night.\"",

	    };
	    private String[] phrasesForFortC = {
	        "Frances points to her new shoes, \"My mom traded with the native americans for some moccasins and beads. They wanted some bread so my mom gave them all she had made for a couple pairs of shoes.\"",
	        
	        "Amelia, \"My husband made a mistake trading expired crackers with some people and now we have to give back Everything we just traded for. This is really upsetting because I just got some new pairs moccasins and beads for the bread I had made.\"",
	        
	        "Amelia, looking ahead, \"It looks like it might storm soon so we better leave sooner rather than later, or we could get rained out. Don’t want to be feeling worse than I already do, I hope my baby is doing alright with all this traveling.\"",
	        
	    };
	    private String[] phrasesForFortD = {
	    	"Amelia complains, \"It's rather warm in this summer heat, and there is nothing in this Fort at all, just a couple of mud houses.\"",
	        
	    	"Plutarch says, \"I’m rather worried about the stock moving across the next river, I heard that that one tends to cause animals to get swept off their feet. The people who live here though are able to help them across for a small fee sometimes.\"",
	    	
	    	"Amelia says, \"The Boise river wasn’t too far back so I can safely say that that river is much more beautiful than this fort. I can’t wait to be out of here and in our new home. We shouldn’t be that far now.\"",
	    };
	    
	    /**
	     * generates the fort conversation by grabbing a random phrase from the correct string array
	     * @return a message for if there is no conversation at a given fort
	     */
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
	            case "Fort Laramie":
	                selectedPhrases = phrasesForFortC;
	                break;
	            case "Fort Boise":
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
