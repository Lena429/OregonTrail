/**
 * FortFrame.java
 * 
 * 
 * @author - Sarah Slusher
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @version 1.1.1 April 20, 2024
 * 
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class FortFrame {
	
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private Money bank;
	
	private int teaTimePlayed; // how many total times the player plays the mini-game in a fort
	
	
	
	/**
	 * 
	 * @param travel
	 * @param wagon
	 * @param food
	 */
	public FortFrame(TravelManager travel, Wagon wagon, Equipment food, Money bank) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
		this.bank = bank;
	}
	
	/**
	 * Resets the counter for how many times the mini-game has been played to zero when a new fort frame has been entered
	 */
	public void resetTeaPlayed() {
		teaTimePlayed = 0;
	}
	
	/**
	 * 
	 * @param currentFort
	 * @param store
	 */
	public void openFortFrame(Fort currentFort, Store store, TeaTime teatime) {
		
		resetTeaPlayed();
		
        // Creates the frame for fort objects and actions
		JFrame frameThree = new JFrame();
		frameThree.setBounds(100, 100, 1289, 767);
		frameThree.setTitle("FORT");
		frameThree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameThree.setVisible(true);
		
		//fort image from outside
		ImageIcon fort = new ImageIcon(this.getClass().getResource("/image/fort copy.png"));
		JLabel forts = new JLabel(fort);
		forts.setBounds(562,108,684,511);
		
		// Creates frame for fort images
		JFrame frameImage = new JFrame();
		frameImage.setBounds(100, 100, 1289, 767);
		frameImage.setTitle("Look Around");
		frameImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameImage.setVisible(false);
		
		// image for look around at fort
		ImageIcon image = new ImageIcon(this.getClass().getResource("/image/lookaround copy.png"));
		JLabel fortImage = new JLabel(image);
		fortImage.setBounds(100, 100, 1289, 767);
		
		JLabel dateLbl_3 = new JLabel("Date:");
		dateLbl_3.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl_3.setForeground(new Color(255,255,255));
		dateLbl_3.setBounds(586, 631, 93, 51);
		
		JLabel dateQtyLbl_3 = new JLabel(travel.getDate());
		dateQtyLbl_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_3.setForeground(new Color(255,255,255));
		dateQtyLbl_3.setBounds(676, 631, 284, 51);
		
		// Label to hold generated phrases of conversation
		JTextPane conversationPane = new JTextPane();
		conversationPane.setEditable(false);
		conversationPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		conversationPane.setBounds(177, 138, 357, 226);
		
		// player talks to other people inside fort
		
		// randomly selected phrases from Fort Class
		JButton talkBtn = new JButton("Talk to people");
		talkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversationPane.setText((currentFort).generatePhrase());
				//disables button so you can only talk once at each fort 
				talkBtn.setEnabled(false);
			}
		});
		talkBtn.setBounds(31, 138, 133, 60);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton restBtn = new JButton("Rest");
		restBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				//dateQtyLbl.setText(travel.getDate());
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		restBtn.setBounds(31, 390, 251, 60);
		
		//player decides to look around at fort
		JButton lookBtn = new JButton("Look Around");
		lookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameImage.setVisible(true);
			}
		});
		lookBtn.setBounds(31, 470, 251, 60);

		// player decides to shop in the store
		JButton shopBtn = new JButton("Shop");
		shopBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
          store.adjustPrices(currentFort);
				  store.openStoreWindow(false);
			}
		});
		shopBtn.setBounds(31, 570, 503, 60);
		
		JButton leaveBtn = new JButton ("Continue Trail");
		leaveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameThree.dispose();
			}
		});
		leaveBtn.setBounds(31, 642, 503, 60);
		
		//player decides to check wagon inventory while in the fort
		JButton inventoryBtn = new JButton("Inventory");
		inventoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame fortInventory = new JFrame();
				fortInventory.setBounds(575, 108, 671, 505);
				fortInventory.setVisible(true);
				
				JTextArea currentInventory = new JTextArea("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
				currentInventory.setEditable(false);
				currentInventory.setWrapStyleWord(true);
				currentInventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
				
				JPanel fortInventoryPanel = new JPanel();
				fortInventoryPanel.setLayout(null);
				fortInventoryPanel.add(currentInventory);
				fortInventory.getContentPane().add(currentInventory);
			}
		});
		inventoryBtn.setBounds(290, 390, 248, 60);
		
		
		//player decides to look for tea
		JButton teaTimeBtn = new JButton("Tea Time");
		teaTimeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		teaTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(teaTimePlayed < 1) {
					teatime.openTeaTime();
					//Increment counter when game is played
					teaTimePlayed++;
					
					//Check if button should be disabled
					if(teaTimePlayed == 1) {
						teaTimeBtn.setEnabled(false);
					}
				}else {
					//message for when player tries to play mini-game more than twice
					JOptionPane.showMessageDialog(frameThree, "You have drinken enough tea for today");
				}

			}
		});
		teaTimeBtn.setBounds(290,470,248,60);
		
		// Greeting header for the fort frames
	    JLabel fortName = new JLabel("Welcome to " + currentFort.getName());
	    fortName.setForeground(new Color(255,255,255));
	    fortName.setHorizontalAlignment(SwingConstants.CENTER);
		fortName.setFont(new Font("Felix Titling", Font.PLAIN, 50));
		fortName.setBounds(81, 11, 1111, 95);
		
		// panel to hold all fort objects to the frame
		JPanel PanelThree = new JPanel();
		PanelThree.setLayout(null);
		PanelThree.add(conversationPane);
		PanelThree.add(talkBtn);
		PanelThree.add(restBtn);
		PanelThree.add(lookBtn);
		PanelThree.add(dateLbl_3);
		PanelThree.add(dateQtyLbl_3);
		PanelThree.add(fortName);
		PanelThree.add(shopBtn);
		PanelThree.add(leaveBtn);
		PanelThree.add(forts);
		PanelThree.add(inventoryBtn);
		PanelThree.add(teaTimeBtn);
		PanelThree.setBackground(new Color(0,0,0));
		frameThree.getContentPane().add(PanelThree);
		
		JButton stopLookBtn = new JButton ("Stop Looking");
		stopLookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stopLookBtn.setFont(new Font("Felix Titling", Font.PLAIN, 55));
		stopLookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameImage.dispose();
			}
		});
		stopLookBtn.setBounds(351, 0, 480, 70);
		
		// panel for the fort images 
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.setBackground(new Color(0,0,0));
		imagePanel.add(fortImage);
		imagePanel.add(stopLookBtn);
		frameImage.getContentPane().add(imagePanel);	
	}
}
