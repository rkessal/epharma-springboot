package hn.epharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hn.epharma.model.Commande;
import hn.epharma.model.JsonViews;
import hn.epharma.repo.CommandeRepository;

@RestController
@RequestMapping("/commande")
public class CommandeRestController {

	@Autowired
	private CommandeRepository repo;

	@RequestMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Commande> findAll() {
		return repo.findAll();
	}

	@GetMapping("{id}")
	@JsonView(JsonViews.CommandeWithClient.class)
	public Commande findbyid(@PathVariable(name = "id") int id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/create")
	public Commande create(@RequestBody Commande c) {
		return repo.save(c);
	}

	@GetMapping("/update")
	public void update(@RequestBody Commande c) {
		repo.save(c);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable(name = "id") int id) {
		repo.deleteById(id);
	}
	//
	// @GetMapping("/marquecontaining/{marque}")
	// public List<Article> marquecontaining(@PathVariable(name = "marque")
	// String marque) {
	// return repo.findByMarqueContaining(marque);
	// }
	//
	// @GetMapping("/prixbetween/{nb1}/{nb2}")
	// public List<Article> prixbetween(@PathVariable(name = "nb1") int nb1,
	// @PathVariable(name = "nb2") int nb2) {
	// return repo.findByPrixBetween(nb1, nb2);
	// }
	//
	// @GetMapping("/prixlessthan/{nb}")
	// public List<Article> prixlessthan(@PathVariable(name = "nb") int nb) {
	// return repo.findByPrixLessThan(nb);
	// }
	//
	// @GetMapping("/firstbymarque/{marque}")
	// public List<Article> firstbymarque(@PathVariable(name = "marque") String
	// marque) {
	// return repo.findFirstByMarque(marque);
	// }
	//
	// @GetMapping("/countbymarque/{marque}")
	// public List<Article> countbymarque(@PathVariable(name = "marque") String
	// marque) {
	// return repo.countByMarque(marque);
	// }
	//
	// @GetMapping("/allbyorderbyprixasc")
	// public List<Article> allbyorderbyprixasc() {
	// return repo.findAllByOrderByPrixAsc();
	// }
}
