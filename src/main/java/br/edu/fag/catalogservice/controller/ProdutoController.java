package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapperDto.ProdutoDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/produtos", "/products"})
public class ProdutoController {

    private final IProdutoService iProdutoService;

    public ProdutoController(IProdutoService iProdutoService) {
        this.iProdutoService = iProdutoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO) {

        if (produtoDTO.getNome() == null || produtoDTO.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (produtoDTO.getPreco() == null || produtoDTO.getPreco() <= 0) {
            throw new RuntimeException("O preço do produto deve ser um número válido.");
        }

        if (produtoDTO.getDescricao() == null) {
            throw new RuntimeException("Adicione uma descrição.");
        }

        ProdutoDomain produtoDomain = ProdutoDtoMapper.toDomain(produtoDTO);
        ProdutoDomain produtoCriado = iProdutoService.criar(produtoDomain);

        ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoCriado);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer id) {

        ProdutoDomain produtoDomain = iProdutoService.buscarPorId(id);

        if (produtoDomain == null) {
            return ResponseEntity.notFound().build();
        }

        ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoDomain);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarAtivos() {

        List<ProdutoDTO> resposta = iProdutoService.listarAtivos()
                .stream()
                .map(ProdutoDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }

    @PatchMapping({"/{id}/deactivate", "/{id}/desativar"})
    public ResponseEntity<ProdutoDTO> desativar(@PathVariable Integer id) {

        ProdutoDomain produtoDomain = iProdutoService.desativar(id);

        if (produtoDomain == null) {
            return ResponseEntity.notFound().build();
        }

        ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoDomain);

        return ResponseEntity.ok(resposta);
    }
}