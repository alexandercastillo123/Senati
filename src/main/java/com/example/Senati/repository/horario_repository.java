package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.horario;
import org.springframework.stereotype.Repository;

@Repository
public interface horario_repository extends JpaRepository<horario, Integer> {
    @Query(value = "CALL sp_ver_mi_horario()", nativeQuery = true)
    List<Map<String, Object>> verMiHorario();
}