package br.edu.fatecpg.tecprog2.revisaop2.restaurante.controller;

import br.edu.fatecpg.tecprog2.revisaop2.restaurante.model.Pedido;

import java.util.ArrayList;

/**
 * Controla a lista de pedidos realizados no restaurante:
 * adicionar, remover, buscar e exibir todos os pedidos.
 */
public class Restaurante {

    private ArrayList<Pedido> pedidos;

    public Restaurante() {
        this.pedidos = new ArrayList<>();
    }

    /**
     * Adiciona um pedido a lista de pedidos do restaurante.
     */
    public void adicionarPedido(Pedido pe) {
        this.pedidos.add(pe);
        System.out.println("Pedido numero " + pe.getNumero() + " adicionado com sucesso.");
    }

    /**
     * Remove um pedido da lista a partir do numero do pedido.
     * @return true se o pedido foi encontrado e removido.
     */
    public boolean removerPedido(int numero) {
        Pedido encontrado = buscarPedido(numero);
        if (encontrado == null) {
            System.out.println("Pedido numero " + numero + " nao encontrado.");
            return false;
        }
        this.pedidos.remove(encontrado);
        System.out.println("Pedido numero " + numero + " removido com sucesso.");
        return true;
    }

    /**
     * Busca um pedido na lista pelo numero do pedido.
     * @return o pedido encontrado, ou null caso nao exista.
     */
    public Pedido buscarPedido(int numero) {
        for (Pedido pe : this.pedidos) {
            if (pe.getNumero() == numero) {
                return pe;
            }
        }
        return null;
    }

    /**
     * Exibe todos os pedidos realizados no restaurante.
     */
    public void exibirTodosPedidos() {
        System.out.println("========= Pedidos Realizados =========");
        if (this.pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            for (Pedido pe : this.pedidos) {
                System.out.println(pe);
            }
        }
        System.out.println("=======================================");
    }

    public ArrayList<Pedido> getPedidos() {
        return this.pedidos;
    }
}
