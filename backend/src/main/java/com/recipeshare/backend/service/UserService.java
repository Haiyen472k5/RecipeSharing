package com.recipeshare.backend.service;

import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.DTO.UserProfileUpdatedDTO;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.DTO.UserSimpleDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserSimpleDTO> getFollowers(String username); /// lay danh sach nguoi dang theo doi minh
    List<UserSimpleDTO> getFollowing(String username); /// lay danh sach nguoi minh dang theo doi
    UserPublicDTO getPublicProfile(String username);
    List<RecipeSimpleDTO> getPostedRecipes(String username);
    List<RecipeSimpleDTO> getSavedRecipes(String username);
    List<RecipeSimpleDTO> getLikedRecipes(String username);
    void updateUserInfo(String currentUsername, UserProfileUpdatedDTO dto);

}
