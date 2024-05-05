/**
 * IntroFrame.java
 * 
 * 
 * @author Lena Frate
 * @version 1.1.1 - May 1 2024
 * 
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class IntroFrame {
	
	private JFrame introFrame;
	private JTextField name1Txt;
	private JTextField name2Txt;
	private JTextField name3Txt;
	private JTextField name4Txt;
	
	/**
	 * 
	 */
	public IntroFrame() {}
	
	/**
	 * 
	 * @param mainDateLbl
	 * @param travel
	 * @param health
	 * @param store
	 */
	public void openIntroFrame(JLabel mainDateLbl, TravelManager travel, WagonParty health, Store store) {
		
		introFrame = new JFrame();
		introFrame.getContentPane().setBackground(new Color(0, 0, 0));
		introFrame.getContentPane().setLayout(null);
		introFrame.setBounds(100, 100, 1289, 767);
		introFrame.setTitle("Setup");
		introFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close operation
		introFrame.setVisible(true);

		JTextArea introBlurb = new JTextArea();
		introBlurb.setForeground(new Color(255, 255, 255));
		introBlurb.setBackground(new Color(0, 0, 0));
		introBlurb.setFont(new Font("Bookman Old Style", Font.ITALIC, 32));
		introBlurb.setLineWrap(true);
		introBlurb.setEditable(false);
		introBlurb.setText("Welcome to the Oregon Trail as told by a female's perspective." +  
							"Your path \r\ntoday is based off of diarist Amelia Knight. Amelia " +
							"travelled the trail from \r\nMarch 1853 to September 1853. She and " +
							"her husband travelled with their \r\nseven children from Iowa to " +
							"their new home in Oregon. She will be in the \r\nsame wagon train " +
							"as you so you may converse with her along the way and \r\nhear more " +
							"about her journey. ");
		introBlurb.setBounds(23, 82, 1226, 249);
		introFrame.getContentPane().add(introBlurb);
		
		JRadioButton marchRadioBtn = new JRadioButton("March");
		marchRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setMonth(3);
			}
		});
		marchRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		marchRadioBtn.setOpaque(false);
		marchRadioBtn.setForeground(new Color(255, 255, 255));
		marchRadioBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		marchRadioBtn.setBounds(120, 408, 232, 45);
		introFrame.getContentPane().add(marchRadioBtn);
		
		JRadioButton aprilRadioBtn = new JRadioButton("April");
		aprilRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setMonth(4);
			}
		});
		aprilRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aprilRadioBtn.setOpaque(false);
		aprilRadioBtn.setForeground(new Color(255, 255, 255));
		aprilRadioBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		aprilRadioBtn.setBounds(120, 461, 232, 45);
		introFrame.getContentPane().add(aprilRadioBtn);
		
		JRadioButton mayRadioBtn = new JRadioButton("May");
		mayRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setMonth(5);
			}
		});
		mayRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mayRadioBtn.setOpaque(false);
		mayRadioBtn.setForeground(new Color(255, 255, 255));
		mayRadioBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		mayRadioBtn.setBounds(120, 518, 232, 45);
		introFrame.getContentPane().add(mayRadioBtn);
		
		JRadioButton juneRadioBtn = new JRadioButton("June");
		juneRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travel.setMonth(6);
			}
		});
		juneRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		juneRadioBtn.setOpaque(false);
		juneRadioBtn.setForeground(new Color(255, 255, 255));
		juneRadioBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		juneRadioBtn.setBounds(120, 575, 232, 45);
		introFrame.getContentPane().add(juneRadioBtn);
		
		JLabel monthQLbl = new JLabel("Which month would you like to start in?");
		monthQLbl.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		monthQLbl.setForeground(new Color(255, 255, 255));
		monthQLbl.setBounds(45, 356, 494, 45);
		introFrame.getContentPane().add(monthQLbl);
		
		JButton startBtn = new JButton("Start Game");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set the names of the agon members
				health.getMembers().get(0).setName(name1Txt.getText());
				health.getMembers().get(1).setName(name2Txt.getText());
				health.getMembers().get(2).setName(name3Txt.getText());
				health.getMembers().get(3).setName(name4Txt.getText());
				
				// set date label on main frame
				mainDateLbl.setText(travel.getDate());
				
				// open the store
				store.openStoreWindow(true);
				
				System.out.println(health.getMembers().get(0).getName());
				
				introFrame.dispose();
			}
		});
		startBtn.setBackground(new Color(253, 253, 253));
		startBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		startBtn.setFont(new Font("Bookman Old Style", Font.PLAIN, 42));
		startBtn.setBounds(23, 647, 1226, 61);
		introFrame.getContentPane().add(startBtn);
		
		JLabel lblWhatAreThe = new JLabel("What are the names of the people in your wagon?");
		lblWhatAreThe.setForeground(Color.WHITE);
		lblWhatAreThe.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		lblWhatAreThe.setBounds(607, 355, 627, 45);
		introFrame.getContentPane().add(lblWhatAreThe);
		
		JTextArea txtrName = new JTextArea();
		txtrName.setEditable(false);
		txtrName.setOpaque(false);
		txtrName.setForeground(new Color(255, 255, 255));
		txtrName.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		txtrName.setText("Name 1:\r\n\r\nName 2:\r\n\r\nName 3:\r\n\r\nName 4:");
		txtrName.setBounds(685, 407, 114, 213);
		introFrame.getContentPane().add(txtrName);
		
		name1Txt = new JTextField();
		name1Txt.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		name1Txt.setBounds(809, 408, 254, 30);
		introFrame.getContentPane().add(name1Txt);
		name1Txt.setColumns(10);
		
		name2Txt = new JTextField();
		name2Txt.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		name2Txt.setBounds(809, 465, 253, 30);
		introFrame.getContentPane().add(name2Txt);
		name2Txt.setColumns(10);
		
		name3Txt = new JTextField();
		name3Txt.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		name3Txt.setBounds(809, 525, 254, 30);
		introFrame.getContentPane().add(name3Txt);
		name3Txt.setColumns(10);
		
		name4Txt = new JTextField();
		name4Txt.setFont(new Font("Bookman Old Style", Font.PLAIN, 24));
		name4Txt.setBounds(809, 585, 254, 30);
		introFrame.getContentPane().add(name4Txt);
		name4Txt.setColumns(10);
		
        ButtonGroup group = new ButtonGroup();
        group.add(marchRadioBtn);
        group.add(aprilRadioBtn);
        group.add(mayRadioBtn);
        group.add(juneRadioBtn);
        
        JLabel introTitleLbl = new JLabel("The Oregon Trail");
        introTitleLbl.setFont(new Font("Felix Titling", Font.PLAIN, 50));
        introTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        introTitleLbl.setForeground(new Color(255, 255, 255));
        introTitleLbl.setBounds(355, 11, 526, 60);
        introFrame.getContentPane().add(introTitleLbl);
        
		// moves the frame in front of all other frames
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				introFrame.toFront();
				introFrame.repaint();
			}
		});
	}
}
