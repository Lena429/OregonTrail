/**
 * FortFrame.java
 * 
 * @author 
 * @version
 * 
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FortFrame {
	
	private Travel travel;
	private Wagon wagon;
	private Equipment food;
	
	/**
	 * 
	 * @param travel
	 * @param wagon
	 * @param food
	 */
	public FortFrame(Travel travel, Wagon wagon, Equipment food) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
	}
	
	/**
	 * 
	 * @param currentFort
	 * @param store
	 */
	public void openFortFrame(Fort currentFort, Store store) {
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
		dateLbl_3.setBounds(586, 631, 93, 51);
		
		JLabel dateQtyLbl_3 = new JLabel(travel.getDate());
		dateQtyLbl_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_3.setBounds(676, 631, 284, 51);
		
		// Label to hold generated phrases of conversation
		JLabel Gossip = new JLabel("");
		Gossip.setBounds(187, 146, 654, 13);
		
		// player talks to other people inside fort
		// randomly selected phrases from Fort Class
		JButton Talking = new JButton("Talk to people");
		Talking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phrase = currentFort.generatePhrase();
               			Gossip.setText(phrase);
			}
		});
		Talking.setBounds(31, 138, 133, 21);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton Rest = new JButton("Rest");
		Rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				//dateQtyLbl.setText(travel.getDate());
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		Rest.setBounds(31, 256, 133, 21);
		
		//player decides to look around at fort
		JButton LookAround = new JButton("Look Around");
		LookAround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameImage.setVisible(true);
			}
		});
		LookAround.setBounds(31, 194, 133, 21);

		// player decides to shop in the store
		JButton Shop = new JButton("Shop");
		Shop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                store.adjustPrices(currentFort);
				store.StoreWindow();
			}
		});
		Shop.setBounds(31, 310, 133, 21);
		
		// Greeting header for the fort frames
	    JLabel fortName = new JLabel("Welcome to " + currentFort.getName());
		fortName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
		fortName.setBounds(343, 11, 569, 86);
		
		// panel to hold all fort objects to the frame
		JPanel PanelThree = new JPanel();
		PanelThree.setLayout(null);
		PanelThree.add(Gossip);
		PanelThree.add(Talking);
		PanelThree.add(Rest);
		PanelThree.add(LookAround);
		PanelThree.add(dateLbl_3);
		PanelThree.add(dateQtyLbl_3);
		PanelThree.add(fortName);
		PanelThree.add(Shop);
		PanelThree.add(forts);
		frameThree.getContentPane().add(PanelThree);
		
		// panel for the fort images 
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.add(fortImage);
		frameImage.getContentPane().add(imagePanel);	
	}
}
