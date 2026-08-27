package br.edu.fag.catalogservice.controller;


import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO) {
        if (produtoDTO.getNome() == null || produtoDTO.getNome().isBlank()) {
            throw new RuntimeException(
                    "O nome do produto é obrigatório.");
        }

       ProdutoDomain produtoDomain = ProdutoDtoMapper.toDomain(produtoDTO);

       ProdutoDomain produtoCriado = produtoService.criar(produtoDomain);

       ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoCriado);
       return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    

}
