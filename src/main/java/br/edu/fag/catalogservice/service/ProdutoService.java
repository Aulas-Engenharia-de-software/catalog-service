package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.domain.Produto;
import br.edu.fag.catalogservice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criar(String nome, String descricao, Double preco) {
        Produto produto = Produto.criar(nome, descricao, preco);
        Long id = produtoRepository.salvar(produto);
        return produto.comId(id);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    public List<Produto> buscarAtivos() {
        return produtoRepository.buscarAtivos();
    }

    public Optional<Produto> desativar(Long id) {
        return produtoRepository.desativar(id);
    }
}
