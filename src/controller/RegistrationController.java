package controller;

import DBconnector.DatabaseConnection;
import model.Admin;
import model.Course;
import model.Student;
import model.Professor;
import model.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class RegistrationController {
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
    public ArrayList<Registration> viewRegisterCourse(Student student) throws ClassNotFoundException, SQLException{
        ArrayList<Registration> RegistrationArr = new ArrayList<Registration>();
        float TotalGradePoint=0,GPA,GradePoint=0;
        int TotalCredit=0;
        String getPoint;
        String sql = "SELECT * FROM registration INNER JOIN course ON registration.C_courseID=course.C_courseID WHERE registration.MID=? ";
        //join registration and course table to make easy to understand information
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, student.getMID());		//where
        ResultSet resultSet = preparedStatement.executeQuery();
        Registration registration=null;

        while (resultSet.next()) {//store to registration (combine of student and course)
            registration = new Registration();
            registration.setMID(resultSet.getString("MID"));
            registration.setC_courseID(resultSet.getString("C_courseID"));
            registration.setGrade(resultSet.getString("grade"));
            registration.setProfID(resultSet.getString("ProfID"));
            registration.setCourseName(resultSet.getString("courseName"));
            registration.setCreditHours(resultSet.getInt("creditHours"));
            getPoint=registration.getGrade();

            if(getPoint != null) {		//set point

                switch (getPoint) {
                    case "a":
                    case "A":
                        GradePoint = 4.0F;
                        break;
                    case "b":
                    case "B":
                        GradePoint = 3.0F;
                        break;
                    case "c":
                    case "C":
                        GradePoint = 2.0F;
                        break;
                    case "d":
                    case "D":
                        GradePoint = 1.0F;
                        break;
                    case "e":
                    case "E":
                        GradePoint = 0;
                        break;
                    default:
                        GradePoint = -100; // if invalid input
                        break;
                }
            }else {
                GradePoint=-100;	//getpoint = null
            }
            TotalCredit+=registration.getCreditHours();		//sum of Credit Hours
            TotalGradePoint+=(GradePoint*registration.getCreditHours());	//sum of (number of credit * Grade point)

            RegistrationArr.add(registration);
        }
        if(registration!=null) {
	        //calculate GPA
	        student.setTotalCredit(TotalCredit);
	        GPA=TotalGradePoint/TotalCredit;	// sum of (number of credit * Grade point) / sum of Credit Hours
	
	        if (GPA<0){
	            GPA= 0;
	        }
	        //update student info
	        adminController admin=new adminController();
	        student.setTotalCredit(TotalCredit);
	        student.setCGPA(GPA);
	        int success=admin.updateStudentGrade(student); //set total credit and GPA to student
	        if(success!=0)
	        	System.out.println("updated 1");
        }
        conn.close();
        return RegistrationArr;
    }
    
    //student register course
    public int RegisterCourse(Student student,Course course) throws ClassNotFoundException, SQLException {

        String sql = "insert into registration (MID,C_courseID) values (?, ?)";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, student.getMID());
        preparedStatement.setString(2, course.getCourseID());

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }
    
    //student withdraw course
    public int DropCourse(Student student,String courseID) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM registration WHERE MID=? and C_courseID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        //where
        preparedStatement.setString(1, student.getMID());
        preparedStatement.setString(2, courseID);

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }
    //admin key in mark
    public int keyInMark(Student student,String courseID,String grade)throws ClassNotFoundException, SQLException{
        String sql = "UPDATE registration SET grade=? WHERE MID = ? AND C_courseID=?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, grade);
        //where
        preparedStatement.setString(2, student.getMID());
        preparedStatement.setString(3, courseID);

        int success = preparedStatement.executeUpdate();

        conn.close();
        if(success!=0) {
        	System.out.println("key in success");
        }
        return success;
    }
    
    //view all course
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
