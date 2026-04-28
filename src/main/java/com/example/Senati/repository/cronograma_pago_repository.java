package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.cronograma_pago;
import org.springframework.stereotype.Repository;

@Repository
public interface cronograma_pago_repository extends JpaRepository<cronograma_pago, Integer> {
    @Query(value = "CALL sp_ver_mis_pagos()", nativeQuery = true)
    List<Map<String, Object>> listarCronogramasProcedimiento();

    @Query(value = "CALL sp_pagos_pendientes(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> listarCronogramasPorEstudiante(Integer id_estudiante);
}