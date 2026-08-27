package br.edu.fag.catalogservice.service.domain;

public class ProdutoDomain {
    
    private String nome;

    private String descricao;

    private Double preco;


    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void validarNome() {
        if (nome.trim().length() < 3) {
            throw new RuntimeException(
                    "O nome do produto deve ter pelo menos 3 caracteres.");
        }
    }

    public void validarPreco() {
        if (preco == null) {
            throw new RuntimeException(
                    "O preço do produto deve ser um número válido.");
        }

        if (preco <= 0) {
            throw new RuntimeException(
                    "O preço do produto deve ser maior que zero.");
        }
    }

    public void validarDescricao() {
        if (preco != null
                && preco >= 1000.00
                && (descricao == null || descricao.isBlank())) {

            throw new RuntimeException(
                    "Produtos a partir de R$ 1.000,00 devem possuir uma descrição.");
        }
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
    
}
