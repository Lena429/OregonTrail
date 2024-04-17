
/**
 * Rivers.java
 * 
 * The river class gets the name, height, flow, and width of a river. 
 *  
 * @author - Lena Frate
 * @author - Lillyan Stewart 
 * @author - Sarah Slusher
 * @version 1.1.1 - April 16 2024
 */


import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.InputStreamReader;



public class River extends Location {
	Scanner scr;
	String river;
	private Wagon wagon = new Wagon();


	
	public River(String name, int miles) {
		super(name, miles);
		   InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream("River.txt"));
		     scr = new Scanner(isr);
	}
	
	public River() {}

	
	// when we do weather we can add parameters to check whether its been raining a bunch or not. 
	// Also ask estell for chapter 12 rivers
	// File file = new File("OregonTrail/Oregon Trail/src/Rivers.txt");
    // Scanner scanner = new Scanner(file);
        
  

     //Present options for what they would like to do at the river.
     //Cross
     //Wait it out 
     //Pay your way 
     //need a money class 
     
    public String getRiver() {
    	String name = scr.nextLine();
    	return name;
    
    }
	
	public int getHeight() {
		int height = scr.nextInt();
		return height;
	}
	
	public String getFlow() {
		int flow = scr.nextInt();
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
	
	public int getWidth() {
		int flow = scr.nextInt();
		return flow; 
		
	}
	
	public void randomEvtCross(Equipment money) {
		//rand number = getradnom number;
		//maybe I should do math formulas instead
		Random rnd = new Random();
		int random = rnd.nextInt(5)+1;
		switch (random)
		{		
		case 1: // You were safe 
		case 2: 
		case 3: 
			JOptionPane.showMessageDialog(null, "You made it across safely.");
			break;
		case 4: 
			//Oh no money fell out of your pocket and you lost it in the river 
			wagon.removeItemQty(money, 22);
			JOptionPane.showMessageDialog(null, "You made it, but $22 fell out of your pocket");
			break;
		}		
	}
	
	public void randomEvtFerry(Equipment money) {
		//rand number = getradnom number;
				//maybe I should do math formulas instead
				Random rnd = new Random();
				int random = rnd.nextInt(9)+1;
				switch (random)
				{		
				case 1: // You were safe 
				case 2: 
				case 3: 	
				case 4: 
				case 5:
				case 6: 
				case 7:
					JOptionPane.showMessageDialog(null, "You made it across safely");
					break;
					//Oh no money fell out of your pocket and you lost it in the river 
				case 9:
					wagon.removeItemQty(money, 22);
					JOptionPane.showMessageDialog(null, "You made it, but $22 fell out of your pocket");
					break;

				}
				
				
			}
	
	}
	

	
	





