package br.edu.fatecpg.tecprog2.revisaop2.passagens.model;

/**
 * Representa um voo disponivel no sistema de reservas.
 * Concentra as regras de negocio ligadas diretamente a um voo:
 * verificacao de disponibilidade, reserva de assentos, pagamento e impressao.
 */
public class Voo {

    private String numeroVoo;
    private String origem;
    private String destino;
    private int assentosDisponiveis;
    private double valorBase;

    private static final double TAXA_IDA_VOLTA = 2.0;
    private static final double TAXA_PONTOS_TURISTICOS = 0.15;

    public Voo(String nv, String or, String de, int qt, double vb) {
        this.numeroVoo = nv;
        this.origem = or;
        this.destino = de;
        this.assentosDisponiveis = qt;
        this.valorBase = vb;
    }

    // ---------- Regras de negocio ----------

    /**
     * Verifica se o voo possui a quantidade de assentos solicitada.
     */
    public boolean verificarDisponibilidade(int quantidadeAssentos) {
        return this.assentosDisponiveis >= quantidadeAssentos;
    }

    /**
     * Realiza a reserva de assentos, caso haja disponibilidade.
     * @return true se a reserva foi concluida, false se nao havia assentos suficientes.
     */
    public boolean realizarReserva(int quantidadeAssentos) {
        if (!verificarDisponibilidade(quantidadeAssentos)) {
            return false;
        }
        this.assentosDisponiveis -= quantidadeAssentos;
        return true;
    }

    /**
     * Calcula o valor do pagamento da passagem.
     * @param tipoViagem "SOMENTE_IDA" ou "IDA_E_VOLTA"
     * @param pontosTuristicos se true, aplica taxa adicional de passeio turistico
     * @return valor total a ser pago
     */
    public double realizarPagamento(String tipoViagem, boolean pontosTuristicos) {
        double total = this.valorBase;

        if ("IDA_E_VOLTA".equalsIgnoreCase(tipoViagem)) {
            total = total * TAXA_IDA_VOLTA;
        }

        if (pontosTuristicos) {
            total += total * TAXA_PONTOS_TURISTICOS;
        }

        return total;
    }

    /**
     * Imprime no console os detalhes basicos da passagem/voo.
     */
    public void imprimirPassagem() {
        System.out.println("===== Detalhes da Passagem =====");
        System.out.println("Numero do voo: " + this.numeroVoo);
        System.out.println("Origem: " + this.origem);
        System.out.println("Destino: " + this.destino);
        System.out.println("Assentos disponiveis: " + this.assentosDisponiveis);
        System.out.println("Valor base: R$ " + String.format("%.2f", this.valorBase));
        System.out.println("=================================");
    }

    // ---------- Getters e Setters ----------

    public String getNumeroVoo() {
        return this.numeroVoo;
    }

    public void setNumeroVoo(String nv) {
        this.numeroVoo = nv;
    }

    public String getOrigem() {
        return this.origem;
    }

    public void setOrigem(String or) {
        this.origem = or;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String de) {
        this.destino = de;
    }

    public int getAssentosDisponiveis() {
        return this.assentosDisponiveis;
    }

    public void setAssentosDisponiveis(int qt) {
        this.assentosDisponiveis = qt;
    }

    public double getValorBase() {
        return this.valorBase;
    }

    public void setValorBase(double vb) {
        this.valorBase = vb;
    }

    @Override
    public String toString() {
        return "Voo{" + numeroVoo + ", " + origem + "->" + destino + ", assentos=" + assentosDisponiveis + "}";
    }
}
