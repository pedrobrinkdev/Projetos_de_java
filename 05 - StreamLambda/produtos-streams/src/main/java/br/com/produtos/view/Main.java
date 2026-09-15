package br.com.produtos.view;

import br.com.produtos.model.Produto;
import br.com.produtos.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Smartphone", "Eletronicos", 1800.0));
        produtos.add(new Produto("Notebook", "Eletronicos", 3500.0));
        produtos.add(new Produto("Fone Bluetooth", "Eletronicos", 250.0));
        produtos.add(new Produto("Romance Classico", "Livros", 45.0));
        produtos.add(new Produto("Livro Tecnico", "Livros", 120.0));
        produtos.add(new Produto("Camiseta", "Roupas", 60.0));
        produtos.add(new Produto("Calca Jeans", "Roupas", 150.0));
        produtos.add(new Produto("Jaqueta", "Roupas", 280.0));

        ProdutoService service = new ProdutoService();

        System.out.println("=== Produtos da categoria Eletronicos ===");
        List<Produto> eletronicos = service.filtrarPorCategoria(produtos, "Eletronicos");
        eletronicos.forEach(System.out::println);

        System.out.println("\n=== Eletronicos com 10% de desconto, ordenados por preco ===");
        List<Produto> comDesconto = service.aplicarDescontoOrdenado(eletronicos, 0.10);
        comDesconto.forEach(System.out::println);

        double totalRoupas = service.calcularTotalPorCategoria(produtos, "Roupas");
        System.out.printf("%n=== Total gasto em Roupas: R$ %.2f ===%n", totalRoupas);

        Map<String, Double> mediaPorCategoria = service.mediaPrecoPorCategoria(produtos);
        System.out.println("\n=== Media de preco por categoria ===");
        mediaPorCategoria.forEach((categoria, media) ->
                System.out.printf("%-12s -> R$ %.2f%n", categoria, media));
    }
}
