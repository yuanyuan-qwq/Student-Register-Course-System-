package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.RegistrationController;
import controller.adminController;
import model.Registration;
import model.Student;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.JButton;

public class StudentDetailAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetailAdmin frame = new StudentDetailAdmin();
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
	public StudentDetailAdmin() {
		
		Object[] column = {"MID", "Name","Password", "Program" ,"Year","Gender","Total Credit","GPA","Email","address","NoHp"};
		final Object[] row = new Object[11];	//student info table
		
		setBackground(new Color(231, 236, 250));
		setTitle("Student Detail (Admin)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Student Registration ");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(290, 0, 686, 53);
		contentPane.add(label);
		
		Label label_1 = new Label("MID* :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 34, 87, 39);
		contentPane.add(label_1);
		
		Label label_1_1 = new Label("Student Name* :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 79, 108, 39);
		contentPane.add(label_1_1);
		
		TextField tfMID = new TextField();
		tfMID.setBounds(138, 48, 113, 27);
		contentPane.add(tfMID);
		
		TextField tfName = new TextField();
		tfName.setBounds(138, 91, 113, 27);
		contentPane.add(tfName);
		
		
		TextField tfProgram = new TextField();
		tfProgram.setBounds(138, 180, 113, 27);
		contentPane.add(tfProgram);
		
		Label label_1_1_1 = new Label("Program* :");
		label_1_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_1.setBounds(10, 167, 108, 39);
		contentPane.add(label_1_1_1);
		
		Label label_1_2 = new Label("Password* :");
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_2.setBounds(10, 122, 87, 39);
		contentPane.add(label_1_2);
		
		TextField tfPassword = new TextField();
		tfPassword.setBounds(138, 136, 113, 27);
		contentPane.add(tfPassword);
		
		TextField tfEmail = new TextField();
		tfEmail.setBounds(138, 308, 113, 27);
		contentPane.add(tfEmail);
		
		Label label_1_1_2 = new Label("Email* :");
		label_1_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2.setBounds(10, 295, 108, 39);
		contentPane.add(label_1_1_2);
		
		Label label_1_3 = new Label("Gender* :");
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3.setBounds(10, 250, 87, 39);
		contentPane.add(label_1_3);
		
		TextField tfGender = new TextField();
		tfGender.setBounds(138, 264, 113, 27);
		contentPane.add(tfGender);
		

		
		TextField tfYear = new TextField();
		tfYear.setBounds(138, 221, 113, 27);
		contentPane.add(tfYear);
		
		Label label_1_3_1 = new Label("year* :");
		label_1_3_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3_1.setBounds(10, 207, 87, 39);
		contentPane.add(label_1_3_1);
		
		Label label_1_1_2_1 = new Label("NoHp :");
		label_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2_1.setBounds(10, 378, 108, 39);
		contentPane.add(label_1_1_2_1);
		
		TextField tfNoHp = new TextField();
		tfNoHp.setBounds(138, 391, 113, 27);
		contentPane.add(tfNoHp);
		
		TextField tfAddress = new TextField();
		tfAddress.setBounds(138, 347, 113, 27);
		contentPane.add(tfAddress);
		
		Label label_1_3_2 = new Label("address :");
		label_1_3_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3_2.setBounds(10, 333, 87, 39);
		contentPane.add(label_1_3_2);
		
		Button btnRegister = new Button("Register");
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegister.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {						//register btn
				//insert new student,
				if(tfMID.getText().equals("") || tfName.getText().equals("") || tfPassword.getText().equals("") || tfProgram.getText().equals("")|| tfYear.getText().equals("")|| tfGender.getText().equals("")|| tfEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(btnRegister, "Please Fill Complete Information");
				}else {
			        adminController admin = new adminController();
			        Student student = new Student();
			        
			        int status2=0;
			        student.setMID(tfMID.getText());
			        student.setName(tfName.getText());
			        student.setPassword(tfPassword.getText());
			        student.setProgram(tfProgram.getText());
			        try {
			        	student.setYear(Integer.parseInt(tfYear.getText()));
			        	status2=1;
			        }catch(Exception e1) {
			        	JOptionPane.showMessageDialog(btnRegister, "Invalid Input");
			        	status2=-1;
			        }
			        student.setGender(tfGender.getText());
			        student.setEmail(tfEmail.getText());
			        
			        student.setAddress(tfAddress.getText());
			        student.setNoHp(tfNoHp.getText());
			        
			        int status = 0;
			        if(status2 >=0){
				        try {
				            status = admin.insertStudent(student);
				        } catch (ClassNotFoundException e1) {
				            throw new RuntimeException(e1);
				        } catch (SQLException e1) {		//if same MID, MID is unique so tell user account already exist
				            JOptionPane.showMessageDialog(btnRegister, "Account Registered");
				        }
			        }

			        if(status !=0){
			        	
						row[0] = tfMID.getText();
						row[1] = tfName.getText();
						row[2] = tfPassword.getText();
						row[3] = tfProgram.getText();
						row[4] = tfYear.getText();
						row[5] = tfGender.getText();
						row[6] = null;					//new student no GPA and credit Hours, need student register by him self
						row[7] = null;
						row[8] = tfEmail.getText();
						row[9] = tfAddress.getText();
						row[10] = tfNoHp.getText();
						model.addRow(row);
						
			        	JOptionPane.showMessageDialog(btnRegister, "Register Successful");
			        }else{
			        	JOptionPane.showMessageDialog(btnRegister, "Fail Register");
			        }
			        
					//clear the text field after register
					tfMID.setText("");
					tfName.setText("");
					tfPassword.setText("");
					tfProgram.setText("");
					tfGender.setText("");
					tfEmail.setText("");
					tfYear.setText("");
					tfAddress.setText("");
					tfNoHp.setText("");
					
				}
	
			
		

			}
		});
		btnRegister.setBounds(29, 435, 88, 45);
		contentPane.add(btnRegister);
		
		Button btnDelete = new Button("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 							//Delete btn
				int i = table.getSelectedRow();
				if(i>=0||tfMID.getText()!="")
				{
					 adminController admin = new adminController();

				        int status;

				        Student student = new Student();

				        try {
				            student=admin.findStudent(tfMID.getText());
				        } catch (ClassNotFoundException e1) {
				            throw new RuntimeException(e1);
				        } catch (SQLException e1) {
				            throw new RuntimeException(e1);
				        }

				        if(student !=null) {
				            try {
				                status = admin.deleteStudent(student);
				            } catch (ClassNotFoundException e1) {
				                throw new RuntimeException(e1);
				            } catch (SQLException e1) {
				                throw new RuntimeException(e1);
				            }

				            if(status !=0){
				                
				                JOptionPane.showMessageDialog(btnDelete, "Delete Successful");
				                model.removeRow(i);
				            }else{
				                
				                JOptionPane.showMessageDialog(btnDelete, "Delete Fail");
				            }
				        }else {
				            
				            JOptionPane.showMessageDialog(btnDelete, "Delete Fail\nMID not match!!");
				        }

				    
					tfMID.setText("");
					tfName.setText("");
					tfPassword.setText("");
					tfProgram.setText("");
					tfGender.setText("");
					tfEmail.setText("");
					tfYear.setText("");
					tfAddress.setText("");
					tfNoHp.setText("");
					
				}else {
					JOptionPane.showMessageDialog(btnDelete, "Please select a row first");
				}
				
			}
		});
		btnDelete.setBounds(151, 435, 88, 45);
		contentPane.add(btnDelete);
		
		Button btnUpdate = new Button("Update");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 					//update btn
				int i = table.getSelectedRow();
				if(i >= 0)
				{
					if(tfMID.getText().equals("") || tfName.getText().equals("") || tfPassword.getText().equals("") || tfProgram.getText().equals("")|| tfYear.getText().equals("")|| tfGender.getText().equals("")|| tfEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(btnUpdate, "Please Fill Complete Information");
					}else {
						 adminController admin = new adminController();
	
					        int status=0;
	
					        Student student = new Student();
	
					        try {
					            student=admin.findStudent(tfMID.getText());
					        } catch (ClassNotFoundException e1) {
					            throw new RuntimeException(e1);
					        } catch (SQLException e1) {
					            throw new RuntimeException(e1);
					        }
	
					        if(student !=null) {
						        int status2=0;
						        student.setMID(tfMID.getText());
						        student.setName(tfName.getText());
						        student.setPassword(tfPassword.getText());
						        student.setProgram(tfProgram.getText());
						        try {
						        	student.setYear(Integer.parseInt(tfYear.getText()));
						        	status2=1;
						        }catch(Exception e1) {
						        	JOptionPane.showMessageDialog(btnRegister, "Invalid Input");
						        	status2=-1;
						        }
						        student.setGender(tfGender.getText());
						        student.setEmail(tfEmail.getText());
						        
						        student.setAddress(tfAddress.getText());
						        student.setNoHp(tfNoHp.getText());
	
						        if(status2>=0) {
					            try {
						                status = admin.updateStudent(student);
						            } catch (ClassNotFoundException e1) {
						                throw new RuntimeException(e1);
						            } catch (SQLException e1) {
						                throw new RuntimeException(e1);
						            }
						        }
	
					            if(status !=0){
									JOptionPane.showMessageDialog(btnUpdate, "Update Successful");
									model.setValueAt(tfMID.getText(), i, 0);
									model.setValueAt(tfName.getText(), i, 1);
									model.setValueAt(tfPassword.getText(), i, 2);
									model.setValueAt(tfProgram.getText(), i, 3);
									model.setValueAt(tfYear.getText(), i, 4);
									model.setValueAt(tfGender.getText(), i, 5);
	
									model.setValueAt(tfEmail.getText(), i, 8);
									model.setValueAt(tfAddress.getText(), i, 9);
									model.setValueAt(tfNoHp.getText(), i, 10);
									
									tfMID.setText("");
									tfName.setText("");
									tfPassword.setText("");
									tfProgram.setText("");
									tfGender.setText("");
									tfEmail.setText("");
									tfYear.setText("");
									tfAddress.setText("");
									tfNoHp.setText("");
					            }else{
					            	JOptionPane.showMessageDialog(btnUpdate, "Update Fail");
					            }
					        
				        }else {
				        	JOptionPane.showMessageDialog(btnUpdate, "Update Fail\nMID not match");
				        }

		
					}

				}else {
					JOptionPane.showMessageDialog(btnUpdate, "Please select a row first");
				}

			}
		});
		btnUpdate.setBounds(88, 508, 88, 45);
		contentPane.add(btnUpdate);
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.setVisible(true);
                dispose();
			}
		});
		ExitButton.setBounds(868, 524, 88, 39);
		contentPane.add(ExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 59, 666, 446);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfMID.setText(model.getValueAt(i, 0).toString());
				tfName.setText(model.getValueAt(i, 1).toString());
				tfPassword.setText(model.getValueAt(i, 2).toString());
				tfProgram.setText(model.getValueAt(i, 3).toString());
				tfYear.setText(model.getValueAt(i, 4).toString());
				tfGender.setText(model.getValueAt(i, 5).toString());
				tfEmail.setText(model.getValueAt(i, 8).toString());
				if(tfAddress.getText()!=null) {
					tfAddress.setText(model.getValueAt(i, 9).toString());
				}
				if(tfNoHp.getText()!=null) {
					tfNoHp.setText(model.getValueAt(i, 10).toString());
				}
			}
		});
		table.setBackground(new Color(255, 255, 255));
		model =new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		adminController admin = new adminController();

        try{
            ArrayList<Student> StuArr = admin.viewStudent();
			
            for(Student student : StuArr){
				row[0] = student.getMID();
				row[1] = student.getName();
				row[2] = student.getPassword();
				row[3] = student.getProgram();
				row[4] = student.getYear();
				row[5] = student.getGender();
				row[6] = student.getTotalCredit();
				row[7] = student.getCGPA();
				row[8] = student.getEmail();
				row[9] = student.getAddress();
				row[10] = student.getNoHp();
				model.addRow(row);
				
                
            }
        }catch (ClassNotFoundException | SQLException e1){
            e1.printStackTrace();
        }
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {//load btn
				//这里load 回去 本来的拿了的course 从database 拿data
				StudentDetailAdmin studentDetial = new StudentDetailAdmin();
				studentDetial.setVisible(true);
                dispose();
				
			}
		});
		
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(622, 524, 150, 39);
		contentPane.add(btnNewButton);
		
		JButton btnExport = new JButton("Export");														//export student
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminController admin = new adminController();    

		        File csvFile = new File("Student_File.csv");
		        PrintWriter out = null;
				try {
					out = new PrintWriter(csvFile);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		        out.printf("%s, %s, %s, %s,%s,%s,%s,%s,%s,%s,%s\n","MID","Student name","Student Password","Program","Year","gender","Total Credit","GPA","Email","Address","NoHp");
		        
		        try{
		            ArrayList<Student> StuArr = admin.viewStudent();
					
		            for(Student student : StuArr){// export all student info to csv file
		            	out.printf("%s, %s, %s, %s,%d,%s,%d,%.2f,%s, %s, %s\n",student.getMID(),student.getName(),student.getPassword(),student.getProgram(),student.getYear(),student.getGender(),student.getTotalCredit(),student.getCGPA(),student.getEmail(),student.getAddress(),student.getNoHp());
		            }
		            JOptionPane.showMessageDialog(btnExport, "Export \"Student_File.csv\" Successful");
		        }catch (ClassNotFoundException | SQLException e1){
		            e1.printStackTrace();
		        }   
		       
		        out.close();
		    
			}
		});
		btnExport.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExport.setBounds(447, 524, 150, 39);
		contentPane.add(btnExport);
	}
}
