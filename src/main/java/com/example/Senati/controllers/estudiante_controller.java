package com.example.Senati.controllers;

import com.example.Senati.services.estudiante_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class estudiante_controller {

    @Autowired
    private estudiante_service service;

    @GetMapping("/perfil")
    public List<Map<String, Object>> getPerfil(@RequestParam Integer id) {
        return service.obtenerPerfil(id);
    }

    @GetMapping("/companeros")
    public List<Map<String, Object>> getCompaneros(@RequestParam Integer id) {
        return service.obtenerCompaneros(id);
    }

    @GetMapping("/buscar")
    public List<Map<String, Object>> buscarDni(@RequestParam String dni) {
        return service.buscarPorDni(dni);
    }

    @GetMapping("/cumpleanos")
    public List<Map<String, Object>> getCumpleanos() {
        return service.obtenerCumpleaños();
    }

    @PostMapping("/cambiar_pass")
    public void cambiarPass(@RequestBody Map<String, String> datos) {
        Integer id = Integer.parseInt(datos.get("id"));
        String nuevaPass = datos.get("nueva_pass");
        service.cambiarPass(id, nuevaPass);
    }
}