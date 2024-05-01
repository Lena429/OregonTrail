
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class TeaTime {
	private WagonParty health;
	private JFrame frame;
	private List<TeaIngredient> availableIngredients;
    private List<TeaIngredient> inventory;
    private Random random;
    
    
 public TeaTime(WagonParty health ) {
    	this.health = health;
    }
    
    private static class TeaIngredient {
        private String name;
        private String effect;
        private int recover;
        
        public TeaIngredient(String name, String effect, int recover) {
            this.name = name;
            this.effect = effect;
            this.recover = recover;
        }

        public String getName() {
            return name;
        }

        public String getEffect() {
            return effect;
        }
        public int getHealth() {
        	return recover;
        }
    }
    
    // Initialize tea ingredients
    private void initializeIngredients() {
        availableIngredients.add(new TeaIngredient("Lavender", "You feel relaxed", 1));
        availableIngredients.add(new TeaIngredient("Hyssop", "You feel energized", 2));
        availableIngredients.add(new TeaIngredient("Lemon Balm", "Your stomach feels better", 3));
        // Add more ingredients as needed
    }
    
    // Method to simulate foraging for tea ingredients
    public TeaIngredient forage() {
        // Simulate finding a random ingredient
        TeaIngredient foundIngredient = availableIngredients.get(random.nextInt(availableIngredients.size()));
        inventory.add(foundIngredient);
        return foundIngredient;
    }

    // Method to brew tea
    public TeaIngredient brewTea() {
        if (inventory.isEmpty()) {
            return null;
        }
        // Simulate selecting a random ingredient from inventory to brew
        TeaIngredient selectedIngredient = inventory.get(random.nextInt(inventory.size()));
        
        // Remove the ingredient from inventory
        inventory.remove(selectedIngredient);
        return selectedIngredient;
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
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(402, 33, 419, 45);
		
		JLabel lblNewLabel_1 = new JLabel("To make tea first you must look for herbs from around the area. Then you brew it !!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(358, 160, 800, 200);
		
		JButton btnNewButton = new JButton("Forage Herbs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeaIngredient herb = forage();
				lblNewLabel_1.setText("You found: " + herb.getName());
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(185, 505, 202, 65);
		
		JButton btnNewButton_1 = new JButton("Brew Tea");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeaIngredient brewedIngredient = brewTea();
				lblNewLabel_1.setText("You brew a cup of " + brewedIngredient.getName() + " tea." + brewedIngredient.getEffect()
				+" \n You gain: " + brewedIngredient.getHealth() + " health");
				health.recoverHealth(brewedIngredient.getHealth());
				}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(497, 505, 202, 63);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1_1.setBounds(852, 505, 202, 63);
		
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
