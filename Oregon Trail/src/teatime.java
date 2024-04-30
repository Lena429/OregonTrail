import java.awt.EventQueue;
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
import java.awt.Color;
import javax.swing.SwingConstants;




public class teatime {
	private WagonParty wagon;
	private JFrame frame;
	private List<TeaIngredient> availableIngredients;
    private List<TeaIngredient> inventory;
    private Random random;
    
 public teatime(WagonParty wagon ) {
    	this.wagon = wagon;
    }
    
    private static class TeaIngredient {
        private String name;
        private String effect;

        public TeaIngredient(String name, String effect) {
            this.name = name;
            this.effect = effect;
        }

        public String getName() {
            return name;
        }

        public String getEffect() {
            return effect;
        }
    }
    
    // Initialize tea ingredients
    private void initializeIngredients() {
        availableIngredients.add(new TeaIngredient("Lavender", "You feel relaxed"));
        availableIngredients.add(new TeaIngredient("Hyssop", "You feel energized"));
        availableIngredients.add(new TeaIngredient("Lemon Balm", "Your stomach feels better"));
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tea Time on the Trail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel.setBounds(402, 33, 419, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("To make tea first you must look for herbs from around the area. Then you brew it !!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(358, 160, 800, 200);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Forage Herbs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeaIngredient herb = forage();
				lblNewLabel_1.setText("You found: " + herb.getName());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(185, 505, 202, 65);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Brew Tea");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeaIngredient brewedIngredient = brewTea();
				lblNewLabel_1.setText("You brew a cup of " + brewedIngredient.getName() + " tea." );
				}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(497, 505, 202, 63);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1_1.setBounds(852, 505, 202, 63);
		frame.getContentPane().add(btnNewButton_1_1);
		
	}
}
