package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoRequestDTO;
import br.edu.fag.catalogservice.controller.dto.ProdutoResponseDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.ProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ProdutoRequestDTO dados) {
        ProdutoDomain produto = ProdutoDtoMapper.toDomain(dados);
        try {
            ProdutoDomain produtoCriado = produtoService.criar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoDtoMapper.toResponseDto(produtoCriado));
        } catch (IllegalArgumentException excecao) {
            return ResponseEntity.badRequest().body(Map.of("erro", excecao.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> consultarPorId(@PathVariable Long id) {
        ProdutoDomain produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(ProdutoDtoMapper.toResponseDto(produto));
    }

    @GetMapping(params = "active=true")
    public ResponseEntity<List<ProdutoResponseDTO>> consultarAtivos() {
        List<ProdutoResponseDTO> ativos = produtoService.buscarAtivos().stream()
                .map(ProdutoDtoMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(ativos);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProdutoResponseDTO> desativar(@PathVariable Long id) {
        ProdutoDomain atualizado = produtoService.desativar(id);
        return ResponseEntity.ok(ProdutoDtoMapper.toResponseDto(atualizado));
    }
}