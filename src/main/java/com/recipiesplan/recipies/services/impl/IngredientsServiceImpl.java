package com.recipiesplan.recipies.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recipiesplan.recipies.dto.IngredientDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.repositories.IngredientsRepository;
import com.recipiesplan.recipies.services.IngredientsServices;

import tools.jackson.databind.ObjectMapper;

@Service
public class IngredientsServiceImpl implements IngredientsServices {

    private IngredientsRepository ingredientsRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public IngredientsServiceImpl(IngredientsRepository ingredientsRepository, ObjectMapper objectMapper) {
        this.ingredientsRepository = ingredientsRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ingredientsRepository.findAll(pageable);
    }

    @Override
    public Ingredient saveIngredient(IngredientDto ingredient) {
        Ingredient ingredientEntity = objectMapper.convertValue(ingredient, Ingredient.class);
        return ingredientsRepository.save(ingredientEntity);
    }

    @Override
    public Ingredient updateIngredient(Long id, IngredientDto ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngredient'");
    }

    @Override
    public void deleteIngredient(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        Ingredient ingredient = ingredientsRepository.findById(id).orElse(null);
        return ingredient;
    }
    
}
