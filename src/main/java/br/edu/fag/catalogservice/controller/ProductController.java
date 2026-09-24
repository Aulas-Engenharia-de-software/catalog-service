package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.dto.ProductResponse;
import br.edu.fag.catalogservice.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProdutoService produtoService;

    public ProductController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(ProductResponse.from(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProductResponse> buscarAtivos(@RequestParam Boolean active) {
        if (!active) {
            return List.of();
        }

        return produtoService.buscarAtivos().stream()
                .map(ProductResponse::from)
                .toList();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProductResponse> desativar(@PathVariable Long id) {
        return produtoService.desativar(id)
                .map(produto -> ResponseEntity.ok(ProductResponse.from(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
