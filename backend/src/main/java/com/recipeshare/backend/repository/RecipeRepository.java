package com.recipeshare.backend.repository;

import com.recipeshare.backend.entity.Recipe;
import com.recipeshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthor(User user);

}
