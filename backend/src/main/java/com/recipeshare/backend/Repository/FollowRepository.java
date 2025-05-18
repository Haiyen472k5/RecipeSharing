package com.recipeshare.backend.Repository;

import com.recipeshare.backend.Entity.Follow;
import com.recipeshare.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    int countByFollower(User user);    // dem so nguoi ma user nay đang theo dõi
    int countByFollowing(User user);   // đếm so nguoi đang theo dõi người này

    List<Follow> findByFollower(User follower);  // lay ra danh sach nhung nguoi ma follower dang theo doi
    List<Follow> findByFollowing(User following); // lay ra danh sach nhung nguoi dang theo doi following
}
