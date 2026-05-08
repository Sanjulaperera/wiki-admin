/*
 * LoginForm.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part A - Wiki Administrator Login
 * Date: 2026
 *
 * Model object used to bind login form fields.
 */

package com.wiki.admin.model;

// Simple POJO for username and password.
public class LoginForm {

    private String username;
    private String password;

    // Default constructor required by Spring form binding.
    public LoginForm() {}

    // Constructor used when creating objects manually.
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Password value is masked for safe debugging output.
    @Override
    public String toString() {
        return "LoginForm{username='" + username + "', password='[HIDDEN]'}";
    }
}
