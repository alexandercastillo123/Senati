package com.example.Senati.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import com.example.Senati.models.estudiante;
import org.springframework.stereotype.Repository;

@Repository
public interface estudiante_repository extends JpaRepository<estudiante, Integer> {

    @Query(value = "CALL sp_login(:correo, :pass)", nativeQuery = true)
    List<Map<String, Object>> login(@Param("correo") String correo, @Param("pass") String pass);

    @Query(value = "CALL sp_perfil_estudiante(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verMiPerfil(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_compañeros(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> listarCompañeros(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_mis_cursos(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verMisCursos(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_mis_notas(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verMisNotas(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_mi_horario(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verMiHorario(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_ver_mis_pagos(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verMisPagos(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_pagos_pendientes(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verPagosPendientes(@Param("id_estudiante") Integer id_estudiante);

    @Query(value = "CALL sp_buscar_dni(:dni)", nativeQuery = true)
    List<Map<String, Object>> buscarEstudiantePorDni(@Param("dni") String dni);

    @Query(value = "CALL sp_cumpleaños_mes()", nativeQuery = true)
    List<Map<String, Object>> verCumpleaños();

    @Query(value = "CALL sp_promedio_ciclo(:id_estudiante)", nativeQuery = true)
    List<Map<String, Object>> verPromedioCiclo(@Param("id_estudiante") Integer id_estudiante);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "CALL sp_cambiar_pass(:id_estudiante, :nueva_pass)", nativeQuery = true)
    void cambiarPass(@Param("id_estudiante") Integer id_estudiante, @Param("nueva_pass") String nueva_pass);
}