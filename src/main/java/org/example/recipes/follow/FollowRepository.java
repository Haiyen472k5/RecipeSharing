package org.example.recipes.follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, String> {
    boolean existsByFollowerIdAndFollowingId(String follower, String following);
    Follow findByFollowerIdAndFollowingId(String follower, String following);
}