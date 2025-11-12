package edu.senai.consultorio_backend.pacientes_api.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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

    // Construtor padrão
    public Consulta() {
    }

    // Construtor com parâmetros
    public Consulta(UUID id, Paciente paciente, Dentista dentista, LocalDateTime dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.dentista = dentista;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

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
