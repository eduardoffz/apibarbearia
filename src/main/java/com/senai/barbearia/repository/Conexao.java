package com.senai.barbearia.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static final String url  = "jdbc:mysql://localhost:3337/db_barbearia";
    public static final String user  = "root";
    public static final String senha = "";

    private static Connection conn = null;

    private Conexao() {
    }

    public static synchronized Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
