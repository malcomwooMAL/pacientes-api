package edu.senai.consultorio_backend.pacientes_api.service;

import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    @Autowired
    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Transactional(readOnly = true)
    public List<Dentista> listarTodos() {
        return dentistaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dentista buscarPorId(UUID id) {
        return dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado com o ID: " + id));
    }

    @Transactional
    public Dentista cadastrar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    @Transactional
    public Dentista atualizar(UUID id, Dentista dentistaDetails) {
        Dentista dentista = buscarPorId(id);
        dentista.setNome(dentistaDetails.getNome());
        dentista.setEspecialidade(dentistaDetails.getEspecialidade());
        return dentistaRepository.save(dentista);
    }

    @Transactional
    public void deletar(UUID id) {
        dentistaRepository.deleteById(id);
    }
}
