package br.com.funcionarios.service;

import br.com.funcionarios.model.Funcionario;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioService {

    // Filtragem: funcionarios com salario acima de um valor definido
    public List<Funcionario> filtrarPorSalario(List<Funcionario> funcionarios, double sl) {
        return funcionarios.stream()
                .filter(f -> f.getSalario() > sl)
                .collect(Collectors.toList());
    }

    // Mapeamento: aumento de 5% para quem tem mais de 10 anos de servico
    public List<Funcionario> aplicarAumento(List<Funcionario> funcionarios, int an, double pct) {
        return funcionarios.stream()
                .map(f -> f.getAnosDeServico() > an
                        ? new Funcionario(f.getNome(), f.getDepartamento(), f.getSalario() * (1 + pct), f.getAnosDeServico())
                        : f)
                .collect(Collectors.toList());
    }

    // Ordenacao: por nome em ordem alfabetica
    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    // Reducao: total gasto com salarios
    public double calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(0.0, Double::sum);
    }

    // Agrupamento: media salarial por departamento
    public Map<String, Double> mediaSalarialPorDepartamento(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getDepartamento, Collectors.averagingDouble(Funcionario::getSalario)));
    }
}
