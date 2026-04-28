package com.example.Senati.controllers;

import com.example.Senati.services.pagos_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class pagos_controller {

    @Autowired
    private pagos_service service;

    @GetMapping("/historial")
    public List<Map<String, Object>> getHistorial(@RequestParam Integer id) {
        return service.historialPagos(id);
    }

    @GetMapping("/pendientes")
    public List<Map<String, Object>> getPendientes(@RequestParam Integer id) {
        return service.deudasPendientes(id);
    }
}