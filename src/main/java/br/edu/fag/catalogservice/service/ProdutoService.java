package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.repository.IRepositorio;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        produto.setActive(true); // Regra da tarefa: todo produto nasce ativo

        return produtoRepository.criar(produto);
    }

    public ProdutoDomain buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    public List<ProdutoDomain> buscarAtivos() {
        return produtoRepository.buscarAtivos();
    }

    public ProdutoDomain desativar(Long id) {
        ProdutoDomain produto = produtoRepository.buscarPorId(id);
        produto.setActive(false); // Desativação lógica
        return produtoRepository.atualizar(produto);
    }
}