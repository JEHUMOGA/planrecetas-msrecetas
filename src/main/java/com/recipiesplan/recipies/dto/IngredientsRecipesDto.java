package com.recipiesplan.recipies.dto;

import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.entities.Recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRecipesDto {
    private Long id;
    private Integer quantity;
    private String unit;
    private Recipe recipe;
    private Ingredient ingredient;
}
