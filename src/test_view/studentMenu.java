package test_view;
//testing controller function 

import controller.RegistrationController;
import controller.adminController;
import controller.studentController;
import model.Course;
import model.Registration;
import model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class studentMenu {
    public void StudentDetail(Student student){
        Scanner scanner = new Scanner(System.in);
        char select;

        do
        {
            System.out.println("========== student menu ==========");
            System.out.println("1. check self");
            System.out.println("2. update stu");
            System.out.println("3. register course");
            System.out.println("4. drop course");
            System.out.println("5. view register course 6");
            System.out.println("0. ");
            System.out.println("select : ");
            select = scanner.next().charAt(0);

            scanner.nextLine();

            switch (select) {
                case '1':
                    checkStudent(student);
                    break;
                case '2':
                    updateStudent(student);
                    break;
                case '3':
                    registerCourse(student);
                    break;
                case '4':
                    DropCourse(student);
                    break;
                case '5':
					viewRegisterCourse(student);
                    break;
                case '6':
				try {
					ExportRegisterCourse(student);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                case '0':
                    System.out.println("========== thanks==========");
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }while(select !='0');
    }
    public void checkStudent(Student student){
        studentController studentCTRL =new studentController();
        String MID=student.getMID();

        try {
            student = studentCTRL.StudentSelf(MID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(student !=null) {
            System.out.println("Student MID : " +student.getMID());
            System.out.println("Student name : " +student.getName());
            System.out.println("Student password : " +student.getPassword());
            System.out.println("Student program : " +student.getProgram());
            System.out.println("Student year : " +student.getYear());
            System.out.println("Student gender : " +student.getGender());
            System.out.println("Student Total Credit : " +student.getTotalCredit());
            System.out.println("Student CGPA : " +student.getCGPA());
            System.out.println("Student Email : " +student.getEmail());
            System.out.println("Student address : " +student.getAddress());
            System.out.println("Student NoHp : " +student.getNoHp());
        }else {
            System.out.println("404 not found");
        }
    }
    public void updateStudent(Student student){
        studentController studentCTRL =new studentController();
        Scanner scanner = new Scanner(System.in);
        int status;

        System.out.println("Student Name : ");
        student.setName(scanner.nextLine());

        System.out.println("Student Password : ");
        student.setPassword(scanner.nextLine());

        System.out.println("Student Program : ");
        student.setProgram(scanner.nextLine());

        System.out.println("Gender : ");
        student.setGender(scanner.nextLine());

        System.out.println("Student Email : ");
        student.setEmail(scanner.nextLine());

        try {
            status = studentCTRL.updateStudent(student);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status !=0){
            System.out.println("success");
        }else{
            System.out.println("Fair");
        }
    }
    public void registerCourse(Student student){
        RegistrationController registrationController = new RegistrationController();
        Scanner scanner = new Scanner(System.in);

        String courseID;
        int status =0;
        System.out.println("enter courseID to register course: ");
        courseID=scanner.nextLine();

        Course course;

        try {
            course=registrationController.findCourse(courseID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(course !=null) {

            try {
                status = registrationController.RegisterCourse(student,course);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println("Duplication registration!!!!!");
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("Fair");
            }
        }else {
            System.out.println("404 not found");
        }
    }
    public void DropCourse(Student student){

        RegistrationController registrationCTRL = new RegistrationController();
        Scanner scanner = new Scanner(System.in);

        String courseID;
        int status;

        System.out.println("enter course ID to drop course: ");
        courseID=scanner.nextLine();

            try {
                status = registrationCTRL.DropCourse(student,courseID);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("Fair");
            }

    }
    public void viewRegisterCourse(Student student){

        RegistrationController registrationCTRL = new RegistrationController();
        Registration registrationNULL = null;

        try{
            ArrayList<Registration> RegistrationArr = registrationCTRL.viewRegisterCourse(student);

            for(Registration registration : RegistrationArr){
                registrationNULL = new Registration();
                System.out.println("==============================================");
                System.out.println("MID : " +registration.getMID());
                System.out.println("course ID: " +registration.getC_courseID());
                System.out.println("course Name: " +registration.getCourseName());
                System.out.println("grade: " +registration.getGrade());
                System.out.println("credit hours: " +registration.getCreditHours());
                System.out.println("ProfID: " +registration.getProfID());
                System.out.println("==============================================");
                
            }
            System.out.println("GPA: " +student.getCGPA());
            System.out.println("Total Credit hours: " +student.getTotalCredit());

            if(registrationNULL ==null) {
                System.out.println("404 Not Found");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    public void ExportRegisterCourse(Student student) throws FileNotFoundException{

        RegistrationController registrationCTRL = new RegistrationController();
        Registration registrationNULL = null;        

        File csvFile = new File("RegistrationFile.csv");
        PrintWriter out = new PrintWriter(csvFile);

        out.printf("%s, %s, %s, %s,%s\n","MID","course ID","course Name","grade","credit hours");
          
        try{
            ArrayList<Registration> RegistrationArr = registrationCTRL.viewRegisterCourse(student);

            for(Registration registration : RegistrationArr){
                registrationNULL = new Registration();
                
                out.printf("%s, %s, %s, %s, %d\n",registration.getMID(),registration.getC_courseID(),registration.getCourseName(),registration.getGrade(),registration.getCreditHours());
                
            }
            out.printf("%s, %f\n","GPA: ",student.getCGPA());
            out.printf("%s, %d\n","Total Credit hours: ",student.getTotalCredit()); 

            if(registrationNULL ==null) {
                System.out.println("404 Not Found");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        out.close();
    }
}
