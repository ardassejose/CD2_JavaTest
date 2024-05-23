package com.sigabem.demo.controller;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.service.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frete")
@Tag(name = "Frete Controller", description = "Controlador para operações de frete")
public class FreteController {
    @Autowired
    private FreteService freteService;

    @PostMapping("/calcular")
    @Operation(summary = "Calcula o frete", description = "Calcula o valor do frete e a data prevista de entrega com base nos dados fornecidos.")
    public Frete calcularFrete(@RequestBody Frete frete){
        return freteService.calcularFrete(frete);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frete> getFreteById(@PathVariable Long id){
        Frete frete = freteService.getFreteById(id);
        if (frete != null) {
            return ResponseEntity.ok(frete);
        }
        return ResponseEntity.notFound().build();
    }
}
