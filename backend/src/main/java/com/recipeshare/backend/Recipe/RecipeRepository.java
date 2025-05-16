package com.recipeshare.backend.Recipe;

import com.recipeshare.backend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthor(User user);


}
