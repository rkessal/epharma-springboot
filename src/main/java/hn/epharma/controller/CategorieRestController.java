package hn.epharma.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import hn.epharma.model.Categorie;
import hn.epharma.model.JsonViews;
import hn.epharma.repo.CategorieRepository;

@RestController
@RequestMapping("categories")
public class CategorieRestController {

	 @Autowired
	    private CategorieRepository categorieRepository;

	 	@CrossOrigin
		@JsonView(JsonViews.Common.class)
	    @GetMapping("")
	    public List<Categorie> getAllCategories() {
	        return categorieRepository.findAll();
	    }
	 	
	 	@CrossOrigin
		@JsonView(JsonViews.Common.class)
	    @GetMapping("/{id}")
	    public ResponseEntity<Categorie> getCategorieById(@PathVariable int id) {
	        Optional<Categorie> categorie = categorieRepository.findById(id);
	        if (categorie.isPresent()) {
	            return ResponseEntity.ok(categorie.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	 	@CrossOrigin
		@JsonView(JsonViews.Common.class)
	    @PostMapping("/")
	    public Categorie createCategorie(@RequestBody Categorie categorie) {
	        return categorieRepository.save(categorie);
	    }

	 	@CrossOrigin
		@JsonView(JsonViews.Common.class)
	    @PutMapping("/{id}")
	    public ResponseEntity<Categorie> updateCategorie(@PathVariable int id, @RequestBody Categorie updatedCategorie) {
	        Optional<Categorie> categorie = categorieRepository.findById(id);
	        if (categorie.isPresent()) {
	            updatedCategorie.setId(id);
	            Categorie savedCategorie = categorieRepository.save(updatedCategorie);
	            return ResponseEntity.ok(savedCategorie);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	 	@CrossOrigin
		@JsonView(JsonViews.Common.class)
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
	        Optional<Categorie> categorie = categorieRepository.findById(id);
	        if (categorie.isPresent()) {
	            categorieRepository.delete(categorie.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	}