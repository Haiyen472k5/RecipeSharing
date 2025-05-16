package com.recipeshare.backend.Recipe;

import com.recipeshare.backend.Comment.CommentDTO;
import com.recipeshare.backend.Rating.Rating;
import com.recipeshare.backend.Comment.CommentRepository;
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
