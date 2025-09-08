package edu.senai.consultorio_backend.pacientes_api.controllers;

import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Controller REST para a entidade Paciente.
 * Esta classe expõe os endpoints da API para realizar as operações de CRUD
 * sobre os pacientes. Ela recebe as requisições HTTP, aciona a camada de
 * serviço correspondente e retorna uma resposta HTTP adequada.
 *
 * @RestController Combina @Controller e @ResponseBody, simplificando a
 * criação de APIs RESTful.
 * @RequestMapping Define o prefixo da URL base para todos os endpoints
 * neste controller.
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    /**
     * Construtor para injeção de dependência do PacienteService.
     *
     * @param pacienteService A instância do serviço de pacientes.
     */
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Endpoint para listar todos os pacientes.
     * Mapeado para a requisição GET em /api/pacientes.
     *
     * @return ResponseEntity contendo a lista de todos os pacientes e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Endpoint para buscar um paciente pelo seu ID.
     * Mapeado para a requisição GET em /api/pacientes/{id}.
     *
     * @param id O UUID do paciente a ser buscado.
     * @return ResponseEntity contendo o paciente encontrado e o status HTTP 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable UUID id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    /**
     * Endpoint para cadastrar um novo paciente.
     * Mapeado para a requisição POST em /api/pacientes.
     *
     * @param paciente O objeto Paciente enviado no corpo da requisição.
     * @return ResponseEntity com o status HTTP 201 (Created), o paciente criado
     * no corpo da resposta e o cabeçalho 'Location' com a URL do novo recurso.
     */
    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrar(paciente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(location).body(novoPaciente);
    }

    /**
     * Endpoint para atualizar os dados de um paciente existente.
     * Mapeado para a requisição PUT em /api/pacientes/{id}.
     *
     * @param id O UUID do paciente a ser atualizado.
     * @param pacienteDetails O objeto Paciente com os novos dados.
     * @return ResponseEntity contendo o paciente atualizado e o status HTTP 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable UUID id, @RequestBody Paciente pacienteDetails) {
        Paciente pacienteAtualizado = pacienteService.atualizar(id, pacienteDetails);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    /**
     * Endpoint para deletar um paciente.
     * Mapeado para a requisição DELETE em /api/pacientes/{id}.
     *
     * @param id O UUID do paciente a ser deletado.
     * @return ResponseEntity com o status HTTP 204 (No Content), indicando
     * que a operação foi bem-sucedida, mas não há conteúdo para retornar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}