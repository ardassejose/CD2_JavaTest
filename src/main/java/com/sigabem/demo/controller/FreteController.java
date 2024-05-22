package com.sigabem.demo.controller;

import com.sigabem.demo.entity.Frete;
import com.sigabem.demo.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/frete")
public class FreteController {
    @Autowired
    private FreteService freteService;

    @PostMapping("/calcular")
    public Frete calcularFrete(@RequestBody Frete frete){
        return freteService.calcularFrete(frete);
    }
}
