package com.recipeshare.backend.repository;

import com.recipeshare.backend.entity.Like;
import com.recipeshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user); /// Bài đã thích
}
