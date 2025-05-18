package com.recipeshare.backend.Controller;

import com.recipeshare.backend.DTO.RecipeDetailDTO;
import com.recipeshare.backend.Service.RecipeService;
import com.recipeshare.backend.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired private LikeService likeService;
    @Autowired private RecipeService recipeService;
    @GetMapping("/{id}")
    private ResponseEntity<RecipeDetailDTO> getRecipe(@PathVariable Long id) {
        RecipeDetailDTO dto = recipeService.getRecipeDetail(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/{id}/like")
    public ResponseEntity<Boolean> toggleLike(@PathVariable Long id, @RequestParam String username) {
        boolean liked = likeService.toggleLike(username, id);
        return ResponseEntity.ok(liked);
    }

    @GetMapping("/{id}/liked")
    public ResponseEntity<Boolean> hasLiked(@PathVariable Long id, @RequestParam String username) {
        boolean liked = likeService.hasLiked(username, id);
        return ResponseEntity.ok(liked);
    }



}
