package org.example.recipes.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipes, String> {
    // Lấy 10 recipe mới nhất
    List<Recipes> findTop10ByOrderByRecipeIdDesc();
    // Tìm kiếm theo tên
    List<Recipes> findByNameContainingIgnoreCase(String keyword);
    // Tìm theo category
    List<Recipes> findByCategory(String category);
}
