package com.recipeshare.backend.Service;

import com.recipeshare.backend.DTO.RecipeDetailDTO;

public interface RecipeService {
    RecipeDetailDTO getRecipeDetail(Long id);

}
