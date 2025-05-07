package org.example.recipes.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.recipes.category.CategoryService;

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

    // Form tạo bài
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("recipeForm", new Recipes());
        return "recipes/new";
    }

    // Xử lý submit
    @PostMapping("/new")
    public String create(@ModelAttribute Recipes recipeForm) {
        recipeService.create(recipeForm);
        return "redirect:/";
    }

    // Gợi ý category (AJAX)
    @GetMapping("/categories/suggest")
    @ResponseBody
    public java.util.List<String> suggestCategories(@RequestParam("q") String q) {
        return categoryService.suggest(q);
    }

    // Edit
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable String id, Model model) {
        Recipes r = recipeService.findById(id);
        Recipes form = new Recipes();
        // map fields
        form.setName(r.getName());
        form.setInstruction(r.getInstruction());
        form.setDescription(r.getDescription());
        form.setIngredients(r.getIngredients());
        form.setCategory(r.getCategory());
        form.setAuthorId(r.getAuthorId());
        model.addAttribute("recipeForm", form);
        model.addAttribute("id", id);
        return "recipes/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Recipes form) {
        recipeService.update(id, form);
        return "redirect:/recipes/" + id;
    }

    // Delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        recipeService.delete(id);
        return "redirect:/";
    }
}