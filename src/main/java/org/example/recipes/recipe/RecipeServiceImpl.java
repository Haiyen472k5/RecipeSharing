package org.example.recipes.recipe;

import org.example.recipes.login.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.recipes.category.CategoryService;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repo;
    private final IdGeneratorService idGen;
    private final CategoryService catService;

    public RecipeServiceImpl(RecipeRepository repo,
                             IdGeneratorService idGen,
                             CategoryService catService) {
        this.repo = repo;
        this.idGen = idGen;
        this.catService = catService;
    }

    @Override
    @Transactional
    public Recipes create(Recipes form) {
        String id = idGen.generateId();
        Recipes r = new Recipes();
        r.setRecipeId(id);
        r.setName(form.getName());
        r.setInstruction(form.getInstruction());
        r.setDescription(form.getDescription());
        r.setIngredients(form.getIngredients());
        r.setCategory(form.getCategory());
        r.setAuthorId(form.getAuthorId());
        // optional
        // r.setImageUrl(form.getImageUrl()); // add field as needed
        repo.save(r);
        catService.recordCategory(form.getCategory());
        return r;
    }

    @Override
    @Transactional
    public Recipes update(String id, Recipes form) {
        Recipes r = repo.findById(id).orElseThrow();
        r.setName(form.getName());
        r.setInstruction(form.getInstruction());
        r.setDescription(form.getDescription());
        r.setIngredients(form.getIngredients());
        // nếu category thay đổi thì record mới
        if (!r.getCategory().equals(form.getCategory())) {
            r.setCategory(form.getCategory());
            catService.recordCategory(form.getCategory());
        }
        // xử lý image/video/notes tương tự
        return r;
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Recipes findById(String id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<Recipes> findLatest() {
        return repo.findTop10ByOrderByRecipeIdDesc();
    }

    @Override
    public List<Recipes> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Recipes> findByCategory(String category) {
        return repo.findByCategory(category);
    }
}