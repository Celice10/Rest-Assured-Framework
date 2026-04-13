package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public static void connectToDatabase() {

        String dbUrl = System.getenv("jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching");
        String dbUsername = System.getenv("ndosian6b8b7_teaching");
        String dbPassword = System.getenv("^{SF0a=#~[~p)@l1");

        try(Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {

            try(Statement statement = connection.createStatement()) {
                String query = "SELECT 1"; // Simple query to test the connection
                statement.executeQuery(query);
            }
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
