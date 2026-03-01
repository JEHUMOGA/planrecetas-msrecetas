package com.recipiesplan.recipies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipiesplan.recipies.entities.Recipie;
import com.recipiesplan.recipies.repositories.RecipieRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "recipies")
public class RecipiesController {
    @Autowired
    private RecipieRepository repository;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Recipie> list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
}
