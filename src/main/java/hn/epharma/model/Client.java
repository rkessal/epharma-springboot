package hn.epharma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;


@Entity
public class Client {
	
	private int id;
	private String pass;
	private String nom;
	private String prenom;
	private String adresse;
	
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
	public Client(int id, String pass, String nom, String prenom, String adresse) {
		super();
		this.id = id;
		this.pass = pass;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	public Client() {
		super();
	}
	@Override
	public String toString() {
		return "client [id=" + id + ", pass=" + pass + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ "]";
	}
	
	
}
