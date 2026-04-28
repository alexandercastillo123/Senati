package com.example.Senati.controllers;

import com.example.Senati.services.login_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class login_controller {

    @Autowired
    private login_service service;

    @PostMapping
    public List<Map<String, Object>> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String pass = datos.get("pass");
        return service.validarAcceso(correo, pass);
    }
}