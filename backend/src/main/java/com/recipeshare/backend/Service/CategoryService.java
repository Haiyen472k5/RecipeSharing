package com.recipeshare.backend.Service;

import com.recipeshare.backend.Entity.Category;

import java.util.*;

public interface CategoryService {
    List<Category> getAll();
    void addCategory(Category category);
    void updateCategory(Long id, Category category);
    void deleteCategory(Long id);

}
