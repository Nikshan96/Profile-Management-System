package com.profilemanagement.model;

// This is the User model class - it represents a user profile in our system
// It holds all the data fields that match our MySQL table columns

public class User {

    // Private fields - these match the columns in our MySQL profile table
    private int id;
    private String name;
    private String email;
    private String password;

    // Default constructor - needed when we create an empty User object
    public User() {
    }

    // Parameterized constructor - used when adding a new user (no id yet, MySQL auto-generates it)
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Full constructor - used when fetching a user from the database (id already exists)
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters - used to read the values of private fields

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters - used to set/update the values of private fields

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method - helpful for printing user details in a readable format
    @Override
    public String toString() {
        return "User ID   : " + id +
               "\nName      : " + name +
               "\nEmail     : " + email +
               "\nPassword  : " + password;
    }
}
