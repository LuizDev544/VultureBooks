create database DonationBook;
use DonationBook;

CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    descricao TEXT,
    ano_publicacao INT,
    quantidade_paginas INT,
    edicao INT,
    idioma VARCHAR(50),
    genero VARCHAR(100),
    condicao VARCHAR(50)
);

create table Admin (

    

);