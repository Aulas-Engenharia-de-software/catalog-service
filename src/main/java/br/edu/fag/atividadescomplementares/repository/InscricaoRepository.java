package br.edu.fag.atividadescomplementares.repository;

import br.edu.fag.atividadescomplementares.repository.entity.InscricaoEntity;
import br.edu.fag.atividadescomplementares.service.domain.InscricaoDomain;
import br.edu.fag.atividadescomplementares.service.mapper.InscricaoEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class InscricaoRepository {

    private final InscricaoRepositoryJpa inscricaoRepositoryJpa;

    public InscricaoRepository(InscricaoRepositoryJpa inscricaoRepositoryJpa) {
        this.inscricaoRepositoryJpa = inscricaoRepositoryJpa;
    }

    public InscricaoDomain criar(InscricaoEntity inscricaoEntity) {
        final InscricaoEntity inscricaoPersistida = inscricaoRepositoryJpa.save(inscricaoEntity);

        return InscricaoEntityMapper.toDomain(inscricaoPersistida);
    }
}
