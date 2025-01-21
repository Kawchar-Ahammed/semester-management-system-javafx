package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:sqlserver://DESKTOP-3NT5988\\SQLEXPRESS;databaseName=SemesterManagementSystem;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
    private static final String USER = ""; // Add if necessary
    private static final String PASSWORD = ""; // Add if necessary

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
