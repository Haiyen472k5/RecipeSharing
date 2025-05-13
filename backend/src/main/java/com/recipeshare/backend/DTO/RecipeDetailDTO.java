package com.recipeshare.backend.DTO;

import java.util.List;

public class RecipeDetailDTO {
    private Long id;
    private String title;
    private String imageUrl;
    private String category;
    private String author;
    private String description;
    private double averageRating;
    private int likeCount;
    private List<CommentDTO> comments;

    public RecipeDetailDTO(Long id, String title, String imageUrl, String category, String author, String description, double averageRating, int likeCount, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.author = author;
        this.description = description;
        this.averageRating = averageRating;
        this.likeCount = likeCount;
        this.comments = comments;
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

    public String getInstructions() {
        return description;
    }

    public void setInstructions(String instructions) {
        this.description = instructions;
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
}

