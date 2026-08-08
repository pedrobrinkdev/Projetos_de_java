package br.edu.fatecpg.tecprog2.revisaop2.passagens.controller;

import br.edu.fatecpg.tecprog2.revisaop2.passagens.model.Voo;

import java.util.ArrayList;

/**
 * Controla a lista de voos disponiveis:
 * adicionar, remover, buscar e exibir todos os voos.
 */
public class Aeroporto {

    private ArrayList<Voo> voos;

    public Aeroporto() {
        this.voos = new ArrayList<>();
    }

    /**
     * Adiciona um voo a lista de voos disponiveis.
     */
    public void adicionarVoo(Voo vo) {
        this.voos.add(vo);
        System.out.println("Voo " + vo.getNumeroVoo() + " adicionado com sucesso.");
    }

    /**
     * Remove um voo da lista a partir do numero do voo.
     * @return true se o voo foi encontrado e removido.
     */
    public boolean removerVoo(String numeroVoo) {
        Voo encontrado = buscarVoo(numeroVoo);
        if (encontrado == null) {
            System.out.println("Voo " + numeroVoo + " nao encontrado.");
            return false;
        }
        this.voos.remove(encontrado);
        System.out.println("Voo " + numeroVoo + " removido com sucesso.");
        return true;
    }

    /**
     * Busca um voo na lista pelo numero do voo.
     * @return o voo encontrado, ou null caso nao exista.
     */
    public Voo buscarVoo(String numeroVoo) {
        for (Voo vo : this.voos) {
            if (vo.getNumeroVoo().equalsIgnoreCase(numeroVoo)) {
                return vo;
            }
        }
        return null;
    }

    /**
     * Exibe todos os voos disponiveis.
     */
    public void exibirTodosVoos() {
        System.out.println("========= Voos Disponiveis =========");
        if (this.voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado.");
        } else {
            for (Voo vo : this.voos) {
                System.out.println(vo);
            }
        }
        System.out.println("=====================================");
    }

    public ArrayList<Voo> getVoos() {
        return this.voos;
    }
}
