package br.edu.fag.atividadescomplementares.controller;

import br.edu.fag.atividadescomplementares.controller.dto.InscricaoDTO;
import br.edu.fag.atividadescomplementares.controller.mapper.InscricaoDtoMapper;
import br.edu.fag.atividadescomplementares.service.InscricaoService;
import br.edu.fag.atividadescomplementares.service.domain.InscricaoDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping
    public ResponseEntity<InscricaoDTO> criar(@RequestBody InscricaoDTO inscricaoDTO) {
        if (inscricaoDTO.getNomeAluno() == null || inscricaoDTO.getNomeAluno().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        InscricaoDomain inscricaoDomain = InscricaoDtoMapper.toDomain(inscricaoDTO);
        InscricaoDomain inscricaoCriada = inscricaoService.criar(inscricaoDomain);

        InscricaoDTO resposta = InscricaoDtoMapper.toDto(inscricaoCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

}
