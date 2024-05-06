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

public class LandmarkFrame {
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private Money bank;
	

	private int teaTimePlayed;
	
	public LandmarkFrame(TravelManager travel, Wagon wagon, Equipment food, Money bank) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
		this.bank = bank;
	}
	
	public void resetTeaPlayed() {
		teaTimePlayed = 0;
	}
	
	public void openLandmarkFrame(Landmarks currentLandmark, TeaTime teaTime) {

		resetTeaPlayed();
		
		//frame for the landmarks and the interactions to be had
		JFrame frameFive = new JFrame();
		frameFive.setBounds(100, 100, 1289, 767);
		frameFive.setTitle("LANDMARK");
		frameFive.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameFive.setVisible(true);
		
		// Retrieve the image file path for the current landmark
	    String imagePath = currentLandmark.getImagePath();
	    ImageIcon landmarkImage = new ImageIcon(getClass().getResource(imagePath));
	    JLabel landmarkLabel = new JLabel(landmarkImage);
	    landmarkLabel.setBounds(50, 100, 500, 500);
	    
		
		// Greeting header for the fort frames
	    JLabel landmarkName = new JLabel("Welcome to " + currentLandmark.getName());
	    landmarkName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
	    landmarkName.setForeground(new Color(255,255,255));
	    landmarkName.setBounds(343, 11, 569, 95);
		
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
		conversationPane.setBounds(178, 371, 357, 215);
		
		JButton talkBtn = new JButton("Talk to people");
		talkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   conversationPane.setText((currentLandmark).generatePhrase());
			}
		});
		talkBtn.setBounds(31, 138, 133, 21);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton restBtn = new JButton("Rest");
		restBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		restBtn.setBounds(31, 256, 133, 21);
		
		JButton leaveBtn = new JButton ("Continue Trail");
		leaveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFive.dispose();
			}
		});
		leaveBtn.setBounds(31, 370, 133, 21);
		
		//player decides to look for tea
		JButton teaTimeBtn = new JButton("Tea Time");
		teaTimeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			teaTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {if(teaTimePlayed < 2) {
				teaTime.openTeaTime();
				//Increment counter when game is played
				teaTimePlayed++;
				//Check if button should be disabled
				if(teaTimePlayed == 2) {
					teaTimeBtn.setEnabled(false);
				}
			}else {
				JOptionPane.showMessageDialog(frameFive, "You have drinken enough tea for today");
			}
			}
		});
		teaTimeBtn.setBounds(31,450,133,21);
		
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
		inventoryBtn.setBounds(31, 400, 133, 21);
		
		
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
