package br.edu.fag.catalogservice.entity;

import java.time.LocalDateTime;

public class ProdutoEntity {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;

    public ProdutoEntity(Long id, String nome, String descricao,
                         Double preco, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.criadoEm = criadoEm;
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