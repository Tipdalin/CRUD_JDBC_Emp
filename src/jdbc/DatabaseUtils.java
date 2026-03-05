package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/users";
            String user = "postgres";
            String password = "123";

            connection = DriverManager.getConnection(url, user, password);

            if(connection.isValid(2)){
//                System.out.println("Connected to database successfully");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return connection;
    }
}