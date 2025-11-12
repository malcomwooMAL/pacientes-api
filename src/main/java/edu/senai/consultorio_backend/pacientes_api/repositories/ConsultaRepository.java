package edu.senai.consultorio_backend.pacientes_api.repositories;

import edu.senai.consultorio_backend.pacientes_api.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    List<Consulta> findByDentistaIdAndDataHoraBetween(UUID dentistaId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
