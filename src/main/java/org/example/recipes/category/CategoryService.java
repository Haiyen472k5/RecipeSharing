package org.example.recipes.category;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    /**
     * Record the usage of a category (increment or create).
     */
    @Transactional
    public void recordCategory(String name) {
        repo.findById(name)
                .ifPresentOrElse(
                        Category::increment,
                        () -> repo.save(new Category(name))
                );
    }

    /**
     * Suggest categories matching a prefix.
     */
    @Transactional(readOnly = true)
    public List<String> suggest(String prefix) {
        return repo.findByNameStartingWithIgnoreCaseOrderByUsageCountDesc(prefix)
                .stream().map(Category::getName).toList();
    }

    /**
     * Get all categories for filter dropdown.
     */
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return repo.findAllByOrderByNameAsc()
                .stream().map(Category::getName).toList();
    }

    /**
     * Get top N categories by usage count.
     */
    @Transactional(readOnly = true)
    public List<String> getTopCategories(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return repo.findAllByOrderByUsageCountDesc(pageable)
                .stream().map(Category::getName).toList();
    }
}
