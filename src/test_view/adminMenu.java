package test_view;//testing controller function 

import controller.RegistrationController;
import controller.adminController;
import model.Course;
import model.Registration;
import model.Student;
import model.Professor;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class adminMenu {
    //Student
    public void StudentDetail(){
        Scanner scanner = new Scanner(System.in);
        char select;

        do
        {
            System.out.println("========== admin menu ==========");
            System.out.println("1. search stu");
            System.out.println("2. insert stu");
            System.out.println("3. update stu");
            System.out.println("4. delete stu");
            System.out.println("5. view stulist");
            System.out.println("0. ");
            System.out.println("select : ");
            select = scanner.next().charAt(0);

            scanner.nextLine();

            switch (select) {
                case '1':
                    searchStudent();
                    break;
                case '2':
                    insertStudent();
                    break;
                case '3':
                    updateStudent();
                    break;
                case '4':
                    deleteStudent();
                    break;
                case '5':
                    viewStudentList();
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
    public void searchStudent(){
        adminController admin = new adminController();
        Student student;
        Scanner scanner = new Scanner(System.in);

        String MID;
        int status;
        System.out.println("Student MID : ");
        MID = scanner.nextLine();

        try {
            student = admin.findStudent(MID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(student !=null){
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
    public void insertStudent(){
        adminController admin = new adminController();
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter Student MID : ");
        student.setMID(scanner.nextLine());

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

        int status;
        try {
            status = admin.insertStudent(student);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status !=0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
    public void updateStudent(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String MID;
        int status;
        System.out.println("enter Student MID to update student's detail: ");
        MID=scanner.nextLine();

        Student student = new Student();

        try {
            student=admin.findStudent(MID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(student !=null) {
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
                status = admin.updateStudent(student);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void deleteStudent(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String MID;
        int status;
        System.out.println("enter Student MID to delete student: ");
        MID=scanner.nextLine();

        Student student = new Student();

        try {
            student=admin.findStudent(MID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(student !=null) {
            try {
                status = admin.deleteStudent(student);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void viewStudentList()
    {
        adminController admin = new adminController();

        try{
            ArrayList<Student> StuArr = admin.viewStudent();
            for(Student student : StuArr){
                System.out.println("==============================================");
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
                System.out.println("==============================================");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    //Professor
    public void ProfessorDetail(){
        Scanner scanner = new Scanner(System.in);
        char select;

        do
        {
            System.out.println("========== professor detail ==========");
            System.out.println("1. search professor");
            System.out.println("2. insert professor");
            System.out.println("3. update professor");
            System.out.println("4. delete professor");
            System.out.println("5. view professor list");
            System.out.println("0. ");
            System.out.println("select : ");
            select = scanner.next().charAt(0);

            scanner.nextLine();

            switch (select) {
                case '1':
                    searchProfessor();
                    break;
                case '2':
                    insertProfessor();
                    break;
                case '3':
                    updateProfessor();
                    break;
                case '4':
                    deleteProfessor();
                    break;
                case '5':
                    viewProfessorList();
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
    public void searchProfessor(){
        adminController admin = new adminController();
        Professor professor;
        Scanner scanner = new Scanner(System.in);

        String ProfID;
        int status;
        System.out.println("Professor ProfID : ");
        ProfID = scanner.nextLine();

        try {
            professor = admin.findProfessor(ProfID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(professor !=null){
            System.out.println("professor ProfID : " +professor.getProfID());
            System.out.println("professor name : " +professor.getName());
            System.out.println("professor password : " +professor.getPassword());
            System.out.println("professor Email : " +professor.getEmail());
            System.out.println("professor address : " +professor.getAddress());
            System.out.println("professor gender : " +professor.getGender());
            System.out.println("professor NoHp : " +professor.getNoHp());
            System.out.println("professor office : " +professor.getOffice());
            System.out.println("professor Department : " +professor.getDepartment());

        }else {
            System.out.println("404 not found");
        }

    }
    public void insertProfessor(){
        adminController admin = new adminController();
        Professor professor = new Professor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter professor ID : ");
        professor.setProfID(scanner.nextLine());

        System.out.println("professor Name : ");
        professor.setName(scanner.nextLine());

        System.out.println("professor Password : ");
        professor.setPassword(scanner.nextLine());

        System.out.println("professor Email : ");
        professor.setEmail(scanner.nextLine());

        System.out.println("address : ");
        professor.setAddress(scanner.nextLine());

        System.out.println("Gender : ");
        professor.setGender(scanner.nextLine());

        System.out.println("NoHp : ");
        professor.setNoHp(scanner.nextLine());

        System.out.println("office : ");
        professor.setOffice(scanner.nextLine());

        System.out.println("department : ");
        professor.setDepartment(scanner.nextLine());

        int status;
        try {
            status = admin.insertProfessor(professor);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status !=0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
    public void updateProfessor(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String ProfID;
        int status;
        System.out.println("enter Professor ID to update Professor's detail: ");
        ProfID=scanner.nextLine();

        Professor professor = new Professor();

        try {
            professor=admin.findProfessor(ProfID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(professor !=null) {

            System.out.println("professor Name : ");
            professor.setName(scanner.nextLine());

            System.out.println("professor Password : ");
            professor.setPassword(scanner.nextLine());

            System.out.println("professor Email : ");
            professor.setEmail(scanner.nextLine());

            System.out.println("address : ");
            professor.setAddress(scanner.nextLine());

            System.out.println("Gender : ");
            professor.setGender(scanner.nextLine());

            System.out.println("NoHp : ");
            professor.setNoHp(scanner.nextLine());

            System.out.println("office : ");
            professor.setOffice(scanner.nextLine());

            System.out.println("department : ");
            professor.setDepartment(scanner.nextLine());

            try {
                status = admin.updateProfessor(professor);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void deleteProfessor(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String ProfID;
        int status;
        System.out.println("enter professor ID to delete professor: ");
        ProfID=scanner.nextLine();

        Professor professor = new Professor();

        try {
            professor=admin.findProfessor(ProfID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(professor !=null) {
            try {
                status = admin.deleteProfessor(professor);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void viewProfessorList()
    {
        adminController admin = new adminController();

        try{
            ArrayList<Professor> ProArr = admin.viewProfessor();
            for(Professor professor : ProArr){
                System.out.println("==============================================");
                System.out.println("professor ProfID : " +professor.getProfID());
                System.out.println("professor name : " +professor.getName());
                System.out.println("professor password : " +professor.getPassword());
                System.out.println("professor Email : " +professor.getEmail());
                System.out.println("professor address : " +professor.getAddress());
                System.out.println("professor gender : " +professor.getGender());
                System.out.println("professor NoHp : " +professor.getNoHp());
                System.out.println("professor office : " +professor.getOffice());
                System.out.println("professor Department : " +professor.getDepartment());
                System.out.println("==============================================");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    //Course
    public void CourseDetail(){
        Scanner scanner = new Scanner(System.in);
        char select;

        do
        {
            System.out.println("========== admin menu ==========");
            System.out.println("1. search Course");
            System.out.println("2. insert Course");
            System.out.println("3. update Course");
            System.out.println("4. delete Course");
            System.out.println("5. view Courselist");
            System.out.println("0. ");
            System.out.println("select : ");
            select = scanner.next().charAt(0);

            scanner.nextLine();

            switch (select) {
                case '1':
                    searchCourse();
                    break;
                case '2':
                    insertCourse();
                    break;
                case '3':
                    updateCourse();
                    break;
                case '4':
                    deleteCourse();
                    break;
                case '5':
                    viewCourseList();
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
    public void searchCourse(){
        adminController admin = new adminController();
        Course course;
        Scanner scanner = new Scanner(System.in);

        String courseID;
        int status;
        System.out.println("course ID : ");
        courseID = scanner.nextLine();

        try {
            course = admin.findCourse(courseID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(course !=null){
            System.out.println("course ID : " +course.getCourseID());
            System.out.println("course name : " +course.getCourseName());
            System.out.println("course credit hours : " +course.getCreditHours());

        }else {
            System.out.println("404 not found");
        }

    }
    public void insertCourse(){
        adminController admin = new adminController();
        Course course = new Course();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter course ID : ");
        course.setCourseID(scanner.nextLine());

        System.out.println("course Name : ");
        course.setCourseName(scanner.nextLine());

        System.out.println("credit hours : ");
        course.setCreditHours(scanner.nextInt());
        scanner.nextLine();


        int status;
        try {
            status = admin.insertCourse(course);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status !=0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
    public void updateCourse(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String courseID;
        int status;
        System.out.println("enter courseID to update course's detail: ");
        courseID=scanner.nextLine();

        Course course = new Course();

        try {
            course=admin.findCourse(courseID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(course !=null) {
            System.out.println("course Name : ");
            course.setCourseName(scanner.nextLine());

            System.out.println("course CreditHours : ");
            course.setCreditHours(scanner.nextInt());


            try {
                status = admin.updateCourse(course);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void deleteCourse(){
        adminController admin = new adminController();
        Scanner scanner = new Scanner(System.in);

        String courseID;
        int status;
        System.out.println("enter course ID to delete course: ");
        courseID=scanner.nextLine();

        Course course = new Course();

        try {
            course=admin.findCourse(courseID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(course !=null) {
            try {
                status = admin.deleteCourse(course);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(status !=0){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else {
            System.out.println("404 not found");
        }

    }
    public void viewCourseList()
    {
        adminController admin = new adminController();

        try{
            ArrayList<Course> CourseArr = admin.viewCourse();
            for(Course course : CourseArr){
                System.out.println("==============================================");
                System.out.println("course ID : " +course.getCourseID());
                System.out.println("course name : " +course.getCourseName());
                System.out.println("credit hours: " +course.getCreditHours());
                System.out.println("==============================================");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    //registration
    public void viewRegisterCourse(){

        RegistrationController registrationCTRL = new RegistrationController();
        Student student =new Student();
        Scanner scanner = new Scanner(System.in);

        Registration registrationNULL = null;
        System.out.println("enter student MID to check register:");
        student.setMID(scanner.nextLine());

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
    public void keyInMark(){
        RegistrationController registrationCTRL = new RegistrationController();
        Student student =new Student();
        Scanner scanner = new Scanner(System.in);

        String courseID,grade;
        int status;



        System.out.println("enter student MID : ");
        student.setMID(scanner.nextLine());
        System.out.println("enter course ID : ");
        courseID=scanner.nextLine();
        System.out.println("enter grade : ");
        grade=scanner.nextLine();

        try {
            status = registrationCTRL.keyInMark(student,courseID,grade);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status !=0){
            System.out.println("success");
        }else{
            System.out.println("fail INVALID INPUT");
        }

    }
}


