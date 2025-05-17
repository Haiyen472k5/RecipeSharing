package org.example.recipes.recipe;

import org.example.recipes.login.IdGeneratorService;
import org.example.recipes.category.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Recipes> getInitialRecipes(int limit) {
        // “first page” → use the default findLatest(limit)
        return repo.findLatest(limit);
    }

    @Override
    public List<Recipes> getMoreRecipes(LocalDateTime before, int limit) {
        // “cursor pagination”
        return repo.findByCreatedAtBeforeOrderByCreatedAtDesc(before, PageRequest.of(0, limit));
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
        repo.save(r);
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
        if (!r.getCategory().equals(form.getCategory())) {
            r.setCategory(form.getCategory());
            catService.recordCategory(form.getCategory());
        }
        return r;
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recipes> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Recipes> findLatest() {
        // legacy “top 10 by ID” behavior, if you still need it
        return repo.findLatest(10);
    }

    @Override
    public List<Recipes> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Recipes> findByCategory(String category) {
        return repo.findByCategory(category);
    }

    @Override
    public Optional<Recipes> findById(String recipeId) {
        return repo.findById(recipeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recipes> findPostedByUser(String authorId) {
        return repo.findByAuthorIdOrderByCreatedAtDesc(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeSimpleDTO> searchRecipes(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    private RecipeSimpleDTO toSimpleDTO(Recipes r) {
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        dto.setId(r.getRecipeId());
        dto.setTitle(r.getName());
        dto.setImageUrl(r.getAvatarUrl());
        dto.setAverageRating(r.getAverageRating());
        dto.setLikeCount(r.getLikeCount());
        return dto;
    }
}
