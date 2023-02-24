package hn.epharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hn.epharma.model.JsonViews;
import hn.epharma.model.Produit;
import hn.epharma.repo.ProduitRepository;

@RestController
@RequestMapping("/produit")
public class ProduitRestController {

	@Autowired
	private ProduitRepository repo;

	@CrossOrigin
	@RequestMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Produit> findAll(@RequestParam(name = "categorie", required = false) String categorie) {
		if (categorie != null) {
			System.out.println("categorie " + categorie);
			return repo.findByCategorie(Integer.parseInt(categorie));
		}
		return repo.findAll();
	}

	@CrossOrigin
	@GetMapping("{id}")
	@JsonView(JsonViews.Common.class)
	public Produit findbyid(@PathVariable(name = "id") int id) {
		return repo.findById(id).get();
	}

	@CrossOrigin
	@GetMapping("/create")
	@JsonView(JsonViews.Common.class)
	public Produit create(@RequestBody Produit p) {
		return repo.save(p);
	}

	@CrossOrigin
	@GetMapping("/update")
	@JsonView(JsonViews.Common.class)
	public void update(@RequestBody Produit p) {
		repo.save(p);
	}

	@CrossOrigin
	@GetMapping("/delete/{id}")
	@JsonView(JsonViews.Common.class)
	public void delete(@PathVariable(name = "id") int id) {
		repo.deleteById(id);
	}

}
