package edu.senai.consultorio_backend.pacientes_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.senai.consultorio_backend.pacientes_api.entities.Dentista;
import edu.senai.consultorio_backend.pacientes_api.service.DentistaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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

@WebMvcTest(DentistaController.class)
public class DentistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DentistaService dentistaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Dentista dentista;

    @BeforeEach
    void setUp() {
        dentista = new Dentista(UUID.randomUUID(), "Dr. Teste", "Cardiologista");
    }

    @Test
    @WithMockUser
    void testListarTodos() throws Exception {
        when(dentistaService.listarTodos()).thenReturn(Collections.singletonList(dentista));

        mockMvc.perform(get("/api/dentistas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Dr. Teste"));
    }

    @Test
    @WithMockUser
    void testBuscarPorId() throws Exception {
        when(dentistaService.buscarPorId(dentista.getId())).thenReturn(dentista);

        mockMvc.perform(get("/api/dentistas/{id}", dentista.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dentista.getId().toString()));
    }

    @Test
    @WithMockUser
    void testCadastrar() throws Exception {
        when(dentistaService.cadastrar(any(Dentista.class))).thenReturn(dentista);

        mockMvc.perform(post("/api/dentistas")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dentista)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.nome").value("Dr. Teste"));
    }

    @Test
    @WithMockUser
    void testAtualizar() throws Exception {
        Dentista dentistaAtualizado = new Dentista(dentista.getId(), "Dr. Teste Atualizado", "Clínico Geral");
        when(dentistaService.atualizar(eq(dentista.getId()), any(Dentista.class))).thenReturn(dentistaAtualizado);

        mockMvc.perform(put("/api/dentistas/{id}", dentista.getId())
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dentistaAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Dr. Teste Atualizado"));
    }

    @Test
    @WithMockUser
    void testDeletar() throws Exception {
        doNothing().when(dentistaService).deletar(dentista.getId());

        mockMvc.perform(delete("/api/dentistas/{id}", dentista.getId())
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }
}
