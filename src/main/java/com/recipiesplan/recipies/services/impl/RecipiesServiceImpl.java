package com.recipiesplan.recipies.services.impl;

import java.util.List;
import java.util.Set;
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
import com.recipiesplan.recipies.repositories.IngredientsRecipesRepository;
import com.recipiesplan.recipies.repositories.IngredientsRepository;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.RecipiesService;



@Service
public class RecipiesServiceImpl implements RecipiesService{

    private RecipiesRepository recipiesRepository;
    private IngredientsRecipesRepository ingredientsRecipesRepository;
    private IngredientsRepository ingredientsRepository;

    @Autowired
    public RecipiesServiceImpl(RecipiesRepository recipiesRepository, IngredientsRecipesRepository ingredientsRecipesRepository, IngredientsRepository ingredientsRepository) {
        this.recipiesRepository = recipiesRepository;
        this.ingredientsRecipesRepository = ingredientsRecipesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Recipe> getAllRecipes(int page, int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> response = recipiesRepository.findAll(pageable);
        return response;
    }

    @Override
    @Transactional
    public Recipe saveRecipe(RecipeInputDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setInstructions(recipeDto.getInstructions());
        recipe.setTimePreparation(recipeDto.getTimePreparation());
        recipe.setPortions(recipeDto.getPortions());
        recipe.setRecipeType(recipeDto.getRecipeType());
        recipe.setUtensils(recipeDto.getUtensils());

        List<IngredientsRecipes> ingredients = recipeDto.getIngredientsDetails().stream()
                .map(detailDto -> {
                    // Cambiado de detailDto.getId() a detailDto.getIngredient()
                    Ingredient ingredient = ingredientsRepository.findById(detailDto.getIngredient())
                            .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + detailDto.getIngredient()));
                    IngredientsRecipes ingredientsRecipes = new IngredientsRecipes();
                    ingredientsRecipes.setRecipe(recipe);
                    ingredientsRecipes.setIngredient(ingredient);
                    ingredientsRecipes.setQuantity(detailDto.getQuantity());
                    ingredientsRecipes.setUnit(detailDto.getUnit());
                    return ingredientsRecipes;
                })
                .collect(Collectors.toList());

        recipe.setIngredients(Set.copyOf(ingredients));
        // Gracias al CascadeType.ALL en la entidad Recipe, al guardar la receta se guardarán sus ingredientes automáticamente
        return recipiesRepository.save(recipe);
    }
    
}
