package br.com.produtos.service;

import br.com.produtos.model.Produto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProdutoService {

    // Filtragem: apenas produtos de uma categoria especifica
    public List<Produto> filtrarPorCategoria(List<Produto> produtos, String ct) {
        return produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(ct))
                .collect(Collectors.toList());
    }

    // Mapeamento + Ordenacao: desconto de 10% e ordenacao crescente por preco
    public List<Produto> aplicarDescontoOrdenado(List<Produto> produtos, double pct) {
        return produtos.stream()
                .map(p -> new Produto(p.getNome(), p.getCategoria(), p.getPreco() * (1 - pct)))
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .collect(Collectors.toList());
    }

    // Reducao: total gasto em produtos de uma categoria (ex.: "Roupas")
    public double calcularTotalPorCategoria(List<Produto> produtos, String ct) {
        return produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(ct))
                .map(Produto::getPreco)
                .reduce(0.0, Double::sum);
    }

    // Agrupamento: media de preco por categoria
    public Map<String, Double> mediaPrecoPorCategoria(List<Produto> produtos) {
        return produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.averagingDouble(Produto::getPreco)));
    }
}
