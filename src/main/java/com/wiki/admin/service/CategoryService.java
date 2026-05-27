/*
 * CategoryService.java
 * Author: Sanjula Perera | Student ID: S1532573
 * Subject: BIT235 Object Oriented Programming
 * Assessment: Assessment 2, Part B - Wiki Content Management and Admin Console
 * Date: 2026
 */

package com.wiki.admin.service;

import com.wiki.admin.model.Category;
import com.wiki.admin.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findByOrderByNameAsc();
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
