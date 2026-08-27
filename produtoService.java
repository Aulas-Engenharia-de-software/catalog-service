package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.model.Produto;
import br.edu.fag.catalogservice.repository.ProdutoRepositorio;
import br.edu.fag.catalogservice.service.dto.ProdutoDTO;
import br.edu.fag.catalogservice.service.mapper.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProdutoService {

    private static final BigDecimal PRECO_DE_PRODUTO_DE_ALTO_VALOR =
            new BigDecimal("1000.00");

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoService(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public ProdutoDTO criar(ProdutoDTO dto) {

        String nome = dto.getNome();
        String descricao = dto.getDescricao();
        BigDecimal preco = dto.getPreco();

        // Validação do nome
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }

        // Validação do preço
        if (preco == null) {
            throw new IllegalArgumentException(
                    "O preço do produto deve ser um número válido."
            );
        }

        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O preço do produto deve ser maior que zero."
            );
        }

        // Limpeza dos dados
        nome = nome.trim();

        if (descricao != null) {
            descricao = descricao.trim();
        }

        // Regra de negócio:
        // nomes muito curtos não identificam bem um produto.
        if (nome.length() < 3) {
            throw new IllegalArgumentException(
                    "O nome do produto deve ter pelo menos 3 caracteres."
            );
        }

        // Regra de negócio:
        // produtos de alto valor precisam ser bem descritos.
        if (preco.compareTo(PRECO_DE_PRODUTO_DE_ALTO_VALOR) >= 0
                && (descricao == null || descricao.isBlank())) {

            throw new IllegalArgumentException(
                    "Produtos a partir de R$ 1.000,00 devem possuir uma descrição."
            );
        }

        // Arredonda o preço para duas casas decimais
        preco = preco.setScale(2, java.math.RoundingMode.HALF_UP);

        // Cria o produto
        Produto produto = new Produto(
                nome,
                descricao,
                preco
        );

        produto.setCriadoEm(LocalDateTime.now());

        // Salva no banco
        Long id = produtoRepositorio.salvar(produto);

        produto.setId(id);

        // Converte para DTO
        return ProdutoMapper.paraDTO(produto);
    }
}
