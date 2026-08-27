package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

public interface IRepositorio {
    public ProdutoDomain criar(ProdutoDomain produto);
}
