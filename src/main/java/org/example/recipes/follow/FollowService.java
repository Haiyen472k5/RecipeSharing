package org.example.recipes.follow;

public interface FollowService {
    boolean isFollowing(String followerId, String followingId);
    void follow(String followerId, String followingId);
    void unfollow(String followerId, String followingId);
}