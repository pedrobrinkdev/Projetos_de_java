package br.edu.fatecpg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonFavorito {

    private int id;
    private String nome;
    private int pokedexId;
    private int altura;
    private String tipos;

    public PokemonFavorito(int id, String nm, int pk, int al, String tp) {
        this.id = id;
        this.nome = nm;
        this.pokedexId = pk;
        this.altura = al;
        this.tipos = tp;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPokedexId() {
        return this.pokedexId;
    }

    public int getAltura() {
        return this.altura;
    }

    public String getTipos() {
        return this.tipos;
    }

    public static void salvar(PokemonFavorito fv) {
        String sql = "INSERT INTO pokemon_favorito (nome, pokedex_id, altura, tipos) VALUES (?, ?, ?, ?)";
        try (Connection cn = DatabaseConfig.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, fv.getNome());
            ps.setInt(2, fv.getPokedexId());
            ps.setInt(3, fv.getAltura());
            ps.setString(4, fv.getTipos());
            ps.executeUpdate();

            System.out.println("Pokémon favoritado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar favorito: " + e.getMessage());
        }
    }

    public static List<PokemonFavorito> listar() {
        List<PokemonFavorito> lista = new ArrayList<>();
        String sql = "SELECT * FROM pokemon_favorito ORDER BY id";

        try (Connection cn = DatabaseConfig.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PokemonFavorito fv = new PokemonFavorito(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("pokedex_id"),
                        rs.getInt("altura"),
                        rs.getString("tipos")
                );
                lista.add(fv);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar favoritos: " + e.getMessage());
        }
        return lista;
    }

    public static void deletar(int id) {
        String sql = "DELETE FROM pokemon_favorito WHERE id = ?";
        try (Connection cn = DatabaseConfig.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Favorito excluído com sucesso!");
            } else {
                System.out.println("Nenhum favorito encontrado com esse id.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir favorito: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return id + " - " + nome + " (pokedex #" + pokedexId + ", altura " + altura + ", tipos: " + tipos + ")";
    }
}
