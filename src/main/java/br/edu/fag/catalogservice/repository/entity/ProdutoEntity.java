package br.edu.fag.catalogservice.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")

public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDateTime criadoEm;
    private Boolean active;


    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}