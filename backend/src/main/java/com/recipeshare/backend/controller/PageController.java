package com.recipeshare.backend.controller;

import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    private String getCurrentUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return (username != null && !username.isBlank()) ? username : "linhngo123";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        String currentUsername = getCurrentUsername(session);
        UserPublicDTO user = userService.getPublicProfile(currentUsername);
        List<RecipeSimpleDTO> posts = userService.getPostedRecipes(currentUsername);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "profile"; // templates/profile.html
    }



    @GetMapping("/edit-profile")
    public String editProfilePage(HttpSession session, Model model) {
        String currentUsername = getCurrentUsername(session);
        UserPublicDTO user = userService.getPublicProfile(currentUsername);
        model.addAttribute("user", user);
        return "edit-profile";
    }


}