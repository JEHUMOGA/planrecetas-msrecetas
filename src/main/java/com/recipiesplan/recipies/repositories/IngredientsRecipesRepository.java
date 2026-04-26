package com.recipiesplan.recipies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipiesplan.recipies.entities.IngredientsRecipes;

@Repository
public interface IngredientsRecipesRepository extends JpaRepository<IngredientsRecipes, Long>{
}
