package com.sigabem.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.service.FreteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(FreteController.class)
class FreteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FreteService freteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calcularFrete_DeveRetornarOK() throws Exception {
        Frete frete = new Frete();
        frete.setPeso(10.0);
        frete.setCepOrigem("01001-000");
        frete.setCepDestino("01101-000");
        frete.setNomeDestinatario("Test User");

        when(freteService.calcularFrete(any(Frete.class))).thenReturn(frete);

        mockMvc.perform(post("/api/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(frete)))
                .andExpect(status().isOk());

        verify(freteService, times(1)).calcularFrete(any(Frete.class));
    }
}
