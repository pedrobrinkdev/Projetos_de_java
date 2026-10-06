package org.example.controller;

import org.example.model.Carrinho;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) throws IOException {
        return mapper.readValue(json, classe);
    }

}
