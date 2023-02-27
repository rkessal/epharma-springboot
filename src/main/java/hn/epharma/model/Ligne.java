package hn.epharma.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Ligne {
	@JsonView(JsonViews.Common.class)
	private int id;
	@JsonView(JsonViews.Common.class)
	private Produit produit;
	@JsonView(JsonViews.Common.class)
	private int quantite;
	@JsonView(JsonViews.Common.class)
	private int version;
	@JsonView(JsonViews.LigneWithCommande.class)
	private Commande commande;

	public Ligne() {
	}

	public Ligne(int id, Produit produit, int quantite, Commande commande) {
		this.id = id;
		this.produit = produit;
		this.quantite = quantite;
		this.commande = commande;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "produit_id")
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

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "commande_id")
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "Ligne [id=" + id + ", quantite=" + quantite + ", version=" + version + "]";
	}

}
