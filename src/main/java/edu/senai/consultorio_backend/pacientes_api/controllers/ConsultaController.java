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

/**
 * Controller for managing appointments.
 * This class provides endpoints for scheduling, canceling, and retrieving appointments.
 */
@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

    private final ConsultaService consultaService;

    /**
     * Constructs a new ConsultaController with the given ConsultaService.
     * @param consultaService The service for managing appointments.
     */
    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /**
     * Schedules a new appointment.
     * @param agendamentoRequest The request object containing the patient ID, dentist ID, and date/time of the appointment.
     * @return A ResponseEntity containing the newly created appointment and a URI to the new resource.
     */
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

    /**
     * Cancels an appointment by its ID.
     * @param id The ID of the appointment to cancel.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all appointments.
     * @return A ResponseEntity containing a list of all appointments.
     */
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaService.listarTodas();
        return ResponseEntity.ok(consultas);
    }

    /**
     * Retrieves an appointment by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return A ResponseEntity containing the appointment with the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    /**
     * Retrieves all appointments for a given dentist on a specific day.
     * @param dentistaId The ID of the dentist.
     * @param dia The day to retrieve appointments for.
     * @return A ResponseEntity containing a list of appointments for the given dentist on the specified day.
     */
    @GetMapping("/dentista/{dentistaId}")
    public ResponseEntity<List<Consulta>> buscarConsultasDoDiaPorDentista(
            @PathVariable UUID dentistaId,
            @RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dia) {
        List<Consulta> consultas = consultaService.buscarConsultasDoDiaPorDentista(dentistaId, dia);
        return ResponseEntity.ok(consultas);
    }
}
