// src/main/java/org/example/recipes/save/SaveService.java
package org.example.recipes.save;

public interface SaveService {
    int countSaves(String recipeId);
    boolean hasSaved(String userId, String recipeId);
    void save(String userId, String recipeId);
    void unsave(String userId, String recipeId);
}
