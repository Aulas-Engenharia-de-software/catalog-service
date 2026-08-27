package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.repository.IProdutoRepository;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements IProdutoService {

    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    
    public ProdutoDomain criar(ProdutoDomain produtoDomain){
        produtoDomain.validarNome();
        produtoDomain.validarPreco();
        produtoDomain.validarDescricao();

        ProdutoEntity produtoEntity = ProdutoEntityMapper.toEntity(produtoDomain);
        return produtoRepository.criar(produtoEntity);
    }
}
