package br.com.produtos.model;

public class Produto {

    private String nome;
    private String categoria;
    private double preco;

    public Produto(String nm, String ct, double pr) {
        this.nome = nm;
        this.categoria = ct;
        this.preco = pr;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public double getPreco() {
        return this.preco;
    }

    @Override
    public String toString() {
        return String.format("%-14s | %-11s | R$ %8.2f", nome, categoria, preco);
    }
}
