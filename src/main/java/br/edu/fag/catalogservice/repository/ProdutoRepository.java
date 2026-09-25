package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapper.ProdutoEntityMapper;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.stereotype.Repository;
import br.edu.fag.catalogservice.service.exception.ProductNotFoundException;

import java.util.List;

@Repository
public class ProdutoRepository implements IRepositorio {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa) {
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    }

    @Override
    public ProdutoDomain criar(ProdutoDomain produto) {
        ProdutoEntity entity = ProdutoEntityMapper.toEntity(produto);
        ProdutoEntity salvo = produtoRepositoryJpa.save(entity);
        return ProdutoEntityMapper.toDomain(salvo);
    }

    @Override
    public ProdutoDomain buscarPorId(Long id) {
        ProdutoEntity entity = produtoRepositoryJpa.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProdutoEntityMapper.toDomain(entity);
    }

    @Override
    public List<ProdutoDomain> buscarAtivos() {
        return produtoRepositoryJpa.findByActiveTrue().stream()
                .map(ProdutoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ProdutoDomain atualizar(ProdutoDomain produto) {
        ProdutoEntity entity = ProdutoEntityMapper.toEntity(produto);
        ProdutoEntity salvo = produtoRepositoryJpa.save(entity);
        return ProdutoEntityMapper.toDomain(salvo);
    }
}