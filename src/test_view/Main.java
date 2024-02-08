package test_view;//testing controller function 
import DBconnector.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        login Login = new login();

        try{
            DatabaseConnection.doConnection();
            Login.mainMenu();
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }



    }
}