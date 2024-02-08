package DBconnector;

import java.sql.*;

public class DatabaseConnection {	//database connector
    static Connection conn = null;
    public static Connection doConnection() throws ClassNotFoundException,SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "");
        return conn;
    }
}
