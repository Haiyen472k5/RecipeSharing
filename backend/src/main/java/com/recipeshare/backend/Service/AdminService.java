package com.recipeshare.backend.Service;

import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.DTO.UserPublicDTO;

import java.util.List;

public interface AdminService {
    List<UserPublicDTO> getAllUsers();
    void deleteUser(Long id);
    List<RecipeSimpleDTO> getAllRecipes();
    void deleteRecipe(Long recipeId);

}
