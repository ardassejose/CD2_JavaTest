package com.sigabem.demo.service;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.repository.FreteRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class FreteService {
    private FreteRepository freteRepository;

    private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json/";

    public Frete calcularFrete(Frete frete){
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

}

@Getter
@Setter
class CepResponse {
    private String uf;
}