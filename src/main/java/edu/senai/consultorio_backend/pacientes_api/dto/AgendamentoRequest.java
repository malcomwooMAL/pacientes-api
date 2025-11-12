package edu.senai.consultorio_backend.pacientes_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgendamentoRequest {
    private UUID pacienteId;
    private UUID dentistaId;
    private LocalDateTime dataHora;

    // Getters e Setters
    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    public UUID getDentistaId() {
        return dentistaId;
    }

    public void setDentistaId(UUID dentistaId) {
        this.dentistaId = dentistaId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
