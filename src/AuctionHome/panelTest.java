package AuctionHome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import AuctionHome.ItemCreation2;


public class panelTest extends JPanel
{
	//create method in here that you can call to update jpanel
	//if I can remove a panel completely i should be able to update one
	
    private JPanel fillerPanel = new JPanel();
    private static ArrayList<JPanel> panels = new ArrayList<JPanel>();
    static panelTest listPanel = new panelTest();
    
    static JPanel panel = new JPanel();

    static JButton btnAdd = new JButton(new ImageIcon(panelTest.class.getClassLoader.getResource("/image/ADD.png")0);
    static JButton btnLogout = new JButton(new ImageIcon("Res/image/LOGOUT.png"));
    static JFrame f;
    public panelTest()
    {
    	setLayout(new GridBagLayout());
    }

    public static void stop() 
    { 
    	System.exit(0);
    } 
    public void addPanel(JPanel p, int height)
    {
    	//Removes the filler panel that was there (see the set of 3 comments below)
        remove(fillerPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        //gridx is where the cell is going to be within the grid (Goes off top left corner)
        //From left to right
        gbc.gridx = 0;
        //up and down
        gbc.gridy = panels.size();
        
        //This fills in the empty space on the sides
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = height;
        gbc.weightx = 1.0;
        
        //Adds panel to array list
        panels.add(p);
        add(p, gbc);
        
        
        
        //Putting an invisible panel on the end pushes the panels to the top
        //This is to fix an issue when you have 1-4 panels they try to center themselves vertically
        //This instead pushes them to the top because the weight it set to 1.0
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = panels.size();
        gbc.weighty = 1.0;
        add(fillerPanel, gbc);
        
        
        revalidate();
        repaint();
    }
   
    
    
    public void removePanel(int i) throws IOException
    {

		//Removes item from CSV file
        ArrayList<Item> arrl = new ArrayList<>(ItemCreation2.getItemList());

        arrl.remove(i);
        
        Item[] strArr = new Item[arrl.size()];
        
        arrl.toArray(strArr);
       
        BufferedWriter br = new BufferedWriter(new FileWriter("items.csv"));
        StringBuilder sb = new StringBuilder();

        for (Item element : strArr) {
         sb.append(element);
         sb.append(",");
        }
        

        br.write(sb.toString());
        br.close();
        
  
	    //Removes JButton (i) from JButton array *WORKS
	    ArrayList <JButton>placeholder = new ArrayList<>(ItemCreation2.getJButtonList());
	    placeholder.remove(i);
        ItemCreation2.setJButtonList(placeholder);
        
        //Removes Item (i) from Item array *WORKS
	    ArrayList<Item> placeholder2 = new ArrayList<>(ItemCreation2.getItemList());
	    placeholder2.remove(i);
        ItemCreation2.setItemList(placeholder2);
         
    	//Removes JPanel
        remove(i);
        //Removes from array
        panels.remove(i);
        
        revalidate();
        repaint();
    }
    
    public static JPanel buildBannerPanel() {
    	JPanel banner = new JPanel();
    	banner.setLayout(new GridLayout(1,6));

		JLabel auctionHome = new JLabel("Auction Home");
		auctionHome.setFont(new Font("Ubuntu", Font.PLAIN, 50));
		btnAdd.setPreferredSize(new Dimension(94, 46));
		btnAdd.setContentAreaFilled(false); 
		btnAdd.setBorderPainted(false); 
		btnLogout.setPreferredSize(new Dimension(76, 44));
		btnLogout.setContentAreaFilled(false); 
		btnLogout.setBorderPainted(false); 
		

		  banner.add(btnAdd, 0,0);
		  banner.add(auctionHome, 3,0);
		  banner.add(btnLogout, 6,0);
		  
	return banner;
    	
    }
 
    //Main where the main frame is built
    public static void main(String[] args) 
    {
  
    	//Creates new frame
        f = new JFrame();
        //exits on close
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Sets size of page
        f.setBounds(100, 100, 1009, 701);
        
        f.getContentPane().setLayout(new BorderLayout());

     

		  btnAdd.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent paramActionEvent)
	            {
		        	ItemCreation2.main(null);
	            }
	        });
      
		  btnLogout.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent paramActionEvent)
	            {
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
	        });

   

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent paramActionEvent)
            {
                try {
					listPanel.removePanel(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
      


        //ADDS BUTTONS TO LAYOUT
        f.getContentPane().add(btnRemove, BorderLayout.EAST);
        f.getContentPane().add(btnAdd, BorderLayout.WEST);
        f.getContentPane().add(buildBannerPanel(), BorderLayout.NORTH);
        
        
        //SCROLLPANE
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(listPanel);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        //Pulls stored items when opening the program
        ItemCreation2.pullItems();
        

        
        //sets frame to be visible
        f.setVisible(true);

    } 
}