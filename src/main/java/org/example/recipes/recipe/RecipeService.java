package org.example.recipes.recipe;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

public interface RecipeService {

    Recipes create(Recipes form);
    Recipes update(String id, Recipes form);
    void delete(String id);
    List<Recipes> findLatest();
    List<Recipes> searchByName(String keyword);
    List<Recipes> findByCategory(String category);
    /** Lần đầu tiên tải, lấy N món mới nhất */
    List<Recipes> getInitialRecipes(int limit);
    List<Recipes> findAll();
    /** Khi cuộn xuống, lấy N món cũ hơn `before` */
    List<Recipes> getMoreRecipes(LocalDateTime before, int limit);
    List<Recipes> findPostedByUser(String authorId);
    Optional<Recipes> findById(String recipeId);
}