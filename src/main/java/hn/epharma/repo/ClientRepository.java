package hn.epharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.epharma.model.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>  {

}
