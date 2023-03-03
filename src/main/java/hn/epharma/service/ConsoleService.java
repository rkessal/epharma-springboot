package hn.epharma.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import hn.epharma.model.Admin;
import hn.epharma.model.Categorie;
import hn.epharma.model.Client;
import hn.epharma.model.Produit;
import hn.epharma.repo.AdminRepository;
import hn.epharma.repo.CategorieRepository;
import hn.epharma.repo.ClientRepository;
import hn.epharma.repo.ProduitRepository;

@Service
public class ConsoleService implements CommandLineRunner {

  @Autowired
  ProduitRepository prepo;

  @Autowired
  CategorieRepository crepo;

  @Autowired
  ClientRepository clprepo;

  @Autowired
  AdminRepository arepo;

  @Override
  public void run(String... args) throws Exception {
    init();
  }

  public void init() {
    setProduits();
    setClients();
    setAdmins();
  }

  public void setClients() {
    Client c = new Client("kessal.rayhan@email.com", "pass");
    c.setPrenom("Rayhan");
    c.setNom("Kessal");
    clprepo.save(c);
  }

  public void setAdmins() {
    arepo.save(new Admin(1, "adm@mail.com", "adm", "Rayhan"));
  }

  public ArrayList<Categorie> getCategories() {
    ArrayList<Categorie> categories = new ArrayList<Categorie>();
    categories.add(new Categorie("Analgésique"));
    categories.add(new Categorie("Soin de la peau"));
    categories.add(new Categorie("Rhume et grippe"));
    categories.add(new Categorie("Anti-acide"));
    categories.add(new Categorie("Peau"));
    categories.add(new Categorie("Yeux"));
    categories.add(new Categorie("Cheveux"));

    for (Categorie c : categories) {
      crepo.save(c);
    }

    return categories;
  }

  public void setProduits() {
    ArrayList<Categorie> categories = getCategories();
    prepo.save(new Produit(1, "Doliprane 500mg",
        "Comprimés à base de paracétamol pour soulager la douleur et réduire la fièvre.", 5.99, "produit-1.jpg",
        categories.get(0)));

    prepo.save(new Produit(2, "Nurofen 400mg",
        "Comprimés à base d'ibuprofène pour soulager la douleur, l'inflammation et la fièvre.", 7.99, "nurofen.jpg",
        categories.get(0)));

    prepo.save(new Produit(3, "Biafine", "Crème pour les brûlures, les irritations et les érythèmes cutanés.", 9.99,
        "biafine.jpg",
        categories.get(1)));
    prepo.save(new Produit(4, "Advil Rhume et Sinus",
        "Comprimés à base d'ibuprofène, de pseudoéphédrine et de chlorhydrate de phényléphrine pour soulager la congestion nasale, les maux de tête et la douleur.",
        8.99, "produit-4.webp", categories.get(2)));
    prepo.save(new Produit(5, "Otrivine", "Spray nasal à base d'oxymétazoline pour soulager la congestion nasale", 6.99,
        "produit-5.jpg", categories.get(2)));
    prepo.save(new Produit(6, "Bepanthen", "Crème pour le traitement des irritations et des éruptions cutanées", 7.99,
        "produit-6.webp", categories.get(1)));
    prepo.save(
        new Produit(7, "Imodium", "Comprimés à base de lopéramide pour traiter la diarrhée", 6.99, "produit-7.jpg",
            categories.get(3)));
    prepo.save(new Produit(8, "Maalox",
        "Suspension buvable pour soulager les brûlures d'estomac, les reflux gastro-œsophagiens et les douleurs abdominales",
        9.99, "produit-8.jpg", categories.get(5)));
    prepo.save(new Produit(9, "Strepsils", "Pastilles à sucer pour soulager la douleur et l'inflammation de la gorge",
        4.99, "produit-9.jpg", categories.get(6)));
    prepo.save(new Produit(10, "Dermophile Indien Shampoing",
        "Shampoing pour cuir chevelu sensible, irrité ou sujet aux démangeaisons", 11.99, "produit-10.jpg",
        categories.get(4)));
  }

}
