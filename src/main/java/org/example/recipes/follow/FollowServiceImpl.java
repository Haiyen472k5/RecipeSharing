package org.example.recipes.follow;

import org.example.recipes.login.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository repo;
    private final IdGeneratorService idGenerator;

    public FollowServiceImpl(FollowRepository repo, IdGeneratorService idGenerator) {
        this.repo = repo;
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean isFollowing(String follower, String following) {
        return repo.existsByFollowerIdAndFollowingId(follower, following);
    }

    @Override
    @Transactional
    public void follow(String follower, String following) {
        if (!isFollowing(follower, following)) {
            Follow e = new Follow();
            e.setFollowId(idGenerator.generateId());
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