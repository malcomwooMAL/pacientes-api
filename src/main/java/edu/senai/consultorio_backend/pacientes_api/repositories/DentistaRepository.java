package edu.senai.consultorio_backend.pacientes_api.repositories;

import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, UUID> {
}
