package com.sigabem.demo.controller;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.service.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Listar frete específico", description = "Com base no ID fornecido, retorna frete específico")
    public ResponseEntity<Frete> getFreteById(@PathVariable Long id){
        Frete frete = freteService.getFreteById(id);
        if (frete != null) {
            return ResponseEntity.ok(frete);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    @Operation(summary = "Listar os frete", description = "Lista todos os fretes disponíveis no banco de dados")
    public ResponseEntity<List<Frete>> getAllFretes() {
        List<Frete> fretes = freteService.getAllFretes();
        return ResponseEntity.ok(fretes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar frete específico", description = "Com base no ID fornecido, exclui frete específico")
    public ResponseEntity<Void> deleteFreteById(@PathVariable Long id) {
        boolean isDeleted = freteService.deleteFreteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Frete> updateFrete(@PathVariable Long id, @RequestBody Frete freteDetails) {
        Frete updatedFrete = freteService.updateFrete(id, freteDetails);
        if (updatedFrete != null) {
            return ResponseEntity.ok(updatedFrete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
