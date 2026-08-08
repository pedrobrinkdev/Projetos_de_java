package br.edu.fatecpg.tecprog2.revisaop2.restaurante.model;

/**
 * Representa um item dentro de um pedido do restaurante.
 */
public class ItemPedido {

    private String nomeDoPrato;
    private int quantidade;
    private double precoUnitario;

    public ItemPedido(String nd, int qt, double pu) {
        this.nomeDoPrato = nd;
        this.quantidade = qt;
        this.precoUnitario = pu;
    }

    /**
     * Calcula o subtotal deste item (quantidade x precoUnitario).
     */
    public double calcularSubtotal() {
        return this.quantidade * this.precoUnitario;
    }

    // ---------- Getters e Setters ----------

    public String getNomeDoPrato() {
        return this.nomeDoPrato;
    }

    public void setNomeDoPrato(String nd) {
        this.nomeDoPrato = nd;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int qt) {
        this.quantidade = qt;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(double pu) {
        this.precoUnitario = pu;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + nomeDoPrato + ", qtd=" + quantidade + ", unit=R$" + precoUnitario + "}";
    }
}
