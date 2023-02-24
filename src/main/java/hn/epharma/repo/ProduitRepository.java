package hn.epharma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	public List<Produit> findByCategorie(int categorie);
}
