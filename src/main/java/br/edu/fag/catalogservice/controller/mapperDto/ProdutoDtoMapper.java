package br.edu.fag.catalogservice.controller.mapperDto;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;

public class ProdutoDtoMapper {

    public static ProdutoDomain toDomain(ProdutoDTO produtoDTO) {
        final ModelMapper mapper = new ModelMapper();
         return mapper.map(produtoDTO, ProdutoDomain.class);
    }

    public static ProdutoDTO toDto(ProdutoDomain produtoDomain) {
        final ModelMapper mapper = new ModelMapper();
        return mapper.map(produtoDomain, ProdutoDTO.class);
    }
}
