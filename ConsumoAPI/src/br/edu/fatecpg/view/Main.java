package br.edu.fatecpg.view;

import br.edu.fatecpg.model.Endereco;
import br.edu.fatecpg.service.ConsomeApi;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();
        List<Endereco> historico = new ArrayList<>();

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== CONSULTA VIA CEP ===");
            System.out.println("1 - Consultar");
            System.out.println("2 - Ver Consultados");
            System.out.println("3 - Limpar Histórico de Consulta");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("\n Desligando a procura...");
                    rodando = false;
                    break;
                case 1:
                    sc.nextLine();

                    System.out.print("Digite o CEP: ");
                    String cep = sc.nextLine();

                    try {
                        String endereco = ConsomeApi.buscaEndereco(cep);
                        System.out.println(endereco);

                        Endereco objEndereco = gson.fromJson(endereco, Endereco.class);

                        if (objEndereco.getCep() != null) {
                            historico.add(objEndereco);

                            System.out.println(objEndereco.getLogradouro());
                            System.out.println(objEndereco);
                        } else {
                            System.out.println("CEP digitado não encontrado.");
                        }

                    } catch (Exception e) {
                        System.out.println("Erro ao realizar a consulta, formato inválido.");
                    }
                    break;

                case 2:
                    if (historico.isEmpty()) {
                        System.out.println("\nNenhum CEP consultado ainda.");
                    } else {
                        System.out.println("\n=== HISTÓRICO DE CONSULTAS ===");
                        for (Endereco e : historico) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 3:
                    historico.clear();
                    System.out.println("\nHistórico limpo com sucesso.");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
                    break;
            }
        }

    }
}