package com.recipiesplan.recipies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipiesplan.recipies.entities.Recipe;

@Repository
public interface RecipiesRepository extends JpaRepository<Recipe, Long>{
    
}
