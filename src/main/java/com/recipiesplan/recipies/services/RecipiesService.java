package com.recipiesplan.recipies.services;

import org.springframework.data.domain.Page;

import com.recipiesplan.recipies.entities.Recipe;

public interface RecipiesService {
    public Page<Recipe> getAllRecipes(int page, int size);
   
}