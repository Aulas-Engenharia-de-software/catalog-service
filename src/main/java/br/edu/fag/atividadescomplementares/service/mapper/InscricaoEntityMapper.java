package br.edu.fag.atividadescomplementares.service.mapper;

import br.edu.fag.atividadescomplementares.repository.entity.InscricaoEntity;
import br.edu.fag.atividadescomplementares.service.domain.InscricaoDomain;
import org.modelmapper.ModelMapper;

public class InscricaoEntityMapper {

    public static InscricaoEntity toEntity(InscricaoDomain inscricaoDomain) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(inscricaoDomain, InscricaoEntity.class);
    }

    public static InscricaoDomain toDomain(InscricaoEntity inscricaoEntity) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(inscricaoEntity, InscricaoDomain.class);
    }
}
