package br.edu.fag.catalogservice.entity;

import br.edu.fag.catalogservice.domain.Produto;

import java.time.LocalDateTime;

public record ProdutoEntity(
        Long id,
        String nome,
        String descricao,
        Double preco,
        LocalDateTime criadoEm,
        Boolean ativo) {

    public static ProdutoEntity de(Produto produto) {
        return new ProdutoEntity(produto.id(), produto.nome(), produto.descricao(),
                produto.preco(), produto.criadoEm(), produto.ativo());
    }
}
