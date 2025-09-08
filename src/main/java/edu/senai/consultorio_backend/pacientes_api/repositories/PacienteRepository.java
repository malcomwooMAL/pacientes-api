package edu.senai.consultorio_backend.pacientes_api.repositories;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Interface Repository para a entidade Paciente.
 * Esta interface estende JpaRepository, fornecendo acesso a um conjunto completo
 * de métodos de persistência CRUD (Create, Read, Update, Delete) para a 
 * entidade Paciente, sem a necessidade de implementação manual. 
 * O Spring Data JPA se encarrega de criar a implementação em tempo de execução.
 * O primeiro parâmetro genérico, 'Paciente', especifica a entidade gerenciada,
 * enquanto o segundo, 'UUID', define o tipo da chave primária da entidade.
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see edu.senai.consultorio_backend.pacientes_api.entities.Paciente
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    
    /*
     * Nenhum método precisa ser declarado aqui para as operações básicas de CRUD,
     * pois são herdados de JpaRepository. Métodos de consulta customizados
     * (Custom Query Methods) poderão ser adicionados futuramente, se necessário.
     * Ex: findByCpf(String cpf);
     */
    
}