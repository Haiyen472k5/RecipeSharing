package com.recipeshare.backend.Rating;

import com.recipeshare.backend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Tính điểm trung bình tất cả các bài viết của user
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.recipe.author = :user")
    Double findAverageScoreByAuthor(@Param("user") User user);

}
