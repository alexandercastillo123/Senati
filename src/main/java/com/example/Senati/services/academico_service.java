package com.example.Senati.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.example.Senati.repository.estudiante_repository;

@Service
public class academico_service {
    @Autowired
    private estudiante_repository repo;

    public List<Map<String, Object>> misCursos(Integer id) {
        return repo.verMisCursos(id);
    }

    public List<Map<String, Object>> misNotas(Integer id) {
        return repo.verMisNotas(id);
    }

    public List<Map<String, Object>> miHorario(Integer id) {
        return repo.verMiHorario(id);
    }
}