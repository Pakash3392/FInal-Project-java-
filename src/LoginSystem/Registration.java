package LoginSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Registration extends JFrame{
	public Registration() {
	}
	
	//Panel
	private JPanel MainPanel = new JPanel();

	//Labels
	private JLabel lblAccountRegistration = new JLabel("Account Registration");
	private JLabel lblFirstName = new JLabel("First Name:");
	private JLabel lblLastName = new JLabel("Last Name: ");
	private JLabel lblUsername = new JLabel("Username: ");
	private JLabel lblPassword = new JLabel("Password: ");

	//Text Fields
	private JTextField txtFirstName = new JTextField();
	private JTextField txtLastName = new JTextField();
	private JTextField txtUsername = new JTextField();
	
	//Password Field
	private JPasswordField txtPassword = new JPasswordField();

	//Buttons
	private JButton btnRegister = new JButton("Register");
	private JButton btnCancel = new JButton("Cancel");
	
	
	
	
	private class AddBtnListener implements ActionListener	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
	        if (e.getActionCommand().equals("Register")) 
	        {
	        	
	        	
	        	String firstName;
	        	String lastName;
	        	String username;
	        	String password;
	        	String usernameFileLine;
	        	
	        	boolean usernameCheck = true;

	        	
	        	//User inputs 
			    firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				username = txtUsername.getText();
				password = txtPassword.getText();
			
				
				//Creates two new file variables equal to user name and password file
				File usernameFile = new File("Username.txt");
				File passwordFile = new File("Password.txt");

				
				//Checks to see if the file already exists on the users computer
				//and if it doesn't it creates the file
				 if(usernameFile.exists()==false)
				    {
				            try {
								usernameFile.createNewFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    }
				 
				 if(passwordFile.exists()==false)
				    {
				            try {
								passwordFile.createNewFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    }
				 
				 
				 
				 
				 
				 //Creating Scanner instance to read the user name file 
			        Scanner usernameScnr = null;
					try {
						usernameScnr = new Scanner(usernameFile);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
					
				//While loop cycles through user name file and checks to see if the users registered user name
				//is already in the file, if it is it has the user re-enter a new user name
				 while(usernameScnr.hasNextLine()) {

					 //Sets what's on the current line of the file equal to the string
					 usernameFileLine = usernameScnr.nextLine();
					 	//if users inputed user name equals the one already in the database it turns the user name check false
						if (username.equals(usernameFileLine))
						{
				        	usernameCheck = false;
				        	break;
						}
						//else the users name is authentic and the usernameCheck remains true
						else {
							usernameCheck = true;
						}
						
				 }
					
				 //if the usernameCheck is set to false it triggers this error message and resets the user name and password text fields 
				  if(usernameCheck == false) 
				  {
						JOptionPane.showMessageDialog(null, "Username already exists, please try again."
			        			, "Registration Error", JOptionPane.ERROR_MESSAGE);
			        	//Sets text fields to null
			        	txtUsername.setText(null);
			        	txtPassword.setText(null);

				 }
				  

				 //If Statement verifies that no inputs are blanks and that usernameCheck is true
				  else if (username != null && !username.isEmpty() && password != null && !password.isEmpty() 
					&& firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()
					&& usernameCheck == true)
				 {


				 	//print writer allows you to output to a file
				    PrintWriter out1 = null;
					try {
						out1 = new PrintWriter(new FileWriter(usernameFile, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
		
					//writing user name to username.txt
				    out1.append(username);
				    out1.append(System.getProperty("line.separator"));
				    out1.close();
				    
				    PrintWriter out2 = null;
					try {
						out2 = new PrintWriter(new FileWriter(passwordFile, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//writing password to password.txt					
				    out2.append(password);
				    out2.append(System.getProperty("line.separator"));
				    out2.close();
				    //message dialog that tells user account was successfully created
		        	JOptionPane.showMessageDialog(null, "Account Registration Successful", 
		        			"Account created", 
		        			JOptionPane.INFORMATION_MESSAGE);

		        	
		        	
		        	
					        

						
						
						
		        	//Closes the account registration page
					setVisible(false);
				    //Re-opens the LoginSystem
		        	Login.main(null);
		        	
		        	
		        	

				 }
				 

				 //else if text field is left blank error message is displayed
				  
				 else
			        {
			        	JOptionPane.showMessageDialog(null, "Invalid Registration Details! Please Retry"
			        			, "Registration Error", JOptionPane.ERROR_MESSAGE);
			        	//Sets text fields to null
			        	txtUsername.setText(null);
			        	txtPassword.setText(null);
			        	txtFirstName.setText(null);
			        	txtLastName.setText(null);
			        }

				  
	        	
	        }
	        
	        if (e.getActionCommand().equals("Cancel")) 
	        {
				setVisible(false);
	        	Login.main(null);

	        }
	        
		}
		
	}
	
	
	
	
	public Registration(String title)	{
		super(title);
		
		
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		buildMainFrame();
		addListeners();
		
		setVisible(true);
	}
	
	
	
	
	private void addListeners() {
		btnRegister.addActionListener(new AddBtnListener());
		btnCancel.addActionListener(new AddBtnListener());
	}
	
	
	
	
	private void buildMainFrame() {
		MainPanel.setLayout(null);
		
		lblAccountRegistration.setBounds(57, 11, 282, 54);
		lblAccountRegistration.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MainPanel.add(lblAccountRegistration);
		
		lblFirstName.setBounds(57, 122, 76, 19);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblFirstName);
		
		lblLastName.setBounds(57, 178, 85, 19);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblLastName);
		
		lblUsername.setBounds(57, 239, 85, 19);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblUsername);
		
		lblPassword.setBounds(66, 303, 76, 19);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPassword);
		
		txtLastName.setBounds(146, 179, 142, 20);
		MainPanel.add(txtLastName);
		
		txtFirstName.setBounds(146, 123, 142, 20);
		MainPanel.add(txtFirstName);
		
		txtUsername.setBounds(146, 240, 142, 20);
		MainPanel.add(txtUsername);
		
		txtPassword.setBounds(146, 304, 142, 21);
		MainPanel.add(txtPassword);
		
		btnRegister.setBounds(66, 392, 89, 23);
		MainPanel.add(btnRegister);
		
		btnCancel.setBounds(205, 391, 97, 25);
		MainPanel.add(btnCancel);
		
		
		getContentPane().add(MainPanel);
	}
	
	
	

	public static void main(String[] args)
	{
		Registration gui = new Registration("Register");
	}
	
	
	
	


}
