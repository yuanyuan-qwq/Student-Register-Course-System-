package controller;

import DBconnector.DatabaseConnection;
import model.Professor;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentController {

	//for student login
    public Student getAuthenticatedStudent(String name, String password) throws ClassNotFoundException, SQLException {
        String sql = "select * from student where S_name = ? and S_password = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        //where
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;

        if (resultSet.next()) {	//read data from database
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
    //student update self information
    public int updateStudent(Student student)throws ClassNotFoundException, SQLException {
        String sql = "UPDATE student SET S_name=?,S_password=?, year=?,S_gender=?,S_email=?,S_address=?,S_NoHp=? WHERE MID = ?";
        Connection conn = DatabaseConnection.doConnection();

        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getPassword());
        preparedStatement.setInt(3, student.getYear());
        preparedStatement.setString(4, student.getGender());
        preparedStatement.setString(5, student.getEmail());
        preparedStatement.setString(6, student.getAddress());
        preparedStatement.setString(7, student.getNoHp());

        preparedStatement.setString(8, student.getMID());      //where

        int success = preparedStatement.executeUpdate();

        conn.close();
        return success;
    }
    public Student StudentSelf(String MID) throws ClassNotFoundException,SQLException //stu
    {
        String sql = "select * from student where MID = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, MID);	//where
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;

        if(resultSet.next()) {
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
    //find pofessor for student
    public Professor findProfessor(String ProfID) throws ClassNotFoundException,SQLException //stu
    {
        String sql = "select * from professor where ProfID = ?";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);

        preparedStatement.setString(1, ProfID);	//where
        ResultSet resultSet = preparedStatement.executeQuery();
        Professor professor = null;

        if(resultSet.next()) {
            professor = new Professor();
            professor.setName(resultSet.getString("P_name"));
            professor.setEmail(resultSet.getString("P_email"));
            professor.setOffice(resultSet.getString("office"));
            professor.setDepartment(resultSet.getString("department"));

        }
        conn.close();
        return professor;

    }





}
