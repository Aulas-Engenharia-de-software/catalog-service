package br.edu.fag.catalogservice.domain;

import java.time.LocalDateTime;

public class Produto {

    private static final double PRECO_DE_PRODUTO_DE_ALTO_VALOR = 1000.00;

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;

    public Produto(String nome, String descricao, Double preco) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório.");
        }

        if (preco == null) {
            throw new IllegalArgumentException(
                    "O preço do produto deve ser um número válido.");
        }

        if (preco <= 0) {
            throw new IllegalArgumentException(
                    "O preço do produto deve ser maior que zero.");
        }

        this.nome = nome.trim();
        this.descricao = descricao == null ? null : descricao.trim();

        if (this.nome.length() < 3) {
            throw new IllegalArgumentException(
                    "O nome do produto deve ter pelo menos 3 caracteres.");
        }

        if (preco >= PRECO_DE_PRODUTO_DE_ALTO_VALOR
                && (this.descricao == null || this.descricao.isBlank())) {
            throw new IllegalArgumentException(
                    "Produtos a partir de R$ 1.000,00 devem possuir uma descrição.");
        }

        this.preco = Math.round(preco * 100.0) / 100.0;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}