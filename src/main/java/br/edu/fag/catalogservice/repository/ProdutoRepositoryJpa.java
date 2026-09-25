package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Faltava esta linha!

@Repository
public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByActiveTrue();
}