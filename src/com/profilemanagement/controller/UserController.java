package com.profilemanagement.controller;

import com.profilemanagement.dao.UserDAO;
import com.profilemanagement.model.User;

import java.util.List;

// The Controller acts as a bridge between the View (what the user sees)
// and the DAO (which talks to the database)
// The View never directly calls the DAO - it always goes through the Controller

public class UserController {

    // Create a single instance of UserDAO to use throughout this controller
    private UserDAO userDAO = new UserDAO();

    // -----------------------------------------------------------------------
    // Add a new user - called by the View when user enters profile details
    // Creates a User object and passes it to the DAO for insertion
    // -----------------------------------------------------------------------
    public boolean addUser(String name, String email, String password) {

        // Basic validation - make sure no field is left empty
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email cannot be empty.");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        // Create a new User object with the provided details
        User newUser = new User(name.trim(), email.trim(), password.trim());

        // Call the DAO to insert the user and return the result
        boolean success = userDAO.insertUser(newUser);
        return success;
    }

    // -----------------------------------------------------------------------
    // Show all users - fetches the list from DAO and prints each one
    // -----------------------------------------------------------------------
    public void showAllUsers() {

        List<User> userList = userDAO.getAllUsers();

        // Check if there are any users in the database
        if (userList.isEmpty()) {
            System.out.println("No user profiles found in the system.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("         ALL REGISTERED USERS          ");
        System.out.println("========================================");

        // Loop through the list and print each user
        for (User user : userList) {
            System.out.println(user.toString());
            System.out.println("----------------------------------------");
        }

        System.out.println("Total Users: " + userList.size());
    }

    // -----------------------------------------------------------------------
    // Show a specific user by ID - fetches from DAO and prints the result
    // -----------------------------------------------------------------------
    public void showUserById(int id) {

        User user = userDAO.getUserById(id);

        // Check if a user with that ID actually exists
        if (user == null) {
            System.out.println("No user found with ID: " + id);
            return;
        }

        System.out.println("\n========================================");
        System.out.println("           USER PROFILE DETAILS        ");
        System.out.println("========================================");
        System.out.println(user.toString());
        System.out.println("========================================");
    }
}
