/*
 * DataSeeder.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.config;

import com.wiki.admin.model.Admin;
import com.wiki.admin.model.Article;
import com.wiki.admin.model.Category;
import com.wiki.admin.repository.AdminRepository;
import com.wiki.admin.repository.ArticleRepository;
import com.wiki.admin.repository.CategoryRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            AdminRepository adminRepository,
            CategoryRepository categoryRepository,
            ArticleRepository articleRepository,
            PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdmins();
        seedContent();
    }

    private void seedAdmins() {
        if (adminRepository.count() > 0) {
            return;
        }

        adminRepository.save(new Admin("1", passwordEncoder.encode("1"), "Marker"));
        adminRepository.save(new Admin("sanjula", passwordEncoder.encode("123"), "Sanjula Perera"));
    }

    private void seedContent() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category history = categoryRepository.save(new Category(
                "History",
                "Important events, people, and places from the past."));
        Category science = categoryRepository.save(new Category(
                "Science",
                "Topics that explain the natural world and living things."));
        Category programming = categoryRepository.save(new Category(
                "Programming",
                "Software development and object oriented programming notes."));

        articleRepository.save(new Article(
                "American Revolution",
                "The American Revolution was an ideological and political revolution that occurred in British America between 1765 and 1791. The American colonies formed independent states and created a new nation based on ideas of representation and liberty.",
                LocalDate.of(2023, 3, 20),
                history));

        articleRepository.save(new Article(
                "Ants",
                "Ants are social insects that live in organised colonies. They work together to find food, protect their nests, and care for young ants. Their teamwork makes them a useful example when learning about living systems.",
                LocalDate.of(2023, 4, 14),
                science));

        articleRepository.save(new Article(
                "Object-oriented programming",
                "Object-oriented programming is a programming approach based on objects that contain data and behaviour. In Java, classes are used as blueprints, and objects are created from those classes to solve real problems.",
                LocalDate.of(2023, 5, 8),
                programming));
    }
}
