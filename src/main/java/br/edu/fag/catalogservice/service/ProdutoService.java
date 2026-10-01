package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapperEntity.ProdutoEntityMapper;
import br.edu.fag.catalogservice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDomain criar(ProdutoDomain produtoDomain) {
        produtoDomain.tamanhoNome();
        produtoDomain.precoAlto();

        produtoDomain.setCriadoEm(LocalDateTime.now());
        produtoDomain.setActive(true);

        ProdutoEntity produtoEntity = ProdutoEntityMapper.toEntity(produtoDomain);

        return produtoRepository.criar(produtoEntity);
    }

    public ProdutoDomain buscarPorId(Integer id) {
        return produtoRepository.buscarPorId(id);
    }

    public List<ProdutoDomain> listarAtivos() {
        return produtoRepository.listarAtivos();
    }

    public ProdutoDomain desativar(Integer id) {
        return produtoRepository.desativar(id);
    }
}