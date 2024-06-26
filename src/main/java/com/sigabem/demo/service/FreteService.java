package com.sigabem.demo.service;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.repository.FreteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Frete Service", description = "Serviço para cálculo de frete")
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json/";

    @Operation(summary = "Calcula o frete", description = "Calcula o valor do frete e a data prevista de entrega com base nos CEPs de origem e destino e o peso da encomenda.")
    public Frete calcularFrete(Frete frete){
        if (frete.getCepOrigem() == null || frete.getCepDestino() == null || frete.getPeso() == null || frete.getNomeDestinatario() == null) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }

        String origemUF = getUFByCep(frete.getCepOrigem());
        String destinoUF = getUFByCep(frete.getCepDestino());

        if (origemUF == null || destinoUF == null) {
            throw new IllegalArgumentException("CEP inválido");
        }

        double valorFrete = frete.getPeso();
        LocalDateTime dataPrevistaEntrega = LocalDateTime.now();

        if (frete.getCepOrigem().substring(0, 2).equals(frete.getCepDestino().substring(0, 2))) {
            valorFrete *= 0.5;
            dataPrevistaEntrega = dataPrevistaEntrega.plusDays(1);
        } else if (origemUF.equals(destinoUF)) {
            valorFrete *= 0.25;
            dataPrevistaEntrega = dataPrevistaEntrega.plusDays(3);
        } else {
            dataPrevistaEntrega = dataPrevistaEntrega.plusDays(10);
        }

        frete.setVlTotalFrete(valorFrete);
        frete.setDataPrevistaEntrega(dataPrevistaEntrega);
        frete.setDataConsulta(LocalDateTime.now());

        return freteRepository.save(frete);
    }

    private String getUFByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(VIACEP_URL, cep);
        CepResponse response = restTemplate.getForObject(url, CepResponse.class);

        return response != null ? response.getUf() : null;
    }

    public Frete getFreteById(Long id) {
        Optional<Frete> optionalFrete = freteRepository.findById(id);
        return optionalFrete.orElse(null);
    }

    public List<Frete> getAllFretes() {
        return freteRepository.findAll();
    }

    public boolean deleteFreteById(Long id) {
        if (freteRepository.existsById(id)) {
            freteRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Frete updateFrete(Long id, Frete freteDetails) {
        Optional<Frete> optionalFrete = freteRepository.findById(id);
        if (optionalFrete.isPresent()) {
            Frete frete = optionalFrete.get();
            frete.setNomeDestinatario(freteDetails.getNomeDestinatario());
            frete.setCepOrigem(freteDetails.getCepOrigem());
            frete.setCepDestino(freteDetails.getCepDestino());
            frete.setPeso(freteDetails.getPeso());
            return freteRepository.save(frete);
        } else {
            return null;
        }
    }

    @Getter
    private static class CepResponse {
        private String uf;
    }
}