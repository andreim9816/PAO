package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil
{
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/sys";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "1234";

    public static Connection getInstance()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeDBConnection(Connection connection)
    {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Connection closed!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}