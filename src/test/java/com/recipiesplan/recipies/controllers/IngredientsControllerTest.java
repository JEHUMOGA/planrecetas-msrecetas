package com.recipiesplan.recipies.controllers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.recipiesplan.recipies.dto.IngredientDto;
import com.recipiesplan.recipies.entities.Ingredient;
import com.recipiesplan.recipies.services.IngredientsServices;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(IngredientsController.class)
public class IngredientsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private IngredientsServices ingredientsServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnIngredientsPage() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Tomato");
        ingredient.setDescription("Red tomato");


        List<Ingredient> ingredients = List.of(ingredient);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ingredient> page = new PageImpl<>(ingredients, pageable, ingredients.size());

        when(ingredientsServices.getAllIngredients(0, 10)).thenReturn(page);

        mockMvc.perform(get("/list-ingredients?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].name").value("Tomato"))
                .andExpect(jsonPath("$.meta.status").value("OK"));
    }

    @Test
    void shouldSaveIngredient() throws Exception {
        IngredientDto dto = new IngredientDto();
        dto.setName("Salt");
        dto.setDescription("Salt");

        Ingredient saved = new Ingredient();
        saved.setId(1L);
        saved.setName("Salt");

        when(ingredientsServices.saveIngredient(any(IngredientDto.class))).thenReturn(saved);

        mockMvc.perform(post("/ingredient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.meta.status").value("CREATED"));
    }

}
