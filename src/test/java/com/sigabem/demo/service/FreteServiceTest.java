package com.sigabem.demo.service;

import com.sigabem.demo.controller.FreteController;
import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.repository.FreteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FreteServiceTest {

    @Mock
    private FreteRepository freteRepository;

    @InjectMocks
    private FreteService freteService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calcularFrete_CepsMesmaDDD_DeveAplicarDesconto(){
        Frete frete = new Frete();
        frete.setPeso(10.0);
        frete.setCepOrigem("01001-000");
        frete.setCepDestino("01101-000");
        frete.setNomeDestinatario("Test User");

        when(freteRepository.save(any(Frete.class))).thenReturn(frete);

        Frete result = freteService.calcularFrete(frete);

        assertEquals(5.0, result.getVlTotalFrete());
        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), result.getDataPrevistaEntrega().toLocalDate());
    }
}
