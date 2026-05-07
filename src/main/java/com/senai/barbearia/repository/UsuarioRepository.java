package com.senai.barbearia.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public void salvarUsuario(String email, String senha) {
        String sql = "INSERT INTO usuarios (email, senha) VALUES (?, ?)";

        
        
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuario: " + e.getMessage());
        }
    }

    public boolean existeUsuario(String email, String senha) {
        String sql = "SELECT id FROM usuarios WHERE email = ? AND senha = ?";

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
