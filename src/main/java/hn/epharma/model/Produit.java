package hn.epharma.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Produit {
	@JsonView(JsonViews.Common.class)
	private int id;
	@JsonView(JsonViews.Common.class)
	private String nom;
	@JsonView(JsonViews.Common.class)
	private String description;
	@JsonView(JsonViews.Common.class)
	private double prix;
	@JsonView(JsonViews.Common.class)
	private String image;
	@JsonView(JsonViews.Common.class)
	private int categorie;
	private Collection<Ligne> lignes;

	@JsonView(JsonViews.Common.class)
	private int version;

	public Produit(int id, String nom, String description, double prix, String image, int categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.image = image;
		this.categorie = categorie;
		this.lignes = new ArrayList<Ligne>();
	}

	public Produit() {
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "produit")
	public Collection<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(Collection<Ligne> lignes) {
		this.lignes = lignes;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", image="
				+ image + ", categorie=" + categorie + "]";
	}
}
