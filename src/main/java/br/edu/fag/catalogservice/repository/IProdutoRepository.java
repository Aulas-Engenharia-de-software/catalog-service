package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

public interface IProdutoRepository {

    public ProdutoDomain criar(ProdutoEntity produtoEntity);
    
}