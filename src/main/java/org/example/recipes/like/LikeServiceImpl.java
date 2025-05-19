package org.example.recipes.like;

import org.example.recipes.Entity.Like;
import org.example.recipes.Entity.Users;
import org.example.recipes.login.IdGeneratorService;
import org.example.recipes.recipe.RecipeRepository;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.recipe.Recipes;
import org.example.recipes.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repo;
    private final RecipeService recipeService;
    private final IdGeneratorService idGen;
    private final UserRepository userRepository;

    public LikeServiceImpl(
            LikeRepository repo,
            RecipeService recipeService,
            IdGeneratorService idGen,
            UserRepository userRepository) {
        this.repo = repo;
        this.recipeService = recipeService;
        this.idGen = idGen;
        this.userRepository = userRepository;
    }

    @Override
    public int countLikes(String recipeId) {
        return repo.countByRecipeId(recipeId);
    }

    @Override
    @Transactional
    public boolean hasLiked(String userId, String recipeId) {
        return repo.existsByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    @Transactional
    public boolean toggleLike(String userId, String recipeId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User không tồn tại: " + userId);
        }
        if (repo.existsByUserIdAndRecipeId(userId, recipeId)) {
                repo.deleteByUserIdAndRecipeId(userId, recipeId);
                return false;
        } else {
            Like like = new Like();
            like.setLikeId(idGen.generateId());
            like.setUserId(userId);
            like.setRecipeId(recipeId);
            like.setCreatedAt(java.time.LocalDateTime.now());
            repo.save(like);
            return true;
        }
    }

    @Override
    @Transactional
    public void unlike(String userId, String recipeId) {
        if (repo.findByUserIdAndRecipeId(userId, recipeId) != null) {
            repo.deleteByUserIdAndRecipeId(userId, recipeId);
        }
    }

    @Override
    public List<Recipes> getLikedRecipes(String userId) {
        return repo.findByUserId(userId).stream()
                .map(like -> recipeService.findById(like.getRecipeId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Recipe không tồn tại: " + like.getRecipeId())))
                .collect(Collectors.toList());
    }
}