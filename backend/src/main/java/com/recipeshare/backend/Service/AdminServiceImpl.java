package com.recipeshare.backend.Service;

import com.recipeshare.backend.DTO.CommentDTO;
import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.Repository.CommentRepository;
import com.recipeshare.backend.Repository.RecipeRepository;
import com.recipeshare.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<UserPublicDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserPublicDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getAvatarUrl(),
                        user.getDateOfBirth(),
                        user.getFollowers().size(),
                        user.getFollowing().size(),
                        user.getRecipes().size(),
                        user.getRecipes().stream()
                                .flatMap(r -> r.getRatings().stream())
                                .mapToInt(r -> r.getScore())
                                .average().orElse(0.0)
                )).toList();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<RecipeSimpleDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipe -> new RecipeSimpleDTO(
                        recipe.getId(),
                        recipe.getTitle(),
                        recipe.getImageUrl(),
                        recipe.getRatings().stream()
                                .mapToInt(r -> r.getScore())
                                .average().orElse(0.0),
                        recipe.getLikes().size(),
                        commentRepository.findByRecipe(recipe).stream()
                                .map(c -> new CommentDTO(
                                        c.getUser().getUsername(),
                                        c.getContent()
                                )).toList()
                )).toList();
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}

