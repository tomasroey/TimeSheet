package pfgmoney.timesheet.employee;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Color;

public class LogOn2 {
	
	public PrintStream originalOut = System.out;
	public PrintStream logFile = System.out;
	public String forPassCompare;
	public static String forUserCompare;
	

	private JFrame frame;
	private JTextField userNameInput;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
					//FOR WRITING LOG FILES
//					PrintWriter writer = new PrintWriter("C:\\Users\\Azima\\Desktop\\log.txt", "UTF-8");
//					writer.println("The first line");
//					writer.println("The second line");
//					writer.close();
				

					LogOn2 window = new LogOn2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public LogOn2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 292, 282);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblUsernamepasswordInvalid = new JLabel("UserName/Password invalid");
		lblUsernamepasswordInvalid.setVisible(false);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 392, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 572, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		userNameInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, userNameInput, 83, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, userNameInput, -39, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(userNameInput);
		userNameInput.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 3, SpringLayout.NORTH, userNameInput);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -25, SpringLayout.WEST, userNameInput);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 20, SpringLayout.SOUTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogIn = new JButton("Log In");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogIn, 86, SpringLayout.SOUTH, userNameInput);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(userNameInput.getText().equalsIgnoreCase(null) 
						&& passwordField.getText().equals(null))){
				forPassCompare = passwordField.getText();
				forUserCompare = userNameInput.getText();
				System.out.println(forPassCompare+"   "+ forUserCompare);
				
				//READ FROM TEXT FILE
				BufferedReader in = null;
		        FileReader fr = null;
		        List<String> userlist = new ArrayList<String>();
		        List<String> passwordlist = new ArrayList<String>();

		        try {
		            fr = new FileReader("C:\\Users\\Azima\\Desktop\\passwords.txt");
		            in = new BufferedReader(fr);
		            
		            String str;
		            int i=0;
		            while ((str = in.readLine()) != null) {
		            	if(i==0 || i%2==0){
		            	 	    userlist.add(str);
		            	}
		            	else{
		            		passwordlist.add(str);
		            	}
		            	   i++; 
		            	
		            }
		            in.close();
					 fr.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
		       for (int i = 0; i < userlist.size(); i++) {
		    	   String u=userlist.get(i).replaceAll("\\s","");
		    	   String p=passwordlist.get(i).replaceAll("\\s","");
				if(u.equalsIgnoreCase(forUserCompare) && 
						p.equalsIgnoreCase(forPassCompare)){
					
					UiApp2 s = null;
					s.main(null); 
					frame.dispose();
					
					//LOG UPDATION
				
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		            	Calendar cal = Calendar.getInstance();
		            	System.out.println(dateFormat.format(cal.getTime()));
		            	
		            	String textToAppend = dateFormat.format(cal.getTime())+"  Logged in as "+u;
		                
		                BufferedWriter writer;
						try {
							writer = new BufferedWriter(new FileWriter
									("C:\\Users\\Azima\\Desktop\\log.txt", true));
						    writer.newLine(); 
							writer.write(textToAppend);
							writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                
					
				}
				else{
					lblUsernamepasswordInvalid.setVisible(true);
				}
			}
		    }
			else{
				lblUsernamepasswordInvalid.setVisible(true);
			}
		}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnLogIn, 0, SpringLayout.WEST, userNameInput);
		springLayout.putConstraint(SpringLayout.EAST, btnLogIn, 67, SpringLayout.WEST, userNameInput);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnExit = new JButton("Exit");
		springLayout.putConstraint(SpringLayout.NORTH, btnExit, 86, SpringLayout.SOUTH, userNameInput);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnExit, 0, SpringLayout.EAST, userNameInput);
		frame.getContentPane().add(btnExit);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 14, SpringLayout.SOUTH, userNameInput);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 28, SpringLayout.EAST, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, userNameInput);
		frame.getContentPane().add(passwordField);
		
		
		lblUsernamepasswordInvalid.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.WEST, lblUsernamepasswordInvalid, 0, SpringLayout.WEST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsernamepasswordInvalid, -11, SpringLayout.NORTH, userNameInput);
		frame.getContentPane().add(lblUsernamepasswordInvalid);
		
	}
}
