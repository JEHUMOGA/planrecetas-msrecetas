package com.recipiesplan.recipies.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "det_ingredients_recipes")
public class IngredientsRecipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String unit;
    @JsonIgnore//it is necesary for avoid infinite recursion
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Recipe.class)
    @JoinColumn(name = "recipe_id", nullable = true)   
    private Recipe recipe;
    @OneToOne(fetch = FetchType.LAZY,targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
