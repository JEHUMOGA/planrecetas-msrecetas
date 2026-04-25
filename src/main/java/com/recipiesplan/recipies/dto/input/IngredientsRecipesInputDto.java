package com.recipiesplan.recipies.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRecipesInputDto {
    private Long id;
    private Integer quantity;
    private String unit;
    private Long ingredient;
}
