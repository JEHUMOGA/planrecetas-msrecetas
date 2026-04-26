package com.recipiesplan.recipies.entities;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipies")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String instructions;
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IngredientsRecipes> ingredients = new LinkedHashSet<>();
    
    private String timePreparation;
    private Integer portions;
    
    @ElementCollection
    @CollectionTable(name = "recipe_utensils", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> utensils;
    private String recipeType;
}
