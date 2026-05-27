/*
 * ArticleService.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.service;

import com.wiki.admin.model.Article;
import com.wiki.admin.model.ArticleForm;
import com.wiki.admin.model.Category;
import com.wiki.admin.repository.ArticleRepository;
import com.wiki.admin.repository.CategoryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Article> findRecentArticles() {
        return articleRepository.findTop3ByOrderByCreatedDateDescIdDesc();
    }

    public List<Article> findAllArticles() {
        return articleRepository.findByOrderByCreatedDateDescIdDesc();
    }

    public List<Article> findArticlesByCategory(Long categoryId) {
        return articleRepository.findByCategoryIdOrderByCreatedDateDescIdDesc(categoryId);
    }

    public Article findArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
    }

    public ArticleForm newArticleForm() {
        return new ArticleForm();
    }

    public ArticleForm editArticleForm(Long id) {
        Article article = findArticle(id);
        return new ArticleForm(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCategory().getId());
    }

    public List<String> validateArticleForm(ArticleForm form) {
        List<String> errors = new ArrayList<>();

        if (isBlank(form.getTitle())) {
            errors.add("Title is required.");
        } else if (form.getTitle().trim().length() < 3) {
            errors.add("Title must be at least 3 characters.");
        }

        if (isBlank(form.getContent())) {
            errors.add("Content is required.");
        } else if (form.getContent().trim().length() < 30) {
            errors.add("Content must be at least 30 characters.");
        }

        if (form.getCategoryId() == null || !categoryRepository.existsById(form.getCategoryId())) {
            errors.add("Please choose a valid category.");
        }

        return errors;
    }

    public Article saveArticle(ArticleForm form) {
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Article article = form.getId() == null ? new Article() : findArticle(form.getId());
        article.setTitle(form.getTitle().trim());
        article.setContent(form.getContent().trim());
        article.setCategory(category);

        if (article.getCreatedDate() == null) {
            article.setCreatedDate(LocalDate.now());
        }

        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
