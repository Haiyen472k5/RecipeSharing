package com.recipeshare.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower; /// người đi theo dõi

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following; /// người bị theo dõi

    private LocalDate followedAt;

    public Follow() {}

    public Follow(User follower, User following, LocalDate followedAt) {
        this.follower = follower;
        this.following = following;
        this.followedAt = followedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public LocalDate getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(LocalDate followedAt) {
        this.followedAt = followedAt;
    }
}
