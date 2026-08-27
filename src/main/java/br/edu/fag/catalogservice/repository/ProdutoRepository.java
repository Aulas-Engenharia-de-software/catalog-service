package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa){
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    }
    
    public ProdutoDomain criar(ProdutoEntity produtoEntity){
        final ProdutoEntity produtoPersistido = produtoRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoPersistido);
    }
}
