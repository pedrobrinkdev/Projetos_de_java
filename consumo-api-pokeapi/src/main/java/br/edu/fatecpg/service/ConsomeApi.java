package br.edu.fatecpg.service;

import br.edu.fatecpg.model.PokemonFavorito;
import br.edu.fatecpg.model.PokemonResumo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsomeApi {

    private static final String URL_BASE = "https://pokeapi.co/api/v2/pokemon";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static List<PokemonResumo> listarPokemons() {
        List<PokemonResumo> lista = new ArrayList<>();
        try {
            String json = buscar(URL_BASE + "?limit=100");
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
            JsonArray resultados = obj.getAsJsonArray("results");

            for (int i = 0; i < resultados.size(); i++) {
                JsonObject item = resultados.get(i).getAsJsonObject();
                String nm = item.get("name").getAsString();
                String ur = item.get("url").getAsString();
                lista.add(new PokemonResumo(nm, ur));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar lista de pokémons: " + e.getMessage());
        }
        return lista;
    }

    public static PokemonFavorito buscarDetalhe(String nome) {
        try {
            String json = buscar(URL_BASE + "/" + nome.toLowerCase());
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            int pk = obj.get("id").getAsInt();
            int al = obj.get("height").getAsInt();
            String tp = extrairTipos(obj.getAsJsonArray("types"));

            return new PokemonFavorito(0, nome, pk, al, tp);
        } catch (Exception e) {
            System.out.println("Erro ao buscar detalhe do pokémon: " + e.getMessage());
            return null;
        }
    }

    private static String extrairTipos(JsonArray typesArray) {
        StringBuilder tipos = new StringBuilder();
        for (int i = 0; i < typesArray.size(); i++) {
            String tipo = typesArray.get(i).getAsJsonObject()
                    .getAsJsonObject("type").get("name").getAsString();
            tipos.append(tipo);
            if (i < typesArray.size() - 1) {
                tipos.append(", ");
            }
        }
        return tipos.toString();
    }

    private static String buscar(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
