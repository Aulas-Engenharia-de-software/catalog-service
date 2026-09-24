package br.edu.fag.catalogservice.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class ProdutoCriacaoRequest {

    private JsonNode nome;
    private JsonNode descricao;
    private JsonNode preco;

    public JsonNode getNome() {
        return nome;
    }

    public void setNome(JsonNode nome) {
        this.nome = nome;
    }

    public JsonNode getDescricao() {
        return descricao;
    }

    public void setDescricao(JsonNode descricao) {
        this.descricao = descricao;
    }

    public JsonNode getPreco() {
        return preco;
    }

    public void setPreco(JsonNode preco) {
        this.preco = preco;
    }
}
