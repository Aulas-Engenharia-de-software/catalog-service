package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;

import java.util.List;

public interface IProdutoRepository {

    public ProdutoDomain criar(ProdutoEntity produtoEntity);

    public ProdutoDomain buscarPorId(Integer id);

    public List<ProdutoDomain> listarAtivos();

    public ProdutoDomain desativar(Integer id);
}