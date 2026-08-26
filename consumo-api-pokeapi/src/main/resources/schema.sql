CREATE TABLE pokemon_favorito (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    pokedex_id INT,
    altura INT,
    tipos TEXT
);
