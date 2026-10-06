package org.example.model;


import java.util.List;

public class RespostaCarrinho {

    private List<Carrinho> carts;
    private int total;
    private int skip;
    private int limit;

    public List<Carrinho> getCarts() {
        return carts;
    }

    public void setCarts(List<Carrinho> carts) {
        this.carts = carts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
