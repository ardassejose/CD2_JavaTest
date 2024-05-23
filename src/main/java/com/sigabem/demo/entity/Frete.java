package com.sigabem.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "cep_origem", nullable = false)
    private String cepOrigem = "00000-000";

    @Column(name = "cep_destino", nullable = false)
    private String cepDestino = "00000-000";

    @Column(name = "nome_destinatario", nullable = false)
    private String nomeDestinatario;

    @Column(name = "vl_total_frete", nullable = false)
    private Double vlTotalFrete;

    @Column(name = "data_prevista_entrega", nullable = false)
    private LocalDateTime dataPrevistaEntrega;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;
}
