package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.adminController;
import controller.studentController;
import model.Admin;
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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StudentInfo extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;


	/**
	 * Create the frame.
	 * @param mID 
	 */
	public StudentInfo(Student student) {
		
		Object[] column = {"MID", "Name","Password", "Program" ,"Year","Gender","Total Credit","GPA","Email","address","NoHp"};
		final Object[] row = new Object[11]; //for student info table
		
		setBackground(new Color(231, 236, 250));
		setTitle("Student Information (Student)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Student Information ");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(290, 0, 686, 53);
		contentPane.add(label);
		
		Label label_1 = new Label("MID :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 34, 87, 39);
		contentPane.add(label_1);
		
		Label label_1_1 = new Label("Student Name* :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 79, 108, 39);
		contentPane.add(label_1_1);
		
		TextField tfName = new TextField();
		tfName.setBounds(138, 91, 113, 27);
		contentPane.add(tfName);
		
		Label label_1_1_1 = new Label("Program :");
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
		
		JLabel lbMID = new JLabel("");
		lbMID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMID.setBounds(138, 47, 113, 27);
		contentPane.add(lbMID);
		
		JLabel lbProgram = new JLabel("");
		lbProgram.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbProgram.setBounds(138, 179, 113, 27);
		contentPane.add(lbProgram);
				

		studentController studentCTRL = new studentController();
		
		lbMID.setText(student.getMID());
		lbProgram.setText(student.getProgram());
		
			
		Button btnUpdate = new Button("Update");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 					//update btn
				int i = table.getSelectedRow();
				if(i >= 0)
				{
					if(tfName.getText().equals("") || tfPassword.getText().equals("") || tfYear.getText().equals("")|| tfGender.getText().equals("")|| tfEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(btnUpdate, "Please Fill Complete Information");
					}else {
				        int status2=0;

				        student.setName(tfName.getText());
				        student.setPassword(tfPassword.getText());

				        try {
				        	student.setYear(Integer.parseInt(tfYear.getText()));	//avoid user input invalid value
				        	status2=1;
				        }catch(Exception e1) {
				        	JOptionPane.showMessageDialog(btnUpdate, "Invalid Input");
				        	status2=-1;
				        }
				        student.setGender(tfGender.getText());
				        student.setEmail(tfEmail.getText());
				        
				        student.setAddress(tfAddress.getText());
				        student.setNoHp(tfNoHp.getText());

				        int status = 0;
				        if(status2>=0) {
				            try {
					                status = studentCTRL.updateStudent(student);	
					            } catch (ClassNotFoundException e1) {
					                throw new RuntimeException(e1);
					            } catch (SQLException e1) {
					                throw new RuntimeException(e1);
					            }
				        }

			            if(status !=0){
							JOptionPane.showMessageDialog(btnUpdate, "Update Successful");

							model.setValueAt(tfName.getText(), i, 1);
							model.setValueAt(tfPassword.getText(), i, 2);

							model.setValueAt(tfYear.getText(), i, 4);
							model.setValueAt(tfGender.getText(), i, 5);

							model.setValueAt(tfEmail.getText(), i, 8);
							model.setValueAt(tfAddress.getText(), i, 9);
							model.setValueAt(tfNoHp.getText(), i, 10);
							
							tfName.setText("");
							tfPassword.setText("");
			
							tfGender.setText("");
							tfEmail.setText("");
							tfYear.setText("");
							tfAddress.setText("");
							tfNoHp.setText("");
			            }else{
			            	JOptionPane.showMessageDialog(btnUpdate, "Update Fail");
			            }
			       

					}
					
	
				}else {
					JOptionPane.showMessageDialog(btnUpdate, "Please select a row first");
				}

		      			        
			}
			
		});
		btnUpdate.setBounds(95, 490, 88, 45);
		contentPane.add(btnUpdate);
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
                StudentMenu stuMenu = new StudentMenu(student);
                stuMenu.setVisible(true);
                dispose();
			}
		});
		ExitButton.setBounds(868, 516, 88, 34);
		contentPane.add(ExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 59, 666, 441);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				tfName.setText(model.getValueAt(i, 1).toString());
				tfPassword.setText(model.getValueAt(i, 2).toString());

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
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {
				
				StudentInfo stu = new StudentInfo(student);
                stu.setVisible(true);
                dispose();
											      			
			}
		});

		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(542, 511, 150, 39);
		contentPane.add(btnNewButton);
	

	}
}
