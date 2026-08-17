package br.edu.fatecpg.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nome;
    private int cargaHoraria;
    private String coordenador;

    public Curso() {}

    public Curso(String no, int ch, String co) {
        this.nome = no;
        this.cargaHoraria = ch;
        this.coordenador = co;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCargaHoraria() { return this.cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public String getCoordenador() { return this.coordenador; }
    public void setCoordenador(String coordenador) { this.coordenador = coordenador; }

    // ---------- persistência (JDBC) ----------

    public void inserir() {
        String sql = "INSERT INTO cursos (nome, carga_horaria, coordenador) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setInt(2, this.cargaHoraria);
            stmt.setString(3, this.coordenador);
            stmt.executeUpdate();
            System.out.println("Curso cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir curso: " + e.getMessage());
        }
    }

    public static List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos ORDER BY id";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCargaHoraria(rs.getInt("carga_horaria"));
                c.setCoordenador(rs.getString("coordenador"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }
        return cursos;
    }

    public void atualizar() {
        String sql = "UPDATE cursos SET nome = ?, carga_horaria = ?, coordenador = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setInt(2, this.cargaHoraria);
            stmt.setString(3, this.coordenador);
            stmt.setInt(4, this.id);
            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Curso atualizado com sucesso!" : "Curso não encontrado.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    public static void deletar(int id) {
        String sql = "DELETE FROM cursos WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Curso excluído com sucesso!" : "Curso não encontrado.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - " + cargaHoraria + "h - Coord.: " + coordenador;
    }
}
