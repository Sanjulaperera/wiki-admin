/*
 * AuthService.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part A - Wiki Administrator Login
 * Date: 2026
 *
 * Service layer for authentication and user messages.
 */

package com.wiki.admin.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // Hardcoded credentials required for Part A.
    private static final String VALID_USERNAME = "sanjula";
    private static final String VALID_PASSWORD = "123";

    /**
     * Authenticates a user by comparing submitted credentials against the hardcoded values.
     *
     * @param username  the username string entered by the user in the login form
     * @param password  the password string entered by the user in the login form
     * @return true if BOTH username AND password match the accepted values; false otherwise
     */
    public boolean authenticate(String username, String password) {

        // Guard clause to avoid null input errors.
        if (username == null || password == null) {
            return false;
        }

        // Remove extra spaces from user input.
        String trimmedUsername = username.trim();
        String trimmedPassword = password.trim();

        // Empty values are always invalid.
        if (trimmedUsername.isEmpty() || trimmedPassword.isEmpty()) {
            return false;
        }

        // Username is case-insensitive, password is case-sensitive.
        boolean usernameMatches = VALID_USERNAME.equalsIgnoreCase(trimmedUsername);
        boolean passwordMatches = VALID_PASSWORD.equals(trimmedPassword);

        return usernameMatches && passwordMatches;
    }

    /**
     * Returns a personalised welcome message for a successfully authenticated user.
     *
     * @param username the username of the logged-in admin
     * @return a formatted welcome string to display on the welcome page
     */
    public String buildWelcomeMessage(String username) {
        // Format username for a cleaner welcome message.
        String displayName = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        return "Welcome, " + displayName + "! You are now logged in as Wiki Administrator.";
    }

    /**
     * Returns an error message to display when authentication fails.
     *
     * @return a user-facing error string
     */
    public String buildErrorMessage() {
        return "Invalid username or password. Please try again.";
    }
}
