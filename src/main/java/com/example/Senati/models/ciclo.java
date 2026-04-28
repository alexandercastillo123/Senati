package com.example.Senati.models;

import com.example.Senati.models.ciclo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciclo")
public class ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre_ciclo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre_ciclo;
    }

    public void setNombre(String nombre_ciclo) {
        this.nombre_ciclo = nombre_ciclo;
    }
}