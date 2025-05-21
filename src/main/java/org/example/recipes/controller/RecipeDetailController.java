package org.example.recipes.controller;

import org.example.recipes.Entity.Users;
import org.example.recipes.comment.CommentDTO;
import org.example.recipes.comment.CommentRequest;
import org.example.recipes.comment.CommentService;
import org.example.recipes.like.LikeService;
import org.example.recipes.rate.RateRequest;
import org.example.recipes.rate.RateService;
import org.example.recipes.recipe.RecipeDetailDTO;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.recipe.Recipes;
import org.example.recipes.save.SaveService;
import org.example.recipes.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeDetailController {

    @Autowired private LikeService likeService;
    @Autowired private RecipeService recipeService;
    @Autowired private UserRepository userRepository;
    @Autowired private SaveService saveService;
    @Autowired private CommentService commentService;
    @Autowired private RateService rateService;

    public RecipeDetailController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
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
        dto.setAuthorName(recipe.getAuthorName());
        dto.setAuthorUrl(recipe.getAuthorUrl());
        dto.setCreatedAt(recipe.getCreatedAt());
        dto.setLikeCount(recipe.getLikeCount());
        dto.setAverageRating(recipe.getAverageRating());
        dto.setSaveCount(recipe.getSaveCount());
        dto.setImageUrl(recipe.getAvatarUrl());
        List<CommentDTO> comments = recipeService.findCommentsByRecipeId(id);
        dto.setComments(comments);
        dto.setCommentCount(comments.size());


        // nếu DTO có thêm trường nào, set thêm ở đây

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Boolean> toggleLike(@PathVariable String id, @RequestParam String userId) {
        boolean liked = likeService.toggleLike(userId, id);
        return ResponseEntity.ok(liked);
    }

    @GetMapping("/{id}/liked")
    public ResponseEntity<Boolean> hasLiked(@PathVariable String id, @RequestParam String userId) {
        boolean liked = likeService.hasLiked(userId, id);
        return ResponseEntity.ok(liked);
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<Boolean> toggleSave(@PathVariable String id, @RequestParam String userId) {
        boolean saved = saveService.toggleSave(userId, id);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}/saved")
    public ResponseEntity<Boolean> hasSaved(@PathVariable String id, @RequestParam String userId) {
        boolean saved = saveService.hasSaved(userId, id);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> postComment(@PathVariable String id, @RequestBody CommentRequest request) {
        try {
            commentService.addComment(request.getUserId(), id, request.getContent());
            return ResponseEntity.ok("Gửi bình luận thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/rating")
    public ResponseEntity<?> postRating(@PathVariable String id, @RequestBody RateRequest request) {
        try {
            rateService.rate(request.getUserId(), id, request.getRating());
            return  ResponseEntity.ok("Đánh giá thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

}
