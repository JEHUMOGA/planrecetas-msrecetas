package com.recipiesplan.recipies.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.recipiesplan.recipies.dto.IngredientDto;
import com.recipiesplan.recipies.dto.RecipeDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.impl.RecipiesServiceImpl;

import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class RepositoryTest {
    @Mock
    private RecipiesRepository recipiesRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private RecipiesServiceImpl recipiesService;

    @BeforeEach
    void setUp() {
        recipiesService = new RecipiesServiceImpl(recipiesRepository, objectMapper);
    }


    @Test
    void testFindAll() {
        // Creation mock data
        Recipe recipe = makeRecipe();
        List<Recipe> recipes = List.of(recipe);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Recipe> page = new PageImpl<>(recipes, pageable, recipes.size());

        // Define mock behavior
        Mockito.when(recipiesRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Execute and verify
        Page<Recipe> response = recipiesService.getAllRecipes(0, 10);
        assertEquals(1, response.getTotalElements());
        verify(recipiesRepository).findAll(pageable);
        assertNotNull(response.getContent());
    }

    @Test
    void testFindAllWithoutData(){
        // Creation mock data
        Pageable pageable = PageRequest.of(0, 10);
        Page<Recipe> page = new PageImpl<>(List.of(), pageable, 0);

        // Define mock behavior
        Mockito.when(recipiesRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Execute and verify
        Page<Recipe> response = recipiesService.getAllRecipes(0, 10);
        assertEquals(0, response.getTotalElements());
        verify(recipiesRepository).findAll(pageable);
        assertNotNull(response.getContent());

    }

    @Test
    void testSaveData(){
        // Creation mock data
        Recipe recipe = makeRecipe();
        RecipeDto recipeDto = makeRecipeDto();


        // Define mock behavior
        Mockito.when(recipiesRepository.save(any(Recipe.class))).thenReturn(recipe);

        // Execute and verify
        Recipe response = recipiesService.saveRecipe(recipeDto);
        assertEquals(recipe, response);
        verify(recipiesRepository).save(recipe);
    }

    private Recipe makeRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(makeIngredient());

        List<String> utensils = new ArrayList<>();
        utensils.add("Cuchara");


        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setDescription("test");
        recipe.setInstructions("test");
        recipe.setIngredients(ingredients);
        recipe.setTimePreparation("test");
        recipe.setPortions(1);
        recipe.setUtensils(utensils);
        recipe.setRecipeType("test");
        return recipe;
    }


    private Ingredient makeIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("test");
        ingredient.setQuantity(1);
        ingredient.setUnit("test");
        return ingredient;
    }

    private RecipeDto makeRecipeDto() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("test");
        recipeDto.setDescription("test");
        recipeDto.setInstructions("test");
        recipeDto.setIngredients(List.of(makeIngredientDto()));
        recipeDto.setTimePreparation("test");
        recipeDto.setPortions(1);
        recipeDto.setUtensils(List.of("Cuchara"));
        recipeDto.setRecipeType("test");
        return recipeDto;
    }

    private IngredientDto makeIngredientDto() {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setName("test");
        ingredientDto.setQuantity(1);
        ingredientDto.setUnit("test");
        return ingredientDto;
    }

}
