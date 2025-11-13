package edu.senai.consultorio_backend.pacientes_api.service;

import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service for managing dentists.
 * This class provides business logic for creating, retrieving, updating, and deleting dentists.
 */
@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    /**
     * Constructs a new DentistaService with the given DentistaRepository.
     * @param dentistaRepository The repository for dentists.
     */
    @Autowired
    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    /**
     * Retrieves all dentists.
     * @return A list of all dentists.
     */
    @Transactional(readOnly = true)
    public List<Dentista> listarTodos() {
        return dentistaRepository.findAll();
    }

    /**
     * Retrieves a dentist by their ID.
     * @param id The ID of the dentist to retrieve.
     * @return The dentist with the given ID.
     */
    @Transactional(readOnly = true)
    public Dentista buscarPorId(UUID id) {
        return dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado com o ID: " + id));
    }

    /**
     * Creates a new dentist.
     * @param dentista The dentist to create.
     * @return The newly created dentist.
     */
    @Transactional
    public Dentista cadastrar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    /**
     * Updates an existing dentist.
     * @param id The ID of the dentist to update.
     * @param dentistaDetails The new details for the dentist.
     * @return The updated dentist.
     */
    @Transactional
    public Dentista atualizar(UUID id, Dentista dentistaDetails) {
        Dentista dentista = buscarPorId(id);
        dentista.setNome(dentistaDetails.getNome());
        dentista.setEspecialidade(dentistaDetails.getEspecialidade());
        return dentistaRepository.save(dentista);
    }

    /**
     * Deletes a dentist by their ID.
     * @param id The ID of the dentist to delete.
     */
    @Transactional
    public void deletar(UUID id) {
        dentistaRepository.deleteById(id);
    }
}
