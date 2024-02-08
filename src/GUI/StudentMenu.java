package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Student;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class StudentMenu extends JFrame {

	private JPanel contentPane;



	public StudentMenu(Student student) {
		setTitle("Student Menu");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label_1 = new Label("MID  :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1.setBounds(26, 389, 189, 39);
		contentPane.add(label_1);
		
		JLabel lbMID = new JLabel("");
		lbMID.setBackground(new Color(255, 255, 255));
		lbMID.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbMID.setBounds(226, 389, 232, 39);
		contentPane.add(lbMID);
		
		Label label_1_1 = new Label("Student Name :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1_1.setBounds(26, 434, 189, 39);
		contentPane.add(label_1_1);
		
		JLabel lbName = new JLabel("");
		lbName.setBackground(new Color(255, 255, 255));
		lbName.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbName.setBounds(226, 439, 232, 39);
		contentPane.add(lbName);
		
		lbMID.setText(student.getMID());
		lbName.setText(student.getName());
		
		
		JButton btnStudent = new JButton("Student detail");		
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                StudentInfo stu = new StudentInfo(student);
                stu.setVisible(true);				//goto StudentInfo
                dispose();
              
			}
		});
		btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStudent.setBounds(582, 121, 246, 54);
		contentPane.add(btnStudent);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);		//goto Login
                dispose();
			}
		});
		
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogout.setBounds(582, 419, 246, 54);
		contentPane.add(btnLogout);
		
		JButton btnRegister = new JButton("course registration info");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseRegistration register = new CourseRegistration(student);
				register.setVisible(true);		//goto 		CourseRegistration
                dispose();
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("~~~~ Welcome ~~~~");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
		lblNewLabel.setBounds(10, 11, 966, 67);
		contentPane.add(lblNewLabel);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBounds(582, 220, 246, 54);
		contentPane.add(btnRegister);
		
		JButton btnFindProfessor = new JButton("Find Professor detail");
		btnFindProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindProf FProf = new FindProf(student);
				FProf.setVisible(true);			//goto  FindProf
                dispose();
			}
		});
		btnFindProfessor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFindProfessor.setBounds(582, 321, 246, 54);
		contentPane.add(btnFindProfessor);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setIcon(new ImageIcon(StudentMenu.class.getResource("/IMAGE/stu.jpg")));
		lblNewLabel_1.setBounds(78, 89, 225, 240);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(257, 389, 201, 84);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(StudentMenu.class.getResource("/IMAGE/menu.jpg")));
		lblNewLabel_2.setBounds(-382, -87, 1400, 707);
		contentPane.add(lblNewLabel_2);
		
		
		
	}
}
