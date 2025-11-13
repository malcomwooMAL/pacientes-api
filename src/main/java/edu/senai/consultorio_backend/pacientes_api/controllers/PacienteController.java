package edu.senai.consultorio_backend.pacientes_api.controllers;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * REST Controller for the Patient entity.
 * This class exposes API endpoints to perform CRUD operations on patients.
 * It receives HTTP requests, triggers the corresponding service layer,
 * and returns an appropriate HTTP response.
 */
@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {

    private final PacienteService pacienteService;

    /**
     * Constructor for dependency injection of PacienteService.
     *
     * @param pacienteService The patient service instance.
     */
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Endpoint to list all patients.
     * Mapped to the GET request on /api/pacientes.
     *
     * @return ResponseEntity containing the list of all patients and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Endpoint to find a patient by their ID.
     * Mapped to the GET request on /api/pacientes/{id}.
     *
     * @param id The UUID of the patient to be fetched.
     * @return ResponseEntity containing the found patient and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable UUID id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    /**
     * Endpoint to register a new patient.
     * Mapped to the POST request on /api/pacientes.
     *
     * @param paciente The Patient object sent in the request body.
     * @return ResponseEntity with HTTP status 201 (Created), the created patient
     * in the response body, and the 'Location' header with the URL of the new resource.
     */
    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrar(paciente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(location).body(novoPaciente);
    }

    /**
     * Endpoint to update the data of an existing patient.
     * Mapped to the PUT request on /api/pacientes/{id}.
     *
     * @param id The UUID of the patient to be updated.
     * @param pacienteDetails The Patient object with the new data.
     * @return ResponseEntity containing the updated patient and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable UUID id, @RequestBody Paciente pacienteDetails) {
        Paciente pacienteAtualizado = pacienteService.atualizar(id, pacienteDetails);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    /**
     * Endpoint to delete a patient.
     * Mapped to the DELETE request on /api/pacientes/{id}.
     *
     * @param id The UUID of the patient to be deleted.
     * @return ResponseEntity with HTTP status 204 (No Content), indicating
     * that the operation was successful, but there is no content to return.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}