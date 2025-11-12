package edu.senai.consultorio_backend.pacientes_api.service;

import edu.senai.consultorio_backend.pacientes_api.entities.Consulta;
import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.repositories.ConsultaRepository;
import edu.senai.consultorio_backend.pacientes_api.repositories.DentistaRepository;
import edu.senai.consultorio_backend.pacientes_api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, DentistaRepository dentistaRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    @Transactional
    public Consulta agendar(UUID pacienteId, UUID dentistaId, LocalDateTime dataHora) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Dentista dentista = dentistaRepository.findById(dentistaId)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setPaciente(paciente);
        novaConsulta.setDentista(dentista);
        novaConsulta.setDataHora(dataHora);

        return consultaRepository.save(novaConsulta);
    }

    @Transactional
    public void cancelar(UUID consultaId) {
        consultaRepository.deleteById(consultaId);
    }

    @Transactional(readOnly = true)
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Consulta buscarPorId(UUID id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarConsultasDoDiaPorDentista(UUID dentistaId, LocalDateTime dia) {
        LocalDateTime inicioDoDia = dia.toLocalDate().atStartOfDay();
        LocalDateTime fimDoDia = dia.toLocalDate().atTime(23, 59, 59);
        return consultaRepository.findByDentistaIdAndDataHoraBetween(dentistaId, inicioDoDia, fimDoDia);
    }
}
