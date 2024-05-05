/**
 * FortFrame.java
 * 
 * @author 
 * @version
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

public class FortFrame {
	
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private Money bank;
	
	private int teaTimePlayed;
	
	
	
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
		fortImage.setBounds(562, 108, 684, 511);
		
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
		
		// player talks to other people inside fort
		// randomly selected phrases from Fort Class
		JButton talkBtn = new JButton("Talk to people");
		talkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversationPane.setText((currentFort).generatePhrase());
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
				//dateQtyLbl.setText(travel.getDate());
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		restBtn.setBounds(31, 256, 133, 21);
		
		//player decides to look around at fort
		JButton lookBtn = new JButton("Look Around");
		lookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameImage.setVisible(true);
			}
		});
		lookBtn.setBounds(31, 194, 133, 21);

		// player decides to shop in the store
		JButton shopBtn = new JButton("Shop");
		shopBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		shopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
          store.adjustPrices(currentFort);
				  store.openStoreWindow(false);
			}
		});
		shopBtn.setBounds(31, 310, 133, 21);
		
		JButton leaveBtn = new JButton ("Continue Trail");
		leaveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameThree.dispose();
			}
		});
		leaveBtn.setBounds(31, 370, 133, 21);
		
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
		inventoryBtn.setBounds(31, 390, 133, 21);
		
		
		//player decides to look for tea
		JButton teaTimeBtn = new JButton("Tea Time");
		teaTimeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		teaTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(teaTimePlayed < 2) {
					teatime.openTeaTime();
					//Increment counter when game is played
					teaTimePlayed++;
					//Check if button should be disabled
					if(teaTimePlayed == 2) {
						teaTimeBtn.setEnabled(false);
					}
				}else {
					JOptionPane.showMessageDialog(frameThree, "You have drinken enough tea for today");
				}

			}
		});
		teaTimeBtn.setBounds(31,450,133,21);
		
		// Greeting header for the fort frames
	    JLabel fortName = new JLabel("Welcome to " + currentFort.getName());
	    fortName.setForeground(new Color(255,255,255));
		fortName.setFont(new Font("Bookma,n Old Style", Font.PLAIN, 50));
		fortName.setBounds(343, 11, 569, 68);
		
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
		
		// panel for the fort images 
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.add(fortImage);
		frameImage.getContentPane().add(imagePanel);	
	}
}
