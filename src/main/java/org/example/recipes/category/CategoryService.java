package org.example.recipes.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void recordCategory(String name) {
        repo.findById(name)
                .ifPresentOrElse(
                        cat -> cat.increment(),
                        () -> repo.save(new Category(name))
                );
    }

    @Transactional(readOnly = true)
    public List<String> suggest(String prefix) {
        return repo.findByNameStartingWithIgnoreCaseOrderByUsageCountDesc(prefix)
                .stream().map(Category::getName).toList();
    }
}