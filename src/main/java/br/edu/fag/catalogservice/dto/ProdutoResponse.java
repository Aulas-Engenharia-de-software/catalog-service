package br.edu.fag.catalogservice.dto;

import br.edu.fag.catalogservice.domain.Produto;

import java.time.LocalDateTime;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
    LocalDateTime criadoEm) {

    public static ProdutoResponse de(Produto produto) {
        return new ProdutoResponse(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.criadoEm());
    }
}
