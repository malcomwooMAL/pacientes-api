package edu.senai.consultorio_backend.pacientes_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents the request object for scheduling an appointment.
 * This class is used to transfer the data for a new appointment from the client to the server.
 */
public class AgendamentoRequest {
    private UUID pacienteId;
    private UUID dentistaId;
    private LocalDateTime dataHora;

    /**
     * Gets the patient ID.
     * @return The patient ID.
     */
    public UUID getPacienteId() {
        return pacienteId;
    }

    /**
     * Sets the patient ID.
     * @param pacienteId The patient ID.
     */
    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    /**
     * Gets the dentist ID.
     * @return The dentist ID.
     */
    public UUID getDentistaId() {
        return dentistaId;
    }

    /**
     * Sets the dentist ID.
     * @param dentistaId The dentist ID.
     */
    public void setDentistaId(UUID dentistaId) {
        this.dentistaId = dentistaId;
    }

    /**
     * Gets the date and time of the appointment.
     * @return The date and time of the appointment.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Sets the date and time of the appointment.
     * @param dataHora The date and time of the appointment.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
