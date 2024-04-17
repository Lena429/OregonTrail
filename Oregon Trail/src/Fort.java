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
import java.util.Random;
public class Fort extends Location {

	public Fort(String name, int miles, Wagon wagon) {
		super(name, miles, wagon);
		// TODO Auto-generated constructor stub
	}
	
	private String[] phrases = {
			"I'm hoping the weather stays nice", "Have you seen that large caravan up ahead?", "I heard the flu is going around, so be careful!"
	};
	
	public String generateRandomPhrase() {
		   Random random = new Random();
		   int index = random.nextInt(phrases.length);
		   return phrases[index];
		 }

}
