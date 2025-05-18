package com.recipeshare.backend.Controller;

import com.recipeshare.backend.DTO.RecipeDetailDTO;
import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.Service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    private String getCurrentUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return (username != null && !username.isBlank()) ? username : "lin123";
    }

    /// hiển thị trang cá nhân của người dùng
    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        String currentUsername = getCurrentUsername(session);
        UserPublicDTO user = userService.getPublicProfile(currentUsername);
       List<RecipeSimpleDTO> posts = userService.getPostedRecipes(currentUsername);
       List<RecipeSimpleDTO> saves = userService.getSavedRecipes(currentUsername);
        List<RecipeSimpleDTO> likes = userService.getLikedRecipes(currentUsername);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
       model.addAttribute("saves", saves);
       model.addAttribute("likes", likes);
        return "profile"; // templates/profile.html
    }

    /// xem trang cá nhân của người khác
    @GetMapping("/user/{username}")
    public String viewOtherProfile(HttpSession session, @PathVariable String username, Model model) {
        String currentUsername = getCurrentUsername(session);
        // Lấy thông tin người dùng được xem
        UserPublicDTO user = userService.getPublicProfile(username);

        // Lấy danh sách bài viết đã đăng
        List<RecipeSimpleDTO> posts = userService.getPostedRecipes(username);

        // Kiểm tra người xem có phải chính chủ không
        boolean isOwner = username.equals(currentUsername);
        if (isOwner) {
            return "redirect:/profile";
        }
        // Truyền sang view
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "other-profile"; // Tương ứng templates/user-profile.html

    }


    /// trang chỉnh sửa trang cá nhân
    @GetMapping("/edit-profile")
    public String editProfilePage(HttpSession session, Model model) {
        String currentUsername = getCurrentUsername(session);
        UserPublicDTO user = userService.getPublicProfile(currentUsername);
        model.addAttribute("user", user);
        return "edit-profile";
    }


    @GetMapping("/set-user/{username}")
    public String setUserSession(@PathVariable String username, HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/profile";
    }

    /// trang hiển thị công thức
    @GetMapping("/recipes/{id}")
    public String recipeDetailPage(@PathVariable Long id, HttpSession session, Model model) {
        RecipeDetailDTO recipe = recipeService.getRecipeDetail(id);
        String currentUsername = getCurrentUsername(session); // lấy từ session
        model.addAttribute("recipe", recipe);
        model.addAttribute("currentUsername", currentUsername); // thêm dòng này
        return "recipe-detail";
    }

    /// trang admin
    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }


}