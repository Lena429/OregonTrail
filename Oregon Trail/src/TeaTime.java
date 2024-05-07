/**
 * TeaTime.java
 * 
 * TeaTime class takes place for the mini-game component of the main game.
 * Lets players look for and brew random teas to regain a bit of health
 * 
 * @author - Sarah Slusher
 * @version 1.1.1 April 30, 2024
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TeaTime {
	private static final int MAX_FORAGE_TIMES = 2; //how many times you are able to forage for herbs each time game is played
	private static final int MAX_BREW_TIMES = 2; // how many times you are able to brew tea each time game is played
	private int forageCounter;
	private int brewCounter;
	private Equipment water;
	private Wagon wagon;
	
	private WagonParty health;
	private JFrame frame;
	private List<TeaIngredient> availableIngredients;
    private List<TeaIngredient> inventory;
    private Random random;
    
 /**
  * The mini-game taking place instead of the hunting mini-game from the original
  * @param health - need to have access to people's health
  * @param water - it takes water to make the tea
  * @param wagon - need to look at wagon for inventory reasons
  */
 public TeaTime(WagonParty health, Equipment water, Wagon wagon) {
    	this.health = health;
    	this.water = water;
    	this.wagon = wagon;
    }
 
    
    private static class TeaIngredient {
        private String name;
        private String effect;
        private int recover;
        
        /**
         * Creates the teas that a player can possibly find and consume
         * @param name - the name of the tea or herb
         * @param effect - what effect the tea has on a player
         * @param recover - how much health the player gains from drinking the tea
         */
        public TeaIngredient(String name, String effect, int recover) {
            this.name = name;
            this.effect = effect;
            this.recover = recover;
        }
        /**
         * 
         * @return the name of the tea
         */

        public String getName() {
            return name;
        }
        
        /**
         * 
         * @return the effect of the tea
         */
        public String getEffect() {
            return effect;
        }
        
        /**
         * 
         * @return how much health the tea gives the player
         */
        public int getHealth() {
        	return recover;
        }
    }
    
    // Initialize tea ingredients
    private void initializeIngredients() {
        availableIngredients.add(new TeaIngredient("Lavender", "You feel relaxed", 1));
        availableIngredients.add(new TeaIngredient("Hyssop", "You feel energized", 2));
        availableIngredients.add(new TeaIngredient("Lemon Balm", "Your stomach feels better", 3));
        
    }
    
    /**
     * Method to simulate foraging for tea ingredients
     * @return foundIngredient - what random tea or herb the player has found
     */
    public TeaIngredient forage() {
    	
    	//Check if the maximum forage limit has been reached
    	if(forageCounter >= MAX_FORAGE_TIMES) {
    		JOptionPane.showMessageDialog(frame, "This area has no more herbs to brew.");
    		return null;
    	}
    	forageCounter++;
        TeaIngredient foundIngredient = availableIngredients.get(random.nextInt(availableIngredients.size()));
        inventory.add(foundIngredient);
        return foundIngredient;
    }

    
    /**
     * Brews the tea and takes it out of tea inventory
     * @return selectedIngredient - the tea that the player has brewed
     */
    public TeaIngredient brewTea() {
    	//Check if max brew limit has been reached
    	if(brewCounter >= MAX_BREW_TIMES) {
    		JOptionPane.showMessageDialog(frame, "You've made enough tea for now.");
    		return null;
    	}
    	brewCounter++;
    	
    	//check if they haven't foraged yet
        if (inventory.isEmpty()) {
            return null;
        }
        
        
        // Simulate selecting a random ingredient from tea inventory to brew
        TeaIngredient selectedIngredient = inventory.get(random.nextInt(inventory.size()));
        
        // Remove the ingredient from inventory
        inventory.remove(selectedIngredient);
        return selectedIngredient;
        
    }
    
    /**
     * resets the counters for how many times a player can forage and brew tea
     */
    public void resetCounters() {
    	forageCounter = 0;
    	brewCounter = 0;
    }
    


	/**
	 * Initialize the contents of the frame.
	 */
	public void openTeaTime() {	
		availableIngredients = new ArrayList<>();
        inventory = new ArrayList<>();
        random = new Random();
        initializeIngredients();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1289, 767);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		
		JLabel lblNewLabel = new JLabel("Tea Time on the Trail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Felix Titling", Font.PLAIN, 50));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(31, 33, 1200, 45);
		
		JLabel lblNewLabel_1 = new JLabel("To make tea first you must look for herbs from around the area. Then you brew it!!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(31, 50, 1200, 200);
		
		//button for when player wants to look for herbs
		JButton btnNewButton = new JButton("Forage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeaIngredient herb = forage();
				lblNewLabel_1.setText("You found: " + herb.getName());
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		btnNewButton.setBounds(185, 505, 202, 65);
		
		//button for when player wants to brew their teas
		JButton btnNewButton_1 = new JButton("Brew Tea");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 boolean waterFound = false;
			        for (Equipment item : wagon.getItems()) {
			            if (item instanceof Equipment && item.getName().equals("Water")) {
			                int waterQuantity = item.getQuantity();
			                if (waterQuantity > 0) {
			                    // Water is available to brew tea
			                    waterFound = true;
			                    TeaIngredient brewedIngredient = brewTea();
			                    // Remove one pound of water from inventory
			                    wagon.removeItemQty(item, 1);
			                    lblNewLabel_1.setText("You brew a cup of " + brewedIngredient.getName() + " tea. " + brewedIngredient.getEffect()
			                            + " \n You gain: " + brewedIngredient.getHealth() + " health");
			                    health.recoverHealth(brewedIngredient.getHealth());
			                    break; // Stop searching for water
			                }
			            }
			        }
			        if (!waterFound) {
			        	
			            // No water found in inventory
			            lblNewLabel_1.setText("You don't have enough water to brew tea.");
			        }
			}
		});
		btnNewButton_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		btnNewButton_1.setBounds(497, 505, 202, 63);
		
		//button to close mini-game after player is done
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				resetCounters();
			}
		});
		btnNewButton_1_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		btnNewButton_1_1.setBounds(852, 505, 202, 63);
		
		//panel to hold all tea related components
		JPanel teaPanel = new JPanel();
		teaPanel.setBackground(new Color(0, 0, 0));
		teaPanel.setLayout(null);
		teaPanel.add(lblNewLabel);
		teaPanel.add(lblNewLabel_1);
		teaPanel.add(btnNewButton);
		teaPanel.add(btnNewButton_1);
		teaPanel.add(btnNewButton_1_1);
		frame.getContentPane().add(teaPanel);
		
	}
}
