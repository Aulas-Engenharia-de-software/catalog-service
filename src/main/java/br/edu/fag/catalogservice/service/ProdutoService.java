package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.model.Produto;
import br.edu.fag.catalogservice.repository.ProdutoRepository;
import br.edu.fag.catalogservice.service.exception.ProdutoInvalidoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ProdutoService {
    private static final double PRECO_DE_PRODUTO_DE_ALTO_VALOR = 1000.00; // Como n"ao posso mudar a logica ainda, deixei aqui, mas penso em colocar isso em uma variavel de config no database para que se possa ser mudado por um user futuramente

private final ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criar(String nomeRecebido, String descricaoRecebida, Double preco) {
        if (nomeRecebido == null || nomeRecebido.isBlank()) {
            throw new ProdutoInvalidoException("O nome do produto é obrigatorio.");
        }

        if (preco == null) {
            throw new ProdutoInvalidoException("O preço do produto deve ser um número válido.");
        }

        if (preco <= 0) {
            throw new ProdutoInvalidoException("O preço do produto deve ser maior que zero.");
        }

        String nome = nomeRecebido.trim();
        String descricao = descricaoRecebida == null ? null : descricaoRecebida.trim();

        if (nome.length() < 3) {
            throw new ProdutoInvalidoException("O nome do produto deve ter pelo menos 3 caracteres.");
        }

        if (preco >= PRECO_DE_PRODUTO_DE_ALTO_VALOR
                && (descricao == null || descricao.isBlank())) {
            throw new ProdutoInvalidoException(
                    "Produtos a partir de R$ 1.000,00 devem possuir uma descrição.");
        }

        double precoArredondado = Math.round(preco * 100.0) / 100.0;
        LocalDateTime criadoEm = LocalDateTime.now();

        Produto produto = new Produto(nome, descricao, precoArredondado, criadoEm);

        return produtoRepository.salvar(produto);
    }
}
