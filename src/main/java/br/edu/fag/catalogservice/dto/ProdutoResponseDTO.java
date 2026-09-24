package br.edu.fag.catalogservice.dto;

import java.time.LocalDateTime;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;

    public ProdutoResponseDTO(Long id, String nome, String descricao,
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