package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

import java.util.List;

public interface IProdutoService {

    public ProdutoDomain criar(ProdutoDomain produtoDomain);

    public ProdutoDomain buscarPorId(Integer id);

    public List<ProdutoDomain> listarAtivos();

    public ProdutoDomain desativar(Integer id);
}