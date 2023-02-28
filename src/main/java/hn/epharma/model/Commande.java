package hn.epharma.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Commande {
	@JsonView(JsonViews.Common.class)
	private int id;
	@JsonView(JsonViews.Common.class)
	private Collection<Ligne> lignes;
	@JsonView(JsonViews.Common.class)
	private double prixTotal;
	@JsonView(JsonViews.Common.class)
	private LocalDateTime date;
	@JsonView(JsonViews.CommandeWithClient.class)
	private Client client;
	@JsonView(JsonViews.Common.class)
	private int version;

	public Commande() {
	}

	public Commande(Collection<Ligne> lignes, Client client, double prixTotal) {
		this.client = client;
		this.lignes = lignes;
		this.prixTotal = prixTotal;
		this.date = LocalDateTime.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "commande")
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

	@ManyToOne()
	@JoinColumn(name = "client_id")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", lignes=" + lignes + ", prixTotal=" + prixTotal + ", version=" + version + "]";
	}
}
