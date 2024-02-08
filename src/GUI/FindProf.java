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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class FindProf extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;


	public FindProf(Student student) {
		
		Object[] column = { "Professor Name","Email","NoHp","Office","Department"};
		final Object[] row = new Object[5];
		
		setBackground(new Color(231, 236, 250));
		setTitle("Professor Detail (Student)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Professor Detail");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 29));
		label.setBounds(290, 0, 686, 53);
		contentPane.add(label);
		
		Label label_1_1 = new Label("Professor Name :");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1.setBounds(10, 207, 124, 39);
		contentPane.add(label_1_1);
		
		Label label_1_1_2 = new Label("Email :");
		label_1_1_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2.setBounds(10, 266, 124, 39);
		contentPane.add(label_1_1_2);
		
		Label label_1_1_2_1 = new Label("NoHp :");
		label_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2_1.setBounds(10, 453, 124, 39);
		contentPane.add(label_1_1_2_1);
		
		Label label_1_1_2_1_1 = new Label("Department :");
		label_1_1_2_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_1_2_1_1.setBounds(10, 391, 124, 39);
		contentPane.add(label_1_1_2_1_1);
		
		Label label_1_3_2_1 = new Label("Office :");
		label_1_3_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1_3_2_1.setBounds(10, 325, 124, 39);
		contentPane.add(label_1_3_2_1);
		
		JLabel lbNoHp = new JLabel("");
		lbNoHp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbNoHp.setBounds(140, 460, 183, 27);
		contentPane.add(lbNoHp);
		
		JLabel lbDepartment = new JLabel("");
		lbDepartment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbDepartment.setBounds(140, 398, 183, 27);
		contentPane.add(lbDepartment);
		
		JLabel lbOffice = new JLabel("");
		lbOffice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbOffice.setBounds(140, 332, 183, 27);
		contentPane.add(lbOffice);
		
		JLabel lbEmail = new JLabel("");
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbEmail.setBounds(140, 274, 183, 27);
		contentPane.add(lbEmail);
		
		JLabel lbname = new JLabel("");
		lbname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbname.setBounds(140, 214, 183, 27);
		contentPane.add(lbname);
		
		
		Button ExitButton = new Button("Exit");
		ExitButton.setFont(new Font("Dialog", Font.BOLD, 12));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						//exit btn
				StudentMenu StuMenu = new StudentMenu(student);
				StuMenu.setVisible(true);
                dispose();
			}
		});
		ExitButton.setBounds(868, 524, 88, 39);
		contentPane.add(ExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 59, 643, 446);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {								//mouse func
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				lbname.setText(model.getValueAt(i, 0).toString());	//set data to text field
				lbEmail.setText(model.getValueAt(i, 1).toString());
				
				lbNoHp.setText(model.getValueAt(i, 2).toString());
				lbOffice.setText(model.getValueAt(i, 3).toString());
				lbDepartment.setText(model.getValueAt(i, 4).toString());
				
				


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
			
            for(Professor professor : ProArr){// load professor info to table
				
				row[0] = professor.getName();
				row[1] = professor.getEmail();
				row[2] = professor.getNoHp();
				row[3] = professor.getOffice();
				row[4] = professor.getDepartment();
				
				model.addRow(row);
				
                
            }
        }catch (ClassNotFoundException | SQLException e1){
            e1.printStackTrace();
        }
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {							//refresh btn
			public void actionPerformed(ActionEvent e) {
			
				FindProf ProDetial = new FindProf(student);
				ProDetial.setVisible(true);
                dispose();
				
			}
		});
		
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(540, 524, 150, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FindProf.class.getResource("/IMAGE/pro.png")));
		lblNewLabel.setBounds(86, 11, 158, 168);
		contentPane.add(lblNewLabel);
		
		

		

	}
}
