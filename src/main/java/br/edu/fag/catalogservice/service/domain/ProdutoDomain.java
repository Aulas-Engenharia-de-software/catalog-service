package br.edu.fag.catalogservice.service.domain;

import java.time.LocalDateTime;

public class ProdutoDomain {

    private static final double PRECO_DE_PRODUTO_DE_ALTO_VALOR = 1000.00;

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;

    public void validarPreco() {
        if (preco <= 0) {
            throw new IllegalArgumentException(
                    "O preço do produto deve ser maior que zero."
            );
        }
    }

    public void normalizarDados() {
        nome = nome.trim();

        if (descricao != null) {
            descricao = descricao.trim();
        }
    }

    public void validarNome() {
        if (nome.length() < 3) {
            throw new IllegalArgumentException(
                    "O nome do produto deve ter pelo menos 3 caracteres."
            );
        }
    }

    public void validarProdutoDeAltoValor() {
        if (preco >= PRECO_DE_PRODUTO_DE_ALTO_VALOR
                && (descricao == null || descricao.isBlank())) {

            throw new IllegalArgumentException(
                    "Produtos a partir de R$ 1.000,00 devem possuir uma descrição."
            );
        }
    }

    public void arredondarPreco() {
        preco = Math.round(preco * 100.0) / 100.0;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}