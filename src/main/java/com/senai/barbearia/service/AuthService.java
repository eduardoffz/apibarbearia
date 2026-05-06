package com.senai.barbearia.service;

import com.senai.barbearia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public String login(String email, String senha) {
        boolean credenciaisValidas = usuarioRepository.existeUsuario(email, senha);

        if (!credenciaisValidas) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        return tokenService.gerarToken(email);
    }
}
