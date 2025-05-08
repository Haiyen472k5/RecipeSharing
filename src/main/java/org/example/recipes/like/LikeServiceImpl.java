package org.example.recipes.like;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repo;

    public LikeServiceImpl(LikeRepository repo) {
        this.repo = repo;
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
            e.setLikeId(UUID.randomUUID().toString().substring(0,10));
            e.setUserId(userId);
            e.setRecipeId(recipeId);
            repo.save(e);
        }
    }

    @Override
    @Transactional
    public void unlike(String userId, String recipeId) {
        Like e = repo.findByUserIdAndRecipeId(userId, recipeId);
        if (e != null) repo.delete(e);
    }
}