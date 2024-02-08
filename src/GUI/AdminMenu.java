package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setTitle("Admin Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStudent = new JButton("Student detail");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                StudentDetailAdmin stu = new StudentDetailAdmin();
                stu.setVisible(true);
                dispose();
			}
		});
		btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStudent.setBounds(136, 298, 324, 47);
		contentPane.add(btnStudent);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//logout button
                Login login = new Login();
                login.setVisible(true);	//goto login
                dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogout.setBounds(337, 485, 324, 47);
		contentPane.add(btnLogout);
		
		JButton btnCourseDetail = new JButton("Course detail");
		btnCourseDetail.addActionListener(new ActionListener() {		//course detail button
			public void actionPerformed(ActionEvent e) {
				CourseDetailAdmin courseDetial =new CourseDetailAdmin();
				courseDetial.setVisible(true); //goto CourseDetailAdmin
				dispose();
			}
		});
		btnCourseDetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCourseDetail.setBounds(136, 392, 324, 47);
		contentPane.add(btnCourseDetail);
		
		JButton btnInsertGrade = new JButton("Key In Grade");
		btnInsertGrade.addActionListener(new ActionListener() {		//key in grade button
			public void actionPerformed(ActionEvent e) {
				InsertStuGrade INGrad = new InsertStuGrade();
				INGrad.setVisible(true);//goto InsertStuGrade
				dispose();
			}
		});
		btnInsertGrade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInsertGrade.setBounds(537, 392, 324, 47);
		contentPane.add(btnInsertGrade);
		
		JButton btnProfessorDetail = new JButton("Professor detail"); //Professor detail button
		btnProfessorDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfessorDetailAdmin ProfDetail = new ProfessorDetailAdmin();
				ProfDetail.setVisible(true); //goto ProfessorDetailAdmin
				dispose();
			}
		});
		btnProfessorDetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProfessorDetail.setBounds(537, 298, 324, 47);
		contentPane.add(btnProfessorDetail);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminMenu.class.getResource("/IMAGE/admin.jpg")));
		lblNewLabel.setBounds(370, 28, 240, 216);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AdminMenu.class.getResource("/IMAGE/MicrosoftTeams-image-16.png")));
		lblNewLabel_1.setBounds(-78, -11, 1090, 606);
		contentPane.add(lblNewLabel_1);
	}
}
