package com.recipeshare.backend.User;

import java.time.LocalDate;

public class UserPublicDTO {
    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
    private LocalDate birthDate;
    private int followersCount;
    private int followingCount;
    private int totalPosts;
    private double averageRating;

    public UserPublicDTO() {}

    public UserPublicDTO(Long id, String username, String fullName, String avatarUrl, LocalDate birthDate, int followersCount, int followingCount, int totalPosts, double averageRating) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.totalPosts = totalPosts;
        this.averageRating = averageRating;
    }

    public UserPublicDTO(String username, String fullName, String avatarUrl, LocalDate birthDate, int followersCount, int followingCount, int totalPosts, double averageRating) {
        this.username = username;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.totalPosts = totalPosts;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
