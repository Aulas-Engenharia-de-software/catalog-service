package br.edu.fag.catalogservice.repository.mapperEntity;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import org.modelmapper.ModelMapper;

public class ProdutoEntityMapper {

    public static ProdutoEntity toEntity(ProdutoDomain produtoDomain) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(produtoDomain, ProdutoEntity.class);
    }

    public static ProdutoDomain toDomain(ProdutoEntity produtoEntity) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(produtoEntity, ProdutoDomain.class);
    }
}
