// src/main/java/org/example/recipes/rate/RateServiceImpl.java
package org.example.recipes.rate;

import org.example.recipes.Entity.Rate;
import org.example.recipes.login.IdGeneratorService;
import org.example.recipes.recipe.RecipeRepository;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.recipe.Recipes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    private final RateRepository repo;
    private final IdGeneratorService idGenerator;
    private final RateRepository rateRepo;
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepo;

    public RateServiceImpl(RateRepository repo, IdGeneratorService idGenerator, RateRepository rateRepo, RecipeService recipeService, RecipeRepository recipeRepo) {
        this.repo = repo;
        this.idGenerator = idGenerator;
        this.rateRepo = rateRepo;
        this.recipeService = recipeService;
        this.recipeRepo = recipeRepo;
    }

    @Override
    public float getAverageRating(String recipeId) {
        Float avg = repo.findAverageRating(recipeId);
        return avg == null ? 0f : avg;
    }

    @Override
    public Integer getUserRating(String userId, String recipeId) {
        Rate e = repo.findByUserIdAndRecipeId(userId, recipeId);
        return e == null ? null : e.getRating();
    }

    @Override
    @Transactional
    public void rate(String userId, String recipeId, int rating) {
        Rate e = repo.findByUserIdAndRecipeId(userId, recipeId);
        if (e == null) {
            e = new Rate();
            e.setRateId(idGenerator.generateId());
            e.setUserId(userId);
            e.setRecipeId(recipeId);
            e.setCreatedAt(java.time.LocalDateTime.now());
        }
        e.setRating(rating);
        repo.save(e);

        // Cập nhật averageRating trong bảng recipes
        //List<Rate> ratings = rateRepo.findByRecipeId(recipeId);
        /*float avg = (float) ratings.stream().mapToInt(Rate::getRating).average().orElse(0);
        avg = Math.round(avg * 100.0) / 100.0f;
        Recipes recipe = recipeService.findById(recipeId).orElseThrow();
        recipe.setAverageRating(avg);
        recipeRepo.save(recipe);*/





    }

    @Override
    @Transactional
    public void removeRating(String userId, String recipeId) {
        Rate e = repo.findByUserIdAndRecipeId(userId, recipeId);
        if (e != null) repo.delete(e);
    }

    @Override
    public int countRating(String recipeId) {
        // Trả về số lượt đánh giá
        return repo.countByRecipeId(recipeId);
    }

}
