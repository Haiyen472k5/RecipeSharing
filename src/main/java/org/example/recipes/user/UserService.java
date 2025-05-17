package org.example.recipes.user;

import org.example.recipes.recipe.RecipeSimpleDTO;
import org.example.recipes.recipe.Recipes;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserSimpleDTO> getFollowers(String username); /// lay danh sach nguoi dang theo doi minh
    List<UserSimpleDTO> getFollowing(String username); /// lay danh sach nguoi minh dang theo doi
    UserPublicDTO getPublicProfile(String username);
    List<RecipeSimpleDTO> getPostedRecipes(String username);
    List<RecipeSimpleDTO> getSavedRecipes(String username);
    List<RecipeSimpleDTO> getLikedRecipes(String username);
    void updateUserInfo(String currentUsername, UserProfileUpdatedDTO dto);
}