package hn.epharma.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Client {

	@JsonView(JsonViews.Common.class)
	private int id;
	@JsonView(JsonViews.Common.class)
	private String email;
	@JsonView(JsonViews.Common.class)
	private String pass;
	@JsonView(JsonViews.Common.class)
	private String nom;
	@JsonView(JsonViews.Common.class)
	private String prenom;
	@JsonView(JsonViews.Common.class)
	private String adresse;

	@JsonView(JsonViews.ClientWithCommand.class)
	private Collection<Commande> commandes;

	private int version;

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Client(int id, String pass, String nom, String prenom, String adresse, String email) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.commandes = new ArrayList<Commande>();
	}

	public Client() {
		super();
	}

	@OneToMany(mappedBy = "client")
	public Collection<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}

	public void addCommande(Commande commande) {
		this.commandes.add(commande);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", pass=" + pass + ", nom=" + nom + ", prenom=" + prenom
				+ ", adresse=" + adresse + ", commandes=" + commandes + ", version=" + version + "]";
	}

}
