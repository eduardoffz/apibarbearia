-- =============================================

CREATE DATABASE IF NOT EXISTS db_barbearia;

USE db_barbearia;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Usuário de teste (senha: 123456)
INSERT INTO usuarios (nome, email, senha)
VALUES ('Admin', 'admin@barbearia.com', '123456');