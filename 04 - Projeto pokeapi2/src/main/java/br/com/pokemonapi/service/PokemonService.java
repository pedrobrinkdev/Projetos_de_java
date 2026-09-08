package br.com.pokemonapi.service;

import br.com.pokemonapi.model.Pokemon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PokemonService {

    private final HttpClient client;
    private final ConverteDados conversor;
    private final Path arquivoLog;

    public PokemonService() {
        client = HttpClient.newHttpClient();
        conversor = new ConverteDados();
        arquivoLog = Path.of("pokemon.log");
    }

    public Pokemon consultarPokemon(String idOuNome)
            throws IOException, InterruptedException {

        String url =
                "https://pokeapi.co/api/v2/pokemon/"
                        + idOuNome.toLowerCase();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            return null;
        }

        Pokemon pokemon = conversor.obterDados(
                response.body(),
                Pokemon.class
        );

        registrarConsulta(
                pokemon.getId(),
                pokemon.getNome()
        );

        return pokemon;
    }

    private void registrarConsulta(
            int id,
            String nomePokemon
    ) throws IOException {

        ZonedDateTime agora = ZonedDateTime.now(
                ZoneId.of("America/Sao_Paulo")
        );

        String registro = agora
                + " - Consulta: #" + id
                + " - " + nomePokemon
                + System.lineSeparator();

        Files.writeString(
                arquivoLog,
                registro,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public void listarConsultas() throws IOException {

        if (!Files.exists(arquivoLog)) {
            System.out.println(
                    "\nNenhuma consulta realizada."
            );
            return;
        }

        System.out.println(
                "\n===== CONSULTAS REALIZADAS ====="
        );

        String consultas = Files.readString(
                arquivoLog
        );

        System.out.println(consultas);
    }

    private static class ConverteDados {

        private final ObjectMapper mapper =
                new ObjectMapper();

        public <T> T obterDados(
                String json,
                Class<T> classe
        ) throws JsonProcessingException {

            return mapper.readValue(
                    json,
                    classe
            );
        }
    }
}
