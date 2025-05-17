package org.example.recipes.controller;

import org.example.recipes.recipe.RecipeDetailDTO;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.recipe.Recipes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/recipes")
public class RecipeDetailController {

    private final RecipeService recipeService;

    public RecipeDetailController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<RecipeDetailDTO> getRecipeDetail(@PathVariable("id") String id) {
        Recipes recipe = recipeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy recipe với id=" + id));

        RecipeDetailDTO dto = new RecipeDetailDTO();
        dto.setRecipeId(recipe.getRecipeId());
        dto.setTitle(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setInstructions(recipe.getInstruction());
        dto.setIngredients(recipe.getIngredients());
        dto.setCategory(recipe.getCategory());
        dto.setAuthorId(recipe.getAuthorId());
        dto.setCreatedAt(recipe.getCreatedAt());
        dto.setLikeCount(recipe.getLikeCount());
        dto.setAverageRating(recipe.getAverageRating());
        dto.setSaveCount(recipe.getSaveCount());
        // nếu DTO có thêm trường nào, set thêm ở đây

        return ResponseEntity.ok(dto);
    }
}
