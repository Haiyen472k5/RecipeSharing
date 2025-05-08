// src/main/java/org/example/recipes/save/SaveServiceImpl.java
package org.example.recipes.save;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class SaveServiceImpl implements SaveService {
    private final SaveRepository repo;

    public SaveServiceImpl(SaveRepository repo) { this.repo = repo; }

    @Override
    public int countSaves(String recipeId) {
        return repo.countByRecipeId(recipeId);
    }

    @Override
    public boolean hasSaved(String userId, String recipeId) {
        return repo.existsByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    @Transactional
    public void save(String userId, String recipeId) {
        if (!hasSaved(userId, recipeId)) {
            Save e = new Save();
            e.setSaveId(UUID.randomUUID().toString().substring(0,10));
            e.setUserId(userId);
            e.setRecipeId(recipeId);
            repo.save(e);
        }
    }

    @Override
    @Transactional
    public void unsave(String userId, String recipeId) {
        Save e = repo.findByUserIdAndRecipeId(userId, recipeId);
        if (e != null) repo.delete(e);
    }
}
