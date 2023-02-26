package hn.epharma.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import hn.epharma.model.JsonViews;
import hn.epharma.model.Produit;
import hn.epharma.repo.ProduitRepository;

@RestController
@RequestMapping("/produit")
public class ProduitRestController {

	@Autowired
	private ProduitRepository repo;

	@Autowired
	private ServletContext servletContext;

	@Value("${app.upload.dir:${user.home}}")
	private String uploadDir;

	@CrossOrigin
	@PostMapping("/upload/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id) {
		Produit p = repo.findById(id).orElse(null);
		if (p == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		try {

			String[] parts = file.getOriginalFilename().split("\\.");
			System.out.println(file.getOriginalFilename());
			String extension = parts[parts.length - 1];
			String newName = "produit-" + id + "." + extension;
			Path filePath = Paths.get("src/main/resources/static/", "image", newName);
			Files.createDirectories(filePath.getParent());
			Files.write(filePath, file.getBytes());
			p.setImage(newName);
			repo.save(p);
			return ResponseEntity.ok().build();
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/image/{imageName}")
	public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream is = getClass().getResourceAsStream("/static/image/" + imageName);
		if (is == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return;
		}
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		IOUtils.copy(is, response.getOutputStream());
	}

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
	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	public Produit create(@RequestBody Produit p) {
		return repo.save(p);
	}

	@CrossOrigin
	@PutMapping("{id}")
	@JsonView(JsonViews.Common.class)
	public void update(@RequestBody Produit p) {
		repo.save(p);
	}

	@CrossOrigin
	@DeleteMapping("{id}")
	@JsonView(JsonViews.Common.class)
	public void delete(@PathVariable(name = "id") int id) {
		repo.deleteById(id);
	}

}
