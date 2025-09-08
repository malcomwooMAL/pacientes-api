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
 * 
 * Explicação das Anotações
@Entity: Marca esta classe como uma entidade JPA, tornando-a gerenciável pelo Hibernate.

@Table(name = "pacientes"): Especifica que esta entidade corresponde à tabela pacientes no banco de dados.

@Id: Designa o campo id como a chave primária da tabela.

@GeneratedValue(strategy = GenerationType.IDENTITY): Configura a geração da chave primária para ser autoincremental, delegando esta responsabilidade ao banco de dados.

@Column(...): Mapeia o campo para uma coluna na tabela, permitindo a definição de restrições como nullable=false (não pode ser nulo) e unique=true (deve ser único).
 * 
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

    @Column(nullable = false, length = 15)
    private String telefone;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    // Construtor padrão exigido pelo JPA
    public Paciente() {
    }

    // Construtor para facilitar a criação de instâncias
    public Paciente(UUID id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Métodos equals e hashCode para comparações de objetos
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