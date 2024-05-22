package com.sigabem.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private Double vlTotalFrete;
    private LocalDateTime dataPrevistaEntrega;
    private LocalDateTime dataConsulta;
}
