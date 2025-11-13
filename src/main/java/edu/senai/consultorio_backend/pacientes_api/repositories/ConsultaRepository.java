package edu.senai.consultorio_backend.pacientes_api.repositories;

import edu.senai.consultorio_backend.pacientes_api.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repository for the Consulta entity.
 * This interface provides methods for querying the "consultas" table in the database.
 */
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    /**
     * Finds all appointments for a given dentist within a specific time range.
     * @param dentistaId The ID of the dentist.
     * @param startOfDay The start of the time range.
     * @param endOfDay The end of the time range.
     * @return A list of appointments for the given dentist within the specified time range.
     */
    List<Consulta> findByDentistaIdAndDataHoraBetween(UUID dentistaId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
