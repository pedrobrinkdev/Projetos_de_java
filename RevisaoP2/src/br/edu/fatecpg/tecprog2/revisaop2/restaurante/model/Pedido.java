package br.edu.fatecpg.tecprog2.revisaop2.restaurante.model;

import java.util.ArrayList;

/**
 * Representa um pedido feito no restaurante.
 * Concentra as regras de negocio: itens do pedido, calculo do total
 * (itens + taxa de entrega) e reserva de mesa.
 */
public class Pedido {

    private int numero;
    private ArrayList<ItemPedido> itens;
    private double taxaEntrega;
    private int mesaReservada;

    public Pedido(int nu, double te) {
        this.numero = nu;
        this.taxaEntrega = te;
        this.itens = new ArrayList<>();
        this.mesaReservada = 0;
    }

    // ---------- Regras de negocio ----------

    /**
     * Adiciona um item ao pedido.
     */
    public void adicionarItem(ItemPedido it) {
        this.itens.add(it);
    }

    /**
     * Remove um item do pedido.
     * @return true se o item foi encontrado e removido.
     */
    public boolean removerItem(ItemPedido it) {
        return this.itens.remove(it);
    }

    /**
     * Reserva uma mesa especifica para o pedido.
     * @return true se a reserva foi realizada, false se o numero da mesa for invalido.
     */
    public boolean reservarMesa(int numeroMesa) {
        if (numeroMesa <= 0) {
            return false;
        }
        this.mesaReservada = numeroMesa;
        return true;
    }

    /**
     * Calcula o valor total do pedido, somando os precos de todos os itens
     * mais a taxa de entrega.
     */
    public double calcularTotalPedido() {
        double totalItens = 0;
        for (ItemPedido it : this.itens) {
            totalItens += it.calcularSubtotal();
        }
        return totalItens + this.taxaEntrega;
    }

    // ---------- Getters e Setters ----------

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int nu) {
        this.numero = nu;
    }

    public ArrayList<ItemPedido> getItens() {
        return this.itens;
    }

    public double getTaxaEntrega() {
        return this.taxaEntrega;
    }

    public void setTaxaEntrega(double te) {
        this.taxaEntrega = te;
    }

    public int getMesaReservada() {
        return this.mesaReservada;
    }

    @Override
    public String toString() {
        return "Pedido{" + numero + ", itens=" + itens.size() + ", mesa=" + mesaReservada
                + ", total=R$" + String.format("%.2f", calcularTotalPedido()) + "}";
    }
}
