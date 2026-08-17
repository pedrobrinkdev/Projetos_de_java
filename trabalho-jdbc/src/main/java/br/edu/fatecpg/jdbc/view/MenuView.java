package br.edu.fatecpg.jdbc.view;

import br.edu.fatecpg.jdbc.model.Curso;
import br.edu.fatecpg.jdbc.model.Tarefa;
import br.edu.fatecpg.jdbc.model.Tarefa.Categoria;
import br.edu.fatecpg.jdbc.model.Tarefa.Prioridade;
import br.edu.fatecpg.jdbc.model.Tarefa.StatusTarefa;

import java.util.List;
import java.util.Scanner;

public class MenuView {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== TRABALHO JDBC - FATEC ===");
            System.out.println("1. Gestão de Cursos");
            System.out.println("2. Gestão de Tarefas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: menuCursos(); break;
                case 2: menuTarefas(); break;
                case 0: System.out.println("Encerrando..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

    // ---------- exercício 1: CRUD de Curso ----------

    private static void menuCursos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE CURSOS ---");
            System.out.println("1. Inserir Curso");
            System.out.println("2. Listar Cursos");
            System.out.println("3. Atualizar Curso");
            System.out.println("4. Deletar Curso");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do curso: ");
                    String nome = sc.nextLine();
                    System.out.print("Carga horária (horas): ");
                    int carga = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Coordenador: ");
                    String coord = sc.nextLine();
                    new Curso(nome, carga, coord).inserir();
                    break;
                case 2:
                    List<Curso> cursos = Curso.listarTodos();
                    if (cursos.isEmpty()) System.out.println("Nenhum curso cadastrado.");
                    else cursos.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID do curso a atualizar: ");
                    int idUp = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Nova carga horária: ");
                    int novaCarga = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo coordenador: ");
                    String novoCoord = sc.nextLine();
                    Curso c = new Curso();
                    c.setId(idUp);
                    c.setNome(novoNome);
                    c.setCargaHoraria(novaCarga);
                    c.setCoordenador(novoCoord);
                    c.atualizar();
                    break;
                case 4:
                    System.out.print("ID do curso a deletar: ");
                    int idDel = sc.nextInt();
                    Curso.deletar(idDel);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // ---------- exercício 2: Gestão de Tarefas ----------

    private static void menuTarefas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE TAREFAS ---");
            System.out.println("1. Cadastrar Tarefa");
            System.out.println("2. Listar Todas as Tarefas");
            System.out.println("3. Listar por Categoria");
            System.out.println("4. Listar por Status");
            System.out.println("5. Atualizar Tarefa");
            System.out.println("6. Marcar Tarefa como Concluída");
            System.out.println("7. Excluir Tarefa");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();

                    System.out.println("Categorias disponíveis:");
                    Categoria.listarTodas().forEach(System.out::println);
                    System.out.print("ID da categoria: ");
                    int catId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Prioridade: 1-BAIXA 2-MEDIA 3-ALTA");
                    int op = sc.nextInt();
                    sc.nextLine();
                    Prioridade prioridade = op == 1 ? Prioridade.BAIXA : op == 3 ? Prioridade.ALTA : Prioridade.MEDIA;

                    new Tarefa(titulo, desc, catId, prioridade).inserir();
                    break;
                case 2:
                    imprimir(Tarefa.listarTodas());
                    break;
                case 3:
                    System.out.print("ID da categoria: ");
                    int idCat = sc.nextInt();
                    imprimir(Tarefa.listarPorCategoria(idCat));
                    break;
                case 4:
                    System.out.println("1-PENDENTE 2-EM_ANDAMENTO 3-CONCLUIDA");
                    int stOp = sc.nextInt();
                    StatusTarefa status = stOp == 2 ? StatusTarefa.EM_ANDAMENTO
                            : stOp == 3 ? StatusTarefa.CONCLUIDA : StatusTarefa.PENDENTE;
                    imprimir(Tarefa.listarPorStatus(status));
                    break;
                case 5:
                    System.out.print("ID da tarefa: ");
                    int idUp = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo título: ");
                    String nt = sc.nextLine();
                    System.out.print("Nova descrição: ");
                    String nd = sc.nextLine();
                    System.out.println("Nova prioridade: 1-BAIXA 2-MEDIA 3-ALTA");
                    int npOp = sc.nextInt();
                    Prioridade np = npOp == 1 ? Prioridade.BAIXA : npOp == 3 ? Prioridade.ALTA : Prioridade.MEDIA;
                    Tarefa t = new Tarefa();
                    t.setId(idUp);
                    t.setTitulo(nt);
                    t.setDescricao(nd);
                    t.setPrioridade(np);
                    t.atualizar();
                    break;
                case 6:
                    System.out.print("ID da tarefa: ");
                    int idConc = sc.nextInt();
                    Tarefa tc = new Tarefa();
                    tc.setId(idConc);
                    tc.concluir();
                    break;
                case 7:
                    System.out.print("ID da tarefa: ");
                    int idDel = sc.nextInt();
                    Tarefa.deletar(idDel);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void imprimir(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
            return;
        }
        tarefas.forEach(System.out::println);
    }
}
