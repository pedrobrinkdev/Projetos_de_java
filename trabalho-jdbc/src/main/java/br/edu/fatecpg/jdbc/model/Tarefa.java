package br.edu.fatecpg.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private Prioridade prioridade;
    private int categoriaId;
    private String nomeCategoria;

    public Tarefa() {}

    public Tarefa(String ti, String de, int ca, Prioridade pr) {
        this.titulo = ti;
        this.descricao = de;
        this.categoriaId = ca;
        this.prioridade = pr;
        this.status = StatusTarefa.PENDENTE;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return this.descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusTarefa getStatus() { return this.status; }
    public void setStatus(StatusTarefa status) { this.status = status; }

    public Prioridade getPrioridade() { return this.prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public int getCategoriaId() { return this.categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public String getNomeCategoria() { return this.nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }

    // ---------- persistência (JDBC) ----------

    private static final String SELECT_BASE =
            "SELECT t.*, c.nome AS categoria_nome FROM tarefas t JOIN categorias c ON t.categoria_id = c.id";

    public void inserir() {
        String sql = "INSERT INTO tarefas (titulo, descricao, status, prioridade, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.descricao);
            stmt.setString(3, this.status.name());
            stmt.setString(4, this.prioridade.name());
            stmt.setInt(5, this.categoriaId);
            stmt.executeUpdate();
            System.out.println("Tarefa cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public static List<Tarefa> listarTodas() {
        return buscar(SELECT_BASE + " ORDER BY t.id");
    }

    public static List<Tarefa> listarPorCategoria(int categoriaId) {
        return buscar(SELECT_BASE + " WHERE t.categoria_id = ? ORDER BY t.id", categoriaId);
    }

    public static List<Tarefa> listarPorStatus(StatusTarefa status) {
        return buscar(SELECT_BASE + " WHERE t.status = ? ORDER BY t.id", status.name());
    }

    private static List<Tarefa> buscar(String sql, Object... params) {
        List<Tarefa> tarefas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa t = new Tarefa();
                    t.setId(rs.getInt("id"));
                    t.setTitulo(rs.getString("titulo"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setStatus(StatusTarefa.valueOf(rs.getString("status")));
                    t.setPrioridade(Prioridade.valueOf(rs.getString("prioridade")));
                    t.setCategoriaId(rs.getInt("categoria_id"));
                    t.setNomeCategoria(rs.getString("categoria_nome"));
                    tarefas.add(t);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    public void atualizar() {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, prioridade = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.descricao);
            stmt.setString(3, this.prioridade.name());
            stmt.setInt(4, this.id);
            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Tarefa atualizada com sucesso!" : "Tarefa não encontrada.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void concluir() {
        String sql = "UPDATE tarefas SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, StatusTarefa.CONCLUIDA.name());
            stmt.setInt(2, this.id);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                this.status = StatusTarefa.CONCLUIDA;
                System.out.println("Tarefa marcada como concluída!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao concluir tarefa: " + e.getMessage());
        }
    }

    public static void deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Tarefa excluída com sucesso!" : "Tarefa não encontrada.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tarefa: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " | " + status + " | " + prioridade + " | " + nomeCategoria;
    }

    // ---------- tipos aninhados do domínio Tarefa ----------
    // "public" aqui pois precisam ser enxergados do pacote view (Tarefa.StatusTarefa, Tarefa.Categoria...)

    public enum StatusTarefa {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }

    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    public static class Categoria {
        private int id;
        private String nome;

        public Categoria(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public int getId() { return this.id; }
        public String getNome() { return this.nome; }

        public static List<Categoria> listarTodas() {
            List<Categoria> categorias = new ArrayList<>();
            String sql = "SELECT * FROM categorias ORDER BY id";
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categorias.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
                }
            } catch (SQLException e) {
                System.err.println("Erro ao listar categorias: " + e.getMessage());
            }
            return categorias;
        }

        @Override
        public String toString() {
            return "[" + id + "] " + nome;
        }
    }
}
