package com.recipiesplan.recipies.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.recipiesplan.recipies.dto.input.RecipeInputDto;
import com.recipiesplan.recipies.entities.Recipe;

@Service
public interface RecipiesService {
    public Page<Recipe> getAllRecipes(int page, int size);
   public Recipe saveRecipe(RecipeInputDto recipe);

}