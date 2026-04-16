package com.examen.EjercicioPractico2_FelipeRuiz.repository;

import com.examen.EjercicioPractico2_FelipeRuiz.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByActivo(Boolean activo);

    List<Evento> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.activo = true")
    Long contarEventosActivos();

    List<Evento> findByActivoTrueOrderByFechaAsc();
}
