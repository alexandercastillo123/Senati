package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.ciclo;
import org.springframework.stereotype.Repository;

@Repository
public interface ciclo_repository extends JpaRepository<ciclo, Integer> {
    @Query(value = "CALL sp_listar_ciclos()", nativeQuery = true)
    List<Map<String, Object>> listarCiclosProcedimiento();
}