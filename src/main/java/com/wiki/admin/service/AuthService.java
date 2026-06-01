/*
 * AuthService.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.service;

import com.wiki.admin.model.Admin;
import com.wiki.admin.repository.AdminRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Admin> authenticate(String username, String password) {

        if (username == null || password == null) {
            return Optional.empty();
        }

        String trimmedUsername = username.trim();

        if (trimmedUsername.isEmpty() || password.isEmpty()) {
            return Optional.empty();
        }

        return adminRepository.findByUsernameIgnoreCase(trimmedUsername)
                .filter(admin -> passwordEncoder.matches(password, admin.getPasswordHash()));
    }

    public String buildErrorMessage() {
        return "Invalid username or password. Please try again.";
    }
}
