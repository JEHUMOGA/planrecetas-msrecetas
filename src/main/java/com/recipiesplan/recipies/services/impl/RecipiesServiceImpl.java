package com.recipiesplan.recipies.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recipiesplan.recipies.dto.RecipeDto;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.mappers.RecipesMapper;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.RecipiesService;

import tools.jackson.databind.ObjectMapper;


@Service
public class RecipiesServiceImpl implements RecipiesService{

    private RecipiesRepository recipiesRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public RecipiesServiceImpl(RecipiesRepository recipiesRepository, ObjectMapper objectMapper) {
        this.recipiesRepository = recipiesRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public Page<Recipe> getAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  recipiesRepository.findAll(pageable);
    }

    @Override
    public Recipe saveRecipe(RecipeDto recipe) {
        Recipe recipeEntity = objectMapper.convertValue(recipe, Recipe.class);
        return recipiesRepository.save(recipeEntity);
    }

    /*private List<IngredientsRecipes> findAllIngredientsRecipes(RecipeDto recipe) {
        return null;
    }*/
    
}
