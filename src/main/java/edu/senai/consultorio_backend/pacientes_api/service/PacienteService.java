package edu.senai.consultorio_backend.pacientes_api.service;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service class for the Patient entity.
 * This class encapsulates the business logic related to CRUD operations
 * for patients. It acts as an intermediate layer between the Controller
 * and the Repository, ensuring the separation of responsibilities.
 * The @Service annotation marks it as a Spring component, making it
 * eligible for dependency injection.
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    /**
     * Constructor for dependency injection of PacienteRepository.
     * Spring will automatically inject an instance of PacienteRepository.
     * This is the recommended way of dependency injection (via constructor).
     *
     * @param pacienteRepository The patient repository instance.
     */
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Retrieves and returns all registered patients.
     * The @Transactional(readOnly = true) annotation optimizes the query,
     * indicating that this is a read-only operation.
     *
     * @return A list of all Patient objects.
     */
    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    /**
     * Finds a patient by their ID.
     *
     * @param id The UUID of the patient to be fetched.
     * @return The Patient object corresponding to the ID.
     * @throws EntityNotFoundException if no patient is found with the provided ID.
     */
    @Transactional(readOnly = true)
    public Paciente buscarPorId(UUID id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + id));
    }

    /**
     * Registers a new patient in the database.
     *
     * @param paciente The Patient object to be saved.
     * @return The saved Patient object, with the generated ID.
     */
    @Transactional
    public Paciente cadastrar(Paciente paciente) {
        // In the future, validations (e.g., unique CPF) can be added here.
        return pacienteRepository.save(paciente);
    }

    /**
     * Updates the data of an existing patient.
     *
     * @param id The UUID of the patient to be updated.
     * @param pacienteDetails The Patient object with the new data.
     * @return The updated Patient object.
     * @throws EntityNotFoundException if the patient to be updated is not found.
     */
    @Transactional
    public Paciente atualizar(UUID id, Paciente pacienteDetails) {
        Paciente pacienteExistente = buscarPorId(id); // Reuses the find by ID
        
        pacienteExistente.setNome(pacienteDetails.getNome());
        pacienteExistente.setCpf(pacienteDetails.getCpf());
        pacienteExistente.setEmail(pacienteDetails.getEmail());
        pacienteExistente.setTelefoneWhatsapp(pacienteDetails.getTelefoneWhatsapp());
        pacienteExistente.setDataNascimento(pacienteDetails.getDataNascimento());
        pacienteExistente.setDataUltimaConsulta(pacienteDetails.getDataUltimaConsulta());
        pacienteExistente.setUltimoProcedimento(pacienteDetails.getUltimoProcedimento());
        pacienteExistente.setDataUltimoContato(pacienteDetails.getDataUltimoContato());

        return pacienteRepository.save(pacienteExistente);
    }

    /**
     * Deletes a patient from the database by their ID.
     *
     * @param id The UUID of the patient to be deleted.
     * @throws EntityNotFoundException if the patient to be deleted is not found.
     */
    @Transactional
    public void deletar(UUID id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}