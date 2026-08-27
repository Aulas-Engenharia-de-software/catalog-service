package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoRequestDTO;
import br.edu.fag.catalogservice.controller.dto.ProdutoResponseDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.ProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

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
    public ResponseEntity<?> criar(@RequestBody ProdutoRequestDTO dados) {

        ProdutoDomain produto = ProdutoDtoMapper.toDomain(dados);

        // Validações básicas da entrada
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "O nome do produto é obrigatório."));
        }

        if (produto.getPreco() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "O preço do produto deve ser um número válido."));
        }

        try {

            ProdutoDomain produtoCriado = produtoService.criar(produto);

            ProdutoResponseDTO resposta =
                    ProdutoDtoMapper.toResponseDto(produtoCriado);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resposta);

        } catch (IllegalArgumentException excecao) {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", excecao.getMessage()));
        }
    }
}