package com.profilemanagement.dao;

import com.profilemanagement.model.User;
import com.profilemanagement.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO stands for Data Access Object
// This class is responsible for ALL database operations related to users
// No other class should directly talk to the database - everything goes through here

public class UserDAO {

    // -----------------------------------------------------------------------
    // INSERT a new user into the profile table
    // We use PreparedStatement to safely insert data (prevents SQL injection)
    // -----------------------------------------------------------------------
    public boolean insertUser(User user) {

        // SQL query with placeholders (?) for safe data insertion
        String sql = "INSERT INTO profile (name, email, password) VALUES (?, ?, ?)";

        try {
            // Get a connection from our utility class
            Connection connection = DBConnection.getConnection();

            // Create a PreparedStatement and fill in the placeholders
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            // Execute the insert and check how many rows were affected
            int rowsAffected = preparedStatement.executeUpdate();

            // Close resources after use
            preparedStatement.close();
            connection.close();

            // If at least 1 row was inserted, return true (success)
            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error inserting user into database.");
            e.printStackTrace();
        }

        return false; // Insert failed
    }

    // -----------------------------------------------------------------------
    // FETCH ALL users from the profile table
    // Returns a list of User objects
    // -----------------------------------------------------------------------
    public List<User> getAllUsers() {

        // This list will store all users retrieved from the database
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM profile";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute the query and store the result
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through each row in the result and create a User object
            while (resultSet.next()) {
                int id          = resultSet.getInt("id");
                String name     = resultSet.getString("name");
                String email    = resultSet.getString("email");
                String password = resultSet.getString("password");

                // Create a User object and add it to the list
                User user = new User(id, name, email, password);
                userList.add(user);
            }

            // Close all resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error fetching all users from database.");
            e.printStackTrace();
        }

        return userList; // Return the full list (could be empty if no records)
    }

    // -----------------------------------------------------------------------
    // FETCH a single user by their ID
    // Returns a User object or null if not found
    // -----------------------------------------------------------------------
    public User getUserById(int id) {

        String sql = "SELECT * FROM profile WHERE id = ?";
        User user = null;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set the id placeholder
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            // If a matching record is found, build the User object
            if (resultSet.next()) {
                String name     = resultSet.getString("name");
                String email    = resultSet.getString("email");
                String password = resultSet.getString("password");

                user = new User(id, name, email, password);
            }

            // Close all resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error fetching user by ID from database.");
            e.printStackTrace();
        }

        return user; // Returns null if no user was found with that ID
    }
}
