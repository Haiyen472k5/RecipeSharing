package org.example.recipes.comment;

public class CommentRequest {
    private String userId;
    private String recipeId;
    private String content;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRecipeId() { return recipeId; }
    public void setRecipeId(String recipeId) { this.recipeId = recipeId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
