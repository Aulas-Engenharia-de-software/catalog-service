package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.domain.Produto;

public interface ProdutoRepository {

    Produto salvar(Produto produto);
}