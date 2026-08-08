package br.edu.fatecpg.tecprog2.revisaop2.restaurante.view;

import br.edu.fatecpg.tecprog2.revisaop2.restaurante.controller.Restaurante;
import br.edu.fatecpg.tecprog2.revisaop2.restaurante.model.ItemPedido;
import br.edu.fatecpg.tecprog2.revisaop2.restaurante.model.Pedido;

public class Main {

    public static void main(String[] args) {

        // ----- Setup -----
        Restaurante restaurante = new Restaurante();

        Pedido pedido1 = new Pedido(1, 5.00);
        pedido1.adicionarItem(new ItemPedido("Feijoada", 2, 35.00));
        pedido1.adicionarItem(new ItemPedido("Suco de Laranja", 2, 8.00));

        Pedido pedido2 = new Pedido(2, 7.50);
        pedido2.adicionarItem(new ItemPedido("Pizza Margherita", 1, 48.00));

        restaurante.adicionarPedido(pedido1);
        restaurante.adicionarPedido(pedido2);

        System.out.println();
        restaurante.exibirTodosPedidos();

        // ----- Teste 1: calcularTotalPedido() -----
        System.out.println("\n--- Teste de calculo do total do pedido ---");
        System.out.println("Total do pedido 1: R$ " + String.format("%.2f", pedido1.calcularTotalPedido()));
        System.out.println("Total do pedido 2: R$ " + String.format("%.2f", pedido2.calcularTotalPedido()));

        // ----- Teste 2: reservarMesa() -----
        System.out.println("\n--- Teste de reserva de mesa ---");
        boolean reservaOk = pedido1.reservarMesa(12);
        System.out.println("Reserva da mesa 12 para o pedido 1: " + (reservaOk ? "sucesso" : "falhou"));

        boolean reservaInvalida = pedido2.reservarMesa(0);
        System.out.println("Reserva da mesa 0 para o pedido 2: " + (reservaInvalida ? "sucesso" : "falhou"));

        // ----- Teste extra: removerItem() e buscarPedido() -----
        System.out.println("\n--- Teste de remocao de item e busca de pedido ---");
        ItemPedido sucoRemovido = pedido1.getItens().get(1);
        pedido1.removerItem(sucoRemovido);
        System.out.println("Novo total do pedido 1 apos remover item: R$ "
                + String.format("%.2f", pedido1.calcularTotalPedido()));

        Pedido encontrado = restaurante.buscarPedido(2);
        System.out.println("Pedido encontrado: " + encontrado);

        System.out.println();
        restaurante.exibirTodosPedidos();
    }
}
