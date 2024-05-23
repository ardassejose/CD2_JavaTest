-- Deletar o schema se ele existir
DROP DATABASE IF EXISTS sigabem;

-- Criar o schema novamente
CREATE DATABASE sigabem;

-- Usar o novo schema
USE sigabem;

-- Criar a tabela com valores padrão definidos
CREATE TABLE frete (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    peso DOUBLE NOT NULL DEFAULT 0.0,
    cep_origem VARCHAR(9) NOT NULL DEFAULT '00000-000',
    cep_destino VARCHAR(9) NOT NULL DEFAULT '00000-000',
    nome_destinatario VARCHAR(255) NOT NULL DEFAULT 'destinatario',
    vl_total_frete DOUBLE NOT NULL DEFAULT 0.0,
    data_prevista_entrega DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_consulta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);