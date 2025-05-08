package org.example.recipes.follow;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository repo;

    public FollowServiceImpl(FollowRepository repo) { this.repo = repo; }

    @Override
    public boolean isFollowing(String follower, String following) {
        return repo.existsByFollowerIdAndFollowingId(follower, following);
    }

    @Override
    @Transactional
    public void follow(String follower, String following) {
        if (!isFollowing(follower, following)) {
            Follow e = new Follow();
            e.setFollowId(UUID.randomUUID().toString().substring(0,10));
            e.setFollowerId(follower);
            e.setFollowingId(following);
            repo.save(e);
        }
    }

    @Override
    @Transactional
    public void unfollow(String follower, String following) {
        Follow e = repo.findByFollowerIdAndFollowingId(follower, following);
        if (e != null) repo.delete(e);
    }
}