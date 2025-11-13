package edu.senai.consultorio_backend.pacientes_api.controllers;

import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing dentists.
 * This class provides endpoints for creating, retrieving, updating, and deleting dentists.
 */
@RestController
@RequestMapping("/api/dentistas")
@CrossOrigin(origins = "http://localhost:4200")
public class DentistaController {

    private final DentistaService dentistaService;

    /**
     * Constructs a new DentistaController with the given DentistaService.
     * @param dentistaService The service for managing dentists.
     */
    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    /**
     * Retrieves all dentists.
     * @return A ResponseEntity containing a list of all dentists.
     */
    @GetMapping
    public ResponseEntity<List<Dentista>> listarTodos() {
        List<Dentista> dentistas = dentistaService.listarTodos();
        return ResponseEntity.ok(dentistas);
    }

    /**
     * Retrieves a dentist by their ID.
     * @param id The ID of the dentist to retrieve.
     * @return A ResponseEntity containing the dentist with the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarPorId(@PathVariable UUID id) {
        Dentista dentista = dentistaService.buscarPorId(id);
        return ResponseEntity.ok(dentista);
    }

    /**
     * Creates a new dentist.
     * @param dentista The dentist to create.
     * @return A ResponseEntity containing the newly created dentist and a URI to the new resource.
     */
    @PostMapping
    public ResponseEntity<Dentista> cadastrar(@RequestBody Dentista dentista) {
        Dentista novoDentista = dentistaService.cadastrar(dentista);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoDentista.getId()).toUri();
        return ResponseEntity.created(location).body(novoDentista);
    }

    /**
     * Updates an existing dentist.
     * @param id The ID of the dentist to update.
     * @param dentistaDetails The new details for the dentist.
     * @return A ResponseEntity containing the updated dentist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Dentista> atualizar(@PathVariable UUID id, @RequestBody Dentista dentistaDetails) {
        Dentista dentistaAtualizado = dentistaService.atualizar(id, dentistaDetails);
        return ResponseEntity.ok(dentistaAtualizado);
    }

    /**
     * Deletes a dentist by their ID.
     * @param id The ID of the dentist to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        dentistaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
