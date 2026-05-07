package com.senai.barbearia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarbeariaController {

    @GetMapping("/servicos")
    public ResponseEntity<String> listarServicos() {
        return ResponseEntity.ok("Lista de servicos da barbearia.");
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<String> listarAgendamentos() {
        return ResponseEntity.ok("Lista de agendamentos.");
    }
}
