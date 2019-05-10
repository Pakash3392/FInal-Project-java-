package LoginSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import AuctionHome.panelTest;
import LoginSystem.User;


public class Login extends JFrame {
	public Login() {
	}
	
	//Labels
	private JLabel lblLogin = new JLabel("Login");
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	
	//Text Fields
	private JTextField txtUsername = new JTextField();
	
	//password fields
	private JPasswordField txtPassword = new JPasswordField();
	
	//Buttons
	private JButton  btnLogin = new JButton("Login");
	private JButton  btnRegister = new JButton("Register");
	
	//Panel
	private JPanel MainPanel = new JPanel();

	//User name and password entered by user
	private static String username;
	private String password;
	
	User user = new User();
	
	
	
	
	//Action Listener class that holds each button
	private class AddBtnListener implements ActionListener	{
		@Override
		public void actionPerformed(ActionEvent e) {
		
			//LOGIN BUTTON
	        if (e.getActionCommand().equals("Login")) {
	        	
				//User inputs set equal to strings

				 user.setUsername(txtUsername.getText());
				 user.setPassword(txtPassword.getText());

				 username = user.getUsername();
				 password = user.getPassword();
				 
				//Verification system
				Boolean verify = false;
				
				//creates new file variables equal to user name and password text file
				File usernameFile = new File("Username.txt");
				File passwordFile = new File("Password.txt");

				 
				
			        //Creating Scanner instance to read the user name file 
			        Scanner usernameScnr = null;
					try {
						usernameScnr = new Scanner(usernameFile);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			        //Creating Scanner instance to read the password file
			        Scanner passwordScnr = null;
					try {
						passwordScnr = new Scanner(passwordFile);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


			        //Reading each line of each file using Scanner class
			        while(usernameScnr.hasNextLine() && passwordScnr.hasNextLine()){
			            String line = usernameScnr.nextLine();
			            String line2 = passwordScnr.nextLine();

			            //If statement to determine if user name and password are correct
			            if (username.equals(line) && password.equals(line2))
			            {
			            	verify = true;
			            }
			        }
			        
			        
			        //if the user successfully inputs user name and password login screen will close
			        //and open the auction home
			        if (verify == true) {

			    		setVisible(false);
			        	panelTest.main(null);
			        	
			        }
			        
			        
			        //else the user inputs invalid login details
			        //This opens a error message
			        else 
			        {
			        	JOptionPane.showMessageDialog(null, "Invalid Login Details"
			        			, "Login Error", JOptionPane.ERROR_MESSAGE);
			        	//sets text fields to blank
			        	txtUsername.setText(null);
			        	txtPassword.setText(null);
			        }
			      
                }
	        
	        
	        //=======REGISTER BUTTON=============
	        if (e.getActionCommand().equals("Register")) 
	        {
	        	//closes the current page
	    		setVisible(false);
	    		//opens the registration page
	        	Registration.main(null);	        
	        	}
	        
	      
	        
		}
	}
	
	
	
	
	public Login(String title)	
	{
		super(title);
		
		//Sets window size
		setBounds(200, 200, 500, 300);
		//exit and terminates program on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildMainFrame();
		addListeners();
		
		setVisible(true);

	}
	
	
	
	
	private void addListeners() {
		btnLogin.addActionListener(new AddBtnListener());
		btnRegister.addActionListener(new AddBtnListener());
	}
	
	
	
	private void buildMainFrame() {
		MainPanel.setLayout(null);

		lblLogin.setBounds(193, 11, 103, 45);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MainPanel.add(lblLogin);
		
		lblPassword.setBounds(82, 145, 82, 32);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPassword);
		
		lblUsername.setBounds(72, 94, 72, 32);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblUsername);
		
		txtUsername.setBounds(154, 102, 200, 20);
		MainPanel.add(txtUsername);
		
		txtPassword.setBounds(154, 153, 200, 20);
		MainPanel.add(txtPassword);
		
		btnLogin.setBounds(154, 207, 89, 23);
		MainPanel.add(btnLogin);
		
		btnRegister.setBounds(265, 207, 89, 23);
		MainPanel.add(btnRegister);
		
		getContentPane().add(MainPanel);

		
		
	}
	
	
	




	public static void main(String[] args) {
		Login gui = new Login("Login");
	}
	
	
}
