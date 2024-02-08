package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.adminController;
import model.Course;
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
import javax.swing.ImageIcon;

public class CourseDetailAdmin extends JFrame {

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
					CourseDetailAdmin frame = new CourseDetailAdmin();
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
	public CourseDetailAdmin() {
		
		Object[] column = {"Course ID", "Course Name","Credit Hours"};	//table top row info
		final Object[] row = new Object[3];
		
		setBackground(new Color(231, 236, 250));
		setTitle("Course menu (Admin)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Course Information");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(290, 0, 686, 53);
		contentPane.add(label);
		
		Label label_1 = new Label("Course ID* :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 220, 87, 39);
		contentPane.add(label_1);
		
		Label label_1_1 = new Label("Course Name* :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 265, 108, 39);
		contentPane.add(label_1_1);
		
		TextField tfCourse = new TextField();
		tfCourse.setBounds(138, 234, 113, 27);
		contentPane.add(tfCourse);
		
		TextField tfName = new TextField();
		tfName.setBounds(138, 277, 113, 27);
		contentPane.add(tfName);
		
		Label label_1_2 = new Label("Credit Hours* :");
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_2.setBounds(10, 308, 87, 39);
		contentPane.add(label_1_2);
		
		TextField tfCredit = new TextField();
		tfCredit.setBounds(138, 322, 113, 27);
		contentPane.add(tfCredit);
		
		Button btnAdd = new Button("Add");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {						//add btn
				// if no * data is null, tell user to fill it
				if(tfCourse.getText().equals("") || tfName.getText().equals("") || tfCredit.getText().equals("") ) {
					JOptionPane.showMessageDialog(btnAdd, "Please Fill Complete Information");
				}else {
					 adminController admin = new adminController();
				     Course course = new Course();
	
			        
			        int status2=0;
			        course.setCourseID(tfCourse.getText());
			        course.setCourseName(tfName.getText());	//get data from text field
			        try {
			        	course.setCreditHours(Integer.parseInt(tfCredit.getText()));
				    	status2=1;
			        }catch(Exception e1) {
			        	JOptionPane.showMessageDialog(btnAdd, "Invalid Input");
			        	status2=-1;
			        }
			        int status=0;
			        if(status2 >=0){
				        try {
				            status = admin.insertCourse(course);
				        } catch (ClassNotFoundException e1) {
				            throw new RuntimeException(e1);
				        } catch (SQLException e1) {				//primary key can't duplication,so not allow admin same course ID
				        	JOptionPane.showMessageDialog(btnAdd, "Duplication Course ID!!!!!");
				        }
			        }
			        if(status !=0){
			        	JOptionPane.showMessageDialog(btnAdd, "Add Successful");
			            row[0] = tfCourse.getText();	//save to table
						row[1] = tfName.getText();
						row[2] = tfCredit.getText();

						model.addRow(row);
			        }else{
			        	JOptionPane.showMessageDialog(btnAdd, "Fail");
			        }

			        
					//clear the text field after register
					tfCourse.setText("");
					tfName.setText("");
					tfCredit.setText("");

					
				}
	
			
			}
		});
		btnAdd.setBounds(29, 425, 88, 45);
		contentPane.add(btnAdd);
		
		Button btnDelete = new Button("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 							//Delete btn
				int i = table.getSelectedRow();
				if(i>=0||tfCourse.getText()!="")
				{
					adminController admin = new adminController();

			        int status;
			        Course course = null;

			        try {
			            course=admin.findCourse(tfCourse.getText());
			        } catch (ClassNotFoundException e1) {
			            throw new RuntimeException(e1);
			        } catch (SQLException e1) {
			            throw new RuntimeException(e1);
			        }

			        if(course !=null) {
			            try {
			                status = admin.deleteCourse(course);
			            } catch (ClassNotFoundException e1) {
			                throw new RuntimeException(e1);
			            } catch (SQLException e1) {
			                throw new RuntimeException(e1);
			            }

			            if(status !=0){
			            	JOptionPane.showMessageDialog(btnDelete, "Delete Successful");
			                model.removeRow(i);	//delect success remove that row data
			            }else{
			            	JOptionPane.showMessageDialog(btnDelete, "Delete Fail");
			            }
			        }else {
			        	JOptionPane.showMessageDialog(btnDelete, "Delete Fail\nCourse ID not match!!");
			        }

				    
					tfCourse.setText("");
					tfName.setText("");
					tfCredit.setText("");

					
				}else {
					JOptionPane.showMessageDialog(btnDelete, "Please select a row first");
				}
				
			}
		});
		btnDelete.setBounds(151, 425, 88, 45);
		contentPane.add(btnDelete);
		
		Button btnUpdate = new Button("Update");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 					//update btn
				 adminController admin = new adminController();
				 int i = table.getSelectedRow();

			        Course course = new Course();

			        try {
			            course=admin.findCourse(tfCourse.getText());
			        } catch (ClassNotFoundException e1) {
			            throw new RuntimeException(e1);
			        } catch (SQLException e1) {
			            throw new RuntimeException(e1);
			        }

			        if(course !=null) {
			        	int status2=0;
				        course.setCourseID(tfCourse.getText());
				        course.setCourseName(tfName.getText());
				        try {
				        	course.setCreditHours(Integer.parseInt(tfCredit.getText()));
					    	status2=1;
				        }catch(Exception e1) {
				        	JOptionPane.showMessageDialog(btnAdd, "Invalid Input");
				        	status2=-1;
				        }
				        int status=0;
				        
				        if(status2 >=0){

				            try {
				                status = admin.updateCourse(course);
				            } catch (ClassNotFoundException e1) {
				                throw new RuntimeException(e1);
				            } catch (SQLException e1) {
				                throw new RuntimeException(e1);
				            }
				        }
			            if(status !=0){
			            	JOptionPane.showMessageDialog(btnUpdate, "Update Successful");
			            	model.setValueAt(tfCourse.getText(), i, 0);
							model.setValueAt(tfName.getText(), i, 1);
							model.setValueAt(tfCredit.getText(), i, 2);
			            }else{
			            	JOptionPane.showMessageDialog(btnUpdate, "Update Fail");
			            }
			        }else {
			        	JOptionPane.showMessageDialog(btnUpdate, "Update Fail\nCourse ID not match");
			        }
			        tfCourse.setText("");
					tfName.setText("");
					tfCredit.setText("");

			}
		});
		btnUpdate.setBounds(88, 492, 88, 45);
		contentPane.add(btnUpdate);
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
                AdminMenu adminMenu = new AdminMenu();//go back to admin menu
                adminMenu.setVisible(true);
                dispose();
			}
		});
		ExitButton.setBounds(868, 524, 88, 39);
		contentPane.add(ExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 48, 666, 468);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func
			@Override
			public void mouseClicked(MouseEvent e) {	//mouse read and wrint to text field
				int i = table.getSelectedRow();
				tfCourse.setText(model.getValueAt(i, 0).toString());	
				tfName.setText(model.getValueAt(i, 1).toString());
				tfCredit.setText(model.getValueAt(i, 2).toString());

			}
		});
		table.setBackground(new Color(255, 255, 255));
		model =new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		adminController admin = new adminController();

        try{
            ArrayList<Course> CourseArr = admin.viewCourse();	//list all course
            for(Course course : CourseArr){
				row[0] = course.getCourseID();
				row[1] = course.getCourseName();
				row[2] = course.getCreditHours();

				model.addRow(row);

            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {
				
				CourseDetailAdmin CDetial = new CourseDetailAdmin();
				CDetial.setVisible(true);
                dispose();
				
			}
		});
		
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(614, 524, 150, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CourseDetailAdmin.class.getResource("/IMAGE/course.png")));
		lblNewLabel.setBounds(69, 32, 158, 155);
		contentPane.add(lblNewLabel);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					//export Course
				adminController admin = new adminController();

				 File csvFile = new File("Course_File.csv");
			        PrintWriter out = null;
					try {
						out = new PrintWriter(csvFile);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

			        out.printf("%s, %s, %s\n","Course ID","Course name","Credit Hours"); //write first row
			        
		        try{
		            ArrayList<Course> CourseArr = admin.viewCourse();
		            for(Course course : CourseArr){ //loop from head until tail
		            	out.printf("%s, %s, %s\n",course.getCourseID(),course.getCourseName(),course.getCreditHours());
		            }
		            JOptionPane.showMessageDialog(btnExport, "Export \"Course_File.csv\" Successful");
		        }catch (ClassNotFoundException | SQLException e1){
		            e1.printStackTrace();
		        }
		        out.close();
			}
		});
		btnExport.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExport.setBounds(442, 524, 150, 39);
		contentPane.add(btnExport);
	}
	
}
