package com.recipeshare.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")

public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private LocalDateTime ratedAt;

    public Rating() {}

    public Rating(long id, int score, User user, Recipe recipe, LocalDateTime ratedAt) {
        this.id = id;
        this.score = score;
        this.user = user;
        this.recipe = recipe;
        this.ratedAt = ratedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public LocalDateTime getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(LocalDateTime ratedAt) {
        this.ratedAt = ratedAt;
    }
}
