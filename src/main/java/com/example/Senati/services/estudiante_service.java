package com.example.Senati.services;

import com.example.Senati.repository.estudiante_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class estudiante_service {

    @Autowired
    private estudiante_repository repo;

    public List<Map<String, Object>> login(String correo, String pass) {
        return repo.login(correo, pass);
    }

    public List<Map<String, Object>> obtenerPerfil(Integer idEstudiante) {
        return repo.verMiPerfil(idEstudiante);
    }

    public List<Map<String, Object>> obtenerCompaneros(Integer idEstudiante) {
        return repo.listarCompañeros(idEstudiante);
    }

    public List<Map<String, Object>> buscarPorDni(String dni) {
        return repo.buscarEstudiantePorDni(dni);
    }

    public List<Map<String, Object>> obtenerCumpleaños() {
        return repo.verCumpleaños();
    }

    public void cambiarPass(Integer idEstudiante, String nuevaPass) {
        repo.cambiarPass(idEstudiante, nuevaPass);
    }
}