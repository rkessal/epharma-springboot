package hn.epharma.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Optional<Admin> findByEmail(String email);

}
