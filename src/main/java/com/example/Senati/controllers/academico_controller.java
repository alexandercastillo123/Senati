package com.example.Senati.controllers;

import com.example.Senati.services.academico_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/academico")
public class academico_controller {

    @Autowired
    private academico_service service;

    @GetMapping("/cursos/{id}")
    public List<Map<String, Object>> getCursos(@PathVariable("id") Integer id) {
        return service.misCursos(id);
    }

    @GetMapping("/notas/{id}")
    public List<Map<String, Object>> getNotas(@PathVariable("id") Integer id) {
        return service.misNotas(id);
    }

    @GetMapping("/horario/{id}")
    public List<Map<String, Object>> getHorario(@PathVariable("id") Integer id) {
        return service.miHorario(id);
    }

    @GetMapping("/promedio/{id}")
    public List<Map<String, Object>> getPromedio(@PathVariable("id") Integer id) {
        return service.promedioCiclo(id);
    }

    @GetMapping("/carreras")
    public List<Map<String, Object>> getCarreras() {
        return service.listarCarreras();
    }

    @GetMapping("/ciclos")
    public List<Map<String, Object>> getCiclos() {
        return service.listarCiclos();
    }

    @GetMapping("/resumen")
    public List<Map<String, Object>> getResumen() {
        return service.resumenCarreras();
    }
}