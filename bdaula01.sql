CREATE DATABASE IF NOT EXISTS bdaula01 CHARACTER SET utf8mb4;
USE bdaula01;

CREATE TABLE IF NOT EXISTS pessoa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    idioma VARCHAR(50) NOT NULL
);

-- Exercicios dos slides
CREATE DATABASE IF NOT EXISTS escola CHARACTER SET utf8mb4;
USE escola;
CREATE TABLE IF NOT EXISTS alunos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    curso VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS professores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    disciplina VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS matriculas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_aluno INT NOT NULL,
    id_professor INT NOT NULL,
    data_matricula DATE NOT NULL,
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (id_aluno) REFERENCES alunos(id),
    CONSTRAINT fk_matricula_professor FOREIGN KEY (id_professor) REFERENCES professores(id)
);
