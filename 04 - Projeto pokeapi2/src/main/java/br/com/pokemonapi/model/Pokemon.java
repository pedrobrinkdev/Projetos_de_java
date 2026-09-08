package br.com.pokemonapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Pokemon {

    private int id;

    @JsonAlias("name")
    private String nome;

    @JsonAlias("height")
    private int altura;

    @JsonAlias("weight")
    private int peso;

    public Pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nAltura: " + altura +
                "\nPeso: " + peso;
    }
}
