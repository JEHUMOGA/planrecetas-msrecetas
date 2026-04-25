package com.recipiesplan.recipies.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.recipiesplan.recipies.dto.input.IngredientsRecipesInputDto;
import com.recipiesplan.recipies.dto.input.RecipeInputDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.entities.IngredientsRecipes;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.repositories.IngredientsRepository;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.impl.RecipiesServiceImpl;


@ExtendWith(MockitoExtension.class)
public class RecipesServiceTest {
    @Mock
    private RecipiesRepository recipiesRepository;
    @Mock
    private IngredientsRepository ingredientsRepository;

    @InjectMocks
    private RecipiesServiceImpl recipiesService;

    @BeforeEach
    void setUp() {
        recipiesService = new RecipiesServiceImpl(recipiesRepository, ingredientsRepository);
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
        //Mockito.when(ingredientsRecipesRepository.findAll()).thenReturn(List.of(makeIngredientsRecipes()));

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
        RecipeInputDto recipeDto = makeRecipeInputDto();


        // Define mock behavior
        Mockito.when(recipiesRepository.save(any(Recipe.class))).thenReturn(recipe);
        Mockito.when(ingredientsRepository.findById(any(Long.class))).thenReturn(Optional.of(makeIngredient()));

        // Execute and verify
        Recipe response = recipiesService.saveRecipe(recipeDto);
        assertEquals(recipe, response);
        verify(recipiesRepository).save(any(Recipe.class));
    }

    private Recipe makeRecipe() {
        List<IngredientsRecipes> ingredients = new ArrayList<>();
        ingredients.add(makeIngredientsRecipes());

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
        ingredient.setDescription("test");
        return ingredient;
    }

    private IngredientsRecipes makeIngredientsRecipes() {
        IngredientsRecipes ingredientsRecipes = new IngredientsRecipes();
        ingredientsRecipes.setQuantity(1);
        ingredientsRecipes.setUnit("test");
        ingredientsRecipes.setIngredient(makeIngredient());
        return ingredientsRecipes;
    }

    private RecipeInputDto makeRecipeInputDto() {
        RecipeInputDto recipeDto = new RecipeInputDto();
        recipeDto.setName("test");
        recipeDto.setDescription("test");
        recipeDto.setInstructions("test");
        recipeDto.setIngredientsDetails(List.of(makeIngredientsRecipesDto()));
        recipeDto.setTimePreparation("test");
        recipeDto.setPortions(1);
        recipeDto.setUtensils(List.of("Cuchara"));
        recipeDto.setRecipeType("test");
        return recipeDto;
    }

    private IngredientsRecipesInputDto makeIngredientsRecipesDto() {
        IngredientsRecipesInputDto ingredientsRecipesDto = new IngredientsRecipesInputDto();
        ingredientsRecipesDto.setId(1L);
        ingredientsRecipesDto.setQuantity(1);
        ingredientsRecipesDto.setUnit("test");
        ingredientsRecipesDto.setIngredient(makeIngredient().getId());
        return ingredientsRecipesDto;
    }
}
