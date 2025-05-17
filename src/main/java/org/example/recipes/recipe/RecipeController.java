package org.example.recipes.recipe;

import org.example.recipes.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService,
                            CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    // Hiển thị form tạo mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("recipeForm", new Recipes());
        return "recipes/new";
    }

    // Xử lý submit form tạo
    @PostMapping("/new")
    public String createRecipe(@ModelAttribute("recipeForm") Recipes recipeForm) {
        recipeService.create(recipeForm);
        return "redirect:/";
    }

    // Hiển thị form sửa
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Recipes recipe = recipeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Recipe not found: " + id));
        model.addAttribute("recipeForm", recipe);
        model.addAttribute("id", id);
        return "recipes/edit";
    }

    // Xử lý submit form sửa
    @PostMapping("/{id}/edit")
    public String updateRecipe(@PathVariable("id") String id,
                               @ModelAttribute("recipeForm") Recipes recipeForm) {
        recipeService.update(id, recipeForm);
        return "redirect:/recipes/" + id;
    }

    // Xoá recipe
    @PostMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable("id") String id) {
        recipeService.delete(id);
        return "redirect:/";
    }

    // Gợi ý category trả về JSON
    @GetMapping("/categories/suggest")
    @ResponseBody
    public List<String> suggestCategories(@RequestParam("q") String q) {
        return categoryService.suggest(q);
    }
}
