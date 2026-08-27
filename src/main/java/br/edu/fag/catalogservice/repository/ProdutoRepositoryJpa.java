package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long> {

}