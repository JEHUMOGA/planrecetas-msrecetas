package com.recipiesplan.recipies.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRecipesOutput {
    private Long id;
    private Integer quantity;
    private String unit;
    private String ingredientName;
}
