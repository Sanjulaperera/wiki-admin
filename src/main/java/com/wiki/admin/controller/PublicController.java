/*
 * PublicController.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.controller;

import com.wiki.admin.model.Category;
import com.wiki.admin.service.ArticleService;
import com.wiki.admin.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    public PublicController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("articles", articleService.findRecentArticles());
        return "home";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleService.findAllArticles());
        return "articles/list";
    }

    @GetMapping("/articles/{id}")
    public String articleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findArticle(id));
        return "articles/detail";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories/list";
    }

    @GetMapping("/categories/{id}/articles")
    public String articlesByCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("articles", articleService.findArticlesByCategory(id));
        return "articles/category";
    }
}
