package edu.senai.consultorio_backend.pacientes_api.service;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Classe de Serviço para a entidade Paciente.
 * Esta classe encapsula a lógica de negócio relacionada às operações de CRUD
 * para pacientes. Ela atua como uma camada intermediária entre o Controller
 * e o Repository, garantindo a separação de responsabilidades.
 * A anotação @Service a marca como um componente do Spring, tornando-a
 * elegível para injeção de dependência.
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    /**
     * Construtor para injeção de dependência do PacienteRepository.
     * O Spring injetará uma instância de PacienteRepository automaticamente.
     * Esta é a forma recomendada de injeção de dependência (via construtor).
     *
     * @param pacienteRepository A instância do repositório de pacientes.
     */
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Busca e retorna todos os pacientes cadastrados.
     * A anotação @Transactional(readOnly = true) otimiza a consulta,
     * indicando que esta é uma operação de apenas leitura.
     *
     * @return Uma lista de todos os objetos Paciente.
     */
    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    /**
     * Busca um paciente pelo seu ID.
     *
     * @param id O UUID do paciente a ser buscado.
     * @return O objeto Paciente correspondente ao ID.
     * @throws EntityNotFoundException se nenhum paciente for encontrado com o ID fornecido.
     */
    @Transactional(readOnly = true)
    public Paciente buscarPorId(UUID id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + id));
    }

    /**
     * Cadastra um novo paciente no banco de dados.
     *
     * @param paciente O objeto Paciente a ser salvo.
     * @return O objeto Paciente salvo, com o ID gerado.
     */
    @Transactional
    public Paciente cadastrar(Paciente paciente) {
        // Futuramente, validações (ex: CPF único) podem ser adicionadas aqui.
        return pacienteRepository.save(paciente);
    }

    /**
     * Atualiza os dados de um paciente existente.
     *
     * @param id O UUID do paciente a ser atualizado.
     * @param pacienteDetails O objeto Paciente com os novos dados.
     * @return O objeto Paciente atualizado.
     * @throws EntityNotFoundException se o paciente a ser atualizado não for encontrado.
     */
    @Transactional
    public Paciente atualizar(UUID id, Paciente pacienteDetails) {
        Paciente pacienteExistente = buscarPorId(id); // Reutiliza a busca por ID
        
        pacienteExistente.setNome(pacienteDetails.getNome());
        pacienteExistente.setCpf(pacienteDetails.getCpf());
        pacienteExistente.setEmail(pacienteDetails.getEmail());
        pacienteExistente.setTelefoneWhatsapp(pacienteDetails.getTelefoneWhatsapp());
        pacienteExistente.setDataNascimento(pacienteDetails.getDataNascimento());
        pacienteExistente.setDataUltimaConsulta(pacienteDetails.getDataUltimaConsulta());
        pacienteExistente.setUltimoProcedimento(pacienteDetails.getUltimoProcedimento());
        pacienteExistente.setDataUltimoContato(pacienteDetails.getDataUltimoContato());

        return pacienteRepository.save(pacienteExistente);
    }

    /**
     * Deleta um paciente do banco de dados pelo seu ID.
     *
     * @param id O UUID do paciente a ser deletado.
     * @throws EntityNotFoundException se o paciente a ser deletado não for encontrado.
     */
    @Transactional
    public void deletar(UUID id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}