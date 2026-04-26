package com.recipiesplan.recipies.services;

import org.springframework.data.domain.Page;

import com.recipiesplan.recipies.dto.IngredientDto;
import com.recipiesplan.recipies.entities.Ingredient;

public interface IngredientsServices {
    public Page<Ingredient> getAllIngredients(int page, int size);
    public Ingredient saveIngredient(IngredientDto ingredient);
    public Ingredient updateIngredient(Long id, IngredientDto ingredient);
    public void deleteIngredient(Long id);
    public Ingredient getIngredientById(Long id);
}
