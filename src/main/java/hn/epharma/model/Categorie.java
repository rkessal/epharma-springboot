package hn.epharma.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Categorie {

  @JsonView(JsonViews.Common.class)
  private int id;

  @JsonView(JsonViews.Common.class)
  private String name;

  @JsonView(JsonViews.CategorieWithProduit.class)
  private Collection<Produit> produits;

  @JsonView(JsonViews.Common.class)
  private int version;

  public Categorie() {
  }

  public Categorie(String name) {
    this.name = name;
    this.produits = new ArrayList<Produit>();
  }

  @Version
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  // Getters and setters
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @JsonView(JsonViews.Common.class)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "categorie")
  public Collection<Produit> getProduits() {
    return produits;
  }

  public void setProduits(Collection<Produit> produits) {
    this.produits = produits;
  }

}
