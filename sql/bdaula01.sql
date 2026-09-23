-- Aula 07 - Java Swing + MySQL

-- =========================================================
-- Parte 1 - Banco BDAula01 e tabela pessoa
-- =========================================================

CREATE DATABASE IF NOT EXISTS BDAula01;
USE BDAula01;

CREATE TABLE IF NOT EXISTS pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    idioma VARCHAR(10) NOT NULL
);

-- Exemplos mostrados na aula
INSERT INTO pessoa (nome, sexo, idioma)
VALUES ('Ricardo', 'M', 'Português');

INSERT INTO pessoa (nome, sexo, idioma)
VALUES
('Gerald', 'M', 'Inglês'),
('William', 'M', 'Inglês'),
('Umberto', 'M', 'Espanhol'),
('Jostein', 'M', 'Alemão'),
('Stephen', 'M', 'Holandês');

SELECT * FROM pessoa;

-- =========================================================
-- Parte 2 - Exercícios do banco escola
-- =========================================================

CREATE DATABASE IF NOT EXISTS escola;
USE escola;

CREATE TABLE IF NOT EXISTS alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    curso VARCHAR(50)
);

INSERT INTO alunos (nome, idade, curso)
VALUES
('João', 20, 'Matemática'),
('Maria', 22, 'História'),
('Pedro', 21, 'Ciência da Computação'),
('Ana', 19, 'Biologia'),
('Carlos', 23, 'Economia');

CREATE TABLE IF NOT EXISTS professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    disciplina VARCHAR(50)
);

INSERT INTO professores (nome, idade, disciplina)
VALUES
('Prof. Mario', 35, 'Matemática'),
('Prof. Augusto', 40, 'História'),
('Prof. Ricardo', 38, 'Sistemas de Informação');

CREATE TABLE IF NOT EXISTS matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_professor INT,
    data_matricula DATE,
    FOREIGN KEY (id_aluno) REFERENCES alunos(id),
    FOREIGN KEY (id_professor) REFERENCES professores(id)
);

INSERT INTO matriculas (id_aluno, id_professor, data_matricula)
VALUES
(1, 1, '2023-01-15'),
(2, 2, '2023-02-20'),
(3, 3, '2023-03-10'),
(4, 1, '2023-04-05'),
(5, 2, '2023-05-12');

-- Exercício 9
SELECT nome, curso FROM alunos;

-- Exercício 10
SELECT nome, disciplina FROM professores;
