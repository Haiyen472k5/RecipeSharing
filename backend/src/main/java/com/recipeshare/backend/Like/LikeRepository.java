package com.recipeshare.backend.Like;

import com.recipeshare.backend.Recipe.Recipe;
import com.recipeshare.backend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user); /// Bài đã thích
    boolean existsByUserAndRecipe(User user, Recipe recipe);
    void deleteByUserAndRecipe(User user, Recipe recipe);
}
