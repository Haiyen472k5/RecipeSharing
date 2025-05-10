package org.example.recipes.recipe;

import org.example.recipes.comment.Comment;
import org.example.recipes.comment.CommentDTO;
import org.example.recipes.comment.CommentService;
import org.example.recipes.like.LikeService;
import org.example.recipes.save.SaveService;
import org.example.recipes.rate.RateService;
import org.example.recipes.media.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeDetailController {

    private final RecipeService recipeService;
    private final MediaService mediaService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final SaveService saveService;
    private final RateService rateService;

    public RecipeDetailController(RecipeService recipeService,
                                  MediaService mediaService,
                                  CommentService commentService,
                                  LikeService likeService,
                                  SaveService saveService,
                                  RateService rateService) {
        this.recipeService   = recipeService;
        this.mediaService    = mediaService;
        this.commentService  = commentService;
        this.likeService     = likeService;
        this.saveService     = saveService;
        this.rateService     = rateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDetailDTO> getRecipeDetail(
            @PathVariable String id,
            Principal principal) {

        // 1. Lấy recipe
        Recipes recipe = recipeService.findById(id);

        // 2. Map sang DTO
        RecipeDetailDTO dto = new RecipeDetailDTO();
        dto.setRecipeId(recipe.getRecipeId());
        dto.setTitle(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setIngredients(splitLines(recipe.getIngredients()));
        dto.setInstructions(splitLines(recipe.getInstruction()));
        dto.setCategory(recipe.getCategory());
        dto.setAuthorId(recipe.getAuthorId());
        dto.setCreatedAt(recipe.getCreatedAt());

        // 3. Media URLs
        dto.setMediaUrls(mediaService.getMediaUrls(id));

        // 4. Tương tác
        dto.setLikeCount(likeService.countLikes(id));
        dto.setHasLiked(principal != null && likeService.hasLiked(principal.getName(), id));
        dto.setSaveCount(saveService.countSaves(id));
        dto.setHasSaved(principal != null && saveService.hasSaved(principal.getName(), id));
        dto.setAverageRating(rateService.getAverageRating(id));
        dto.setRatingCount(rateService.countRating(id));
        dto.setUserRating(principal != null ? rateService.getUserRating(principal.getName(), id) : 0);

        // 5. Comments
        List<CommentDTO> comments = commentService.listComments(id).stream()
                .map(c -> {
                    CommentDTO cd = new CommentDTO();
                    cd.setCommentId(c.getCommentId());
                    cd.setRecipeId(c.getRecipeId());
                    cd.setUserId(c.getUserId());
                    cd.setContent(c.getContent());
                    cd.setCreatedAt(c.getCreatedAt());
                    return cd;
                })
                .collect(Collectors.toList());
        dto.setComments(comments);

        return ResponseEntity.ok(dto);
    }

    private List<String> splitLines(String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        return Arrays.stream(raw.split("\\r?\\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
