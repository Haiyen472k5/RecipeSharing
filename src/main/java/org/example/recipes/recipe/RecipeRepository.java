package org.example.recipes.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipes, String> {

    // --- infinite‐scroll methods ---
    List<Recipes> findByOrderByCreatedAtDesc(Pageable page);
    List<Recipes> findByCreatedAtBeforeOrderByCreatedAtDesc(LocalDateTime before, Pageable page);

    default List<Recipes> findLatest(int limit) {
        return findByOrderByCreatedAtDesc(Pageable.ofSize(limit));
    }

    // --- search & category ---
    List<Recipes> findByNameContainingIgnoreCase(String keyword);
    List<Recipes> findByCategory(String category);
    Page<Recipes> findByCategory(String category, Pageable pageable);
    List<Recipes> findByAuthorIdOrderByCreatedAtDesc(String authorId);
    Optional<Recipes> findByRecipeId(String recipeId);

}
