package br.edu.fatecpg.model;

public class PokemonResumo {

    private String nome;
    private String url;

    public PokemonResumo(String nm, String ur) {
        this.nome = nm;
        this.url = ur;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return nome;
    }
}
