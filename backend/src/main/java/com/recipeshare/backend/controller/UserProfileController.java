package com.recipeshare.backend.controller;

import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.DTO.UserProfileUpdatedDTO;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.DTO.UserSimpleDTO;
import com.recipeshare.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserProfileController {

    private final UserService userService;



    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/{username}/profile")
    public String showUserProfilePage() {
        return "profile.html"; // map đúng với profile.html trong /templates
    }

    @GetMapping("/{username}/public")
    public ResponseEntity<UserPublicDTO> getPublicProfile(@PathVariable String username) {
        UserPublicDTO dto = userService.getPublicProfile(username);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<List<UserSimpleDTO>> getFollowing(@PathVariable String username) {
        List<UserSimpleDTO> following = userService.getFollowing(username);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserSimpleDTO>> getFollowers(@PathVariable String username) {
        List<UserSimpleDTO> followers = userService.getFollowers(username);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<List<RecipeSimpleDTO>> getPosts(@PathVariable String username) {
        List<RecipeSimpleDTO> recipes = userService.getPostedRecipes(username);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{username}/saves")
    public ResponseEntity<List<RecipeSimpleDTO>> getSavedRecipes(@PathVariable String username) {
        List<RecipeSimpleDTO> savedRecipes = userService.getSavedRecipes(username);
        return ResponseEntity.ok(savedRecipes);
    }

    @GetMapping("/{username}/likes")
    public ResponseEntity<List<RecipeSimpleDTO>> getLikedRecipes(@PathVariable String username) {
        List<RecipeSimpleDTO> likedRecipes = userService.getLikedRecipes(username);
        return ResponseEntity.ok(likedRecipes);
    }

    @PutMapping("/{username}/edit")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable String username,
            @RequestBody UserProfileUpdatedDTO dto,
            HttpSession session) {

        userService.updateUserInfo(username, dto);

        // 🔁 Cập nhật session nếu username đã thay đổi
        session.setAttribute("username", dto.getUsername());

        return ResponseEntity.ok("Cập nhật thành công");
    }


}
