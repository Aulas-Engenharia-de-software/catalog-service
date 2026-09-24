package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Long salvar(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    List<Produto> buscarAtivos();

    Optional<Produto> desativar(Long id);
}
