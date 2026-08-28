package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.model.Produto;
import br.edu.fag.catalogservice.service.ProdutoService;
import br.edu.fag.catalogservice.service.exception.ProdutoInvalidoException;
import br.edu.fag.catalogservice.utils.ConversorDeTipos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@RequestBody Map<String, Object> dados) {
        String nome = ConversorDeTipos.texto(dados.get("nome"));
        String descricao = ConversorDeTipos.texto(dados.get("descricao"));
        Double preco = ConversorDeTipos.numeroDecimal(dados.get("preco"));

        try {
            Produto produto = produtoService.criar(nome, descricao, preco);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta(produto));
        } catch (ProdutoInvalidoException excecao) {
            return ResponseEntity.badRequest().body(erro(excecao.getMessage()));
        }
    }

    private Map<String, Object> resposta(Produto produto) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("id", produto.getId());
        resposta.put("nome", produto.getNome());
        resposta.put("descricao", produto.getDescricao());
        resposta.put("preco", produto.getPreco());
        resposta.put("criadoEm", produto.getCriadoEm());
        return resposta;
    }

    private Map<String, Object> erro(String mensagem) {
        return Map.of("erro", mensagem);
    }
}
