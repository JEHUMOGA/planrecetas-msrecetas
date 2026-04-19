package com.recipiesplan.recipies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDto {
    private Long id;
    private String name;
    private Integer quantity;
    private String unit;
}
