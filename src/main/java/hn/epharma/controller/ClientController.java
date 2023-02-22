package hn.epharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.epharma.model.Client;
import hn.epharma.repo.ClientRepository;



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
    public Client getClientById(@PathVariable int id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }

    // méthode pour ajouter un nouveau client
    @PostMapping("/")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // méthode pour mettre à jour un client existant
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        client.setPass(clientDetails.getPass());
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setAdresse(clientDetails.getAdresse());

        return clientRepository.save(client);
    }

    // méthode pour supprimer un client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        clientRepository.delete(client);
    }
}
