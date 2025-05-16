package com.recipeshare.backend.Admin;

import com.recipeshare.backend.Recipe.RecipeSimpleDTO;
import com.recipeshare.backend.User.UserPublicDTO;

import java.util.List;

public interface AdminService {
    List<UserPublicDTO> getAllUsers();
    void deleteUser(Long id);
    List<RecipeSimpleDTO> getAllRecipes();
    void deleteRecipe(Long recipeId);

}
