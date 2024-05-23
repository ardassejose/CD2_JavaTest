package com.sigabem.demo.service;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FreteServiceIntegrationTest {

    @Autowired
    private FreteService freteService;

    @Autowired
    private FreteRepository freteRepository;

    @Test
    @Transactional
    void calcularFrete_DevePersistirDadosCorretamente() {
        Frete frete = new Frete();
        frete.setPeso(10.0);
        frete.setCepOrigem("01001-000");
        frete.setCepDestino("01101-000");
        frete.setNomeDestinatario("Test User");

        Frete result = freteService.calcularFrete(frete);

        assertEquals(5.0, result.getVlTotalFrete());
        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), result.getDataPrevistaEntrega().toLocalDate());
        assertEquals(frete.getNomeDestinatario(), result.getNomeDestinatario());

        Frete persistedFrete = freteRepository.findById(result.getId()).orElse(null);

        assertEquals(result, persistedFrete);
    }
}
