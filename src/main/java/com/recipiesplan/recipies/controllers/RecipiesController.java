package com.recipiesplan.recipies.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.recipiesplan.recipies.dto.Meta;
import com.recipiesplan.recipies.dto.RecipeDto;
import com.recipiesplan.recipies.dto.Response;
import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.services.RecipiesService;
import com.recipiesplan.recipies.utils.Utilities;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class RecipiesController {

    private final RecipiesService recipiesService;

    public RecipiesController(RecipiesService recipiesService) {
        this.recipiesService = recipiesService;
    }

    @GetMapping("list-recipies")
    public ResponseEntity<?> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Recipe> recipesPage = recipiesService.getAllRecipes(page, size);
        
        Meta meta = new Meta(HttpStatus.OK, HttpStatusCode.valueOf(200), Utilities.timestampGeneration());
        Response<Page<Recipe>> response = new com.recipiesplan.recipies.dto.Response<>();
        response.setData(recipesPage);
        response.setMeta(meta);

        return ResponseEntity.ok(response);
    }

    @PostMapping("recipe")
    public ResponseEntity<?> postRecipe(@RequestBody RecipeDto recipe) {
        Recipe savedRecipe = recipiesService.saveRecipe(recipe);
        Meta meta = new Meta(HttpStatus.CREATED, HttpStatusCode.valueOf(201), Utilities.timestampGeneration());
        Response<Recipe> response = new com.recipiesplan.recipies.dto.Response<>();
        response.setData(savedRecipe);
        response.setMeta(meta);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    
}
