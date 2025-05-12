package com.recipeshare.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "likes")

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private LocalDate likedAt;

    public Like() {}

    public Like(User user, Recipe recipe, LocalDate likedAt) {
        this.user = user;
        this.recipe = recipe;
        this.likedAt = likedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public LocalDate getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDate likedAt) {
        this.likedAt = likedAt;
    }
}
