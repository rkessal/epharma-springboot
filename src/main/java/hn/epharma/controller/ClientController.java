package hn.epharma.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hn.epharma.model.Client;
import hn.epharma.model.JsonViews;
import hn.epharma.repo.ClientRepository;
import hn.rayhan.model.form.LoginForm;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	// méthode pour récupérer tous les clients

	@CrossOrigin
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	// méthode pour récupérer un client par ID
	@CrossOrigin
	@GetMapping("/{id}")
	@JsonView(JsonViews.ClientWithCommand.class)
	public ResponseEntity<Client> getClientById(@PathVariable int id) {
		Optional<Client> client = clientRepository.findById(id);

		if (client.isPresent()) {
			return new ResponseEntity<>(client.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@PostMapping("connexion")
	@JsonView(JsonViews.ClientWithCommand.class)
	public ResponseEntity<Client> getClientByEmail(@RequestBody LoginForm loginForm) {
		Optional<Client> client = clientRepository.findByEmail(loginForm.getEmail());

		if (client.isPresent()) {
			Client c = client.get();
			if (c.getPass().equals(loginForm.getPassword())) {
				return new ResponseEntity<>(client.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// méthode pour ajouter un nouveau client

	@CrossOrigin
	@PostMapping("")
	@JsonView(JsonViews.ClientWithCommand.class)
	public Client addClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}

	// méthode pour mettre à jour un client existant
	@CrossOrigin
	@PutMapping("/{id}")
	@JsonView(JsonViews.ClientWithCommand.class)
	public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client clientDetails) {
		Optional<Client> client = clientRepository.findById(id);

		if (client.isPresent()) {
			Client updatedClient = client.get();
			updatedClient.setPass(clientDetails.getPass());
			updatedClient.setNom(clientDetails.getNom());
			updatedClient.setPrenom(clientDetails.getPrenom());
			updatedClient.setAdresse(clientDetails.getAdresse());
			return new ResponseEntity<>(clientRepository.save(updatedClient), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// méthode pour supprimer un client
	@CrossOrigin
	@DeleteMapping("/{id}")
	@JsonView(JsonViews.ClientWithCommand.class)
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable int id) {
		Optional<Client> client = clientRepository.findById(id);

		if (client.isPresent()) {
			clientRepository.delete(client.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
