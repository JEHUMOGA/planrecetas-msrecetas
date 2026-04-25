package com.recipiesplan.recipies.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipiesplan.recipies.dto.input.RecipeInputDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.entities.IngredientsRecipes;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.repositories.IngredientsRepository;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.RecipiesService;



@Service
public class RecipiesServiceImpl implements RecipiesService{

    private RecipiesRepository recipiesRepository;
    private IngredientsRepository ingredientsRepository;

    @Autowired
    public RecipiesServiceImpl(RecipiesRepository recipiesRepository, IngredientsRepository ingredientsRepository) {
        this.recipiesRepository = recipiesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Recipe> getAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  recipiesRepository.findAll(pageable);
    }

    @Override
    public Recipe saveRecipe(RecipeInputDto recipeDto) {
        //Recipe recipeEntity = objectMapper.convertValue(recipeDto, Recipe.class);
        //if (recipeEntity.getIngredients() != null) {
        //    recipeEntity.getIngredients().forEach(detail -> detail.setRecipe(recipeEntity));
        //}
        try{
            Recipe recipe = new Recipe();
            recipe.setName(recipeDto.getName());
            recipe.setDescription(recipeDto.getDescription());
            recipe.setInstructions(recipeDto.getInstructions());
            recipe.setTimePreparation(recipeDto.getTimePreparation());
            recipe.setPortions(recipeDto.getPortions());
            recipe.setRecipeType(recipeDto.getRecipeType());
            recipe.setUtensils(recipeDto.getUtensils());
            List<IngredientsRecipes> ingredients = recipeDto.getIngredientsDetails().stream()
                    .map(ingredientsDetails -> {
                        Ingredient ingredient = ingredientsRepository.findById(ingredientsDetails.getId()).orElseThrow(() -> new RuntimeException("Ingredient not found"));
                            IngredientsRecipes ingredientsRecipes = new IngredientsRecipes();
                            ingredientsRecipes.setRecipe(recipe);
                            ingredientsRecipes.setIngredient(ingredient);
                            ingredientsRecipes.setQuantity(ingredientsDetails.getQuantity());
                            ingredientsRecipes.setUnit(ingredientsDetails.getUnit());
                            return ingredientsRecipes;
                    })
                    .collect(Collectors.toList());
            recipe.setIngredients(ingredients);
            return recipiesRepository.save(recipe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
