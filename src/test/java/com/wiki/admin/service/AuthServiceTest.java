/*
 * AuthServiceTest.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wiki.admin.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void validMarkerLoginReturnsAdmin() {
        assertThat(authService.authenticate("1", "1")).isPresent();
    }

    @Test
    void wrongPasswordDoesNotAuthenticate() {
        assertThat(authService.authenticate("1", "wrong")).isEmpty();
    }

    @Test
    void seededPasswordIsStoredAsHash() {
        String storedPassword = adminRepository.findByUsernameIgnoreCase("1")
                .orElseThrow()
                .getPasswordHash();

        assertThat(storedPassword).isNotEqualTo("1");
        assertThat(storedPassword).startsWith("$2");
    }
}
