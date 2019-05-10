package AuctionHome;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AuctionHome.panelTest;


public class ItemCreation2 extends JFrame{
	public ItemCreation2() {
	}
	
	
	//Panel
	private JPanel MainPanel = new JPanel();

	//Labels
	private JLabel lblItemCreation = new JLabel("Item Creation");
	private JLabel lblItemName = new JLabel("Item Name: ");
	private JLabel lblPicture = new JLabel("Picture:");
	private JLabel lblPrice = new JLabel("starting price:");
	private JLabel lblTime = new JLabel("time limit:");

	//Text Fields
	private static JTextField txtItemName = new JTextField();
	private static JTextField txtPrice = new JTextField();
	private static JTextField txtTime = new JTextField();

	//Buttons
	private JButton btnRegister = new JButton("Add Item");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnPicture = new JButton("Browse");

	 private static String itemName;
	 private static String picture;
	 private static Double price;
	 private static String time;

		private static int itemCount = 0;

		private static Item item = new Item();
		//private static JButton[] buttonList = new JButton[x];
	    private static ArrayList<JButton> buttonList = new ArrayList<JButton>();


	    private static ArrayList<Item> itemList = new ArrayList<Item>();

		
	    
	    
	     
	private class AddBtnListener implements ActionListener	
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//Register Button
		    if (e.getActionCommand().equals("Add Item")) 
		    {
				saveItem();

		       panelTest.listPanel.addPanel(getJPanel(), 59);

				setVisible(false);
		    }
		       
		    //Cancel Button
	        if (e.getActionCommand().equals("Cancel")) 
	        {
				setVisible(false);
	        }
	        
