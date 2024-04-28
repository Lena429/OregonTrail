

public class Landmarks extends Location{
	
	
	public Landmarks(String name, int miles) {
		super(name, miles);
    
  }
	// Phrases for chattering, categorized by landmark names
    private String[] phrasesLandmarkA = {"Amelia says to the group, \"that a big rock.\"" };
    private String[] phrasesLandmarkB = {"Amelia points ahead \" that a bigger rock.\""};
    		
   
    		
    // Add more fort-specific phrases as needed

    public String generatePhrase() {
        String phrase = "";

        // Check the fort's name and select phrases accordingly
        // More phrases can be added and selected later
        if (getName().equals("Chimney Rock")) {
            phrase = phrasesLandmarkA[0]; // Select the first phrase for Fort A
        } else if (getName().equals("Scott's Bluff")) {
            phrase = phrasesLandmarkB[0]; // Select the first phrase for Fort B
        }
       
        
        // Add more conditions for other forts as needed

        return phrase;
    }
	
	
	
}


