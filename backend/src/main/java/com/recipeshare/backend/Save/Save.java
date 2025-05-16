package com.recipeshare.backend.Save;

import com.recipeshare.backend.Recipe.Recipe;
import com.recipeshare.backend.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saves")
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private LocalDateTime savedAt;

    public Save(){}

    public Save(long id, User user, LocalDateTime savedAt, Recipe recipe) {
        this.id = id;
        this.user = user;
        this.savedAt = savedAt;
        this.recipe = recipe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
