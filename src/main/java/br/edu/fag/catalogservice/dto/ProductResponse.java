package br.edu.fag.catalogservice.dto;

import br.edu.fag.catalogservice.domain.Produto;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Boolean active,
        LocalDateTime createdAt) {

    public static ProductResponse from(Produto produto) {
        return new ProductResponse(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.ativo(),
                produto.criadoEm());
    }
}
