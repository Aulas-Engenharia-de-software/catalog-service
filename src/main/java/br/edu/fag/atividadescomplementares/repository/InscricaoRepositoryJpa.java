package br.edu.fag.atividadescomplementares.repository;

import br.edu.fag.atividadescomplementares.repository.entity.InscricaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepositoryJpa extends JpaRepository<InscricaoEntity, Integer> {
}
