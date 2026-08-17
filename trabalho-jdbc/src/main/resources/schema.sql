-- Banco: fatec_jdbc
-- Rode este script no PostgreSQL antes de executar a aplicação.

CREATE TABLE cursos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    coordenador VARCHAR(100)
);

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO categorias (nome) VALUES ('Estudo'), ('Trabalho'), ('Pessoal'), ('Projeto');

CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    prioridade VARCHAR(10) NOT NULL DEFAULT 'MEDIA',
    categoria_id INT NOT NULL REFERENCES categorias(id)
);
