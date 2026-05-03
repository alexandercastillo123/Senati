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

    @GetMapping("/cursos")
    public List<Map<String, Object>> getCursos(@RequestParam Integer id) {
        return service.misCursos(id);
    }

    @GetMapping("/notas")
    public List<Map<String, Object>> getNotas(@RequestParam Integer id) {
        return service.misNotas(id);
    }

    @GetMapping("/horario")
    public List<Map<String, Object>> getHorario(@RequestParam Integer id) {
        return service.miHorario(id);
    }

    @GetMapping("/promedio")
    public List<Map<String, Object>> getPromedio(@RequestParam Integer id) {
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