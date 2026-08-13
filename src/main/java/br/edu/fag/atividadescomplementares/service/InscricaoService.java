package br.edu.fag.atividadescomplementares.service;

import br.edu.fag.atividadescomplementares.repository.InscricaoRepository;
import br.edu.fag.atividadescomplementares.repository.entity.InscricaoEntity;
import br.edu.fag.atividadescomplementares.service.domain.InscricaoDomain;
import br.edu.fag.atividadescomplementares.service.mapper.InscricaoEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    public InscricaoDomain criar(InscricaoDomain inscricaoDomain) {
        inscricaoDomain.validarModalidade();
        inscricaoDomain.validarCargaHoraria();
        inscricaoDomain.validarLinkAcessoParaOnline();

        InscricaoEntity inscricaoEntity = InscricaoEntityMapper.toEntity(inscricaoDomain);
        return inscricaoRepository.criar(inscricaoEntity);
    }


}
