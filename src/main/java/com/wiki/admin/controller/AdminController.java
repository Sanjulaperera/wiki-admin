/*
 * AdminController.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.controller;

import com.wiki.admin.model.ArticleForm;
import com.wiki.admin.service.ArticleService;
import com.wiki.admin.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    public AdminController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("articles", articleService.findAllArticles());
        return "admin/dashboard";
    }

    @GetMapping("/articles/new")
    public String newArticle(Model model) {
        addFormData(model, articleService.newArticleForm(), "Create Article", List.of());
        return "admin/article-form";
    }

    @GetMapping("/articles/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        addFormData(model, articleService.editArticleForm(id), "Edit Article", List.of());
        return "admin/article-form";
    }

    @PostMapping("/articles/save")
    public String saveArticle(@ModelAttribute("articleForm") ArticleForm articleForm, Model model) {
        List<String> errors = articleService.validateArticleForm(articleForm);

        if (!errors.isEmpty()) {
            String pageTitle = articleForm.getId() == null ? "Create Article" : "Edit Article";
            addFormData(model, articleForm, pageTitle, errors);
            return "admin/article-form";
        }

        articleService.saveArticle(articleForm);
        return "redirect:/admin";
    }

    @PostMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/admin";
    }

    private void addFormData(Model model, ArticleForm articleForm, String pageTitle, List<String> errors) {
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("errors", errors);
    }
}
