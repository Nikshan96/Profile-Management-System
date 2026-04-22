package com.profilemanagement.view;

import com.profilemanagement.controller.UserController;

import java.util.Scanner;

// The View is what the user directly interacts with
// It shows the menu, takes input, and calls the Controller to process things
// It does NOT handle any database logic - that is the Controller and DAO's job

public class MainView {

    // Scanner to read user input from the console
    private Scanner scanner = new Scanner(System.in);

    // Create a Controller instance to handle all business logic
    private UserController userController = new UserController();

    // -----------------------------------------------------------------------
    // Entry point - this is the first method that runs when the app starts
    // -----------------------------------------------------------------------
    public void startProgram() {
        System.out.println("========================================");
        System.out.println("   WELCOME TO PROFILE MANAGEMENT SYSTEM");
        System.out.println("========================================");

        // Keep showing the menu until the user chooses to exit
        boolean running = true;

        while (running) {
            showMenu();

            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            // Handle the user's menu choice
            switch (input) {
                case "1":
                    addUser();
                    break;

                case "2":
                    viewAllUsers();
                    break;

                case "3":
                    viewUserById();
                    break;

                case "4":
                    System.out.println("\nThank you for using Profile Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid option. Please enter a number between 1 and 4.");
                    break;
            }
        }

        scanner.close();
    }

    // -----------------------------------------------------------------------
    // Displays the main menu options
    // -----------------------------------------------------------------------
    public void showMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("               MAIN MENU               ");
        System.out.println("----------------------------------------");
        System.out.println("1. Add User Profile");
        System.out.println("2. View All Users");
        System.out.println("3. View My Profile (by ID)");
        System.out.println("4. Exit");
        System.out.println("----------------------------------------");
    }

    // -----------------------------------------------------------------------
    // Takes user input for name, email, and password then calls the Controller
    // -----------------------------------------------------------------------
    public void addUser() {
        System.out.println("\n--- Add New User Profile ---");

        System.out.print("Enter Name     : ");
        String name = scanner.nextLine();

        System.out.print("Enter Email    : ");
        String email = scanner.nextLine();

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        // Pass the input to the controller
        boolean success = userController.addUser(name, email, password);

        if (success) {
            System.out.println("\nUser profile added successfully!");
        } else {
            System.out.println("\nFailed to add user profile. Please try again.");
        }
    }

    // -----------------------------------------------------------------------
    // Calls the Controller to fetch and display all users
    // -----------------------------------------------------------------------
    public void viewAllUsers() {
        userController.showAllUsers();
    }

    // -----------------------------------------------------------------------
    // Asks for an ID and calls the Controller to fetch that specific user
    // -----------------------------------------------------------------------
    public void viewUserById() {
        System.out.println("\n--- View My Profile ---");
        System.out.print("Enter your User ID: ");

        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            userController.showUserById(id);

        } catch (NumberFormatException e) {
            // Handles the case where the user types letters instead of a number
            System.out.println("Invalid input. Please enter a valid numeric ID.");
        }
    }

    // -----------------------------------------------------------------------
    // Main method - this is where the entire program starts
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        MainView app = new MainView();
        app.startProgram();
    }
}
