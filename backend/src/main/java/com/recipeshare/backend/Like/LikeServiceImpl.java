package com.recipeshare.backend.Like;

import com.recipeshare.backend.Recipe.Recipe;
import com.recipeshare.backend.User.User;
import com.recipeshare.backend.User.UserRepository;
import com.recipeshare.backend.Recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired private LikeRepository likeRepository;
    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;

    @Transactional
    @Override
    public boolean toggleLike(String username, Long recipeId) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();

        if (likeRepository.existsByUserAndRecipe(user, recipe)) {
            likeRepository.deleteByUserAndRecipe(user, recipe);
            return false;
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setRecipe(recipe);
            like.setLikedAt(java.time.LocalDate.now());
            likeRepository.save(like);
            return true;
        }
    }

    @Transactional
    @Override
    public boolean hasLiked(String username, Long recipeId) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        return likeRepository.existsByUserAndRecipe(user, recipe);
    }
}
