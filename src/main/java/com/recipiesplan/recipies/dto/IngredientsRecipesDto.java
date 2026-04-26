package com.recipiesplan.recipies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRecipesDto {
    private Long id;
    private Integer quantity;
    private String unit;
    private Long recipe;
    private Long ingredient;
}
