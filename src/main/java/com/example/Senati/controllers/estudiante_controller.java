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

    @GetMapping("/perfil/{id}")
    public List<Map<String, Object>> getPerfil(@PathVariable("id") Integer id) {
        return service.obtenerPerfil(id);
    }

    @GetMapping("/companeros/{id_carrera}/{id_ciclo}")
    public List<Map<String, Object>> getCompaneros(@PathVariable("id_carrera") Integer idCarrera, @PathVariable("id_ciclo") Integer idCiclo) {
        return service.obtenerCompaneros(idCarrera, idCiclo);
    }

    @GetMapping("/buscar/{dni}")
    public List<Map<String, Object>> buscarDni(@PathVariable("dni") String dni) {
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