package hn.epharma.model;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Commande {
	private int id;
	private Collection<Ligne> lignes;
	private double prixTotal;
	private Client client;

	private int version;

	public Commande() {
	}

	public Commande(int id, Collection<Ligne> lignes, Client client, double prixTotal) {
		this.id = id;
		this.client = client;
		this.lignes = lignes;
		this.prixTotal = prixTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(Collection<Ligne> lignes) {
		this.lignes = lignes;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", lignes=" + lignes + ", prixTotal=" + prixTotal + ", version=" + version + "]";
	}
}
