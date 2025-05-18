package com.recipeshare.backend.Entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String imageUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private  Set<Like> likes;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Save> saves;



    @ManyToOne
    private Category category;

    public Recipe() {}

    public Recipe(Long id, String title, String imageUrl, String description, User author, Set<Rating> ratings, Set<Like> likes, Set<Save> saves, Category category) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.author = author;
        this.ratings = ratings;
        this.likes = likes;
        this.saves = saves;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Save> getSaves() {
        return saves;
    }

    public void setSaves(Set<Save> saves) {
        this.saves = saves;
    }



}
