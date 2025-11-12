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

@RestController
@RequestMapping("/api/dentistas")
@CrossOrigin(origins = "http://localhost:4200")
public class DentistaController {

    private final DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public ResponseEntity<List<Dentista>> listarTodos() {
        List<Dentista> dentistas = dentistaService.listarTodos();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarPorId(@PathVariable UUID id) {
        Dentista dentista = dentistaService.buscarPorId(id);
        return ResponseEntity.ok(dentista);
    }

    @PostMapping
    public ResponseEntity<Dentista> cadastrar(@RequestBody Dentista dentista) {
        Dentista novoDentista = dentistaService.cadastrar(dentista);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoDentista.getId()).toUri();
        return ResponseEntity.created(location).body(novoDentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentista> atualizar(@PathVariable UUID id, @RequestBody Dentista dentistaDetails) {
        Dentista dentistaAtualizado = dentistaService.atualizar(id, dentistaDetails);
        return ResponseEntity.ok(dentistaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        dentistaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
