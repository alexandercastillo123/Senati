package com.example.Senati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.example.Senati.models.carrera;

@Repository
public interface carrera_repository extends JpaRepository<carrera, Integer> {
    @Query(value = "CALL sp_listar_carreras()", nativeQuery = true)
    List<Map<String, Object>> listarCarrerasProcedimiento();

    @Query(value = "CALL sp_resumen_alumnos_carrera()", nativeQuery = true)
    List<Map<String, Object>> obtenerResumenAlumnos();
}