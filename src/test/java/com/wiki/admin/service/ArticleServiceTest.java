/*
 * ArticleServiceTest.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wiki.admin.model.Article;
import com.wiki.admin.model.ArticleForm;
import com.wiki.admin.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void emptyArticleFormReturnsValidationErrors() {
        ArticleForm form = new ArticleForm();

        assertThat(articleService.validateArticleForm(form))
                .contains("Title is required.", "Content is required.", "Please choose a valid category.");
    }

    @Test
    void validArticleFormCanBeSaved() {
        Category category = categoryService.findAllCategories().get(0);
        ArticleForm form = new ArticleForm(
                null,
                "Test Article",
                "This article has enough content to pass the validation rule.",
                category.getId());

        Article savedArticle = articleService.saveArticle(form);

        assertThat(savedArticle.getId()).isNotNull();
        assertThat(savedArticle.getCategory().getId()).isEqualTo(category.getId());
        assertThat(articleService.findArticle(savedArticle.getId()).getTitle()).isEqualTo("Test Article");
    }
}
