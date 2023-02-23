package hn.epharma.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	public Optional<Client> findByEmail(String email);

}
