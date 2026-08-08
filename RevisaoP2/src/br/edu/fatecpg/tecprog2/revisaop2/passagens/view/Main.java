package br.edu.fatecpg.tecprog2.revisaop2.passagens.view;

import br.edu.fatecpg.tecprog2.revisaop2.passagens.controller.Aeroporto;
import br.edu.fatecpg.tecprog2.revisaop2.passagens.model.Voo;

public class Main {

    public static void main(String[] args) {

        // ----- Setup -----
        Aeroporto aeroporto = new Aeroporto();

        Voo voo1 = new Voo("LA3456", "Sao Paulo", "Rio de Janeiro", 5, 450.00);
        Voo voo2 = new Voo("G31200", "Sao Paulo", "Salvador", 2, 780.00);

        aeroporto.adicionarVoo(voo1);
        aeroporto.adicionarVoo(voo2);

        System.out.println();
        aeroporto.exibirTodosVoos();

        // ----- Teste 1: realizarReserva() -----
        System.out.println("\n--- Teste de reserva de assentos ---");
        boolean reserva1 = voo1.realizarReserva(3);
        System.out.println("Reserva de 3 assentos no voo " + voo1.getNumeroVoo() + ": "
                + (reserva1 ? "sucesso" : "falhou") + " (restantes: " + voo1.getAssentosDisponiveis() + ")");

        boolean reserva2 = voo2.realizarReserva(5);
        System.out.println("Reserva de 5 assentos no voo " + voo2.getNumeroVoo() + ": "
                + (reserva2 ? "sucesso" : "falhou") + " (disponiveis: " + voo2.getAssentosDisponiveis() + ")");

        // ----- Teste 2: realizarPagamento() -----
        System.out.println("\n--- Teste de pagamento ---");
        double totalVoo1 = voo1.realizarPagamento("IDA_E_VOLTA", true);
        System.out.println("Pagamento do voo " + voo1.getNumeroVoo() + " (ida e volta + pontos turisticos): R$ "
                + String.format("%.2f", totalVoo1));

        double totalVoo2 = voo2.realizarPagamento("SOMENTE_IDA", false);
        System.out.println("Pagamento do voo " + voo2.getNumeroVoo() + " (somente ida): R$ "
                + String.format("%.2f", totalVoo2));

        // ----- Teste extra: buscarVoo() e imprimirPassagem() -----
        System.out.println("\n--- Teste de busca e impressao da passagem ---");
        Voo encontrado = aeroporto.buscarVoo("LA3456");
        if (encontrado != null) {
            encontrado.imprimirPassagem();
        }

        System.out.println();
        aeroporto.exibirTodosVoos();
    }
}
