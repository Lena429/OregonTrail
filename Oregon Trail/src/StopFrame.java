/**
 * StopFrame.java
 * 
 * @author 
 * @version
 * 
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class StopFrame {
	
	private Travel travel;
	private Wagon wagon;
	private Equipment food;
	private Money bank;
	
	/**
	 * 
	 * @param travel
	 * @param wagon
	 * @param food
	 * @param bank
	 */
	public StopFrame(Travel travel, Wagon wagon, Equipment food, Money bank) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
		this.bank = bank;
	}
	
	/**
	 * 
	 * @param dateMainLbl
	 * @param foodMainLbl
	 * @param rationsMainLbl
	 * @param paceMainLbl
	 */
	public void openStopFrame(JLabel dateMainLbl, JLabel foodMainLbl, JLabel rationsMainLbl, JLabel paceMainLbl) {
		Trade offer	= new Trade();
		
		JFrame frame = new JFrame();
		frame.setBounds(100,100,1289,767);
		frame.setTitle("OPTIONS");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		// ComboBox for miles per day/pace
		JComboBox<String> paceComboBox= new JComboBox<String>();
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setPace(paceComboBox);
				paceMainLbl.setText(travel.getPace() + "");
			}
		});
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		paceComboBox.setBounds(300, 313, 152, 54);
		paceComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));

		// ComboBox for rations/how much the user would like to eat per day 
		JComboBox<String> rationsComboBox = new JComboBox<String>();
		rationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setRations(rationsComboBox);
				rationsMainLbl.setText(travel.displayRations());
			}
		});
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bare Bones", "Meager", "Filling"}));
		rationsComboBox.setBounds(300, 200, 220, 54);
		rationsComboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));

		// Inventory of the wagon 
		JTextArea inventory = new JTextArea("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
		inventory.setEditable(false);
		inventory.setWrapStyleWord(true);
		inventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		inventory.setBounds(575, 108, 671, 505);
		
		// Label for frame two changing rations combobox
		JLabel changeRatLbl = new JLabel("Change Rations:");
		changeRatLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changeRatLbl.setBounds(10, 187, 275, 86);
		changeRatLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		
		// Label for frame two changing pace combobox
		JLabel changePaceLbl = new JLabel("Change Pace:");
		changePaceLbl.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		changePaceLbl.setBounds(10, 300, 275, 86);
		changePaceLbl.setHorizontalAlignment(SwingConstants.TRAILING);

		// Label for frame two date label
		JLabel dateLbl_2 = new JLabel("Date:");
		dateLbl_2.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl_2.setBounds(586, 631, 93, 51);
		
		JLabel dateQtyLbl_2 = new JLabel(travel.getDate());
		dateQtyLbl_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_2.setBounds(676, 631, 284, 51);
		
		// A button that will allow you to rest.
		// When you rest food decreases and a day will pass every time button is pushed.
		JButton restBtn = new JButton("Rest");
		restBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		restBtn.setBounds(180, 521, 189, 62);
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateQtyLbl_2.setText(travel.getDate());
				dateMainLbl.setText(travel.getDate());
		        
				// Update the inventory display so user can see correct food value
				inventory.setText("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
			}
		});
		
		JButton tradeBtn = new JButton("Trade");
		tradeBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		tradeBtn.setBounds(180, 430, 189, 62);
		tradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generates the trade offer
				offer.getTrader(travel.getMilesTravelled());
				offer.getOffer(wagon.getItems());
				
				// day increments and food decrements
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateMainLbl.setText(travel.getDate());
				dateQtyLbl_2.setText(travel.getDate());
		        // Update the inventory display so user can see correct food value
				inventory.setText("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
				
				// displays the trade offer in a dialogue box
				String text = offer.displayTradeOffer();;
				String title = "Trade";
				int type = JOptionPane.QUESTION_MESSAGE;
				int response = JOptionPane.showConfirmDialog(frame,  text, title, JOptionPane.YES_NO_OPTION, type);
				
				// checks if the user accepted the trade or not
		        if (response == JOptionPane.YES_OPTION) {
		        	// Yes, add/remove the items and update the inventory display
		        	offer.tradeAccepted(wagon.getItems(), wagon);
					inventory.setText("Wagon Contents: \n" + wagon.displayingInventory() + bank.displayMoney());
					
		        } else if (response == JOptionPane.NO_OPTION) {
		            // No, close the dialogue box
		        }
		        
		        // updates the food label on frame 1
		        foodMainLbl.setText(wagon.getConsumableWeight() + "");
			}
		});
		
		// Panel with added components for frame two 
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(inventory);
		panel.add(changePaceLbl);
		panel.add(paceComboBox);
		panel.add(changeRatLbl);
		panel.add(rationsComboBox);
		panel.add(dateLbl_2);
		panel.add(dateQtyLbl_2);
		panel.add(restBtn);
		panel.add(tradeBtn);
		frame.getContentPane().add(panel);

	}
}
