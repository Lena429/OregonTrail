/**
 * RiverFrame.java
 * 
 * @author 
 * @version
 * 
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class RiverFrame {
	
	private Money bank;
	private ArrayList<Location> locations;
	private Travel travel;
	private Wagon wagon;
	private Equipment food; 
	
	/**
	 * 
	 * @param locations
	 * @param bank
	 */

	public RiverFrame(ArrayList<Location> locations, Money bank, Travel travel) {
		this.locations = locations;
		this.bank = bank;
		this.travel = travel; 
	}
	
	/**
	 * 
	 * @param currentRiver
	 */
	public void openRiverFrame(River currentRiver, JLabel dateMainLbl, JLabel foodMainLbl, JLabel wthrQtyLbl) {
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1289, 767);
		frame.setTitle("River");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
        frame.setResizable(false);
        
        //Image of a river for the frame
        ImageIcon riverImage = new ImageIcon(this.getClass().getResource("/image/river image.png"));
        JLabel riverImg = new JLabel(riverImage);
        riverImg.setBounds(562, 108, 684, 441);
    	
		
        // displays river name
	    JLabel riverName = new JLabel("Welcome to " + currentRiver.getName());
	    riverName.setHorizontalAlignment(SwingConstants.CENTER);
	    riverName.setBounds(222, 11, 821, 86);
		riverName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
		
		// this is a height label so the user knows what is being displayed
		JLabel heightLbl = new JLabel("Height:");
		heightLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		heightLbl.setBounds(10, 160, 158, 51);
		heightLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// this is a flow label so the user knows what is being displayed
		JLabel flowLbl = new JLabel("Flow:");
		flowLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		flowLbl.setBounds(10, 222, 158, 51);
		flowLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// this is a width label so the user knows what is being displayed 
		JLabel widthLbl = new JLabel("Width:");
		widthLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		widthLbl.setBounds(10, 284, 158, 51);
		widthLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// this displays the height of the river water level 
		JLabel heightNumLbl = new JLabel("");
		heightNumLbl.setBounds(178, 160, 137, 51);
		heightNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// this displays the flow of the river
		JLabel flowNumLbl = new JLabel("");
		flowNumLbl.setBounds(178, 222, 137, 51);
		flowNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// this displays the width of the river
		JLabel widthNumLbl = new JLabel("");
		widthNumLbl.setBounds(178, 284, 137, 51);
		widthNumLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		JLabel crossingLbl = new JLabel("How would you like to traverse the river?");
		crossingLbl.setBounds(10, 592, 832, 83);
		crossingLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		JLabel 	dateQtyLbl = new JLabel("Date: ");
		dateQtyLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl.setBounds(902, 592, 280, 51);
		
		JLabel dateLbl = new JLabel ("");
		dateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateLbl.setBounds(734, 592, 158, 51);
		
		//button to cross river with wagon 
		JButton fordBtn = new JButton("Ford the river");
		fordBtn.setBounds(369, 668, 261, 51);
		fordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, currentRiver.randomEvtCross(bank)); // Displays if the user made it across safely, or with consequences
				frame.dispose();		  											    // closes frame after button is hit
			}
		});
		fordBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		
		// button to cross river on ferry 
		JButton ferryBtn = new JButton("Pay the Ferry ($8)");
		ferryBtn.setBounds(20, 668, 324, 51);
		ferryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bank.spendMoney(800);									  				// removes money because user paid to cross with ferry 
				JOptionPane.showMessageDialog(null, currentRiver.randomEvtFerry(bank)); // Displays if the user made it across safely, or with consequences
				frame.dispose();		   											    // closes frame after button hit
			}
		});
		ferryBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));

		JButton caulkBtn = new JButton("Caulk the wagon");
		caulkBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		caulkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bank.spendMoney(800);									 			    // removes money because user paid to cross with ferry 
				JOptionPane.showMessageDialog(null, currentRiver.randomEvtFerry(bank)); // Displays if the user made it across safely, or with consequences
				frame.dispose();		   											    // closes frame after button hit
			}
		});
		caulkBtn.setBounds(655, 668, 253, 51);
		
		JButton waitBtn = new JButton("Wait");
		waitBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		waitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4); 
				foodMainLbl.setText(wagon.getConsumableWeight() + "");
				dateQtyLbl.setText(travel.getDate());
				dateMainLbl.setText(travel.getDate());			
				}
		});
		waitBtn.setBounds(933, 668, 253, 51);
		
		JTextPane conversationPane = new JTextPane();
		conversationPane.setEditable(false);
		conversationPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		conversationPane.setBounds(178, 371, 357, 215);
		
		JButton talkBtn = new JButton("Talk");
		talkBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		talkBtn.setBounds(10, 371, 158, 51);
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Location location : locations) {
					if(location instanceof River && !location.hasvisited()) {
						conversationPane.setText(((River) location).generatePhrase());
						break;
					}
				}
			}
		});

    	River.openFile();
    	heightNumLbl.setText(currentRiver.getHeight(wthrQtyLbl)+ ""); // displays height of river user is at 
    	flowNumLbl.setText(currentRiver.getFlow(wthrQtyLbl)); 		  // displays flow of river the user is at 
    	widthNumLbl.setText(currentRiver.getWidth(wthrQtyLbl)+ "");   // displays width of the river the user is at
    	River.closeFile();
		
		// adding components to panel and frame
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(heightLbl);
		panel.add(flowLbl);
		panel.add(widthLbl);
		panel.add(heightNumLbl);
		panel.add(flowNumLbl);
		panel.add(widthNumLbl);
		panel.add(riverName);
		panel.add(crossingLbl);
		panel.add(caulkBtn);
		panel.add(ferryBtn);
		panel.add(riverImg);
		panel.add(fordBtn);
		panel.add(waitBtn);
		panel.add(conversationPane);
		panel.add(talkBtn);
		panel.add(dateQtyLbl);
		panel.add(dateLbl);
		frame.getContentPane().add(panel);
		
		
	}
}
