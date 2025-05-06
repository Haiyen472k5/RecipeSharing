package org.example.recipes;

import java.util.ArrayList;

public class Recipes {
    private String id;
    private String name;
    private String description;
    private String category;
    private ArrayList<String> tags;
    private String author;

    public Recipes(String id, String name, String description, String category, ArrayList<String> tags, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.author = author;
    }

    public Recipes(String name, String description, String author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}