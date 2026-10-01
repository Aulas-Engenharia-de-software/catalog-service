package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

import java.util.List;

public interface IProdutoService {

    ProdutoDomain criar(ProdutoDomain produtoDomain);

    ProdutoDomain buscarPorId(Integer id);

    List<ProdutoDomain> listarAtivos();

    ProdutoDomain desativar(Integer id);
}