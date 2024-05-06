/**
 * Landmark.java
 * 
 * @author
 * @version - 1.1.1 April , 2024
 */

import java.util.Random;

public class Landmarks extends Location{
	private String imagePath;
	
	public Landmarks(String name, int miles, String imagePath) {
		super(name, miles);
		this.imagePath = imagePath;
    
  }
	
	public String getImagePath() {
        return imagePath;
    }	
	
	
	// Phrases for chattering, categorized by landmark names
    private String[] phrasesLandmarkA = {"Amelia says to the group, \"that a big rock.\"" };
    private String[] phrasesLandmarkB = {"Amelia points ahead \" that a bigger rock.\""};
    private String[] phrasesLandmarkC = {"Amelia shouts out, \"that is one free rock.\""};
    
    
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


