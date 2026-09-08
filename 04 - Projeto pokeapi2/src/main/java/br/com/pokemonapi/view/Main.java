package br.com.pokemonapi.view;

import br.com.pokemonapi.model.Pokemon;
import br.com.pokemonapi.service.PokemonService;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PokemonService service = new PokemonService();

        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n===== POKÉMON API =====");
            System.out.println("1 - Consultar (por ID ou nome)");
            System.out.println("2 - Listar");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    System.out.print(
                            "\nDigite o número da Pokédex ou o nome do Pokémon: "
                    );

                    String idOuNome = scanner.next();

                    try {

                        Pokemon pokemon =
                                service.consultarPokemon(idOuNome);

                        if (pokemon == null) {

                            System.out.println(
                                    "Pokémon não encontrado."
                            );

                        } else {

                            System.out.println(
                                    "\n===== POKÉMON ====="
                            );

                            System.out.println(pokemon);
                        }

                    } catch (
                            IOException |
                            InterruptedException e
                    ) {

                        System.out.println(
                                "Erro ao consultar a API: "
                                        + e.getMessage()
                        );
                    }

                    break;

                case 2:

                    try {

                        service.listarConsultas();

                    } catch (IOException e) {

                        System.out.println(
                                "Erro ao ler o arquivo de log."
                        );
                    }

                    break;

                case 3:

                    System.out.println(
                            "\nPrograma encerrado."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpção inválida."
                    );
            }
        }

        scanner.close();
    }
}
