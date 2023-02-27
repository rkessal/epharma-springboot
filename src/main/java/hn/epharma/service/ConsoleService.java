package hn.epharma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import hn.epharma.model.Produit;
import hn.epharma.repo.ProduitRepository;

@Service
public class ConsoleService implements CommandLineRunner {

	@Autowired
	ProduitRepository prepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("Hello World !");
		// testFindAll();
		//testFindByCategorie();
		testCreate();
	}

	public void testFindByCategorie() {
		prepo.findByCategorie(2);
	}

	public void testFindAll() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("FINDALL START !!!");
		System.out.println(prepo.findAll());
		System.out.println("FINDALL END !!!");
	}

	public void testCreate() throws Exception {
		// TODO Auto-generated method stub
		// System.out.print("Create !!");
		 prepo.save(new Produit(1, "Doliprane", "anti-douleur", 12.5, null, 6));
		 prepo.save(new Produit(2, "Eau oxygénée", "ça pique !", 19.99, null, 4));
		 prepo.save(new Produit(3, "Pansements", "protège les plaies", 9.85, null, 2));
		 prepo.save(new Produit(4, "Amoxicilline", "infection bactérienne", 15.0, null, 4));
		 prepo.save(new Produit(5, "Lorazépam", "anxiété et troubles du sommeil", 23.15, null, 5));
	}
}
