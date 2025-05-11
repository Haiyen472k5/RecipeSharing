package org.example.recipes.like;

import org.example.recipes.login.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repo;
    private final IdGeneratorService idGenerator;

    public LikeServiceImpl(LikeRepository repo, IdGeneratorService idGenerator) {
        this.repo = repo;
        this.idGenerator = idGenerator;
    }

    @Override
    public int countLikes(String recipeId) {
        return repo.countByRecipeId(recipeId);
    }

    @Override
    public boolean hasLiked(String userId, String recipeId) {
        return repo.existsByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    @Transactional
    public void like(String userId, String recipeId) {
        if (!hasLiked(userId, recipeId)) {
            Like e = new Like();
            e.setLikeId(idGenerator.generateId());
            e.setUserId(userId);
            e.setRecipeId(recipeId);
            e.setCreatedAt(java.time.LocalDateTime.now());
            repo.save(e);
        }
    }

    @Override
    @Transactional
    public void unlike(String userId, String recipeId) {
        if (repo.findByUserIdAndRecipeId(userId, recipeId) != null) {
            repo.deleteByUserIdAndRecipeId(userId, recipeId);
        }
    }
}