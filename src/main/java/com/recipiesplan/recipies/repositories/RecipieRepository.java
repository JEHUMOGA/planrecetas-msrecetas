package com.recipiesplan.recipies.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.recipiesplan.recipies.entities.Recipie;

@Service
public interface RecipieRepository extends JpaRepository<Recipie, UUID> {
    
}
