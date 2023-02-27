package hn.epharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import hn.epharma.model.Commande;
import hn.epharma.model.JsonViews;
import hn.epharma.model.Ligne;
import hn.epharma.repo.CommandeRepository;
import hn.epharma.repo.LigneRepository;

@RestController
@RequestMapping("/commande")
public class CommandeRestController {

	@Autowired
	private CommandeRepository repo;
	@Autowired
	private LigneRepository repoL;

	@CrossOrigin
	@RequestMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Commande> findAll() {
		return repo.findAll();
	}

	@CrossOrigin
	@GetMapping("{id}")
	@JsonView(JsonViews.CommandeWithClient.class)
	public Commande findbyid(@PathVariable(name = "id") int id) {
		return repo.findById(id).orElse(null);
	}

	@CrossOrigin
	@PostMapping("")
	@JsonView(JsonViews.CommandeWithLigne.class)
	public Commande create(@RequestBody Commande c) {
		Commande co = new Commande(null, c.getClient(), c.getPrixTotal());
		repo.save(co);
		for (Ligne l : c.getLignes()) {
			l.setCommande(co);
			repoL.save(l);
		}
		
		return repo.findById(co.getId()).orElse(null);
	}

	@CrossOrigin
	@PutMapping("")
	@JsonView(JsonViews.CommandeWithClient.class)
	public Commande update(@RequestBody Commande c) {
		repo.save(c);
		return repo.findById(c.getId()).orElse(null);
	}

	@CrossOrigin
	@DeleteMapping("/{id}")
	@JsonView(JsonViews.CommandeWithLigne.class)
	public void delete(@PathVariable(name = "id") int id) {
		repo.deleteById(id);
	}
	
	@CrossOrigin
	@GetMapping("/lastCommande")
	public Commande lastCommande() {
		return repo.findFirstByOrderByIdDesc();
	}
}
