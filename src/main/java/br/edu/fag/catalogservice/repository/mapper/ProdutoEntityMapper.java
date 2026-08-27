package br.edu.fag.catalogservice.repository.mapper;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

public class ProdutoEntityMapper {

    public static ProdutoEntity toEntity(ProdutoDomain produto) {

        ProdutoEntity entity = new ProdutoEntity();

        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setDescricao(produto.getDescricao());
        entity.setPreco(produto.getPreco());
        entity.setCriadoEm(produto.getCriadoEm());

        return entity;
    }

    public static ProdutoDomain toDomain(ProdutoEntity entity) {

        ProdutoDomain produto = new ProdutoDomain();

        produto.setId(entity.getId());
        produto.setNome(entity.getNome());
        produto.setDescricao(entity.getDescricao());
        produto.setPreco(entity.getPreco());
        produto.setCriadoEm(entity.getCriadoEm());

        return produto;
    }
}