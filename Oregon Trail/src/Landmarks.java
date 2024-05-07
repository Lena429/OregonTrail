/**
 * Landmark.java
 * 
 * Landmark class holds the name, miles and specific images for each landmark the player visits. 
 * Also holds all the conversations to be had in landmarks.
 * 
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version - 1.1.1 April 20 , 2024
 */

import java.util.Random;

public class Landmarks extends Location{
	private String imagePath;
	
	/**
	 * 
	 * @param name - the name of the landmark 
	 * @param miles - gets from Location class how far from previous location landmark is
	 * @param imagePath - the drawing connected to a landmark
	 */
	public Landmarks(String name, int miles, String imagePath) {
		super(name, miles);
		this.imagePath = imagePath;
    
  }
	
	/**
	 * 
	 * @return image path of the specific drawing for a landmark
	 */
	public String getImagePath() {
        return imagePath;
    }	
	
	
	// Phrases for chattering, categorized by landmark names
    private String[] phrasesLandmarkA = {
    	"Amelia says to the group, \" It is always hard to watch the little ones shiver when we have to "
    	+ "shut ourselves in the wagon while it's raining. I just keep in mind that all of this suffering is for Oregon.\"", 
    		
    	"Seneca tells the group, \"We were mixed up with several hundred head of cattle, and only one road to travel in, and the drovers "
    	+ "threatened to drive their cattle over us. Luckily we were able to make it past them quickly and unharmed.\" ",
    		
    	"Almira retells, \" My mother hurried me to finish my dinner the other day when another group attempted to pass us. All hands jumped for their teams, "
    	+ "saying they had earned the road too dearly to let them pass again.\"",
    };
    private String[] phrasesLandmarkB = {
    	"Amelia points ahead \"Camping opposite of Scott's Bluff is really a sight to see. "
    	+ "However, all of the beautiful landscapes that we see will not be able to top our new home in Oregon.\"",
    		
    	"Plutarch says, \"Our oxen are not feeling the best. They developed sore necks from when we traveled in the rain.\"",
    	
    	"Almira mentions, \" My mom said that whenever we get to mountainous areas, we will have to overhaul the"
    	+ "wagons to make them as light as possible. I hope we won't have to throw away too many things.\"",
    };
    private String[] phrasesLandmarkC = {
    	"Amelia shouts out, \"Keep an eye on your cattle. I had a great deal of trouble keeping the stock from drinking the poison water that "
    	+ "is almost sure to kill any man or beast who drinks it.\"",
    		
    	"Lucy says,\" I have been helping my mom with the washing and cooking today. I think we plan on sewing up some of our clothes tomorrow.\"",	
    	
    	"Seneca points out, \"Some of the bridges we cross seem very rickety and old. Sometimes I fear we may fall in.\"",
    };
    
    /**
     * Generates a random phrases from those listed in that specific landmark phrase array
     * Uses a switch case to know which landmark conversation array to grab from
     * @return - a message if for some reason there are no conversations in that landmark
     */
    public String generatePhrase() {
        String[] selectedPhrases = null;

        // Randomly select phrases based on the landmark's name
        Random random = new Random();
        switch (getName()) {
            case "Chimney Rock":
                selectedPhrases = phrasesLandmarkA;
                break;
            case "Scott's Bluff":
                selectedPhrases = phrasesLandmarkB;
                break;
            case "Independence Rock":
                selectedPhrases = phrasesLandmarkC;
                break;
        }

        // Select a random phrase from the selected phrases
        if (selectedPhrases != null) {
            int index = random.nextInt(selectedPhrases.length);
            return selectedPhrases[index];
        } else {
            return "No phrases available for this landmark.";
        }
    }
    
    
    
    
}


