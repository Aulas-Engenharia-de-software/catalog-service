package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.repository.IRepositorio;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProdutoService implements IProdutoService {

    private final IRepositorio produtoRepository;

    public ProdutoService(IRepositorio produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDomain criar(ProdutoDomain produto) {

        produto.validarPreco();

        produto.normalizarDados();

        produto.validarNome();

        produto.validarProdutoDeAltoValor();

        produto.arredondarPreco();

        produto.setCriadoEm(LocalDateTime.now());

        return produtoRepository.criar(produto);
    }
}