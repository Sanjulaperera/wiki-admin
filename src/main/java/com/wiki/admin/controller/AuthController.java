/*
 * AuthController.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part A - Wiki Administrator Login
 * Date: 2026
 *
 * Controller for the login flow.
 * Receives requests, calls AuthService, and returns the correct Thymeleaf view.
 */

package com.wiki.admin.controller;

import com.wiki.admin.model.LoginForm;
import com.wiki.admin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    // Service used for authentication logic.
    @Autowired
    private AuthService authService;

    // Redirect root path to login page.
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Show login page with an empty form object.
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    // Process submitted credentials.
    @PostMapping("/login")
    public String processLogin(
            @ModelAttribute("loginForm") LoginForm loginForm,
            Model model) {

        String submittedUsername = loginForm.getUsername();
        String submittedPassword = loginForm.getPassword();

        boolean isAuthenticated = authService.authenticate(submittedUsername, submittedPassword);

        if (isAuthenticated) {
            String welcomeMessage = authService.buildWelcomeMessage(submittedUsername);
            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("username", submittedUsername);
            return "welcome";

        } else {
            String errorMessage = authService.buildErrorMessage();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("loginForm", loginForm);
            return "error";
        }
    }

    // Return to login page.
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}
