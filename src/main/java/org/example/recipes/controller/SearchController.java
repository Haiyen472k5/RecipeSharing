package org.example.recipes.controller;

import org.example.recipes.recipe.RecipeSimpleDTO;
import org.example.recipes.recipe.RecipeService;
import org.example.recipes.user.UserSimpleDTO;
import org.example.recipes.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final RecipeService recipeService;
    private final UserService userService;

    public SearchController(RecipeService recipeService,
                            UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    /**
     * type = "recipe" hoặc "user"
     * q = từ khóa tìm
     */
    @GetMapping
    public String search(
            @RequestParam("type") String type,
            @RequestParam("q") String q,
            Model model
    ) {
        model.addAttribute("type", type);
        model.addAttribute("q", q);

        if ("recipe".equals(type)) {
            List<RecipeSimpleDTO> recipes = recipeService.searchRecipes(q);
            model.addAttribute("recipes", recipes);
        } else if ("user".equals(type)) {
            List<UserSimpleDTO> users = userService.searchUsers(q);
            model.addAttribute("users", users);
        } else {
            model.addAttribute("recipes", List.of());
            model.addAttribute("users", List.of());
        }

        return "search/results";
    }
}