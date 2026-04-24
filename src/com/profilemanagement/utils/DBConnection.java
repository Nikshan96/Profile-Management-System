package com.profilemanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This utility class handles the database connection
// Instead of writing connection code in every class, we centralize it here
// Any class that needs a database connection just calls DBConnection.getConnection()

public class DBConnection {

    // Database credentials - update these to match your MySQL Workbench setup
    private static final String URL      = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";       // your MySQL username
    private static final String PASSWORD = "yourpassword"; // your MySQL password

    // This static method returns a live connection to our MySQL database
    // It is static so we can call it without creating an object: DBConnection.getConnection()
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try to establish a connection using the credentials above
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            // This happens if the MySQL connector JAR is not added to the project
            System.out.println("MySQL Driver not found. Please add the MySQL connector JAR.");
            e.printStackTrace();

        } catch (SQLException e) {
            // This happens if credentials are wrong or MySQL server is not running
            System.out.println("Connection failed. Check your MySQL credentials and make sure the server is running.");
            e.printStackTrace();
        }

        return connection;
    }
}
