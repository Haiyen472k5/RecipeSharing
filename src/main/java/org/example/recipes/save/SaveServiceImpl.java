package org.example.recipes.save;

import org.example.recipes.login.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SaveServiceImpl implements SaveService {
    private final SaveRepository repo;
    private final IdGeneratorService idGenerator;

    public SaveServiceImpl(SaveRepository repo, IdGeneratorService idGenerator) {
        this.repo = repo;
        this.idGenerator = idGenerator;
    }

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
        if (userId == null || userId.isBlank() || recipeId == null || recipeId.isBlank()) {
            throw new IllegalArgumentException("userId and recipeId must not be null or blank");
        }
        if (!hasSaved(userId, recipeId)) {
            Save e = new Save();
            e.setSaveId(idGenerator.generateId());
            e.setUserId(userId);
            e.setRecipeId(recipeId);
            e.setCreatedAt(LocalDateTime.now());
            repo.save(e);
        }
    }

    @Override
    @Transactional
    public void unsave(String userId, String recipeId) {
        if (userId == null || userId.isBlank() || recipeId == null || recipeId.isBlank()) {
            throw new IllegalArgumentException("userId and recipeId must not be null or blank");
        }
        Save e = repo.findByUserIdAndRecipeId(userId, recipeId);
        if (e != null) {
            repo.delete(e);
        }
    }
}