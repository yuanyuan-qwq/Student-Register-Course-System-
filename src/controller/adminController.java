package controller;

import DBconnector.DatabaseConnection;
import model.Admin;
import model.Course;
import model.Student;
import model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class adminController {
	//use for login
    public Admin getAuthenticatedAdmin(String name, String password) throws ClassNotFoundException, SQLException {
        String sql = "select * from admin where A_name = ? and A_password = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;

        if (resultSet.next()) {
            admin = new Admin();
            admin.setName(resultSet.getString("A_name"));
            admin.setPassword(resultSet.getString("A_password"));

        }
        conn.close();
        return admin;

    }

    //student detail
    public int insertStudent(Student student) throws ClassNotFoundException, SQLException {

        String sql = "insert into student (MID,S_name,S_password,program, year,S_gender,TotalCredit,CGPA,S_email,S_address,S_NoHp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        //Insert
        preparedStatement.setString(1, student.getMID());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setString(3, student.getPassword());
        preparedStatement.setString(4, student.getProgram());
        preparedStatement.setInt(5, student.getYear());
        preparedStatement.setString(6, student.getGender());
        preparedStatement.setInt(7, student.getTotalCredit());  
        preparedStatement.setFloat(8, student.getCGPA());       
        preparedStatement.setString(9, student.getEmail());
        preparedStatement.setString(10, student.getAddress());
        preparedStatement.setString(11, student.getNoHp());

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success; 

    }

    public int updateStudent(Student student) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE student SET S_name=?,S_password=?,program=?, year=?,S_gender=?,TotalCredit=?,CGPA=?,S_email=?,S_address=?,S_NoHp=? WHERE MID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        //SET
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getPassword());
        preparedStatement.setString(3, student.getProgram());
        preparedStatement.setInt(4, student.getYear());
        preparedStatement.setString(5, student.getGender());
        preparedStatement.setInt(6, student.getTotalCredit());  
        preparedStatement.setFloat(7, student.getCGPA());       
        preparedStatement.setString(8, student.getEmail());
        preparedStatement.setString(9, student.getAddress());
        preparedStatement.setString(10, student.getNoHp());

        preparedStatement.setString(11, student.getMID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }

    public int deleteStudent(Student student) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM student WHERE MID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, student.getMID());      //where 

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }

    //findStudent for 1 row
    public Student findStudent(String MID) throws ClassNotFoundException, SQLException {
        String sql = "select * from student where MID = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, MID);	//Where
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;

        if (resultSet.next()) {			//save to student
            student = new Student();
            student.setMID(resultSet.getString("MID"));
            student.setName(resultSet.getString("S_name"));
            student.setPassword(resultSet.getString("S_password"));
            student.setProgram(resultSet.getString("program"));
            student.setYear(resultSet.getInt("year"));
            student.setGender(resultSet.getString("S_gender"));
            student.setTotalCredit(resultSet.getInt("TotalCredit"));
            student.setCGPA(resultSet.getFloat("CGPA"));
            student.setEmail(resultSet.getString("S_email"));
            student.setAddress(resultSet.getString("S_address"));
            student.setNoHp(resultSet.getString("S_NoHp"));
        }
        conn.close();
        return student;

    }

    //View all Student multi row
    public ArrayList<Student> viewStudent() throws ClassNotFoundException, SQLException {
        ArrayList<Student> StuArr = new ArrayList<Student>();	//use array to store multi row data
        String sql = "Select * from student";

        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Student student = new Student();
            student.setMID(resultSet.getString("MID"));
            student.setName(resultSet.getString("S_name"));
            student.setPassword(resultSet.getString("S_password"));
            student.setProgram(resultSet.getString("program"));
            student.setYear(resultSet.getInt("year"));
            student.setGender(resultSet.getString("S_gender"));
            student.setTotalCredit(resultSet.getInt("TotalCredit"));
            student.setCGPA(resultSet.getFloat("CGPA"));
            student.setEmail(resultSet.getString("S_email"));
            student.setAddress(resultSet.getString("S_address"));
            student.setNoHp(resultSet.getString("S_NoHp"));
            StuArr.add(student);
        }
        conn.close();
        return StuArr; //return student array 
    }
    //update grade to student database
    public int updateStudentGrade(Student student) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE student SET TotalCredit=?,CGPA=? WHERE MID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        
        preparedStatement.setInt(1, student.getTotalCredit());  
        preparedStatement.setFloat(2, student.getCGPA());       

        preparedStatement.setString(3, student.getMID());      //where

        int success = preparedStatement.executeUpdate();
        if(success!=0)
        	System.out.println("updated 2");
        conn.close();
        return success;
    }

    //professor detail
    public int insertProfessor(Professor professor) throws ClassNotFoundException, SQLException {

        String sql = "insert into professor (ProfID,P_name,P_password,P_email,P_address,P_gender,P_NoHp,office,department) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, professor.getProfID());
        preparedStatement.setString(2, professor.getName());
        preparedStatement.setString(3, professor.getPassword());
        preparedStatement.setString(4, professor.getEmail());
        preparedStatement.setString(5, professor.getAddress());
        preparedStatement.setString(6, professor.getGender());
        preparedStatement.setString(7, professor.getNoHp());
        preparedStatement.setString(8, professor.getOffice());
        preparedStatement.setString(9, professor.getDepartment());


        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;

    }

    public int updateProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE professor SET P_name=?,P_password=?,P_email=?,P_address=?,P_gender=?,P_NoHp=?,office=?,department=? WHERE ProfID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, professor.getName());
        preparedStatement.setString(2, professor.getPassword());
        preparedStatement.setString(3, professor.getEmail());
        preparedStatement.setString(4, professor.getAddress());
        preparedStatement.setString(5, professor.getGender());
        preparedStatement.setString(6, professor.getNoHp());
        preparedStatement.setString(7, professor.getOffice());
        preparedStatement.setString(8, professor.getDepartment());

        preparedStatement.setString(9, professor.getProfID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }

    public int deleteProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM professor WHERE ProfID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, professor.getProfID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }
    //find professor for 1 row
    public Professor findProfessor(String ProfID) throws ClassNotFoundException, SQLException {
        String sql = "select * from professor where ProfID = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, ProfID);	//where
        ResultSet resultSet = preparedStatement.executeQuery();
        Professor professor = null;

        if (resultSet.next()) {
            professor = new Professor();
            professor.setProfID(resultSet.getString("ProfID"));
            professor.setName(resultSet.getString("P_name"));
            professor.setPassword(resultSet.getString("P_password"));
            professor.setEmail(resultSet.getString("P_email"));
            professor.setAddress(resultSet.getString("P_address"));
            professor.setGender(resultSet.getString("P_gender"));
            professor.setNoHp(resultSet.getString("P_NoHp"));
            professor.setOffice(resultSet.getString("office"));
            professor.setDepartment(resultSet.getString("department"));

        }
        conn.close();
        return professor;

    }
    //list professor for multi row
    public ArrayList<Professor> viewProfessor() throws ClassNotFoundException, SQLException {
        ArrayList<Professor> ProArr = new ArrayList<Professor>();
        String sql = "Select * from professor";

        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Professor professor = new Professor();
            professor.setProfID(resultSet.getString("ProfID"));
            professor.setName(resultSet.getString("P_name"));
            professor.setPassword(resultSet.getString("P_password"));
            professor.setEmail(resultSet.getString("P_email"));
            professor.setAddress(resultSet.getString("P_address"));
            professor.setGender(resultSet.getString("P_gender"));
            professor.setNoHp(resultSet.getString("P_NoHp"));
            professor.setOffice(resultSet.getString("office"));
            professor.setDepartment(resultSet.getString("department"));
            ProArr.add(professor);
        }
        conn.close();
        return ProArr;
    }

    //course detail
    public int insertCourse(Course course) throws ClassNotFoundException, SQLException {

        String sql = "insert into course (C_courseID,courseName,creditHours) values (?, ?, ?)";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, course.getCourseID());
        preparedStatement.setString(2, course.getCourseName());
        preparedStatement.setInt(3, course.getCreditHours());


        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;

    }
    
    public int updateCourse(Course course) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE course SET courseName=?,creditHours=? WHERE C_courseID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, course.getCourseName());
        preparedStatement.setInt(2, course.getCreditHours());


        preparedStatement.setString(3, course.getCourseID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }

    public int deleteCourse(Course course) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM course WHERE C_courseID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, course.getCourseID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }

    public Course findCourse(String courseID) throws ClassNotFoundException, SQLException {
        String sql = "select * from course where C_courseID = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, courseID);		//where
        ResultSet resultSet = preparedStatement.executeQuery();
        Course course = null;

        if (resultSet.next()) {
            course = new Course();
            course.setCourseID(resultSet.getString("C_courseID"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCreditHours(resultSet.getInt("creditHours"));
        }
        conn.close();
        return course;

    }

    public ArrayList<Course> viewCourse() throws ClassNotFoundException, SQLException {
        ArrayList<Course> CourseArr = new ArrayList<Course>();
        String sql = "Select * from course";

        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Course course = new Course();
            course.setCourseID(resultSet.getString("C_courseID"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCreditHours(resultSet.getInt("creditHours"));

            CourseArr.add(course);
        }
        conn.close();
        return CourseArr;
    }
}
