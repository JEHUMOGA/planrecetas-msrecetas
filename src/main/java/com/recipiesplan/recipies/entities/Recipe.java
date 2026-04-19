package com.recipiesplan.recipies.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "recipies")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String instructions;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    private String timePreparation;
    private Integer portions;
    @ElementCollection
    private List<String> utensils;
    private String recipeType;
}
