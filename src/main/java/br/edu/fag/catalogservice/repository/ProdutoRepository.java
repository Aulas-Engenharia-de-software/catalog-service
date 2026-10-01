package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private final InterfaceRepositoryJpa interfaceRepositoryJpa;

    public ProdutoRepository(InterfaceRepositoryJpa interfaceRepositoryJpa) {
        this.interfaceRepositoryJpa = interfaceRepositoryJpa;
    }

    public ProdutoDomain criar(ProdutoEntity produtoEntity) {
        final ProdutoEntity produtoPersistida = interfaceRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoPersistida);
    }

    public ProdutoDomain buscarPorId(Integer id) {
        return interfaceRepositoryJpa.findById(id)
                .map(ProdutoEntityMapper::toDomain)
                .orElse(null);
    }

    public List<ProdutoDomain> listarAtivos() {
        return interfaceRepositoryJpa.findByActiveTrue()
                .stream()
                .map(ProdutoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public ProdutoDomain desativar(Integer id) {
        ProdutoEntity produtoEntity = interfaceRepositoryJpa.findById(id)
                .orElse(null);

        if (produtoEntity == null) {
            return null;
        }

        produtoEntity.setActive(false);

        ProdutoEntity produtoAtualizado = interfaceRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoAtualizado);
    }
}