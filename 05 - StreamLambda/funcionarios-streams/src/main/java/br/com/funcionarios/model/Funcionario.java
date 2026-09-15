package br.com.funcionarios.model;

public class Funcionario {

    private String nome;
    private String departamento;
    private double salario;
    private int anosDeServico;

    public Funcionario(String nm, String dp, double sl, int an) {
        this.nome = nm;
        this.departamento = dp;
        this.salario = sl;
        this.anosDeServico = an;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public double getSalario() {
        return this.salario;
    }

    public int getAnosDeServico() {
        return this.anosDeServico;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-12s | R$ %8.2f | %2d anos", nome, departamento, salario, anosDeServico);
    }
}
