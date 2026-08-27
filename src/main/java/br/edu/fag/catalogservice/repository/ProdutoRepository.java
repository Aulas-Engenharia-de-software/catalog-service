package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapper.ProdutoEntityMapper;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository implements  IRepositorio{

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa) {
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    }

    public ProdutoDomain criar(ProdutoDomain produto) {

        ProdutoEntity entity =
                ProdutoEntityMapper.toEntity(produto);

        ProdutoEntity produtoSalvo =
                produtoRepositoryJpa.save(entity);

        return ProdutoEntityMapper.toDomain(produtoSalvo);
    }
}