package hn.epharma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Admin {
	private int id;
	private String email;
	private String pass;
	private String nom;

	private int version;

	public Admin() {
	}

	public Admin(int id, String email, String pass, String nom) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.nom = nom;
	}

	public Admin(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
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

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", pass=" + pass + ", nom=" + nom + ", version=" + version
				+ "]";
	}

}
