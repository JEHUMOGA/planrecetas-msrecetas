package com.recipiesplan.recipies.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.repositories.IngredientsRepository;
import com.recipiesplan.recipies.services.impl.IngredientsServiceImpl;

import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {
    @Mock
    private IngredientsRepository ingredientsRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private IngredientsServiceImpl ingredientsServices;


    @BeforeEach
    void setUp() {
        ingredientsServices = new IngredientsServiceImpl(ingredientsRepository, objectMapper);
    }

    @Test
    void testFindAll() {
        // Creation mock data
        Ingredient ingredient = makeIngredient();
        List<Ingredient> ingredients = List.of(ingredient);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ingredient> page = new PageImpl<>(ingredients, pageable, ingredients.size());

        // Define mock behavior     
        when(ingredientsRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Execute and verify
        Page<Ingredient> response = ingredientsServices.getAllIngredients(0, 10);
        assertEquals(1, response.getTotalElements());
        verify(ingredientsRepository).findAll(pageable);
        assertNotNull(response.getContent());
    }

    private Ingredient makeIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("test");
        ingredient.setQuantity(1);
        ingredient.setUnit("test");
        return ingredient;
    }
}
