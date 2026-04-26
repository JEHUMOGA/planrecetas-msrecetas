package com.recipiesplan.recipies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipiesplan.recipies.entities.Recipe;

@Repository
public interface RecipiesRepository extends JpaRepository<Recipe, Long>{
    //@Query("SELECT r FROM Recipe r JOIN FETCH r.ingredients i JOIN FETCH i.ingredient WHERE r.id = ?1")
    //public Recipe getRecipeById(Long id);
}
