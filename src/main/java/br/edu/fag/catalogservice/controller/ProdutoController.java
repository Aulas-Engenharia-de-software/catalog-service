package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.domain.Produto;
import br.edu.fag.catalogservice.dto.ProdutoRequestDTO;
import br.edu.fag.catalogservice.dto.ProdutoResponseDTO;
import br.edu.fag.catalogservice.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> criar(
            @RequestBody ProdutoRequestDTO dados) {

        String nome = texto(dados.getNome());
        String descricao = texto(dados.getDescricao());
        Double preco = numeroDecimal(dados.getPreco());

        try {

            Produto produto = produtoService.criar(
                    nome,
                    descricao,
                    preco
            );

            ProdutoResponseDTO resposta =
                    new ProdutoResponseDTO(
                            produto.getId(),
                            produto.getNome(),
                            produto.getDescricao(),
                            produto.getPreco(),
                            produto.getCriadoEm()
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resposta);

        } catch (IllegalArgumentException excecao) {

            return ResponseEntity
                    .badRequest()
                    .body(erro(excecao.getMessage()));
        }
    }

    private String texto(Object valor) {
        return valor instanceof String ? (String) valor : null;
    }

    private Double numeroDecimal(Object valor) {

        if (valor == null) {
            return null;
        }

        try {
            String numero = valor
                    .toString()
                    .trim()
                    .replace(',', '.');

            double resultado = Double.parseDouble(numero);

            return Double.isFinite(resultado)
                    ? resultado
                    : null;

        } catch (NumberFormatException excecao) {
            return null;
        }
    }

    private Map<String, Object> erro(String mensagem) {
        return Map.of("erro", mensagem);
    }
}