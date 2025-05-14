package com.recipeshare.backend.service;

import com.recipeshare.backend.DTO.CommentDTO;
import com.recipeshare.backend.DTO.RecipeDetailDTO;
import com.recipeshare.backend.entity.Rating;
import com.recipeshare.backend.entity.Recipe;
import com.recipeshare.backend.repository.CommentRepository;
import com.recipeshare.backend.repository.RecipeRepository;
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
        int likeCount = recipe.getLikes().size();
        List<CommentDTO> comments = commentRepository.findByRecipe(recipe).stream()
                .map(c -> new CommentDTO(c.getUser().getUsername(), c.getContent()))
                .toList();

        return new RecipeDetailDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getImageUrl(),
                recipe.getCategory().getName(),
                recipe.getAuthor().getUsername(),
                recipe.getDescription(),
                avgRating,
                likeCount,
                comments
        );
    }


}
