package com.example.Senati.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Senati.repository.estudiante_repository;
import java.util.List;
import java.util.Map;

@Service
public class login_service {
    @Autowired
    private estudiante_repository repo;

    public List<Map<String, Object>> validarAcceso(String correo, String pass) {
        return repo.login(correo, pass);
    }
}