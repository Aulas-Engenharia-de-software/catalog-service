package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapperEntity.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.stream.Collectors;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa) {
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    }

    public ProdutoDomain criar(ProdutoEntity produtoEntity) {
        final ProdutoEntity produtoPersistido = produtoRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoPersistido);
    }

    public ProdutoDomain buscarPorId(Integer id) {
        return produtoRepositoryJpa.findById(id)
                .map(ProdutoEntityMapper::toDomain)
                .orElse(null);
    }

    public List<ProdutoDomain> listarAtivos() {
        return produtoRepositoryJpa.findByActiveTrue()
                .stream()
                .map(ProdutoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public ProdutoDomain desativar(Integer id) {
        ProdutoEntity produtoEntity = produtoRepositoryJpa.findById(id)
                .orElse(null);

        if (produtoEntity == null) {
            return null;
        }

        produtoEntity.setActive(false);

        ProdutoEntity produtoAtualizado = produtoRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoAtualizado);
    }
}