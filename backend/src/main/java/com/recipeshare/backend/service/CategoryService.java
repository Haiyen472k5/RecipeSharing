package com.recipeshare.backend.service;

import com.recipeshare.backend.entity.Category;

import java.util.*;

public interface CategoryService {
    List<Category> getAll();
    void addCategory(Category category);
    void updateCategory(Long id, Category category);
    void deleteCategory(Long id);

}
