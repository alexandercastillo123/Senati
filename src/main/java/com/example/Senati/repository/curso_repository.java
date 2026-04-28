package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.curso;
import org.springframework.stereotype.Repository;

@Repository
public interface curso_repository extends JpaRepository<curso, Integer> {
    @Query(value = "CALL sp_ver_mis_cursos()", nativeQuery = true)
    List<Map<String, Object>> listarCursosProcedimiento();
}