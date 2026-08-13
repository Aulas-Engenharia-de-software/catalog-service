package br.edu.fag.atividadescomplementares.controller.mapper;

import br.edu.fag.atividadescomplementares.controller.dto.InscricaoDTO;
import br.edu.fag.atividadescomplementares.service.domain.InscricaoDomain;
import org.modelmapper.ModelMapper;

public class InscricaoDtoMapper {

    public static InscricaoDomain toDomain(InscricaoDTO inscricaoDTO) {
        final ModelMapper mapper = new ModelMapper();

        return mapper.map(inscricaoDTO, InscricaoDomain.class);
    }

    public static InscricaoDTO toDto(InscricaoDomain inscricaoDomain) {
        final ModelMapper mapper = new ModelMapper();

        return mapper.map(inscricaoDomain, InscricaoDTO.class);
    }

}
