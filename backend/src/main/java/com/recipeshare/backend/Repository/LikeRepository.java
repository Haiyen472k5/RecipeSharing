package com.recipeshare.backend.Repository;

import com.recipeshare.backend.Entity.Like;
import com.recipeshare.backend.Entity.Recipe;
import com.recipeshare.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user); /// Bài đã thích
    boolean existsByUserAndRecipe(User user, Recipe recipe);
    void deleteByUserAndRecipe(User user, Recipe recipe);
}
