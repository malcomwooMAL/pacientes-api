package edu.senai.consultorio_backend.pacientes_api.controllers;

import edu.senai.consultorio_backend.pacientes_api.dto.AgendamentoRequest;
import edu.senai.consultorio_backend.pacientes_api.entities.Consulta;
import edu.senai.consultorio_backend.pacientes_api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendar(@RequestBody AgendamentoRequest agendamentoRequest) {
        Consulta novaConsulta = consultaService.agendar(
                agendamentoRequest.getPacienteId(),
                agendamentoRequest.getDentistaId(),
                agendamentoRequest.getDataHora()
        );
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaConsulta.getId()).toUri();
        return ResponseEntity.created(location).body(novaConsulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaService.listarTodas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/dentista/{dentistaId}")
    public ResponseEntity<List<Consulta>> buscarConsultasDoDiaPorDentista(
            @PathVariable UUID dentistaId,
            @RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dia) {
        List<Consulta> consultas = consultaService.buscarConsultasDoDiaPorDentista(dentistaId, dia);
        return ResponseEntity.ok(consultas);
    }
}
