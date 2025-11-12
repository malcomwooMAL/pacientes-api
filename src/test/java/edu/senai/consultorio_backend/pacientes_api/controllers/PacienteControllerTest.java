package edu.senai.consultorio_backend.pacientes_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.senai.consultorio_backend.pacientes_api.entities.Paciente;
import edu.senai.consultorio_backend.pacientes_api.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
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

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        paciente = new Paciente(UUID.randomUUID(), "Nome Teste", "12345678901", "teste@email.com", "11999998888", LocalDate.now(), null, null, null);
    }

    @Test
    @WithMockUser
    void testListarTodos() throws Exception {
        when(pacienteService.listarTodos()).thenReturn(Collections.singletonList(paciente));

        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Nome Teste"));
    }

    @Test
    @WithMockUser
    void testBuscarPorId() throws Exception {
        when(pacienteService.buscarPorId(paciente.getId())).thenReturn(paciente);

        mockMvc.perform(get("/api/pacientes/{id}", paciente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(paciente.getId().toString()));
    }

    @Test
    @WithMockUser
    void testCadastrar() throws Exception {
        when(pacienteService.cadastrar(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(post("/api/pacientes")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.nome").value("Nome Teste"));
    }

    @Test
    @WithMockUser
    void testAtualizar() throws Exception {
        Paciente pacienteAtualizado = new Paciente(paciente.getId(), "Nome Atualizado", "12345678901", "teste@email.com", "11999998888", LocalDate.now(), null, null, null);
        when(pacienteService.atualizar(eq(paciente.getId()), any(Paciente.class))).thenReturn(pacienteAtualizado);

        mockMvc.perform(put("/api/pacientes/{id}", paciente.getId())
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pacienteAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"));
    }

    @Test
    @WithMockUser
    void testDeletar() throws Exception {
        doNothing().when(pacienteService).deletar(paciente.getId());

        mockMvc.perform(delete("/api/pacientes/{id}", paciente.getId())
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }
}
