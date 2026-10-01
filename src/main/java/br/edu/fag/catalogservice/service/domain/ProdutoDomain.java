package br.edu.fag.catalogservice.service.domain;

import java.time.LocalDateTime;

public class ProdutoDomain {

    private static final double PRECO_DE_PRODUTO_DE_ALTO_VALOR = 1000;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;
    private Integer id;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao == null ? null : descricao.trim();
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void tamanhoNome() {
        if (nome.length() < 3) {
            throw new RuntimeException("O nome do produto deve ter pelo menos 3 caracteres.");
        }
    }

    public void precoAlto() {
        if (preco >= PRECO_DE_PRODUTO_DE_ALTO_VALOR
                && (descricao == null || descricao.isBlank())) {
            throw new RuntimeException("Produtos a partir de R$ 1.000,00 devem possuir uma descrição.");
        }
    }
}