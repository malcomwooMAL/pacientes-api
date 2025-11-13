package edu.senai.consultorio_backend.pacientes_api.repositories;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for the Patient entity.
 * This interface extends JpaRepository, providing access to a complete set
 * of CRUD (Create, Read, Update, Delete) persistence methods for the
 * Patient entity, without the need for manual implementation.
 * Spring Data JPA takes care of creating the implementation at runtime.
 * The first generic parameter, 'Paciente', specifies the managed entity,
 * while the second, 'UUID', defines the type of the entity's primary key.
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see edu.senai.consultorio_backend.pacientes_api.entities.Paciente
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    
    /*
     * No methods need to be declared here for basic CRUD operations,
     * as they are inherited from JpaRepository. Custom Query Methods
     * can be added in the future if necessary.
     * Ex: findByCpf(String cpf);
     */
    
}