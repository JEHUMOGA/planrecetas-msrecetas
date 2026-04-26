package com.recipiesplan.recipies.dto.input;

import java.util.List;

import com.recipiesplan.recipies.dto.RecipeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeInputDto extends RecipeDto{
    private List<IngredientsRecipesInputDto> ingredientsDetails;
}

