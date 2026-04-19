package com.recipiesplan.recipies.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private List<IngredientDto> ingredients;
    private String timePreparation;
    private Integer portions;
    private List<String> utensils;
    private String recipeType;
}
