package com.recipiesplan.recipies.entities;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Recipies")
public class Recipie {
    @Id
    private UUID id;
    private String nombre;
    private String descripcion;
    private String pasos;
    private int caloriasTotales;
}
