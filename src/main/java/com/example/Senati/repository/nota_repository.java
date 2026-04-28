package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.nota;
import org.springframework.stereotype.Repository;

@Repository
public interface nota_repository extends JpaRepository<nota, Integer> {
    @Query(value = "CALL sp_ver_mis_notas()", nativeQuery = true)
    List<Map<String, Object>> listarNotasProcedimiento();

    @Query(value = "CALL sp_promedio_ciclo(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verPromedioDeCiclo(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_mi_nota_por_curso()", nativeQuery = true)
    List<Map<String, Object>> verNotaPorCurso();
}