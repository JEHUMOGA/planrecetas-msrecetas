package com.recipiesplan.recipies.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.recipiesplan.recipies.entities.Recipie;
import com.recipiesplan.recipies.repositories.RecipieRepository;

import jakarta.validation.constraints.AssertTrue;

@ExtendWith(MockitoExtension.class)
public class RecipiesControllerTest {
    @InjectMocks
    private RecipiesController controller;

    @Mock
    private RecipieRepository repository;

    @Test
    void getAll(){
        ResponseEntity<?> test = controller.getList();
        List<Recipie> lista = (List<Recipie>) test.getBody();
        boolean response = lista.size() == 0;
        assertTrue(response);
    }

    void getAllFailure(){
        controller.getList();
    }

}
