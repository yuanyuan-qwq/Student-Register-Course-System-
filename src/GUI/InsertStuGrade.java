package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.RegistrationController;
import controller.adminController;
import controller.studentController;
import model.Admin;
import model.Course;
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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertStuGrade extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model,model2;
	private JTable table2;


	public InsertStuGrade() {
		
		Object[] column1 = {"MID", "Name","Password", "Program" ,"Year","Gender","Total Credit","GPA","Email","address","NoHp"};
		final Object[] row1 = new Object[11];	//table1
		
		Object[] column2 = {"MID", "Course ID","Course Name", "grade" ,"Credit Hours"};
		final Object[] row2 = new Object[5];	//table2
		
		setBackground(new Color(231, 236, 250));
		setTitle("Grade Key In (Admin)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Grade Key In");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(10, 0, 966, 53);
		contentPane.add(label);
		
		Label label_1 = new Label("MID :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 86, 87, 39);
		contentPane.add(label_1);
		
		Label label_1_1 = new Label("Student Name :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 131, 108, 39);
		contentPane.add(label_1_1);
		
		Label label_1_1_1 = new Label("Program :");
		label_1_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_1.setBounds(10, 174, 108, 39);
		contentPane.add(label_1_1_1);
		
		JLabel lbProgram = new JLabel("");
		lbProgram.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbProgram.setBounds(138, 186, 113, 27);
		contentPane.add(lbProgram);
		

		
		Label label_2 = new Label("Course Registration ");
		label_2.setFont(new Font("Dialog", Font.BOLD, 20));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(290, 267, 686, 53);
		contentPane.add(label_2);
		
		Label label_2_1 = new Label("Student Information");
		label_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_2_1.setAlignment(Label.CENTER);
		label_2_1.setBounds(300, 52, 686, 53);
		contentPane.add(label_2_1);
		
		JLabel lbName = new JLabel((String) null);
		lbName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbName.setBounds(138, 138, 113, 27);
		contentPane.add(lbName);
		
		Label label_1_2 = new Label("Course ID :");
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_2.setBounds(10, 346, 87, 39);
		contentPane.add(label_1_2);
		
		TextField tfCourseID = new TextField();
		tfCourseID.setBounds(95, 358, 113, 27);
		contentPane.add(tfCourseID);
		
		TextField tfMID = new TextField();
		tfMID.setBounds(126, 98, 113, 27);
		contentPane.add(tfMID);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "A", "B", "C", "D", "E"})); //set choiceBox
		comboBox.setToolTipText("");
		comboBox.setBounds(95, 402, 113, 22);
		contentPane.add(comboBox);
		
		Label label_1_2_1 = new Label("Grade :");
		label_1_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_2_1.setBounds(10, 391, 87, 39);
		contentPane.add(label_1_2_1);
		
		Student Targetstudent = new Student();
		RegistrationController RegistrationCTRL = new RegistrationController();
		
		Button btnKeyIN = new Button("Key In Grade");
		btnKeyIN.addActionListener(new ActionListener() {							//key IN btn
			public void actionPerformed(ActionEvent e) {
				int i = table2.getSelectedRow();

				String selectedgrade = comboBox.getSelectedItem().toString();
				
				RegistrationController registrationCTRL = new RegistrationController();

		        int status;
		        if(selectedgrade!="") {
			        try {
			            status = registrationCTRL.keyInMark(Targetstudent,tfCourseID.getText(),selectedgrade);
			            ArrayList<Registration> RegistrationArr = RegistrationCTRL.viewRegisterCourse(Targetstudent);
			        } catch (ClassNotFoundException e1) {
			            throw new RuntimeException(e1);
			        } catch (SQLException e1) {
			            throw new RuntimeException(e1);
			        }
	
			        if(status !=0){
			        	JOptionPane.showMessageDialog(btnKeyIN, "Successful");
			        	model2.setValueAt(selectedgrade,i, 3);
			        }else{
			        	JOptionPane.showMessageDialog(btnKeyIN, "Key In Fail\nINVALID INPUT!!");
			        }
		        }else {
		        	JOptionPane.showMessageDialog(btnKeyIN, "Key In Fail\nPlease Select Grade");
		        }
		    
				
			}
		});
		btnKeyIN.setFont(new Font("Dialog", Font.BOLD, 12));
		btnKeyIN.setBounds(76, 476, 150, 39);
		contentPane.add(btnKeyIN);
				
	
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
                AdminMenu AdminMenu = new AdminMenu();
                AdminMenu.setVisible(true);
                dispose();
			}
		});
		ExitButton.setBounds(833, 511, 123, 39);
		contentPane.add(ExitButton);
		
			
		JScrollPane CourseScrollPane = new JScrollPane();						//table 1
		CourseScrollPane.setBounds(290, 111, 666, 162);
		contentPane.add(CourseScrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func for table1
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();

				tfMID.setText(model.getValueAt(i, 0).toString());	//SET data from table to text field and label
				lbName.setText(model.getValueAt(i, 1).toString());
				lbProgram.setText(model.getValueAt(i, 3).toString());
		
			}
			
		});
		table.setBackground(new Color(255, 255, 255));
		model =new DefaultTableModel();
		model.setColumnIdentifiers(column1);
		table.setModel(model);
		CourseScrollPane.setViewportView(table);
		

		adminController admin = new adminController();

        try{
            ArrayList<Student> StuArr = admin.viewStudent();
			
            for(Student student : StuArr){	//load info to table 1
				row1[0] = student.getMID();
				row1[1] = student.getName();
				row1[2] = student.getPassword();
				row1[3] = student.getProgram();
				row1[4] = student.getYear();
				row1[5] = student.getGender();
				row1[6] = student.getTotalCredit();
				row1[7] = student.getCGPA();
				row1[8] = student.getEmail();
				row1[9] = student.getAddress();
				row1[10] = student.getNoHp();
				model.addRow(row1);
				
                
            }
        }catch (ClassNotFoundException | SQLException e1){
            e1.printStackTrace();
        }
			
		JScrollPane RegistrationScrollPane = new JScrollPane();						//table 2
		RegistrationScrollPane.setBounds(290, 326, 666, 179);
		contentPane.add(RegistrationScrollPane);
		
		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {								//mouse func
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table2.getSelectedRow();
				tfCourseID.setText(model2.getValueAt(i, 1).toString());
			}
			
		});
		table2.setBackground(new Color(255, 255, 255));
		model2 =new DefaultTableModel();
		model2.setColumnIdentifiers(column2);
		table2.setModel(model2);
		RegistrationScrollPane.setViewportView(table2);
		
		
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {							//check btn
			public void actionPerformed(ActionEvent e) {	
                
                Registration registrationNULL = null;
                Targetstudent.setMID(tfMID.getText());

                
                try{
                    ArrayList<Registration> RegistrationArr = RegistrationCTRL.viewRegisterCourse(Targetstudent);

                    for(Registration registration : RegistrationArr){
                        registrationNULL = new Registration();
     
                        
	        				row2[0] = registration.getMID();
	        				row2[1] = registration.getC_courseID();
	        				row2[2] = registration.getCourseName();	// load course register info to table2 base on student MID
	        				row2[3] = registration.getGrade();
	        				row2[4] = registration.getCreditHours();	
	
	        				model2.addRow(row2);                      
                        
                    }

                    if(registrationNULL ==null) {
                    	JOptionPane.showMessageDialog(btnCheck, "Student not Register Course!");
                    }else {
        		
                    }
                }catch (ClassNotFoundException | SQLException e1){
                	 JOptionPane.showMessageDialog(btnCheck, "Invalid Student MID!");
                }
						      			
			}
		});

		btnCheck.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCheck.setBounds(76, 249, 150, 39);
		contentPane.add(btnCheck);
		
		Button btnRefresh = new Button("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				      	//reflesh btn

				
				InsertStuGrade INGrad = new InsertStuGrade();
				INGrad.setVisible(true);
				dispose();
				
				
			}
				
		});
		btnRefresh.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRefresh.setBounds(579, 511, 123, 39);
		contentPane.add(btnRefresh);
		
		
		
	
		


		
		

	}
}
