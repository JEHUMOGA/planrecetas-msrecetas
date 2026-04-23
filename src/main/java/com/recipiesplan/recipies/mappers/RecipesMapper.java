package com.recipiesplan.recipies.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recipiesplan.recipies.dto.RecipeDto;
import com.recipiesplan.recipies.entities.Recipe;

@Mapper
public interface RecipesMapper {
    @Mapping(target = "ingredients", ignore = true)
    RecipeDto recipeToRecipeDto(Recipe recipe);
    @Mapping(target = "ingredients", ignore = true)
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
