package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.adminController;
import controller.studentController;
import model.Admin;
import model.Student;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfname;
	private JPasswordField pfPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public int type;
	int repeat=3;

	public Login() {

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblLogin.setBounds(575, 91, 367, 52);
		contentPane.add(lblLogin);
		
		JLabel lblUSERNAME = new JLabel("USERNAME : ");
		lblUSERNAME.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUSERNAME.setBounds(42, 256, 128, 30);
		contentPane.add(lblUSERNAME);
		
		tfname = new JTextField();
		tfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfname.setColumns(10);
		tfname.setBounds(172, 256, 284, 30);
		contentPane.add(tfname);
		
		JLabel lblPASSWORD = new JLabel("PASSWORD: ");
		lblPASSWORD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPASSWORD.setBounds(42, 318, 137, 30);
		contentPane.add(lblPASSWORD);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(172, 323, 284, 29);
		contentPane.add(pfPassword);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(repeat >0) {
					//check box: student = 1, admin = 2
					if(type==1) {
						studentController studentCTRL =new studentController();
						Student student=new Student();
						String name = tfname.getText();
		                String password = String.valueOf(pfPassword.getPassword());
	
	
		                try {	//check username and password
		                	student = studentCTRL.getAuthenticatedStudent(name, password);
		                } catch (ClassNotFoundException ex) {
		                    throw new RuntimeException(ex);
		                } catch (SQLException ex) {
		                    throw new RuntimeException(ex);
		                }
	
		                if (student != null) {
		                	
		                    
		                    Component frame = null;
							JOptionPane.showMessageDialog(frame,  "Welcome "+student.getName()+" (Student)");
		                    
							StudentMenu StuMenu = new StudentMenu(student);
							StuMenu.setVisible(true);
		                    dispose();
		                } else {
		                	repeat--;
		                    JOptionPane.showMessageDialog(Login.this,
		                            "User Name or Password Invalid",
		                            "Try again (chance "+repeat+")",
		                            JOptionPane.ERROR_MESSAGE);
		                }
					}else if(type==2) {
						adminController adminCTRL =new adminController();
						Admin admin;
						String name = tfname.getText();
		                String password = String.valueOf(pfPassword.getPassword());
	
	
		                try {
		                    admin = adminCTRL.getAuthenticatedAdmin(name, password);
		                } catch (ClassNotFoundException ex) {
		                    throw new RuntimeException(ex);
		                } catch (SQLException ex) {
		                    throw new RuntimeException(ex);
		                }
	
		                if (admin != null) {
		                    
		                   
		                    Component frame = null;
							JOptionPane.showMessageDialog(frame,  "Welcome "+admin.getName()+" (Admin)");
	
		                    AdminMenu adminMenu = new AdminMenu();
		                    adminMenu.setVisible(true);
		                    dispose();
	
		                } else { //wrong username or password
		                	repeat--;
		                    JOptionPane.showMessageDialog(Login.this,
		                            "User Name or Password Invalid",
		                            "Try again(chance "+repeat +")",
		                            JOptionPane.ERROR_MESSAGE);
		                }
					}else {	//if user no selected check bot, inform the user
						type=3;
						JOptionPane.showMessageDialog(Login.this,
	                            "Plese Select User type",
	                            "Try again",
	                            JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(Login.this, "Unnormal Action Detected!!\nSystem lockdown!");
					dispose();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(227, 401, 128, 52);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/IMAGE/login-icon-3060.png")));
		lblNewLabel.setBounds(638, 147, 275, 276);
		contentPane.add(lblNewLabel);
		
		JCheckBox cbStudent = new JCheckBox("  STUDENT");
		cbStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbStudent.isSelected()) {
					type=1;			//if selected student check box, set type=1
				}
			}
		});
		buttonGroup.add(cbStudent);
		cbStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cbStudent.setBounds(172, 178, 111, 45);
		contentPane.add(cbStudent);
		
		JLabel lblSelect = new JLabel("SELECT :");
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelect.setBounds(42, 185, 128, 30);
		contentPane.add(lblSelect);
		
		JCheckBox cbAdmin = new JCheckBox("  ADMIN");
		cbAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type=2; 		//if selected admin check box, set type=2
			}
		});
		buttonGroup.add(cbAdmin);
		cbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cbAdmin.setBounds(304, 178, 111, 45);
		contentPane.add(cbAdmin);
		
		JLabel lblLogin_1 =new JLabel("WELCOME TO TSLA UNIVERSITY");
		lblLogin_1.setForeground(new Color(0, 128, 255));
		lblLogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblLogin_1.setBounds(0, 14, 986, 52);
		contentPane.add(lblLogin_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/IMAGE/main2.jpg")));
		lblNewLabel_1.setBounds(-237, -97, 1523, 744);
		contentPane.add(lblNewLabel_1);
	}
}
