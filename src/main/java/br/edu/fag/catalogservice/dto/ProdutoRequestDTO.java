package br.edu.fag.catalogservice.dto;

public class ProdutoRequestDTO {

    private Object nome;
    private Object descricao;
    private Object preco;

    public Object getNome() {
        return nome;
    }

    public void setNome(Object nome) {
        this.nome = nome;
    }

    public Object getDescricao() {
        return descricao;
    }

    public void setDescricao(Object descricao) {
        this.descricao = descricao;
    }

    public Object getPreco() {
        return preco;
    }

    public void setPreco(Object preco) {
        this.preco = preco;
    }
}