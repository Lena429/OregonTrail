import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LandmarkFrame {
	private TravelManager travel;
	private Wagon wagon;
	private Equipment food;
	private ArrayList<Location> locations;
	
	public LandmarkFrame(TravelManager travel, Wagon wagon, Equipment food, ArrayList<Location> locations) {
		this.travel = travel;
		this.wagon = wagon;
		this.food = food;
		this.locations = locations;
	}
	
	public void openLandmarkFrame(Landmarks currentLandmark, TeaTime teaTime) {
		//frame for the landmarks and the interactions to be had
		JFrame frameFive = new JFrame();
		frameFive.setBounds(100, 100, 1289, 767);
		frameFive.setTitle("LANDMARK");
		frameFive.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameFive.setVisible(true);
		
		//landmark images
		
		// Greeting header for the fort frames
	    JLabel landmarkName = new JLabel("Welcome to " + currentLandmark.getName());
	    landmarkName.setFont(new Font("Bookman Old Style", Font.PLAIN, 50));
	    landmarkName.setBounds(343, 11, 569, 86);
		
		JLabel dateLbl_3 = new JLabel("Date:");
		dateLbl_3.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		dateLbl_3.setBounds(586, 631, 93, 51);
		
		JLabel dateQtyLbl_3 = new JLabel(travel.getDate());
		dateQtyLbl_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		dateQtyLbl_3.setBounds(676, 631, 284, 51);
		
		// Label to hold generated phrases of conversation
		JTextPane conversationPane = new JTextPane();
		conversationPane.setEditable(false);
		conversationPane.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		conversationPane.setBounds(178, 371, 357, 215);
		
		JButton talkBtn = new JButton("Talk to people");
		talkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   for (Location location : locations) {
				if(location instanceof Fort && !location.hasvisited()) {
					conversationPane.setText((currentLandmark).generatePhrase());
					break;
				}
			  }
			}
		});
		talkBtn.setBounds(31, 138, 133, 21);
		
		// player decides to rest in the fort
		// updates day counter while in the fort and resting
		JButton restBtn = new JButton("Rest");
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.updateDate();
				wagon.removeItemQty(food, travel.getRations() * 4);
				dateQtyLbl_3.setText(travel.getDate());
			}
		});
		restBtn.setBounds(31, 256, 133, 21);
		
		JButton leaveBtn = new JButton ("Continue Trail");
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFive.dispose();
			}
		});
		leaveBtn.setBounds(31, 370, 133, 21);
		
		//player decides to look for tea
		JButton teaTimeBtn = new JButton("Tea Time");
			teaTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teaTime.openTeaTime();
			}
		});
		teaTimeBtn.setBounds(31,450,133,21);
		
		
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
		frameFive.getContentPane().add(landPanel);
		
	}
}
