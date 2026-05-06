package com.senai.barbearia.controller;

import com.senai.barbearia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /*
     * Rota: POST /login
     * Params: email, senha
     * Retorna: token JWT se credenciais válidas
     *
     * Exemplo de chamada:
     * POST http://localhost:8080/login?email=admin@barbearia.com&senha=123456
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,
                                        @RequestParam String senha) {
        try {
            String token = authService.login(email, senha);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
