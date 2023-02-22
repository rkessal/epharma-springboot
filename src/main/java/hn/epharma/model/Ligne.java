package hn.epharma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Ligne {
	private int id;
	private Produit produit;
	private int quantite;

	private int version;

	public Ligne(int id, Produit produit, int quantite) {
		super();
		this.id = id;
		this.produit = produit;
		this.quantite = quantite;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Ligne [id=" + id + ", quantite=" + quantite + ", version=" + version + "]";
	}

}
