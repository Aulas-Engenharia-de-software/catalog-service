package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.domain.Produto;

public interface ProdutoService {

    Produto criar(String nome, String descricao, Double preco);
}