	        //Browse Button
	        if (e.getActionCommand().equals("Browse")) 
	        {
	        	JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = file.getSelectedFile();
					picture = selectedFile.getAbsolutePath();
					//label.setIcon(ResizeImage(path));
				}
				else if(result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No File selected");	
				}		
	        }

		}	
	}
	
	
	
	
	
	
	
	
	
	//Creates a new Item Panel
    public static JPanel getJPanel()
    {
        JPanel panel = new JPanel();

        
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File(picture));
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        panel.setLayout(new GridLayout(1, 5));
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        ImageIcon imageIcon = new ImageIcon(img);
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        JLabel newPrice = new JLabel("$" + price.toString());
        panel.add(new JLabel(new ImageIcon(newimg)));
        panel.add(new JLabel(itemName));
        panel.add(newPrice);
        panel.add(new JLabel(time));
        //add id to panel like button?
        panel.add(buttonList.get(itemCount));
        itemCount++;
        
        return panel;
    }
    
    static Double getPrice(Double price1) {
    	price1 = price;
    	return price;
    	
    }

    
	public static void saveItem() 
	{
		
		 itemName = txtItemName.getText();
		 //picture = btnPicture.getText();
		 price = Double.parseDouble(txtPrice.getText());
		 time = txtTime.getText();
		 JButton h =new JButton(new ImageIcon(ItemCreation2.class.getResource("BID.png")));
		 h.putClientProperty("id", Integer.valueOf(itemCount));

		 h.addActionListener(new ActionListener() 
		 { 
			  public void actionPerformed(ActionEvent e) 
			  { 
				  Object property = h.getClientProperty("id");
				  if (property instanceof Integer) {
				     int objectCounter = ((Integer)property);
				     System.out.println(objectCounter);
				     
				     // System.out.println(itemList.get(objectCounter));
				     String clickedItem = String.valueOf(itemList.get(objectCounter));
				     
				     String[] clickedItems = clickedItem.split(",");
				     
					 String n = clickedItems[0];
					 String pic = clickedItems[1];
					 Double pri = Double.parseDouble(clickedItems[2]);
					 String t = clickedItems[3];
				     //I need to do the same thing as above, set itemList.get(objectcounter)
				     //Equal to a string then split it by commas into an array
				     //Then set each index of that array equal to a string
				     //Then put those strings in setItem
				     Bid.setItem(pic, n, t, pri);
				     Bid.main(null);
				     // do stuff
				     // do stuff
				  	}				  
			  } 
		 });

	        h.setPreferredSize(new Dimension(40, 40));
	        h.setContentAreaFilled(false); 
	        h.setBorderPainted(false); 

		 buttonList.add(h);

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
			
             Item i = new Item(itemName, picture, price, time, buttonList.get(itemCount));
             itemList.add(i);
			 
			 try (FileWriter writer = new FileWriter(itemFile, true)) 
			 {

            		 writer.append(itemList.get(itemCount).toString() + "\n");
            		 

            	 
	         } catch (IOException e) 
			 {
					// TODO Auto-generated catch block
					e.printStackTrace();
		     }
			 
			 
	}
	
	

	
	
	
    public static void  pullItems()
    {
    	
    	
		File itemsFile = new File("Items.csv");
		
		//Creates new file if it does not exist
		if(itemsFile.exists()==false)
	    {
	            try 
	            {
	            	itemsFile.createNewFile();
				} catch (IOException e1) 
	            {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	            }
	    }
		
		
		//Creates new scanner called input
		Scanner input = new Scanner(System.in);

         try {
			input = new Scanner(itemsFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			//String[] tokens = str.split(",");

		//While loop cycles through user name file and checks to see if the users registered user name
		//is already in the file, if it is it has the user re-enter a new user name
		 while(input.hasNextLine()) {

			 //Sets what's on the current line of the file equal to the string
			String line = input.nextLine();
			 
			 //if users inputed user name equals the one already in the database it turns the user name check false
			 String[] itemDetails = line.split(",");
			 
			 itemName = itemDetails[0];
			 picture = itemDetails[1];
			 price = Double.parseDouble(itemDetails[2]);
			 time = itemDetails[3];
			 
			 
			 
			 JButton h =new JButton(new ImageIcon(ItemCreation2.class.getResource("BID.png")));
			 h.putClientProperty("id", Integer.valueOf(itemCount));

			 h.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  Object property = h.getClientProperty("id");
					  if (property instanceof Integer) {
					     int objectCounter = ((Integer)property);
					    // System.out.println(itemList.get(objectCounter));
					     String clickedItem = String.valueOf(itemList.get(objectCounter));
					     
					     String[] clickedItems = clickedItem.split(",");
					     
						 String n = clickedItems[0];
						 String pic = clickedItems[1];
						 Double pri = Double.parseDouble(clickedItems[2]);
						 String t = clickedItems[3];
					     //I need to do the same thing as above, set itemList.get(objectcounter)
					     //Equal to a string then split it by commas into an array
					     //Then set each index of that array equal to a string
					     //Then put those strings in setItem
					     Bid.setItem(pic, n, t, pri);
					     Bid.main(null);
					     
					     
					     // do stuff
					  }				  } 
				} );

		        h.setPreferredSize(new Dimension(40, 40));
		        h.setContentAreaFilled(false); 
		        h.setBorderPainted(false); 

			 buttonList.add(h);
			 
			 
			 item.setItemName(itemName);
			 item.setPicture(picture);
			 item.setPrice(price);
             item.setTime(time);
			 item.setButtonList(buttonList.get(itemCount));
             Item i = new Item(itemName, picture, price, time, buttonList.get(itemCount));
             itemList.add(i);
			 //adds new item JPanel
             panelTest.listPanel.addPanel(getJPanel(), 59);

			 Arrays.fill(itemDetails, null);

		 }
    }
    
    
    
    

	public static ArrayList<JButton> getJButtonList() {
		return buttonList;
	}

	public static void setJButtonList(ArrayList<JButton> buttonList) {
		ItemCreation2.buttonList = buttonList;
	}
	
	

	public static ArrayList<Item> getItemList() {
		return itemList;
	}

	public static void setItemList(ArrayList<Item> itemList) {
		ItemCreation2.itemList = itemList;
	}

	public ItemCreation2(String title)	
	{
		super(title);
		
		
		setBounds(400, 100, 400, 500);
		
		
		buildMainFrame();
		addListeners();
		
		setVisible(true);
	}
	
	
	
	
	
	
	
	private void addListeners() 
	{
		btnRegister.addActionListener(new AddBtnListener());
		btnCancel.addActionListener(new AddBtnListener());
		btnPicture.addActionListener(new AddBtnListener());

	}
	
	
	
	
	
	
	
	
	private void buildMainFrame() {
		MainPanel.setLayout(null);
		
		lblItemCreation.setBounds(100, 11, 282, 54);
		lblItemCreation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MainPanel.add(lblItemCreation);
		
		lblPicture.setBounds(80, 122, 76, 19);
		lblPicture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPicture);
		
		lblItemName.setBounds(50, 178, 90, 25);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblItemName);
		
		lblPrice.setBounds(49, 239, 90, 19);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPrice);
		
		lblTime.setBounds(68, 303, 90, 19);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblTime);
		
		txtItemName.setBounds(146, 179, 142, 20);
		MainPanel.add(txtItemName);
		
		btnPicture.setBounds(146, 123, 142, 20);
		MainPanel.add(btnPicture);
		
		txtPrice.setBounds(146, 240, 142, 20);
		MainPanel.add(txtPrice);
		
		txtTime.setBounds(146, 304, 142, 21);
		MainPanel.add(txtTime);
		
		btnRegister.setBounds(66, 392, 89, 23);
		MainPanel.add(btnRegister);
		
		btnCancel.setBounds(205, 391, 97, 25);
		MainPanel.add(btnCancel);
		
		
		getContentPane().add(MainPanel);
	}
	
	
	





	public static void main(String[] args)
	{
		ItemCreation2 gui = new ItemCreation2("Item Creation");

	}
	
	
	
	


}
