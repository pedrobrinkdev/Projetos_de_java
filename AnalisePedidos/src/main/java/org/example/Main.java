package org.example;

import org.example.controller.ConverteDados;
import org.example.model.Carrinho;
import org.example.model.ProdutoCarrinho;
import org.example.model.RespostaCarrinho;
import org.example.service.ConsomeAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ConverteDados mapper = new ConverteDados();

        String json = ConsomeAPI.buscar();

        RespostaCarrinho resposta =
                mapper.obterDados(json, RespostaCarrinho.class);

        List<Carrinho> carrinhos = resposta.getCarts();

        List<Carrinho> carrinhosCaros = carrinhos.stream()
                .filter( c -> c.getTotal() > 1000)
                .toList();

        carrinhosCaros.forEach(System.out::println);

        carrinhos.stream()
                .flatMap(c -> c.getProducts().stream())
                .filter(p -> p.getDiscountPercentage() > 15)
                .map(ProdutoCarrinho::getTitle)
                .forEach(System.out::println);


        carrinhos.stream()
                .sorted((c1, c2) -> Double.compare(
                        c2.getTotal() - c2.getDiscountedTotal(),
                        c1.getTotal() - c1.getDiscountedTotal()
                ))
                .forEach(System.out::println);


        double soma = carrinhos.stream()
                .map(Carrinho::getDiscountedTotal)
                .reduce(0.0, Double::sum);

        System.out.println(soma);


        Map<Integer, Long> agrupamento = carrinhos.stream()
                .collect(Collectors.groupingBy(
                        Carrinho::getTotalProducts,
                        Collectors.counting()
                ));

        System.out.println(agrupamento);

    }

}