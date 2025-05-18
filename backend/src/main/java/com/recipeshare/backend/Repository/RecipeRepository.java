package com.recipeshare.backend.Repository;

import com.recipeshare.backend.Entity.Recipe;
import com.recipeshare.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthor(User user);


}
