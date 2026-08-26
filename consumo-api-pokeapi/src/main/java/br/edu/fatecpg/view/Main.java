package br.edu.fatecpg.view;

import br.edu.fatecpg.model.PokemonFavorito;
import br.edu.fatecpg.model.PokemonResumo;
import br.edu.fatecpg.service.ConsomeApi;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> visualizarTodos();
                case 2 -> favoritar();
                case 3 -> listarFavoritos();
                case 4 -> excluirFavorito();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== MENU POKÉDEX =====");
        System.out.println("1 - Visualizar todos os pokémons");
        System.out.println("2 - Favoritar um pokémon");
        System.out.println("3 - Listar favoritos");
        System.out.println("4 - Excluir favorito");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(SC.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void visualizarTodos() {
        List<PokemonResumo> lista = ConsomeApi.listarPokemons();
        if (lista.isEmpty()) {
            System.out.println("Nenhum pokémon encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getNome());
        }
    }

    private static void favoritar() {
        System.out.print("Digite o nome do pokémon que deseja favoritar: ");
        String nome = SC.nextLine().trim();

        PokemonFavorito fv = ConsomeApi.buscarDetalhe(nome);
        if (fv == null) {
            System.out.println("Não foi possível encontrar esse pokémon.");
            return;
        }
        PokemonFavorito.salvar(fv);
    }

    private static void listarFavoritos() {
        List<PokemonFavorito> lista = PokemonFavorito.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum favorito cadastrado.");
            return;
        }
        for (PokemonFavorito fv : lista) {
            System.out.println(fv);
        }
    }

    private static void excluirFavorito() {
        listarFavoritos();
        System.out.print("Digite o id do favorito que deseja excluir: ");
        try {
            int id = Integer.parseInt(SC.nextLine().trim());
            PokemonFavorito.deletar(id);
        } catch (NumberFormatException e) {
            System.out.println("Id inválido.");
        }
    }
}
