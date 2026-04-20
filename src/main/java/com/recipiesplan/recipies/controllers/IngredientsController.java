package com.recipiesplan.recipies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipiesplan.recipies.dto.IngredientDto;
import com.recipiesplan.recipies.dto.Meta;
import com.recipiesplan.recipies.dto.Response;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.services.IngredientsServices;
import com.recipiesplan.recipies.utils.Utilities;

@RestController
public class IngredientsController {
    @Autowired
    private IngredientsServices ingredientsServices;

    @GetMapping("list-ingredients")
    public ResponseEntity<?> getAllIngredients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Ingredient> ingredientsPage = ingredientsServices.getAllIngredients(page, size);

        Meta meta = new Meta(HttpStatus.OK, HttpStatusCode.valueOf(200), Utilities.timestampGeneration());
        Response<Page<Ingredient>> response = new Response<>();
        response.setData(ingredientsPage);
        response.setMeta(meta);

        return ResponseEntity.ok(response);
    }

    @PostMapping("ingredient")
    public ResponseEntity<?> postIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient savedIngredient = ingredientsServices.saveIngredient(ingredientDto);
        Meta meta = new Meta(HttpStatus.CREATED, HttpStatusCode.valueOf(201), Utilities.timestampGeneration());
        Response<Ingredient> response = new Response<>();
        response.setData(savedIngredient);
        response.setMeta(meta);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
