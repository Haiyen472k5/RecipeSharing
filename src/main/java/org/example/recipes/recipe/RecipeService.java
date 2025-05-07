package org.example.recipes.recipe;

import java.util.List;

public interface RecipeService {
    Recipes create(Recipes form);
    Recipes update(String id, Recipes form);
    void delete(String id);
    Recipes findById(String id);
    List<Recipes> findLatest();
    List<Recipes> searchByName(String keyword);
    List<Recipes> findByCategory(String category);
}