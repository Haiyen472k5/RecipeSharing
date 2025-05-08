package org.example.recipes.follow;

import jakarta.persistence.*;

@Entity
@Table(name = "follows",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id","following_id"}))
public class Follow {
    @Id
    @Column(name="follow_id", length=10)
    private String followId;

    @Column(name="follower_id", length=10, nullable=false)
    private String followerId;

    @Column(name="following_id", length=10, nullable=false)
    private String followingId;

    public Follow() {}

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }
}