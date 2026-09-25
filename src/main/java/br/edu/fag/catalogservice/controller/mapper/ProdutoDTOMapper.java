package br.edu.fag.catalogservice.controller.mapper;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
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