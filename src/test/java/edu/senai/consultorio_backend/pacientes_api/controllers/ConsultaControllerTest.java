package edu.senai.consultorio_backend.pacientes_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.senai.consultorio_backend.pacientes_api.dto.AgendamentoRequest;
import edu.senai.consultorio_backend.pacientes_api.entities.Consulta;
import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultaController.class)
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultaService consultaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Consulta consulta;
    private Paciente paciente;
    private Dentista dentista;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        paciente = new Paciente(UUID.randomUUID(), "Paciente Teste", "11122233344", "paciente@teste.com", "11999998888", LocalDate.now(), null, null, null);
        dentista = new Dentista(UUID.randomUUID(), "Dr. Teste", "Especialidade Teste");
        consulta = new Consulta(UUID.randomUUID(), paciente, dentista, LocalDateTime.now().plusDays(1));
    }

    @Test
    @WithMockUser
    void testAgendar() throws Exception {
        AgendamentoRequest request = new AgendamentoRequest();
        request.setPacienteId(paciente.getId());
        request.setDentistaId(dentista.getId());
        request.setDataHora(consulta.getDataHora());

        when(consultaService.agendar(any(UUID.class), any(UUID.class), any(LocalDateTime.class))).thenReturn(consulta);

        mockMvc.perform(post("/api/consultas/agendar")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(consulta.getId().toString()));
    }

    @Test
    @WithMockUser
    void testListarTodas() throws Exception {
        when(consultaService.listarTodas()).thenReturn(Collections.singletonList(consulta));

        mockMvc.perform(get("/api/consultas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(consulta.getId().toString()));
    }

    @Test
    @WithMockUser
    void testBuscarConsultasDoDiaPorDentista() throws Exception {
        LocalDateTime dia = LocalDateTime.now();
        when(consultaService.buscarConsultasDoDiaPorDentista(eq(dentista.getId()), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(consulta));

        mockMvc.perform(get("/api/consultas/dentista/{dentistaId}", dentista.getId())
                .param("dia", dia.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(consulta.getId().toString()));
    }

    @Test
    @WithMockUser
    void testCancelar() throws Exception {
        doNothing().when(consultaService).cancelar(consulta.getId());

        mockMvc.perform(delete("/api/consultas/{id}", consulta.getId())
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }
}
