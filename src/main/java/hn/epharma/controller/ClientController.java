package hn.epharma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hn.epharma.model.Client;
import hn.epharma.repo.ClientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // méthode pour récupérer tous les clients
    @GetMapping("/")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // méthode pour récupérer un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // méthode pour ajouter un nouveau client
    @PostMapping("/")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // méthode pour mettre à jour un client existant
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
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
