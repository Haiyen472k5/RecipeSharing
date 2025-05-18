package com.recipeshare.backend.Service;

import com.recipeshare.backend.DTO.CommentDTO;
import com.recipeshare.backend.DTO.RecipeDetailDTO;
import com.recipeshare.backend.Entity.Rating;
import com.recipeshare.backend.Entity.Recipe;
import com.recipeshare.backend.Repository.CommentRepository;
import com.recipeshare.backend.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;
    CommentRepository commentRepository;


    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, CommentRepository commentRepository) {
        this.recipeRepository = recipeRepository;
        this.commentRepository = commentRepository;
    }

    public RecipeDetailDTO getRecipeDetail(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        double avgRating = recipe.getRatings().stream()
                .mapToInt(Rating::getScore).average().orElse(0.0);
        avgRating = Math.round(avgRating * 100.0) / 100.0;
        int ratingCount = recipe.getRatings().size();
        int likeCount = recipe.getLikes().size();
        List<CommentDTO> comments = commentRepository.findByRecipe(recipe).stream()
                .map(c -> new CommentDTO(c.getUser().getUsername(), c.getContent()))
                .toList();
        int commentCount = comments.size();

        return new RecipeDetailDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getCategory().getName(),
                recipe.getImageUrl(),
                recipe.getAuthor().getUsername(),
                recipe.getAuthor().getAvatarUrl(),
                recipe.getDescription(),
                avgRating,
                ratingCount,
                likeCount,
                comments,
                commentCount
        );
    }


}
