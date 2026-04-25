package com.recipiesplan.recipies.dto.output;

import java.util.List;

import com.recipiesplan.recipies.dto.IngredientsRecipesDto;
import com.recipiesplan.recipies.dto.RecipeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeOutputDto extends RecipeDto{
    private List<IngredientsRecipesDto> ingredients;
}
