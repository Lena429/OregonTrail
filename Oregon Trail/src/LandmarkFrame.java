/**
 * LandmarkFrame.java
 * 
 * The LandmarkFrame class opens when the player reaches a landmark on their journey. It has interactions such as talking, looking around
 * and playing the mini-game
 * 
 * @author - Lena Frate
 * @author - Lillyan Stewart
 * @author - Sarah Slusher
 * @version - 1.1.1 April 20, 2024
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

public class LandmarkFrame {
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private Money bank;
	private int teaTimePlayed; 
	
	/**
	 * Creates on object of landmark frame that holds all the components for landmark interaction
	 *@param travel - needed to access date
	 *@param wagon - needed for access to the inventory
	 *@param food - needed to update the food total
	 *@param bank - needed to display the user's money
	 * 
	 */
	public LandmarkFrame(TravelManager travel, Wagon wagon, Equipment food, Money bank) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
		this.bank = bank;
	}
	
	
	/**
	 * Resets the counter for how many times the mini-game has been played to zero when a new landmark has been entered
	 */
	public void resetTeaPlayed() {
		teaTimePlayed = 0;
	}
	
	/**
	 * Opens the frame that holds all the landmark components including buttons, images and labels
	 * @param currentLandmark - the landmark that the player is currently in
	 * @param teaTime - the instance of the mini-game to make tea
	 */
	public void openLandmarkFrame(Landmarks currentLandmark, TeaTime teaTime) {
		//resets the amount of times the player plays the mini-game
		//makes it so the button is re enabled when frame first pops up
		resetTeaPlayed();
		
		//frame for the landmarks and the interactions to be had
		JFrame frameFive = new JFrame();
		frameFive.setBounds(100, 100, 1289, 767);
		frameFive.setTitle("LANDMARK");
		frameFive.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameFive.setVisible(true);
		
		// Retrieve the image file path for the current landmark drawing
	    String imagePath = currentLandmark.getImagePath();
	    ImageIcon landmarkImage = new ImageIcon(getClass().getResource(imagePath));
	    JLabel landmarkLabel = new JLabel(landmarkImage);
	    landmarkLabel.setBounds(562,108,684,511);
	    
		
		// Greeting header for the landmark frames
	    JLabel landmarkName = new JLabel("Welcome to " + currentLandmark.getName());
	    landmarkName.setHorizontalAlignment(SwingConstants.CENTER);
	    landmarkName.setFont(new Font("Felix Titling", Font.PLAIN, 50));
	    landmarkName.setForeground(new Color(255,255,255));

	    landmarkName.setBounds(81, 11, 1111, 95);

		
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
		

		//player decides to talk to others 
	
		JButton talkBtn = new JButton("Talk");
		talkBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
		talkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   conversationPane.setText((currentLandmark).generatePhrase());
			   talkBtn.setEnabled(false);
			}
		});
		talkBtn.setBounds(31, 138, 133, 60);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton restBtn = new JButton("Rest");
		restBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
		restBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		restBtn.setBounds(31, 390, 503, 60);
		
		JButton leaveBtn = new JButton ("Continue Trail");
		leaveBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
		leaveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFive.dispose();
			}
		});
		leaveBtn.setBounds(31, 642, 503, 60);
		
		//player decides to look for tea
		JButton teaTimeBtn = new JButton("Tea Time");
		teaTimeBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
		teaTimeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			teaTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(teaTimePlayed < 1) {
				teaTime.openTeaTime();
				//Increment counter when game is played
				teaTimePlayed++;
				//Check if button should be disabled
				if(teaTimePlayed == 1) {
					teaTimeBtn.setEnabled(false);
				}
			}else {
				//message that pops up when player tries to play mini-game more than twice
				JOptionPane.showMessageDialog(frameFive, "You have drinken enough tea for today");
				}
			}
		});
		teaTimeBtn.setBounds(31,532,503,60);
		
		//player decides to check wagon inventory while at the landmark
		JButton inventoryBtn = new JButton("Inventory");
		inventoryBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
		inventoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//the frame to view the current inventory
				JFrame landInventory = new JFrame();
				landInventory.setBounds(575, 108, 671, 505);
				landInventory.setVisible(true);
				
				//how the inventory is displayed
				JTextArea currentInventory = new JTextArea("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
				currentInventory.setEditable(false);
				currentInventory.setWrapStyleWord(true);
				currentInventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
				
				//panel to hold inventory viewing objects
				JPanel landInventoryPanel = new JPanel();
				landInventoryPanel.setLayout(null);
				landInventoryPanel.add(currentInventory);
				landInventory.getContentPane().add(currentInventory);
			}
		});
		inventoryBtn.setBounds(31, 461, 503, 60);
		
		//panel that hold all of the landmark components to the frame
		JPanel landPanel = new JPanel();
		landPanel.setLayout(null);
		landPanel.add(restBtn);
		landPanel.add(conversationPane);
		landPanel.add(landmarkName);
		landPanel.add(dateQtyLbl_3);
		landPanel.add(dateLbl_3);
		landPanel.add(talkBtn);
		landPanel.add(leaveBtn);
		landPanel.add(teaTimeBtn);
		landPanel.add(inventoryBtn);
		landPanel.add(landmarkLabel);
		landPanel.setBackground(new Color(0,0,0));
		frameFive.getContentPane().add(landPanel);
		
	}
}
