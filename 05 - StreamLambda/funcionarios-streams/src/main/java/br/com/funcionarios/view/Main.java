package br.com.funcionarios.view;

import br.com.funcionarios.model.Funcionario;
import br.com.funcionarios.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Ana", "TI", 4500.0, 12));
        funcionarios.add(new Funcionario("Bruno", "TI", 2800.0, 3));
        funcionarios.add(new Funcionario("Carla", "RH", 3200.0, 8));
        funcionarios.add(new Funcionario("Diego", "RH", 5100.0, 15));
        funcionarios.add(new Funcionario("Elaine", "Financeiro", 2600.0, 2));
        funcionarios.add(new Funcionario("Fabio", "Financeiro", 3900.0, 11));
        funcionarios.add(new Funcionario("Gustavo", "TI", 6200.0, 20));
        funcionarios.add(new Funcionario("Helena", "RH", 2950.0, 6));

        FuncionarioService service = new FuncionarioService();

        System.out.println("=== Funcionarios com salario acima de R$ 3000,00 ===");
        List<Funcionario> filtrados = service.filtrarPorSalario(funcionarios, 3000.0);
        filtrados.forEach(System.out::println);

        System.out.println("\n=== Funcionarios apos aumento de 5% (mais de 10 anos de servico) ===");
        List<Funcionario> comAumento = service.aplicarAumento(funcionarios, 10, 0.05);
        comAumento.forEach(System.out::println);

        System.out.println("\n=== Funcionarios ordenados por nome ===");
        List<Funcionario> ordenados = service.ordenarPorNome(comAumento);
        ordenados.forEach(System.out::println);

        double total = service.calcularTotalSalarios(comAumento);
        System.out.printf("%n=== Total gasto com salarios: R$ %.2f ===%n", total);

        Map<String, Double> mediaPorDepartamento = service.mediaSalarialPorDepartamento(comAumento);
        System.out.println("\n=== Media salarial por departamento ===");
        mediaPorDepartamento.forEach((departamento, media) ->
                System.out.printf("%-12s -> R$ %.2f%n", departamento, media));
    }
}
