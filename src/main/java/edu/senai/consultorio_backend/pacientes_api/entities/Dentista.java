package edu.senai.consultorio_backend.pacientes_api.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a dentist entity.
 * This class is mapped to the "dentistas" table in the database.
 */
@Entity
@Table(name = "dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especialidade;

    /**
     * Default constructor.
     */
    public Dentista() {
    }

    /**
     * Constructs a new Dentista with the given parameters.
     * @param id The ID of the dentist.
     * @param nome The name of the dentist.
     * @param especialidade The specialty of the dentist.
     */
    public Dentista(UUID id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    /**
     * Gets the ID of the dentist.
     * @return The ID of the dentist.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the dentist.
     * @param id The ID of the dentist.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the name of the dentist.
     * @return The name of the dentist.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the name of the dentist.
     * @param nome The name of the dentist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the specialty of the dentist.
     * @return The specialty of the dentist.
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Sets the specialty of the dentist.
     * @param especialidade The specialty of the dentist.
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dentista dentista = (Dentista) o;
        return Objects.equals(id, dentista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
