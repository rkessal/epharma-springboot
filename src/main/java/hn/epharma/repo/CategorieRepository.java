package hn.epharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Categorie;


public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
