package org.example.recipes.like;

public interface LikeService {
    int countLikes(String recipeId);
    boolean hasLiked(String userId, String recipeId);
    void like(String userId, String recipeId);
    void unlike(String userId, String recipeId);
}