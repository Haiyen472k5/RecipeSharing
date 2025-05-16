package com.recipeshare.backend.Recipe;

import com.recipeshare.backend.Comment.CommentDTO;

import java.util.List;

public class RecipeDetailDTO {
    private Long id;
    private String title;
    private String imageUrl;
    private String category;
    private String author;
    private String authorUrl;
    private String description;
    private double averageRating;
    private int ratingCount;
    private int likeCount;
    private List<CommentDTO> comments;
    private int commentCount;

    public RecipeDetailDTO(Long id, String title, String category, String imageUrl, String author, String authorUrl, String description, double averageRating, int ratingCount, int likeCount, List<CommentDTO> comments, int commentCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.author = author;
        this.authorUrl = authorUrl;
        this.description = description;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.likeCount = likeCount;
        this.comments = comments;
        this.commentCount = commentCount;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    // Getters and Setters (hoặc dùng Lombok nếu muốn)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }


    public int getCommentCount() {
        return commentCount;
    }

}

