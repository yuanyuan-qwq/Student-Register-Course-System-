package test_view;//testing controller function 
import DBconnector.DatabaseConnection;
import controller.adminController;
import  controller.studentController;
import model.Admin;
import model.Student;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class login {
    Scanner scanner = new Scanner(System.in);
    public void mainMenu() throws SQLException, ClassNotFoundException {
        char select;

        do {

            System.out.println("========== welcome ==========");
            System.out.println("1. Student login");
            System.out.println("2. Professor login");
            System.out.println("3. Admin login");
            System.out.println("0. Exit");
            System.out.println("select : ");
            select = scanner.next().charAt(0);;

            switch (select) {
                case '1':
                    studentLogin();
                    break;
                case '2':
                    professorLogin();
                    break;
                case '3':
                    adminLogin();
                    break;
                case '0':
                    System.out.println("========== thanks==========");
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }while (select != '0');
    }
    public void studentLogin(){
        studentMenu menu = new studentMenu();
        studentController studentCTRL = new studentController();
        Student student;

        String name,password;
        System.out.println("========== Student Login ==========");
        System.out.println("Student Name: ");
        scanner.nextLine();
        name = scanner.nextLine();
        System.out.println("password: ");
        password = scanner.nextLine();

        try {
            student = studentCTRL.getAuthenticatedStudent(name, password);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(student !=null){// later settle
            menu.StudentDetail(student);
        }else {
            System.out.println("invalid password");
        }

    }
    public void professorLogin(){

    }
    public void adminLogin() throws SQLException, ClassNotFoundException {
        adminMenu menu = new adminMenu();
        adminController adminCTRL = new adminController();
        Admin admin;

        String name,password;
        System.out.println("========== Admin Login ==========");
        System.out.println("Admin Name: ");
        scanner.nextLine();
        name = scanner.nextLine();
        System.out.println("password: ");
        password = scanner.nextLine();

        try {
            admin = adminCTRL.getAuthenticatedAdmin(name, password);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(admin !=null){
            menu.viewRegisterCourse();
        }else {
            System.out.println("invalid password");
        }


    }

}
