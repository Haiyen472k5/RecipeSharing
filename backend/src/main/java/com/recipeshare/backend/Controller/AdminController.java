package com.recipeshare.backend.Controller;

import com.recipeshare.backend.Service.AdminService;
import com.recipeshare.backend.DTO.RecipeSimpleDTO;
import com.recipeshare.backend.DTO.UserPublicDTO;
import com.recipeshare.backend.Entity.Category;
import com.recipeshare.backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoryService categoryService;

    // users list
    @GetMapping("/users")
    public List<UserPublicDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("Da xoa nguoi dung thanh cong");
    }

    // danh sach tat ca bai viet (hoac bai vi pham)
    @GetMapping("/recipes")
    public List<RecipeSimpleDTO> getAllRecipes() {
        return adminService.getAllRecipes();
    }

    // xoa bai viet
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        adminService.deleteRecipe(id);
        return ResponseEntity.ok("Da xoa cong thuc thanh cong");
    }

    // danh sach category
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    // them the loai moi
    @PostMapping("/categories")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        try {
            categoryService.addCategory(category);
            return ResponseEntity.ok("Đã thêm thể loại mới");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // cap nhat the loai
    @PutMapping("/categories/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok("Da cap nhat the loai thanh cong");
    }

    // xoa the loai
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Da xoa the loai.");
    }
}
