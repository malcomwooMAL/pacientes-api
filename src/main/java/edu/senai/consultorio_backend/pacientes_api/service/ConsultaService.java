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

/**
 * Service for managing appointments.
 * This class provides business logic for scheduling, canceling, and retrieving appointments.
 */
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;

    /**
     * Constructs a new ConsultaService with the given repositories.
     * @param consultaRepository The repository for appointments.
     * @param pacienteRepository The repository for patients.
     * @param dentistaRepository The repository for dentists.
     */
    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, DentistaRepository dentistaRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    /**
     * Schedules a new appointment.
     * @param pacienteId The ID of the patient.
     * @param dentistaId The ID of the dentist.
     * @param dataHora The date and time of the appointment.
     * @return The newly created appointment.
     */
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

    /**
     * Cancels an appointment by its ID.
     * @param consultaId The ID of the appointment to cancel.
     */
    @Transactional
    public void cancelar(UUID consultaId) {
        consultaRepository.deleteById(consultaId);
    }

    /**
     * Retrieves all appointments.
     * @return A list of all appointments.
     */
    @Transactional(readOnly = true)
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    /**
     * Retrieves an appointment by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return The appointment with the given ID.
     */
    @Transactional(readOnly = true)
    public Consulta buscarPorId(UUID id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));
    }

    /**
     * Retrieves all appointments for a given dentist on a specific day.
     * @param dentistaId The ID of the dentist.
     * @param dia The day to retrieve appointments for.
     * @return A list of appointments for the given dentist on the specified day.
     */
    @Transactional(readOnly = true)
    public List<Consulta> buscarConsultasDoDiaPorDentista(UUID dentistaId, LocalDateTime dia) {
        LocalDateTime inicioDoDia = dia.toLocalDate().atStartOfDay();
        LocalDateTime fimDoDia = dia.toLocalDate().atTime(23, 59, 59);
        return consultaRepository.findByDentistaIdAndDataHoraBetween(dentistaId, inicioDoDia, fimDoDia);
    }
}
