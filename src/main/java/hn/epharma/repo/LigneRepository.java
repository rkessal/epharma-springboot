package hn.epharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Ligne;

public interface LigneRepository extends JpaRepository<Ligne, Integer> {
}
