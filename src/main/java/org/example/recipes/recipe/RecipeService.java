package org.example.recipes.recipe;

import java.util.List;
import java.time.LocalDateTime;

public interface RecipeService {

    Recipes create(Recipes form);
    Recipes update(String id, Recipes form);
    void delete(String id);
    Recipes findById(String id);
    List<Recipes> findLatest();
    List<Recipes> searchByName(String keyword);
    List<Recipes> findByCategory(String category);
    /** Lần đầu tiên tải, lấy N món mới nhất */
    List<Recipes> getInitialRecipes(int limit);

    /** Khi cuộn xuống, lấy N món cũ hơn `before` */
    List<Recipes> getMoreRecipes(LocalDateTime before, int limit);

}