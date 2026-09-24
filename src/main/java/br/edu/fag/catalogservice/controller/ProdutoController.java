package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.dto.ProdutoCriacaoRequest;
import br.edu.fag.catalogservice.dto.ProdutoResponse;
import br.edu.fag.catalogservice.domain.RegraDeNegocioException;
import br.edu.fag.catalogservice.service.ProdutoService;
import com.fasterxml.jackson.databind.JsonNode;
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
    public ResponseEntity<?> criar(@RequestBody ProdutoCriacaoRequest dados) {
        try {
            var produto = produtoService.criar(
                    texto(dados.getNome()),
                    texto(dados.getDescricao()),
                    numeroDecimal(dados.getPreco()));

            return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponse.de(produto));
        } catch (RegraDeNegocioException excecao) {
            return ResponseEntity.badRequest().body(Map.of("erro", excecao.getMessage()));
        }
    }

    private String texto(JsonNode valor) {
        return valor != null && valor.isTextual() ? valor.textValue() : null;
    }

    private Double numeroDecimal(JsonNode valor) {
        if (valor == null || valor.isNull()) {
            return null;
        }

        try {
            String numero = valor.isTextual() ? valor.textValue() : valor.toString();
            double resultado = Double.parseDouble(numero.trim().replace(',', '.'));
            return Double.isFinite(resultado) ? resultado : null;
        } catch (NumberFormatException excecao) {
            return null;
        }
    }
}
