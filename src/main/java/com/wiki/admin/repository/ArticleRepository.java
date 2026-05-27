/*
 * ArticleRepository.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.repository;

import com.wiki.admin.model.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByOrderByCreatedDateDescIdDesc();

    List<Article> findTop3ByOrderByCreatedDateDescIdDesc();

    List<Article> findByCategoryIdOrderByCreatedDateDescIdDesc(Long categoryId);
}
