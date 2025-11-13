package edu.senai.consultorio_backend.pacientes_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


/**
 * Represents a patient entity.
 * This class is mapped to the "pacientes" table in the database.
 *
 * Explanation of Annotations:
 * @Entity: Marks this class as a JPA entity, making it manageable by Hibernate.
 * @Table(name = "pacientes"): Specifies that this entity corresponds to the "pacientes" table in the database.
 * @Id: Designates the id field as the primary key of the table.
 * @GeneratedValue(strategy = GenerationType.IDENTITY): Configures the primary key generation to be auto-incremental, delegating this responsibility to the database.
 * @Column(...): Maps the field to a column in the table, allowing the definition of constraints such as nullable=false (cannot be null) and unique=true (must be unique).
 */
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "telefone_whatsapp", nullable = false, length = 15)
    private String telefoneWhatsapp;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_ultima_consulta")
    private LocalDate dataUltimaConsulta;

    @Column(name = "ultimo_procedimento")
    private String ultimoProcedimento;

    @Column(name = "data_ultimo_contato")
    private LocalDate dataUltimoContato;

    /**
     * Default constructor required by JPA.
     */
    public Paciente() {
    }

    /**
     * Constructor to facilitate the creation of instances.
     * @param id The ID of the patient.
     * @param nome The name of the patient.
     * @param cpf The CPF of the patient.
     * @param email The email of the patient.
     * @param telefoneWhatsapp The WhatsApp phone number of the patient.
     * @param dataNascimento The birth date of the patient.
     * @param dataUltimaConsulta The date of the last appointment.
     * @param ultimoProcedimento The last procedure performed.
     * @param dataUltimoContato The date of the last contact.
     */
    public Paciente(UUID id, String nome, String cpf, String email, String telefoneWhatsapp, LocalDate dataNascimento, LocalDate dataUltimaConsulta, String ultimoProcedimento, LocalDate dataUltimoContato) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefoneWhatsapp = telefoneWhatsapp;
        this.dataNascimento = dataNascimento;
        this.dataUltimaConsulta = dataUltimaConsulta;
        this.ultimoProcedimento = ultimoProcedimento;
        this.dataUltimoContato = dataUltimoContato;
    }

    // Getters and Setters

    /**
     * Gets the ID of the patient.
     * @return The ID of the patient.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the patient.
     * @param id The ID of the patient.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the name of the patient.
     * @return The name of the patient.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the name of the patient.
     * @param nome The name of the patient.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the CPF of the patient.
     * @return The CPF of the patient.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the CPF of the patient.
     * @param cpf The CPF of the patient.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets the email of the patient.
     * @return The email of the patient.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patient.
     * @param email The email of the patient.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the WhatsApp phone number of the patient.
     * @return The WhatsApp phone number of the patient.
     */
    public String getTelefoneWhatsapp() {
        return telefoneWhatsapp;
    }

    /**
     * Sets the WhatsApp phone number of the patient.
     * @param telefoneWhatsapp The WhatsApp phone number of the patient.
     */
    public void setTelefoneWhatsapp(String telefoneWhatsapp) {
        this.telefoneWhatsapp = telefoneWhatsapp;
    }

    /**
     * Gets the birth date of the patient.
     * @return The birth date of the patient.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the birth date of the patient.
     * @param dataNascimento The birth date of the patient.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Gets the date of the last appointment.
     * @return The date of the last appointment.
     */
    public LocalDate getDataUltimaConsulta() {
        return dataUltimaConsulta;
    }

    /**
     * Sets the date of the last appointment.
     * @param dataUltimaConsulta The date of the last appointment.
     */
    public void setDataUltimaConsulta(LocalDate dataUltimaConsulta) {
        this.dataUltimaConsulta = dataUltimaConsulta;
    }

    /**
     * Gets the last procedure performed.
     * @return The last procedure performed.
     */
    public String getUltimoProcedimento() {
        return ultimoProcedimento;
    }

    /**
     * Sets the last procedure performed.
     * @param ultimoProcedimento The last procedure performed.
     */
    public void setUltimoProcedimento(String ultimoProcedimento) {
        this.ultimoProcedimento = ultimoProcedimento;
    }

    /**
     * Gets the date of the last contact.
     * @return The date of the last contact.
     */
    public LocalDate getDataUltimoContato() {
        return dataUltimoContato;
    }

    /**
     * Sets the date of the last contact.
     * @param dataUltimoContato The date of the last contact.
     */
    public void setDataUltimoContato(LocalDate dataUltimoContato) {
        this.dataUltimoContato = dataUltimoContato;
    }

    /**
     * equals and hashCode methods for object comparisons
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}