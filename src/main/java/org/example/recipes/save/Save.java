// src/main/java/org/example/recipes/save/Save.java
package org.example.recipes.save;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved")
public class Save {
    @Id
    @Column(name = "save_id", length = 10)
    private String saveId;

    @Column(name = "recipe_id", length = 10, nullable = false)
    private String recipeId;

    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name="create_at")
    private LocalDateTime createdAt;

    public Save() {}

    public String getSaveId() {
        return saveId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setSaveId(String saveId) {
        this.saveId = saveId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
