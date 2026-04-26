package com.recipiesplan.recipies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipiesplan.recipies.entities.Ingredient;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long>{
    
}
