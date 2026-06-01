/*
 * AuthController.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.controller;

import com.wiki.admin.model.Admin;
import com.wiki.admin.model.LoginForm;
import com.wiki.admin.service.AuthService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAdmin") != null) {
            return "redirect:/admin";
        }

        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @ModelAttribute("loginForm") LoginForm loginForm,
            Model model,
            HttpSession session) {

        Optional<Admin> admin = authService.authenticate(loginForm.getUsername(), loginForm.getPassword());

        if (admin.isPresent()) {
            session.setAttribute("loggedInAdmin", admin.get());
            return "redirect:/admin";

        } else {
            model.addAttribute("errorMessage", authService.buildErrorMessage());
            model.addAttribute("loginForm", loginForm);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
