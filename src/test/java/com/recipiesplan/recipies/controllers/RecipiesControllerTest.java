package com.recipiesplan.recipies.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.recipiesplan.recipies.dto.IngredientsRecipesDto;
import com.recipiesplan.recipies.dto.RecipeDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.entities.IngredientsRecipes;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.services.RecipiesService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(RecipiesController.class)
public class RecipiesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean 
    private RecipiesService recipiesService;

    @Test
    void shouldReturnRecipiesPages() throws Exception {
        // Creation mock data
        Recipe recipe = makeRecipe();
        List<Recipe> recipes = List.of(recipe);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Recipe> page = new PageImpl<>(recipes, pageable, recipes.size());
        
        // Define mock behavior
        when(recipiesService.getAllRecipes(0,10)).thenReturn(page);

        mockMvc.perform(get("/list-recipies?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].name").value("test"))
                .andExpect(jsonPath("$.meta.status").value("OK"))
                .andExpect(jsonPath("$.meta.transacionID").exists());
    }

    @Test
    void shouldSaveRecipeDate() throws Exception {
        // Creation mock data
        Recipe recipe = makeRecipe();
        RecipeDto recipeDto = makeRecipeDto();

        // Define mock behavior
        when(recipiesService.saveRecipe(recipeDto)).thenReturn(recipe);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.meta.status").value("CREATED"))
                .andExpect(jsonPath("$.meta.transacionID").exists());
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

    private RecipeDto makeRecipeDto() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("test");
        recipeDto.setDescription("test");
        recipeDto.setInstructions("test");
        recipeDto.setIngredients(List.of(makeIngredientsRecipesDto()));
        recipeDto.setTimePreparation("test");
        recipeDto.setPortions(1);
        recipeDto.setUtensils(List.of("Cuchara"));
        recipeDto.setRecipeType("test");
        return recipeDto;
    }

    private IngredientsRecipesDto makeIngredientsRecipesDto() {
        IngredientsRecipesDto ingredientsRecipesDto = new IngredientsRecipesDto();
        ingredientsRecipesDto.setQuantity(1);
        ingredientsRecipesDto.setUnit("test");
        ingredientsRecipesDto.setIngredient(makeIngredient());
        return ingredientsRecipesDto;
    }

}
