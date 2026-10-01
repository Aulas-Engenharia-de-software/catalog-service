package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByActiveTrue();
}