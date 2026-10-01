package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceRepositoryJpa extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByActiveTrue();
}