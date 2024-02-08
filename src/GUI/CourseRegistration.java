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

public class CourseRegistration extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model,model2;
	private JTable table2;



	public CourseRegistration(Student student) {
		
		Object[] column1 = {"Course ID", "Course Name","Credit Hours"};	//for table course
		final Object[] row1 = new Object[3];
		
		Object[] column2 = {"MID", "Course ID","Course Name", "grade" ,"Credit Hours"}; // for table registration
		final Object[] row2 = new Object[5];
		
		setBackground(new Color(231, 236, 250));
		setTitle("Student Course Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Course Registration ");
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
		
		JLabel lbMID = new JLabel("");
		lbMID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMID.setBounds(138, 94, 113, 27);
		contentPane.add(lbMID);
		
		JLabel lbProgram = new JLabel("");
		lbProgram.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbProgram.setBounds(138, 186, 113, 27);
		contentPane.add(lbProgram);
		

		
		Label label_2 = new Label("Course Registration ");
		label_2.setFont(new Font("Dialog", Font.BOLD, 20));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(290, 267, 686, 53);
		contentPane.add(label_2);
		
		Label label_2_1 = new Label("Course");
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
		label_1_2.setBounds(29, 372, 87, 39);
		contentPane.add(label_1_2);
		
		TextField tfCourseID = new TextField();
		tfCourseID.setBounds(114, 384, 113, 27);
		contentPane.add(tfCourseID);
		
		Label label_1_1_1_1 = new Label("GPA :");
		label_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_1_1.setBounds(10, 267, 108, 27);
		contentPane.add(label_1_1_1_1);
		
		JLabel lbGPA = new JLabel((String) null);
		lbGPA.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbGPA.setBounds(138, 267, 113, 27);
		contentPane.add(lbGPA);
		
		Label label_1_1_2 = new Label("Total Credit Hourse :");
		label_1_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2.setBounds(5, 222, 135, 39);
		contentPane.add(label_1_1_2);
		
		JLabel lbCredit = new JLabel((String) null);
		lbCredit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbCredit.setBounds(161, 229, 113, 27);
		contentPane.add(lbCredit);
		
		Button btnRegister = new Button("Register");
		btnRegister.addActionListener(new ActionListener() {							//register btn
			public void actionPerformed(ActionEvent e) {

				RegistrationController registrationCTRL = new RegistrationController();

			        int status =0;
					Course course=null;
	
			        try {
			            course=registrationCTRL.findCourse(tfCourseID.getText());	//check course ID exist?
			        } catch (ClassNotFoundException e1) {
			            throw new RuntimeException(e1);
			        } catch (SQLException e1) {
			            throw new RuntimeException(e1);
			        }
	
			        if(course !=null) {
	
			            try {
			                status = registrationCTRL.RegisterCourse(student,course); // register course
			            } catch (ClassNotFoundException e1) {
			                throw new RuntimeException(e1);
			            } catch (Exception e1) {		// if same Course ID 
			            	JOptionPane.showMessageDialog(btnRegister, "Duplication registration!!!!!");			                
			            }
	
			            if(status !=0){
			            	JOptionPane.showMessageDialog(btnRegister, "Register Success");
							CourseRegistration stu = new CourseRegistration(student);
			                stu.setVisible(true);	//refresh this page
			                dispose();
			            }else{
			            	JOptionPane.showMessageDialog(btnRegister, "Register Fail");
			            }
			        }else {
			        	JOptionPane.showMessageDialog(btnRegister, "Register Fail\nCourse not Found");
			        }
			        					

				tfCourseID.setText("");
			}
		});
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegister.setBounds(29, 458, 88, 45);
		contentPane.add(btnRegister);
		
		Button btnDrop = new Button("Drop");
		btnDrop.addActionListener(new ActionListener() {							//drop btn
			public void actionPerformed(ActionEvent e) {

					 RegistrationController registrationCTRL = new RegistrationController();

				        int status;

				            try {
				                status = registrationCTRL.DropCourse(student,tfCourseID.getText());
				            } catch (ClassNotFoundException e1) {
				                throw new RuntimeException(e1);
				            } catch (SQLException e1) {
				                throw new RuntimeException(e1);
				            }

				            if(status !=0){
				            	JOptionPane.showMessageDialog(btnDrop, "Withdraw Successful");
								CourseRegistration stu = new CourseRegistration(student);
				                stu.setVisible(true); 	//refresh 
				                dispose();
				            }else{
				            	JOptionPane.showMessageDialog(btnDrop, "Withdraw Fail\nCourse ID not Found!!");
				            }

				tfCourseID.setText("");
			}
		});
		btnDrop.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDrop.setBounds(151, 458, 88, 45);
		contentPane.add(btnDrop);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationController registrationCTRL = new RegistrationController();
		        Registration registrationNULL = null;        

		        File csvFile = new File("RegistrationFile.csv");//create file
		        PrintWriter out = null;
				try {
					out = new PrintWriter(csvFile);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		        out.printf("%s, %s, %s, %s,%s\n","MID","course ID","course Name","grade","credit hours");
		          
		        try{
		            ArrayList<Registration> RegistrationArr = registrationCTRL.viewRegisterCourse(student);

		            for(Registration registration : RegistrationArr){
		                registrationNULL = new Registration();
		                //write to csv
		                out.printf("%s, %s, %s, %s, %d\n",registration.getMID(),registration.getC_courseID(),registration.getCourseName(),registration.getGrade(),registration.getCreditHours());
		                
		            }
		            out.printf("%s, %f\n","GPA: ",student.getCGPA());
		            out.printf("%s, %d\n","Total Credit hours: ",student.getTotalCredit()); 
		            JOptionPane.showMessageDialog(btnDrop, "Export CSV Successful");

		            if(registrationNULL ==null) {
		                System.out.println("404 Not Found");
		            }
		        }catch (ClassNotFoundException | SQLException e1){
		            e1.printStackTrace();
		        }
		        out.close();
		    }
			
		});
		btnExport.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExport.setBounds(462, 513, 150, 39);
		contentPane.add(btnExport);
				
	
		studentController studentCTRL = new studentController();
		lbMID.setText(student.getMID());
		lbProgram.setText(student.getProgram());
		lbName.setText(student.getName());
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
                StudentMenu stuMenu = new StudentMenu(student);
                stuMenu.setVisible(true);	//goback to student menu
                dispose();
			}
		});
		ExitButton.setBounds(868, 511, 88, 41);
		contentPane.add(ExitButton);
		
			
		JScrollPane CourseScrollPane = new JScrollPane();						//table 1
		CourseScrollPane.setBounds(290, 111, 666, 162);
		contentPane.add(CourseScrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func for table1
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();

				tfCourseID.setText(model.getValueAt(i, 0).toString());
		
			}
			
		});
		table.setBackground(new Color(255, 255, 255));
		model =new DefaultTableModel();
		model.setColumnIdentifiers(column1);
		table.setModel(model);
		CourseScrollPane.setViewportView(table);
		

		RegistrationController RegisterCTRL = new RegistrationController();
		try {
			for(Course course : RegisterCTRL.viewCourse()) {//set course info to table 1 
				row1[0] = course.getCourseID();
				row1[1] = course.getCourseName();
				row1[2] = course.getCreditHours();	
				model.addRow(row1);	
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JScrollPane RegistrationScrollPane = new JScrollPane();						//table 2
		RegistrationScrollPane.setBounds(290, 326, 666, 179);
		contentPane.add(RegistrationScrollPane);
		
		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {								//mouse func table 2
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
		
		Registration registrationNULL = null;

        try{
            ArrayList<Registration> RegistrationArr = RegisterCTRL.viewRegisterCourse(student);

            for(Registration registration : RegistrationArr){	//load register course to table 2
                registrationNULL = new Registration();
                
				row2[0] = registration.getMID();
				row2[1] = registration.getC_courseID();
				row2[2] = registration.getCourseName();	
				row2[3] = registration.getGrade();
				row2[4] = registration.getCreditHours();	

				model2.addRow(row2);
               
                
            }

            if(registrationNULL ==null) {
                System.out.println("404 Not Found");
            }else {
            	
        		lbGPA.setText(String.valueOf(student.getCGPA()));
        		lbCredit.setText(String.valueOf(student.getTotalCredit()));
        		
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {
			
				CourseRegistration stu = new CourseRegistration(student);
                stu.setVisible(true);
                dispose();
											      			
			}
		});

		btnRefresh.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRefresh.setBounds(631, 513, 150, 39);
		contentPane.add(btnRefresh);
		

		
		

	}
}
