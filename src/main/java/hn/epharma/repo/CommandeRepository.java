package hn.epharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	public Commande findFirstByOrderByIdDesc();
}
