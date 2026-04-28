package com.example.Senati.services;

import com.example.Senati.repository.cronograma_pago_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class pagos_service {

    @Autowired
    private cronograma_pago_repository repo;

    public List<Map<String, Object>> historialPagos(Integer id) {
        return repo.listarCronogramasPorEstudiante(id);
    }

    public List<Map<String, Object>> deudasPendientes(Integer idEstudiante) {
        return repo.listarCronogramasPorEstudiante(idEstudiante);
    }
}