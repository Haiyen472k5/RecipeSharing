package com.recipeshare.backend.service;

import com.recipeshare.backend.DTO.RecipeDetailDTO;

public interface RecipeService {
    RecipeDetailDTO getRecipeDetail(Long id);

}
