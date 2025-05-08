package org.example.recipes.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, String> {
    int countByRecipeId(String recipeId);
    boolean existsByUserIdAndRecipeId(String userId, String recipeId);
    Like findByUserIdAndRecipeId(String userId, String recipeId);
}