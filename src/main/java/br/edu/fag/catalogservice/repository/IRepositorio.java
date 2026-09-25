package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import java.util.List;

public interface IRepositorio {
    ProdutoDomain criar(ProdutoDomain produto);
    ProdutoDomain buscarPorId(Long id);
    List<ProdutoDomain> buscarAtivos();
    ProdutoDomain atualizar(ProdutoDomain produto);
}