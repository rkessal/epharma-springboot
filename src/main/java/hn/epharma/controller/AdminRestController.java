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

import hn.epharma.model.Admin;
import hn.epharma.model.JsonViews;
import hn.epharma.repo.AdminRepository;
import hn.rayhan.model.form.LoginForm;

@RestController
@RequestMapping("admin")
public class AdminRestController {

	@Autowired
	private AdminRepository adminRepository;

	// méthode pour récupérer tous les Admins

	@CrossOrigin
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	// méthode pour récupérer un Admin par ID
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
		Optional<Admin> Admin = adminRepository.findById(id);

		if (Admin.isPresent()) {
			return new ResponseEntity<>(Admin.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@PostMapping("login")
	public ResponseEntity<Admin> login(@RequestBody LoginForm loginForm) {
		Optional<Admin> admin = adminRepository.findByEmail(loginForm.getEmail());

		if (admin.isPresent()) {
			Admin c = admin.get();
			if (c.getPass().equals(loginForm.getPassword())) {
				return new ResponseEntity<>(admin.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@PostMapping("")
	public ResponseEntity<Admin> signup(@RequestBody(required = true) LoginForm loginForm) {
		Optional<Admin> Admin = adminRepository.findByEmail(loginForm.getEmail());

		if (Admin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			Admin c = new Admin(loginForm.getEmail(), loginForm.getPassword());
			adminRepository.save(c);
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
	}

	// méthode pour mettre à jour un Admin existant
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin AdminDetails) {
		Optional<Admin> Admin = adminRepository.findById(id);

		if (Admin.isPresent()) {
			Admin updatedAdmin = Admin.get();
			updatedAdmin.setPass(AdminDetails.getPass());
			updatedAdmin.setNom(AdminDetails.getNom());
			return new ResponseEntity<>(adminRepository.save(updatedAdmin), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// méthode pour supprimer un Admin
	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable int id) {
		Optional<Admin> Admin = adminRepository.findById(id);

		if (Admin.isPresent()) {
			adminRepository.delete(Admin.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
