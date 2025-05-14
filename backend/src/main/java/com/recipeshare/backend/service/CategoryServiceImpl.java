package com.recipeshare.backend.service;

import com.recipeshare.backend.entity.Category;
import com.recipeshare.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new RuntimeException("Tên thể loại không được để trống");
        }

        if (categoryRepository.existsByName(category.getName())) {

            throw new RuntimeException("Thể loại đã tồn tại");
        }

        categoryRepository.save(category);
    }


    @Override
    public void updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Category not found"));
        existingCategory.setName(category.getName());
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
