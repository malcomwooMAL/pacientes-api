package edu.senai.consultorio_backend.pacientes_api.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an appointment entity.
 * This class is mapped to the "consultas" table in the database.
 */
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    /**
     * Default constructor.
     */
    public Consulta() {
    }

    /**
     * Constructs a new Consulta with the given parameters.
     * @param id The ID of the appointment.
     * @param paciente The patient associated with the appointment.
     * @param dentista The dentist associated with the appointment.
     * @param dataHora The date and time of the appointment.
     */
    public Consulta(UUID id, Paciente paciente, Dentista dentista, LocalDateTime dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.dentista = dentista;
        this.dataHora = dataHora;
    }

    /**
     * Gets the ID of the appointment.
     * @return The ID of the appointment.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the appointment.
     * @param id The ID of the appointment.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the patient associated with the appointment.
     * @return The patient associated with the appointment.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Sets the patient associated with the appointment.
     * @param paciente The patient associated with the appointment.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Gets the dentist associated with the appointment.
     * @return The dentist associated with the appointment.
     */
    public Dentista getDentista() {
        return dentista;
    }

    /**
     * Sets the dentist associated with the appointment.
     * @param dentista The dentist associated with the appointment.
     */
    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
