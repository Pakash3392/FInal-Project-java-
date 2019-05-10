package AuctionHome;

import java.awt.Font;
import java.awt.Image;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Bid extends JFrame{
	
public Bid() {
}
	

		private static Scanner x;

		//Panel
		private static JPanel MainPanel = new JPanel();

		//Labels
		private static JLabel lblName = new JLabel();
		private static JLabel lblPicture = new JLabel();
		private static JLabel lblPrice = new JLabel();
		private static JLabel lblTime = new JLabel();

		//Text Fields
		private static JTextField txtUsersBid = new JTextField();
		//private JTextField itemName = new JLabel();
		//Buttons
		private static JButton btnBid = new JButton("BID");
		private static JButton btnCancel = new JButton("Cancel");
		
		String usersPrice;
		
		static String itemPicture;
		static String itemName;
		static String itemTime;
		static Double itemPrice;
	
		
		private class AddBtnListener implements ActionListener	{
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
		        if (e.getActionCommand().equals("BID")) 
		        {

		        	//User inputs 
				    usersPrice = txtUsersBid.getText();
				   //System.out.println(usersPrice);
				   // System.out.println(Item.getItemList());
				   ItemCreation2.getPrice(Double.parseDouble(usersPrice));
				   double userAmount = Double.parseDouble(usersPrice);
				   System.out.println("==============");
				   System.out.println("current price:" + usersPrice);
				   
				   
				   //Calling the class so we can use the array.
				   ItemCreation2 itemList = new ItemCreation2();
				   

				    ArrayList<Item> testArray = new ArrayList<Item>(ItemCreation2.getItemList());
				  //For loop to run through array in ItemCreation Class. 
				   for (Item item : testArray) {
					   // If loop to find item name to adjust the correct price in array. 
					   if(itemName.equals(item.getItemName())) {
					   item.setPrice(userAmount);
					   
					   //Overwriting into CSV file. 
					   //============================
					   File itemFile = new File("items.csv");

						 if(itemFile.exists()==false)
						    {
						            try 
						            {
						            	itemFile.createNewFile();
									} catch (IOException e1)
						               {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									   }
						    }
						 
						 
						 //This is where the problem is originating from
						 //it is just continually writes the first item over and over
						 // you need to set itemList.getItemList equal to an array in this class then you need to use
						 //array.get(x) and pull each specific item out
						    int x = 0;
						 try (FileWriter writer = new FileWriter(itemFile, false)) 
						 {
							 for (Item item1 : testArray) {
			            		 writer.write(testArray.get(x) + "\n");
			            		 x++;
							 }
			            		 System.out.println("Item list after for loop" + itemList.getItemList());
				         } catch (IOException e1) 
						 {
								// TODO Auto-generated catch block
								e1.printStackTrace();
					     }
					   
						 //=================================================
						 
					// More spam to see where Java currently is. 
					   System.out.println("\n=======updated list======");
					   System.out.println(itemName + " updated");
					   System.out.println(itemList.getItemList());
					   
					 //  panelTest.stop();
					 //  ItemCreation2.pullItems();
					   
				     //  panelTest.f.setVisible(true);
					  // panelTest.main(null);
					 //  panelTest.f.revalidate();
					   
					   JOptionPane.showMessageDialog(null, "Please verify Login Credentials"
			        			, "Bid Verification", JOptionPane.ERROR_MESSAGE);
					   try {
						Runtime.getRuntime().exec("java -jar AuctionJar.jar");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   panelTest.stop();

					   }
				   }
				}
					 

		        
		        
		        if (e.getActionCommand().equals("Cancel")) 
		        {
					setVisible(false);

		        }
		        
			}
			
		}
		
		
		
		
		public Bid(String title)	{
			super(title);
			
			setBounds(400, 100, 400, 500);
			
			buildMainFrame();
			addListeners();
			
			setVisible(true);
		}
		
		
		
		
		private void addListeners() {
			btnBid.addActionListener(new AddBtnListener());
			btnCancel.addActionListener(new AddBtnListener());
		}
		
		
		
		public static void setItem(String itemPic, String itemN,  String itemT, Double itemP)
		{
			itemPicture = itemPic;
			itemName = itemN;
			itemTime = itemT;
			itemPrice = itemP;
			
		}
		
		public void buildMainFrame() {
			MainPanel.setLayout(null);
			
			//do this so the value shows as $1 above the current
			itemPrice++;
//			lblAccountRegistration.setBounds(57, 11, 282, 54);
//			lblAccountRegistration.setFont(new Font("Tahoma", Font.PLAIN, 30));
//			MainPanel.add(lblAccountRegistration);
			  ImageIcon imageIcon = new ImageIcon(itemPicture);
		        Image image = imageIcon.getImage(); // transform it 
		        Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		        
			lblPicture.setIcon(new ImageIcon(newimg));
			lblPicture.setBounds(140, 40, 100, 120);
			MainPanel.add(lblPicture);
			
			lblName.setText("Item Name:    " + itemName);
			lblName.setBounds(57, 178, 1000, 19);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			MainPanel.add(lblName);
			
			lblTime.setText("Item Time:    " + itemTime);
			lblTime.setBounds(57, 239, 150, 19);
			lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
			MainPanel.add(lblTime);
			
			lblPrice.setText("Item Price:   " + itemPrice + " or greater");
			lblPrice.setBounds(65, 304, 300, 19);
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
			MainPanel.add(lblPrice);
			
//			txtLastName.setBounds(146, 179, 142, 20);
//			MainPanel.add(txtLastName);
//			
//			txtFirstName.setBounds(146, 123, 142, 20);
//			MainPanel.add(txtFirstName);
//			
//			txtUsername.setBounds(146, 240, 142, 20);
//			MainPanel.add(txtUsername);
			
			txtUsersBid.setBounds(146, 302, 142, 25);
			MainPanel.add(txtUsersBid);
			
			btnBid.setBounds(66, 392, 89, 23);
			MainPanel.add(btnBid);
			
			btnCancel.setBounds(205, 391, 97, 25);
			MainPanel.add(btnCancel);
			
			getContentPane().add(MainPanel);
		}
		
		
		

		public static void main(String[] args)
		{
			Bid gui = new Bid("BID");
		}
	}