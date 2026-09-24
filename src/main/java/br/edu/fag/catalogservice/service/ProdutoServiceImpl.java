package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.domain.Produto;
import br.edu.fag.catalogservice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
    public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto criar(String nome, String descricao, Double preco) {

        Produto produto = new Produto(nome, descricao, preco);

        return produtoRepository.salvar(produto);
    }
}