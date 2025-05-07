package org.example.recipes.recipe;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipes {

    @Id
    @Column(name = "recipe_id", length = 8, nullable = false, unique = true)
    private String recipeId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "instruction", length = 1000, nullable = false)
    private String instruction;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "ingredients", columnDefinition = "TEXT", nullable = false)
    private String ingredients;

    @Column(name = "author_name", length = 100)
    private String authorName;

    @Column(name = "author_id", length = 10)
    private String authorId;

    // Default constructor
    public Recipes() {}

    // Getters and setters
    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}