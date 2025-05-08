// src/main/java/org/example/recipes/save/SaveRepository.java
package org.example.recipes.save;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveRepository extends JpaRepository<Save, String> {
    int countByRecipeId(String recipeId);
    boolean existsByUserIdAndRecipeId(String userId, String recipeId);
    Save findByUserIdAndRecipeId(String userId, String recipeId);
}
