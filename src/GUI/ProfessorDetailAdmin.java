package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.adminController;
import model.Professor;
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

public class ProfessorDetailAdmin extends JFrame {

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
					ProfessorDetailAdmin frame = new ProfessorDetailAdmin();
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
	public ProfessorDetailAdmin() {
		
		Object[] column = {"ProfID", "Professor Name","Email","address","Gender","NoHp","Office","Department"};
		final Object[] row = new Object[8];//full info for professor
		
		setBackground(new Color(231, 236, 250));
		setTitle("Professor Detail (Admin)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Professor Registration ");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(290, 0, 686, 53);
		contentPane.add(label);
		
		Label label_1 = new Label("ProfID* :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 46, 87, 39);
		contentPane.add(label_1);
		
		Label label_1_1 = new Label("Professor Name* :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 91, 108, 39);
		contentPane.add(label_1_1);
		
		TextField tfPro = new TextField();
		tfPro.setBounds(138, 60, 113, 27);
		contentPane.add(tfPro);
		
		TextField tfName = new TextField();
		tfName.setBounds(138, 103, 113, 27);
		contentPane.add(tfName);
		
		TextField tfEmail = new TextField();
		tfEmail.setBounds(138, 188, 113, 27);
		contentPane.add(tfEmail);
		
		Label label_1_1_2 = new Label("Email* :");
		label_1_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2.setBounds(10, 175, 108, 39);
		contentPane.add(label_1_1_2);
		
		Label label_1_3 = new Label("Gender* :");
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3.setBounds(10, 130, 87, 39);
		contentPane.add(label_1_3);
		
		TextField tfGender = new TextField();
		tfGender.setBounds(138, 144, 113, 27);
		contentPane.add(tfGender);
		
		Label label_1_1_2_1 = new Label("NoHp :");
		label_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2_1.setBounds(10, 354, 108, 39);
		contentPane.add(label_1_1_2_1);
		
		TextField tfNoHp = new TextField();
		tfNoHp.setBounds(138, 367, 113, 27);
		contentPane.add(tfNoHp);
		
		TextField tfAddress = new TextField();
		tfAddress.setBounds(138, 323, 113, 27);
		contentPane.add(tfAddress);
		
		Label label_1_3_2 = new Label("address :");
		label_1_3_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3_2.setBounds(10, 309, 87, 39);
		contentPane.add(label_1_3_2);
		
		TextField tfDepartment = new TextField();
		tfDepartment.setBounds(138, 278, 113, 27);
		contentPane.add(tfDepartment);
		
		Label label_1_1_2_1_1 = new Label("Department* :");
		label_1_1_2_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2_1_1.setBounds(10, 265, 108, 39);
		contentPane.add(label_1_1_2_1_1);
		
		Label label_1_3_2_1 = new Label("Office* :");
		label_1_3_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3_2_1.setBounds(10, 220, 87, 39);
		contentPane.add(label_1_3_2_1);
		
		TextField tfOffice = new TextField();
		tfOffice.setBounds(138, 234, 113, 27);
		contentPane.add(tfOffice);
		
		Button btnRegister = new Button("Register");
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegister.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {						//register btn
				//must input all the * value
				if(tfPro.getText().equals("") || tfName.getText().equals("") || tfEmail.getText().equals("") || tfOffice.getText().equals("")|| tfGender.getText().equals("")|| tfDepartment.getText().equals("")) {
					JOptionPane.showMessageDialog(btnRegister, "Please Fill Complete Information");
				}else {
			        adminController admin = new adminController();
			        Professor professor = new Professor();
			        
			        professor.setProfID(tfPro.getText());
			        professor.setName(tfName.getText());
			        professor.setEmail(tfEmail.getText());	        
			        professor.setAddress(tfAddress.getText());
			        professor.setGender(tfGender.getText());
			        professor.setNoHp(tfNoHp.getText());
			        professor.setOffice(tfOffice.getText());
			        professor.setDepartment(tfDepartment.getText());

			        
			        int status = 0;

				        try {
				            status = admin.insertProfessor(professor);	//save to database
				        } catch (ClassNotFoundException e1) {
				            throw new RuntimeException(e1);
				        } catch (SQLException e1) {
				            JOptionPane.showMessageDialog(btnRegister, "Account Registered");
				        }


			        if(status !=0){
			        	
						row[0] = tfPro.getText();
						row[1] = tfName.getText();
						row[2] = tfEmail.getText();
						row[3] = tfAddress.getText();
						row[4] = tfGender.getText();
						row[5] = tfNoHp.getText();
						row[6] = tfOffice.getText();
						row[7] = tfDepartment.getText();

						model.addRow(row);
						
			        	JOptionPane.showMessageDialog(btnRegister, "Register Successful");
			        }else{
			        	JOptionPane.showMessageDialog(btnRegister, "Fail Register");
			        }
			        
					//clear the text field after register
			        tfPro.setText("");
					tfName.setText("");
					tfOffice.setText("");
					tfDepartment.setText("");
					tfGender.setText("");
					tfEmail.setText("");
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
				if(i>=0||tfPro.getText()!="")
				{
					 adminController admin = new adminController();

				        int status;

				        Professor professor = new Professor();

				        try {
				        	professor=admin.findProfessor(tfPro.getText());		//check professor exist or no
				        } catch (ClassNotFoundException e1) {
				            throw new RuntimeException(e1);
				        } catch (SQLException e1) {
				            throw new RuntimeException(e1);
				        }

				        if(professor !=null) {
				            try {
				                status = admin.deleteProfessor(professor);	//if exist, delete professor
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
				        //clear up text field
				        tfPro.setText("");
						tfName.setText("");
						tfOffice.setText("");
						tfDepartment.setText("");
						tfGender.setText("");
						tfEmail.setText("");
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
				if(i >= 0)		//allow user to select row or key in value to update
				{
					if(tfPro.getText().equals("") || tfName.getText().equals("") || tfEmail.getText().equals("") || tfOffice.getText().equals("")|| tfGender.getText().equals("")|| tfDepartment.getText().equals("")) {
						JOptionPane.showMessageDialog(btnRegister, "Please Fill Complete Information");
					}else {
						 adminController admin = new adminController();
	
					        int status=0;
	
					        Professor professor = new Professor();
	
					        try {
					        	professor=admin.findProfessor(tfPro.getText());	//check exist?
					        } catch (ClassNotFoundException e1) {
					            throw new RuntimeException(e1);
					        } catch (SQLException e1) {
					            throw new RuntimeException(e1);
					        }
	
					        if(professor !=null) {
	
					        	professor.setProfID(tfPro.getText());
						        professor.setName(tfName.getText());
						        professor.setEmail(tfEmail.getText());	        
						        professor.setAddress(tfAddress.getText());
						        professor.setGender(tfGender.getText());
						        professor.setNoHp(tfNoHp.getText());
						        professor.setOffice(tfOffice.getText());
						        professor.setDepartment(tfDepartment.getText());
	
		
					            try {
						                status = admin.updateProfessor(professor);
						            } catch (ClassNotFoundException e1) {
						                throw new RuntimeException(e1);
						            } catch (SQLException e1) {
						                throw new RuntimeException(e1);
						            }
						        
	
					            if(status !=0){
									JOptionPane.showMessageDialog(btnUpdate, "Update Successful");
									ProfessorDetailAdmin ProDetial = new ProfessorDetailAdmin();
									ProDetial.setVisible(true);
					                dispose();
					            }else{
					            	JOptionPane.showMessageDialog(btnUpdate, "Update Fail");
					            }
					        }else {
					        	JOptionPane.showMessageDialog(btnUpdate, "Update Fail\nMID not match");
					        }					
						tfPro.setText("");
						tfName.setText("");
						tfOffice.setText("");
						tfDepartment.setText("");
						tfGender.setText("");
						tfEmail.setText("");
						tfAddress.setText("");
						tfNoHp.setText("");
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
				tfPro.setText(model.getValueAt(i, 0).toString());	//set text field by using mouse click the table
				tfName.setText(model.getValueAt(i, 1).toString());
				tfEmail.setText(model.getValueAt(i, 2).toString());
				tfAddress.setText(model.getValueAt(i, 3).toString());
				tfGender.setText(model.getValueAt(i, 4).toString());
				tfNoHp.setText(model.getValueAt(i, 5).toString());
				tfOffice.setText(model.getValueAt(i, 6).toString());
				tfDepartment.setText(model.getValueAt(i, 7).toString());


			}
		});
		table.setBackground(new Color(255, 255, 255));
		model =new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		adminController admin = new adminController();

        try{
            ArrayList<Professor> ProArr = admin.viewProfessor();
			
            for(Professor professor : ProArr){
				row[0] = professor.getProfID();
				row[1] = professor.getName();
				row[2] = professor.getEmail();
				row[3] = professor.getAddress();
				row[4] = professor.getGender();
				row[5] = professor.getNoHp();
				row[6] = professor.getOffice();
				row[7] = professor.getDepartment();
				
				model.addRow(row);
				
                
            }
        }catch (ClassNotFoundException | SQLException e1){
            e1.printStackTrace();
        }
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {
				
				ProfessorDetailAdmin ProDetial = new ProfessorDetailAdmin();
				ProDetial.setVisible(true);
                dispose();
				
			}
		});
		
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(627, 524, 150, 39);
		contentPane.add(btnNewButton);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								//Export prof
				adminController admin = new adminController();
				
		        File csvFile = new File("Professor_File.csv");	//create file
		        PrintWriter out = null;
				try {
					out = new PrintWriter(csvFile);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		        out.printf("%s,%s,%s,%s,%s,%s,%s,%s\n","ProfID","Professor name","Email","Address","Gender","NoHp","Office","Department");
		        

		        try{
		            ArrayList<Professor> ProArr = admin.viewProfessor();
					
		            for(Professor professor : ProArr){	//export professor info to csv
		            	out.printf("%s, %s, %s,%s,%s,%s,%s,%s\n",professor.getProfID(),professor.getName(),professor.getEmail(),professor.getAddress(),professor.getGender(),professor.getNoHp(),professor.getOffice(),professor.getDepartment()); 
		            }
		            JOptionPane.showMessageDialog(btnExport, "Export \"Professor_File.csv\" Successful");
		        }catch (ClassNotFoundException | SQLException e1){
		            e1.printStackTrace();
		        }
		        out.close();
			}
		});
		btnExport.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExport.setBounds(453, 524, 150, 39);
		contentPane.add(btnExport);
		

	}
	
}
