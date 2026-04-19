package com.recipiesplan.recipies.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recipiesplan.recipies.entities.Recipe;
import com.recipiesplan.recipies.repositories.RecipiesRepository;
import com.recipiesplan.recipies.services.RecipiesService;

@Service
public class RecipiesServiceImpl implements RecipiesService{

    @Autowired
    private RecipiesRepository recipiesRepository;

    @Override
    public Page<Recipe> getAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  recipiesRepository.findAll(pageable);
    }
    
}
